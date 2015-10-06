package checkpoint.andela.parser;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ReactantBuffer {
	private BlockingQueue<Reactant> sharedBuffer;
	
	public ReactantBuffer() {
		sharedBuffer = new LinkedBlockingQueue<>(1);
	}
	
	public void addReactionToBuffer(Reactant reaction) throws InterruptedException {
			sharedBuffer.put(reaction);
		
	}
	
	public Reactant takeReactionFromBuffer() throws InterruptedException {
		return sharedBuffer.take();
	}

	public void clear() {
		sharedBuffer.clear();		
	}

	public int size() {
		return sharedBuffer.size();
	}

}
