package test.checkpoint;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.buffer.Buffer;
import checkpoint.andela.parser.SharedBuffers;
import checkpoint.andela.parser.document.models.Reactant;
import checkpoint.andela.parser.document.processor.ReactionParser;
import checkpoint.andela.parser.reader.DatFileReader;

public class ReactionParserTest {
	private DatFileReader df;
	private Buffer sharedBuffer;
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
		SharedBuffers.sharedBuffer.clear();
		df = new DatFileReader("testReactions");
		sharedBuffer = SharedBuffers.getSharedBuffer();
		reactionParser = new ReactionParser(df.getBufferedReader());
	}

	@After
	public void tearDown() throws Exception {
		SharedBuffers.sharedBuffer.clear();
	}

	@Test
	public void testLineFilter() throws Exception {
		reactionParser.process();
		Reactant reaction = (Reactant) sharedBuffer.takeFromBuffer();
		System.out.println(sharedBuffer.size());
		assertEquals(reaction.get("UNIQUE-ID"), "RXN-8748");
	}

}
