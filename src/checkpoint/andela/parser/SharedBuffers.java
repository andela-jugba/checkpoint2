package checkpoint.andela.parser;

import checkpoint.andela.buffer.Buffer;

public class SharedBuffers {
	public static Buffer logBuffer = new Buffer();
	public static Buffer sharedBuffer = new Buffer();

	public static Buffer getLogBuffer() {
		return logBuffer;
	}
	
	public static Buffer getSharedBuffer() {
		return sharedBuffer;
	}	

}
