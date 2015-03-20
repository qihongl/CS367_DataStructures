/**
 * An ordered collection of items, where items are added to the back and removed
 * from the front.
 * @author
 * 
 */
public interface QueueADT<E> {
    /**
     * Checks if the queue is empty.
     * @return true if queue is empty; otherwise false.
     */
    boolean isEmpty();

    /**
     * removes and returns the front item of the queue.
     * @return the front item of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    E dequeue() throws EmptyQueueException;


    /**
     * Adds an item to the rear of the queue.
     * @param item the item to add to the queue.
     * @throws IllegalArgumentException if item is null.
     */
    void enqueue(E item);

    /**
     * Returns (but does not remove) the front item of the queue.
     * @return the top item of the stack.
     * @throws EmptyQueueException if the queue is empty.
     */
    E peek() throws EmptyQueueException;
    
    /**
     * Returns the size of the queue.
     * @return the size of the queue
     */
    int size();

    /**
     * Returns a string representation of the queue (for printing).
     * @return a string representation of the queue.
     */
    String toString(); 
}
