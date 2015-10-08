package checkpoint.andela.buffer;

public interface BufferInterface {
	public void addToBuffer(Object obj) throws InterruptedException;
	public Object takeFromBuffer() throws InterruptedException;
	public void clear();
	public int size();
}
