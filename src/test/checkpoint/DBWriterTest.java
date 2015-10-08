package test.checkpoint;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.db.DBWriter;
import checkpoint.andela.db.SqlConnector;
import checkpoint.andela.parser.SharedBuffers;
import checkpoint.andela.parser.document.models.Reactant;

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
		sq.trucateTable();
	}

	@Test
	public void testDBWriterExits() throws Exception{
		DBWriter dB = new DBWriter();
		assertNotNull(dB);
		
	}
	@Test
	public void testDBWriterThread() throws Exception{
		Thread dB = new Thread(new DBWriter(), "DB");
		dB.start();
		sq.trucateTable();
	}
	
	@Test
	public void testSql() throws Exception {
		Reactant r = new Reactant();
			sq.writeReact(r);
			sq.trucateTable();
	}

}
