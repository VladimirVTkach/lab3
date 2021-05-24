import java.util.List;

public interface DirectedGraph<T> {
    void addVertex(T vertex);

    void removeVertex(T vertex);

    void addEdge(T sourceVertex, T destinationVertex);

    void removeEdge(T sourceVertex, T destinationVertex);

    List<T> findPath(T sourceVertex, T destinationVertex);

    int getVerticesCount();

    int getEdgesCount();
}
