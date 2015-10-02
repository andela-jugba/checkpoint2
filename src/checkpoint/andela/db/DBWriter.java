package checkpoint.andela.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import checkpoint.andela.parser.FileParser;
import checkpoint.andela.parser.Reactant;
import checkpoint.andela.parser.SharedBuffers;

public class DBWriter implements Runnable{

	private BlockingQueue<Reactant> sharedBuffer;
	private BlockingQueue<String> logBuffer;
	private SqlConnector connector;
	
	public DBWriter() {
		this.sharedBuffer = SharedBuffers.getSharedBuffer();
		this.logBuffer = SharedBuffers.getLogBuffer();
		connector = new SqlConnector();
	}
	
	private void writeReactionsToDB() throws InterruptedException, SQLException, IOException {
		
		Reactant reaction = sharedBuffer.take();
		Date date = new Date();
		logBuffer.put("DBWriter Thread   (" + date.toString() + ")" + "----" + "Collected "
				+ reaction.get("UNIQUE-ID") + " from buffer");	
		connector.writeReact(reaction);			
		
		System.out.println("DBWriter Thread   (" + date.toString() + ")" + "----" + "Collected "
				+ reaction.get("UNIQUE-ID") + " from buffer");
	
	}

	@Override
	public void run() {
		
		try {
			while(FileParser.isRunning()) {
				writeReactionsToDB();
			}
			writeReactionsToDB();
		} catch (InterruptedException | SQLException |IOException e) {
			e.printStackTrace();
		}
	}
	
}
