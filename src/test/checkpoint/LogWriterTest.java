package test.checkpoint;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.log.LogWriter;
import checkpoint.andela.parser.SharedBuffers;
import checkpoint.andela.parser.document.processor.ReactionParser;
import checkpoint.andela.parser.reader.DatFileReader;

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
	public void testLogWriterThread() throws Exception{
		Thread log = new Thread(new LogWriter("Test"), "LogWriterTest");
		reactionParser.process();
		log.start();
		assertEquals(SharedBuffers.getLogBuffer().size() , 1);
	}

}
