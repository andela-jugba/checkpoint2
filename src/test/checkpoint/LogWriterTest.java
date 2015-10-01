package test.checkpoint;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.log.LogWriter;
import checkpoint.andela.parser.DatFileReader;
import checkpoint.andela.parser.ReactionParser;
import checkpoint.andela.parser.SharedBuffers;

public class LogWriterTest {
	private LogWriter logWriter;
	private DatFileReader datFileReader;
	private ReactionParser reactionParser; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		SharedBuffers.getLogBuffer().clear();
		 logWriter = new LogWriter("TestFile");
		 datFileReader = new DatFileReader("testReactions");
		 reactionParser = new ReactionParser(datFileReader.getBufferedReader());
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testLogWriter() {
		assertNotNull(logWriter);
		SharedBuffers.getLogBuffer().clear();
	}
	@Test
	public void testLogWriterReads() {
		int num = SharedBuffers.getLogBuffer().size();
		assertEquals(num,0);
		reactionParser.process();
		int numAfter = SharedBuffers.getLogBuffer().size();
		assertEquals(numAfter,3);
		logWriter.writeToLog();
		int numAfterLogging = SharedBuffers.getLogBuffer().size();
		assertEquals(numAfterLogging,1);	
		
	}

}