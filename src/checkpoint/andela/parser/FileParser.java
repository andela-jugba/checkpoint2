package checkpoint.andela.parser;

public class FileParser implements Runnable {
	private Reader reader;
	private DocumentProcessor documentProcessor;
	private static long timeToRun;

	public FileParser(String fileName) {
		reader = new DatFileReader(fileName);
		documentProcessor = new ReactionParser(reader.getBufferedReader());
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		documentProcessor.process();
		long endTime = System.currentTimeMillis();
		FileParser.setTimeToRun(endTime-startTime);
			
	}

	public static long getTimeToRun() {
		return timeToRun;
	}

	public static void setTimeToRun(long timeToRun) {
		FileParser.timeToRun = timeToRun;
	}


}
