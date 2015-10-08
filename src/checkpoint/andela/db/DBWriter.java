package checkpoint.andela.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import checkpoint.andela.buffer.Buffer;
import checkpoint.andela.parser.FileParser;
import checkpoint.andela.parser.SharedBuffers;
import checkpoint.andela.parser.document.models.Reactant;

public class DBWriter implements Runnable{

	private Buffer sharedBuffer;
	private Buffer logBuffer;
	private SqlConnector connector;
	
	public DBWriter() {
		this.sharedBuffer = SharedBuffers.getSharedBuffer();
		this.logBuffer = SharedBuffers.getLogBuffer();
		connector = new SqlConnector();
	}
	
	private void writeReactionsToDB() throws InterruptedException, SQLException, IOException {
		
		Reactant reaction = (Reactant) sharedBuffer.takeFromBuffer();
		Date date = new Date();
		logBuffer.addToBuffer("DBWriter Thread   (" + date.toString() + ")" + "----" + "Collected "
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
