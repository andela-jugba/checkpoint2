package checkpoint.andela.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ReactionParser implements DocumentProcessor {
	private BlockingQueue<Reactant> sharedBuffer;
	private BlockingQueue<String> logBuffer;
	private BufferedReader bufferedReader;

	private Reactant holder;
	List<String> filter = Arrays.asList("UNIQUE-ID","TYPES","ATOM-MAPPINGS","CREDITS","EC-NUMBER","IN-PATHWAY","ORPHAN?","LEFT");
	
	public ReactionParser(BufferedReader buffer) {
		this.logBuffer = SharedBuffers.getLogBuffer();
		this.sharedBuffer = SharedBuffers.getSharedBuffer();
		bufferedReader = buffer;
		holder = new Reactant();
	}

	private void parseReactions() throws IOException, InterruptedException {
		while (bufferedReader.ready()) {
			String contentLine = bufferedReader.readLine();
			if (!contentLine.startsWith("#")) {
				lineFilter(contentLine);
			}
			if (contentLine.equals("//")) {
				compileReaction();
			}
		}
		System.out.println("Done!");
	}

	private void compileReaction() throws InterruptedException {
		Reactant reactant = holder;
		holder = new Reactant();
		
		System.out.println("FileParser Thread  (" + reactant.getDate() + ")" + "----" + "Wrote "
				+ reactant.get("UNIQUE-ID") + " to buffer");
		sharedBuffer.put(reactant);
		logBuffer.put("FileParser Thread (" + reactant.getDate() + ")" + "----" + "Wrote" + reactant.get("UNIQUE-ID")
				+ " to buffer");

	}

	private void lineFilter(String line) {
		for(String match: filter) {
			if(line.contains(match)) {
				sliceLine(line);
			}
		}
	}
	
	private void sliceLine(String line) {
		String s[] = line.split(" - ");
		holder.put(s[0], s[1]);
	}

	@Override
	public void process() {
		try {
			parseReactions();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
