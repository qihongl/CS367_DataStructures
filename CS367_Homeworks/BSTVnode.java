
public class BSTVnode <K, V>{
    // *** fields ***
    private K key;
    private V value;
    private BSTVnode<K, V> left, right;
 
    // *** constructor ***
    public BSTVnode(K key, V value,
                         BSTVnode<K, V> left, BSTVnode<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
   
    // *** methods ***
    public V getValue() { return value; }
    public void setValue(V newV) { value = newV; }
    
    // accessors (access to fields)
    public K getKey() { return key; }
    public BSTVnode<K, V> getLeft() { return left; }
    public BSTVnode<K, V> getRight() { return right; }
 

    // mutators (change fields)
    public void setKey(K newK) { key = newK; }
    public void setLeft(BSTVnode<K, V> newL) { left = newL; }
    public void setRight(BSTVnode<K, V> newR) { right = newR; }
}
