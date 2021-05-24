import java.util.*;

public class NonSynchronizedDirectedGraph<T> implements DirectedGraph<T> {

    private final Map<T, Vertex<T>> vertices = new HashMap<>();
    private final Map<Vertex<T>, Map<Vertex<T>, Edge<T>>> edges = new HashMap<>();

    @Override
    public void addVertex(T key) {
        vertices.put(key, new Vertex<>(key));
    }

    @Override
    public void removeVertex(T key) {
        if (!containsVertex(key)) return;

        Vertex<T> vertexToRemove = vertices.remove(key);
        edges.remove(vertexToRemove);

        edges.forEach((vertex, adjacentEdges) -> adjacentEdges.remove(vertexToRemove));
    }

    @Override
    public void addEdge(T sourceKey, T destinationKey) {
        if (!containsVertex(sourceKey) || !containsVertex(destinationKey)) {
            return;
        }

        Vertex<T> sourceVertex = vertices.get(sourceKey);
        Vertex<T> destinationVertex = vertices.get(destinationKey);

        Edge<T> edge = new Edge<>(sourceVertex, destinationVertex);

        Map<Vertex<T>, Edge<T>> adjacentEdges = edges.getOrDefault(sourceVertex, new HashMap<>());
        adjacentEdges.put(destinationVertex, edge);
        edges.put(sourceVertex, adjacentEdges);
    }

    @Override
    public void removeEdge(T sourceKey, T destinationKey) {
        if (!containsEdge(sourceKey, destinationKey)) {
            return;
        }

        Vertex<T> sourceVertex = vertices.get(sourceKey);
        Vertex<T> destinationVertex = vertices.get(destinationKey);

        edges.get(sourceVertex).remove(destinationVertex);
    }

    @Override
    public boolean containsVertex(T key) {
        return vertices.containsKey(key);
    }

    @Override
    public boolean containsEdge(T sourceKey, T destinationKey) {
        if (!containsVertex(sourceKey) || !containsVertex(destinationKey)) {
            return false;
        }

        Vertex<T> sourceVertex = vertices.get(sourceKey);
        Vertex<T> destinationVertex = vertices.get(destinationKey);

        return edges.get(sourceVertex).containsKey(destinationVertex);
    }

    @Override
    public List<T> findPath(T sourceKey, T destinationKey) {
        return Collections.emptyList();
    }

    @Override
    public int getVerticesCount() {
        return vertices.size();
    }

    @Override
    public int getEdgesCount() {
        return edges.size();
    }
}
