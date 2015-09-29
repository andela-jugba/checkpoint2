package checkpoint.andela.parser;

public class FileParser implements Runnable {
	private Reader reader;
	private DocumentProcessor documentProcessor;

	public FileParser(String fileName) {
		reader = new DatFileReader(fileName);
		documentProcessor = new ReactionParser(reader.getBufferedReader());
	}

	@Override
	public void run() {
		documentProcessor.process();
	}

}
