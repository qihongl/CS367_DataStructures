import java.util.EmptyStackException;


public interface StackADT<E> {
	boolean isEmpty();
	void push(E ob);
	E pop() throws EmptyStackException;
	E peek() throws EmptyStackException;
}
