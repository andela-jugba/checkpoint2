package checkpoint.andela.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.concurrent.BlockingQueue;

import checkpoint.andela.parser.SharedBuffers;

public class LogWriter implements Runnable{
	private BlockingQueue<String> logBuffer;
	private File file;
	
	


	public LogWriter(String fileName) {
		logBuffer = SharedBuffers.logBuffer;
		file = new File(fileName+".txt");
	}
	

	@Override
	public void run() {
		while(true) {			

			try {

				if (!file.exists()) {
					file.createNewFile();
				}
				
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(logBuffer.take() + "\n");
				bw.write(logBuffer.take() + "\n");
				bw.close();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
