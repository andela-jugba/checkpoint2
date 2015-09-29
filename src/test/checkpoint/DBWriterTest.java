package test.checkpoint;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.db.DBWriter;
import checkpoint.andela.db.SqlConnector;
import checkpoint.andela.parser.FileParser;
import checkpoint.andela.parser.Reactant;
import checkpoint.andela.parser.SharedBuffers;

public class DBWriterTest {
	
	private static SqlConnector sq;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		sq.trucateTable();
		SharedBuffers.getLogBuffer().clear();
		SharedBuffers.getSharedBuffer().clear();
	}

	@Before
	public void setUp() throws Exception {
		sq = new SqlConnector();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testDBWriterExits() throws Exception{
		DBWriter dB = new DBWriter();
		Thread fileParser = new Thread(new FileParser("testReactions"), "FileParser Thread");
		Thread DbThread = new Thread(new DBWriter(), "DBWriter Thread");
		
		DbThread.start();
		fileParser.start();
		System.out.println(SharedBuffers.sharedBuffer.size());
		
		
		assertNotNull(dB);
		
	}
	
	@Test
	public void testSql() throws Exception {
		Reactant r = new Reactant();
			sq.writeReact(r);
	}

}
