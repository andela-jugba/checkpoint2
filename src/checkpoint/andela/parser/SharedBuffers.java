package checkpoint.andela.parser;

public class SharedBuffers {
	public static LogBuffer logBuffer = new LogBuffer();
	public static ReactantBuffer sharedBuffer = new ReactantBuffer();

	public static LogBuffer getLogBuffer() {
		return logBuffer;
	}
	
	public static ReactantBuffer getSharedBuffer() {
		return sharedBuffer;
	}	

}
