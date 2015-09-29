package test.checkpoint;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.parser.DatFileReader;
import checkpoint.andela.parser.FileParser;
import checkpoint.andela.parser.ReactionParser;
import checkpoint.andela.parser.SharedBuffers;

public class FileParserTest {
	private DatFileReader datFileReader;
	private ReactionParser reactionParser;
	private FileParser fileParser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SharedBuffers.sharedBuffer.clear();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		SharedBuffers.logBuffer.clear();
		SharedBuffers.sharedBuffer.clear();
		datFileReader = new DatFileReader("testReactions");
		reactionParser = new ReactionParser(datFileReader.getBufferedReader());
		fileParser = new FileParser("testReactions");
	}

	@After
	public void tearDown() throws Exception {
		datFileReader = null;
		reactionParser = null;
		SharedBuffers.logBuffer.clear();
		SharedBuffers.sharedBuffer.clear();
	}

	@Test
	public void testFileParserResults() throws Exception {
		SharedBuffers.sharedBuffer.clear();
		reactionParser.process();
		int numberOfReactions = SharedBuffers.getSharedBuffer().size();
		assertEquals(numberOfReactions, 3);
	}
	@Test
	public void testFileParser() {
		assertNotNull(fileParser);
	}

}
