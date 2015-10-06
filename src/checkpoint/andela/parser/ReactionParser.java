package checkpoint.andela.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReactionParser implements DocumentProcessor {
	private ReactantBuffer sharedBuffer;
	private LogBuffer logBuffer;
	private BufferedReader bufferedReader;

	private Reactant holder;
	private List<String> filter = Arrays.asList("UNIQUE","TYPES","ATOM","CREDITS","EC","IN","ORPHAN?","LEFT");
	
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
	}

	private void compileReaction() throws InterruptedException {
		Reactant reactant = holder;
		holder = new Reactant();
		System.out.println("FileParser Thread (" + reactant.getDate() + ")" + "----" + "Wrote " + reactant.get("UNIQUE-ID")
				+ " to buffer");
		sharedBuffer.addReactionToBuffer(reactant);
		logBuffer.addToLogBuffer("FileParser Thread (" + reactant.getDate() + ")" + "----" + "Wrote " + reactant.get("UNIQUE-ID")
				+ " to buffer");
	}

	private void lineFilter(String line) {
		String temp = line.replace(" ", "");
		String s[] = temp.split("-");
		if(filter.contains(s[0]))parseLine(line);
	}
	
	private void parseLine(String line) {
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
