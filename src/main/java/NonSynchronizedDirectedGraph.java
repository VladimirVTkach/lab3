import java.util.*;

public class NonSynchronizedDirectedGraph<T> implements DirectedGraph<T> {

    private final Map<T, Vertex<T>> vertices = new HashMap<>();
    private final Map<Vertex<T>, Map<Vertex<T>, Edge<T>>> edges = new HashMap<>();
    private int edgesCount = 0;

    @Override
    public void addVertex(T key) {
        vertices.put(key, new Vertex<>(key));
    }

    @Override
    public void removeVertex(T key) {
        if (!containsVertex(key)) return;

        Vertex<T> vertexToRemove = vertices.remove(key);
        Map<Vertex<T>, Edge<T>> removedEdges = edges.remove(vertexToRemove);

        if(removedEdges != null) edgesCount -= removedEdges.size();

        edges.forEach((vertex, adjacentEdges) -> {
            if(adjacentEdges.containsKey(vertexToRemove)) {
                adjacentEdges.remove(vertexToRemove);
                edgesCount--;
            }
        });
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
        edgesCount++;
    }

    @Override
    public void removeEdge(T sourceKey, T destinationKey) {
        if (!containsEdge(sourceKey, destinationKey)) {
            return;
        }

        Vertex<T> sourceVertex = vertices.get(sourceKey);
        Vertex<T> destinationVertex = vertices.get(destinationKey);

        edges.get(sourceVertex).remove(destinationVertex);
        edgesCount--;
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
    public Optional<List<T>> findPath(T sourceKey, T destinationKey) {
        if (!containsVertex(sourceKey) || !containsVertex(destinationKey)) {
            return Optional.empty();
        }
        return Optional.of(new ArrayList<>());
    }

    @Override
    public int getVerticesCount() {
        return vertices.size();
    }

    @Override
    public int getEdgesCount() {
        return edgesCount;
    }
}
