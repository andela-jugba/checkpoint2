package test.checkpoint;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.db.DBWriter;
import checkpoint.andela.log.LogWriter;
import checkpoint.andela.parser.DatFileReader;
import checkpoint.andela.parser.FileParser;
import checkpoint.andela.parser.ReactionParser;

public class LogWriterTest {
	private LogWriter logWriter;
	private DatFileReader datFileReader;
	private ReactionParser reactionPaser; 
	private DBWriter dBWriter;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 logWriter = new LogWriter("TestFile");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogWriter() {
		assertNotNull(logWriter);
	}
	@Test
	public void testLogWriterReads() {
		
	
		
	}

}
