
public interface QueueADT<E> {
	boolean isEmpty();
	void enqueue(E ob);
	E dequeue() throws EmptyQueueException;
}
