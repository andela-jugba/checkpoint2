package checkpoint.andela.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import checkpoint.andela.parser.Reactant;
import checkpoint.andela.parser.SharedBuffers;

public class DBWriter implements Runnable{

	private BlockingQueue<Reactant> sharedBuffer;
	private BlockingQueue<String> logBuffer;
	private boolean check = true;
	private SqlConnector connector;
	
	public DBWriter() {
		this.sharedBuffer = SharedBuffers.getSharedBuffer();
		this.logBuffer = SharedBuffers.getLogBuffer();
		connector = new SqlConnector();
	}

	private void writeRecord(Reactant reaction) throws SQLException {
		connector.writeReact(reaction);
	}
	
	private void writeReactionsToDB() throws InterruptedException, SQLException {
		
		while (check) {
			Reactant reaction = sharedBuffer.take();
			
			writeRecord(reaction);
			Date date = new Date();
			System.out.println("DBWriter Thread   (" + date.toString() + ")" + "----" + "Collected "
					+ reaction.get("UNIQUE-ID") + " from buffer");
			logBuffer.put("DBWriter Thread   (" + date.toString() + ")" + "----" + "Collected "
					+ reaction.get("UNIQUE-ID") + " from buffer");		
		}
	}

	@Override
	public void run() {
		try {
			writeReactionsToDB();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
