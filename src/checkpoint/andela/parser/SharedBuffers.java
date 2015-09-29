package checkpoint.andela.parser;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class SharedBuffers {
	public static BlockingQueue<String> logBuffer = new LinkedBlockingQueue<>();
	public static BlockingQueue<Reactant> sharedBuffer = new LinkedBlockingQueue<>();

	public static BlockingQueue<String> getLogBuffer() {
		return logBuffer;
	}
	
	public static BlockingQueue<Reactant> getSharedBuffer() {
		return sharedBuffer;
	}	

}
