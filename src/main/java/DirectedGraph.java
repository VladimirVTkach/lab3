import java.util.List;

public interface DirectedGraph<T> {
    void addVertex(T key);

    void removeVertex(T key);

    void addEdge(T sourceKey, T destinationKey);

    void removeEdge(T sourceKey, T destinationKey);

    List<T> findPath(T sourceKey, T destinationKey);

    int getVerticesCount();

    int getEdgesCount();
}
