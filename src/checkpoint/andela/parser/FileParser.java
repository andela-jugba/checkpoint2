package checkpoint.andela.parser;

import checkpoint.andela.parser.document.processor.DocumentProcessor;
import checkpoint.andela.parser.document.processor.ReactionParser;
import checkpoint.andela.parser.reader.DatFileReader;
import checkpoint.andela.parser.reader.Reader;

public class FileParser implements Runnable {
	private Reader reader;
	private DocumentProcessor documentProcessor;
	private static boolean isRunning = true;

	public FileParser(String fileName) {
		reader = new DatFileReader(fileName);
		documentProcessor = new ReactionParser(reader.getBufferedReader());
	}

	@Override
	public void run() {
		documentProcessor.process();
		setRunning(false);
	}

	public static boolean isRunning() {
		return isRunning;
	}

	public static void setRunning(boolean isRunning) {
		FileParser.isRunning = isRunning;
	}

}
