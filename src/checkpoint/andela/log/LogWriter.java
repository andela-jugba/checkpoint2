package checkpoint.andela.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import checkpoint.andela.buffer.Buffer;
import checkpoint.andela.parser.FileParser;
import checkpoint.andela.parser.SharedBuffers;

public class LogWriter implements Runnable{
	private Buffer logBuffer;
	private File file;
	
	public LogWriter(String fileName) {
		logBuffer = SharedBuffers.logBuffer;
		file = new File(fileName+".txt");
	}
	
	private void writeToLog() {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(logBuffer.takeFromBuffer() + "\n");
			
			bw.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void run() {
		while(FileParser.isRunning()) {			
			writeToLog();
		}
		writeToLog();
	}

}
