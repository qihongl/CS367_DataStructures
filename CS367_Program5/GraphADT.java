import java.util.Set;

public interface GraphADT<V> {

   boolean addVertex(V vertex);
   boolean addEdge(V v1, V v2);
   void removeEdge(V v1, V v2);
   Set<V> getNeighbors(V vertex);
   Set<V> getAllVertices();

}
