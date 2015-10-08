package checkpoint.andela.parser.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DatFileReader implements Reader{
	private BufferedReader bufferedReader;
	public DatFileReader(String fileName) {
		read(fileName);
	}

	@Override
	public void read(String fileName) {
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName + ".dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}
}
