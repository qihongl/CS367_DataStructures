import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class UndirectedGraph<V> implements GraphADT<V>{

	// Stores the vertices of this graph, and their adjacency lists.
	// It's protected rather than private so that subclasses can access it.
    protected HashMap<V, ArrayList<V>> hashmap;

    public UndirectedGraph() {
        this.hashmap = new HashMap<V, ArrayList<V>>();
    }

    public UndirectedGraph(HashMap<V, ArrayList<V>> hashmap) {
        if (hashmap == null) throw new IllegalArgumentException();
        this.hashmap = hashmap;
    }

    @Override
    public boolean addVertex(V vertex) {
        //TODO
        return false;
    }

    @Override
    public boolean addEdge(V v1, V v2) {
        //TODO
        return false;
    }

    @Override
    public Set<V> getNeighbors(V vertex) {
        //TODO
        return null;
    }

    @Override
    public void removeEdge(V v1, V v2) {
        //TODO
        return;
    }

    @Override
    public Set<V> getAllVertices() {
        //TODO
        return null;
    }

    /* (non-Javadoc)
     * Returns a print of this graph in adjacency lists form.
     * 
     * This method has been written for your convenience (e.g., for debugging).
     * You are free to modify it or remove the method entirely.
     * THIS METHOD WILL NOT BE PART OF GRADING.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        for (V vertex: this.hashmap.keySet()) {
            writer.append(vertex + " -> " + hashmap.get(vertex) + "\n");
        }
        return writer.toString();
    }

}
