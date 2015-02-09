import java.util.Iterator;
import java.util.NoSuchElementException;
 
public class SimpleArrayListIterator implements Iterator<Object> {
 
    // *** fields ***
    private SimpleArrayList list;  // the list we're iterating over
    private int curPos;            // the position of the next item
     
    // *** constructor ***
    public SimpleArrayListIterator(SimpleArrayList list) {
        this.list = list;
        curPos = 0;
    }
 
    // *** methods ***
 
    public boolean hasNext() {
        return curPos < list.size();
    }
 
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object result = list.get(curPos);
        curPos++;
        return result;
    }
 
    public void remove() {
        throw new UnsupportedOperationException();
    }
}