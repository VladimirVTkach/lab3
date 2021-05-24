import java.util.List;
import java.util.Optional;

public interface DirectedGraph<T> {
    void addVertex(T key);

    void removeVertex(T key);

    void addEdge(T sourceKey, T destinationKey);

    void removeEdge(T sourceKey, T destinationKey);

    boolean containsVertex(T key);

    boolean containsEdge(T sourceKey, T destinationKey);

    Optional<List<T>> findPath(T sourceKey, T destinationKey);

    int getVerticesCount();

    int getEdgesCount();

}
