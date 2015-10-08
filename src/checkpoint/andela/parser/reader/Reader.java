package checkpoint.andela.parser.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public interface Reader {
	public void read(String fileName) throws FileNotFoundException;
	public BufferedReader getBufferedReader();
}
