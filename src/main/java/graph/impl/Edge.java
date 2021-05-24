package graph.impl;

import java.util.Objects;

public class Edge<T> {
    private final Vertex<T> sourceVertex;
    private final Vertex<T> destinationVertex;

    public Edge(Vertex<T> sourceVertex, Vertex<T> destinationVertex) {
        this.sourceVertex = sourceVertex;
        this.destinationVertex = destinationVertex;
    }

    public Vertex<T> getSourceVertex() {
        return sourceVertex;
    }

    public Vertex<T> getDestinationVertex() {
        return destinationVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(sourceVertex, edge.sourceVertex) && Objects.equals(destinationVertex, edge.destinationVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceVertex, destinationVertex);
    }
}
