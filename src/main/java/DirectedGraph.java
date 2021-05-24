public interface DirectedGraph<T> {
    void addVertex(T vertex);

    void removeVertex(T vertex);

    void addEdge(T sourceVertex, T destinationVertex);

    void removeEdge(T sourceVertex, T destinationVertex);

    void findPath(T sourceVertex, T destinationVertex);

    void getVerticesCount();

    void getEdgesCount();
}
