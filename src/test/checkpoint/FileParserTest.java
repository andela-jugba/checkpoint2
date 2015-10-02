package test.checkpoint;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.parser.FileParser;
import checkpoint.andela.parser.SharedBuffers;

public class FileParserTest {
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
		fileParser = new FileParser("testReactions");
	}

	@After
	public void tearDown() throws Exception {
		SharedBuffers.logBuffer.clear();
		SharedBuffers.sharedBuffer.clear();
	}

	@Test
	public void testFileParser() {
		assertNotNull(fileParser);
	}
	
	@Test
	public void testFileParserThread() throws Exception{
		Thread file = new Thread(new FileParser("testReactions"), "Test");
		file.run();
		int numberOfReactions = SharedBuffers.getSharedBuffer().size();
		assertEquals(numberOfReactions, 0);
	}

}
