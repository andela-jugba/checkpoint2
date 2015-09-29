package checkpoint.andela.parser;

public class FileParser implements Runnable {
	private Reader reader;
	private DocumentProcessor documentProcessor;
	private double timeToRun;

	public FileParser(String fileName) {
		reader = new DatFileReader(fileName);
		documentProcessor = new ReactionParser(reader.getBufferedReader());
	}

	@Override
	public void run() {

		long startTime = System.currentTimeMillis();
		documentProcessor.process();
		long time = System.currentTimeMillis() - startTime;
		setTimeToRun(time);
		System.out.println(this.getThreadName() + " execution time: " + getTimeToRun() + "miliseconds");
		System.out.println("Number of reactions: " + SharedBuffers.getSharedBuffer().size());
		System.out.println("Number of logs : " + SharedBuffers.getLogBuffer().size());

	}

	public String getThreadName() {
		return "FileParserThread";
	}

	private void setTimeToRun(long time) {
		timeToRun = (double) time;
	}

	public double getTimeToRun() {
		return timeToRun;
	}
}
