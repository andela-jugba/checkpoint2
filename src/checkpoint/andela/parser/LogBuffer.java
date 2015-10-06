package checkpoint.andela.parser;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogBuffer{

	private  BlockingQueue<String> logBuffer;
	
	public LogBuffer() {
		logBuffer = new LinkedBlockingQueue<>(1);
	}
	
	public void addToLogBuffer(String reaction) throws InterruptedException {
			logBuffer.put(reaction);
		
	}
	public String takeFromLogBuffer() throws InterruptedException {
			return logBuffer.take();

	}

	public void clear() {
		logBuffer.clear();
	}

	public Object size() {
		return logBuffer.size();
	}
}
