package test.checkpoint;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkpoint.andela.parser.DatFileReader;

public class DatFileReaderTest {
	private BufferedReader br;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		br = new BufferedReader(new FileReader("reactions.dat"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReturnObject() throws FileNotFoundException {
		DatFileReader df = new DatFileReader("reactions");
		assertNotNull(df);
		assertEquals(br.getClass(), df.getBufferedReader().getClass());
	}
	
}
