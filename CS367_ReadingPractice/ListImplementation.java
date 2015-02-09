public class ListImplementation<E> implements ListADT<E> {
    private E myItem;
    
    public ListImplementation() {
        myItem = null;
    }
    
    public void add(E item) {
        myItem = item;
    }
    
    public void add(int pos, E item) {
        myItem = item;
    }
    
    public boolean contains(E item) {
        return false;
    }
    
    public int size() {
        return 0;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public E get(int pos) {
        return null;
    }
    
    public E remove(int pos) {

        return null;
    }
}