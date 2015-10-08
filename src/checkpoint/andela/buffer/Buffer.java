package checkpoint.andela.buffer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Buffer implements BufferInterface{
	private BlockingQueue<Object> buffer;

	public Buffer() {
		buffer = new LinkedBlockingQueue<>(1);
	}
	@Override
	public Object takeFromBuffer() throws InterruptedException {
		return buffer.take();
		
	}

	@Override
	public void clear() {
		buffer.clear();

	}

	@Override
	public int size() {
		return buffer.size();

	}

	@Override
	public void addToBuffer(Object obj) throws InterruptedException {
		buffer.put(obj);

	}
}
