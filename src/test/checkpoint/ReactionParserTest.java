package test.checkpoint;

import static org.junit.Assert.*;

import java.util.concurrent.BlockingQueue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.parser.DatFileReader;
import checkpoint.andela.parser.Reactant;
import checkpoint.andela.parser.ReactionParser;
import checkpoint.andela.parser.SharedBuffers;

public class ReactionParserTest {
	private DatFileReader df;
	private BlockingQueue<Reactant> sharedBuffer;
	private ReactionParser reactionParser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SharedBuffers.logBuffer.clear();
		SharedBuffers.sharedBuffer.clear();
	}

	@Before
	public void setUp() throws Exception {
		df = new DatFileReader("testReactions");
		sharedBuffer = SharedBuffers.getSharedBuffer();
		reactionParser = new ReactionParser(df.getBufferedReader());
	}

	@After
	public void tearDown() throws Exception {
		SharedBuffers.logBuffer.clear();
		SharedBuffers.sharedBuffer.clear();
	}

	@Test
	public void testLineFilter() throws Exception {
		reactionParser.process();
		Reactant reaction = sharedBuffer.take();
		System.out.println(sharedBuffer.size());
		//assertEquals(reaction.get("UNIQUE-ID"), "RXN-8748");
	}

}