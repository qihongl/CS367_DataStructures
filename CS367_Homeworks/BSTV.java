import java.io.PrintStream;

public class BSTV<K extends Comparable<K>, T> {
    // *** fields ***
    private BSTVnode<K, T> root; // ptr to the root of the BST
 
    // *** constructor ***
    public BSTV() { root = null; } 
    
    // *** methods ***  
    
    public void insert(K key, T value) throws DuplicateException {
    	
    }
      // add key and associated value to this BST;
      // error if key is already there
      
    public void delete(K key) {
    	
    }
      // remove the node containing key from this BST if it is there;
      // otherwise, do nothing
      
    public T lookup(K key) {
    	return null;
    	}
      // if key is in this BST, return its associated value; otherwise, return null
     
    public void print(PrintStream p) {
    	
    }
      // print the values in this BST in sorted order (to p)
    
    public T findLargest() {
    	T maxValue = root.getValue();
    	BSTVnode<K, T>currentNode = root; 
    	while(currentNode.getLeft() != null || currentNode.getRight() != null){
    		if(currentNode.getLeft() != null) {
//    			currentNode.getLeft().getValue()
    			
    		} 
    		
    	}
    	
    	
		return maxValue;
    }
    
    public T findLargestKey() {
		return null;
        //TODO: complete this method
    }
}
