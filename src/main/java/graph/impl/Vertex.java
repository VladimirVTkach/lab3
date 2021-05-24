package graph.impl;

import java.util.Objects;

public class Vertex<T> {
    private final T key;

    public Vertex(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(key, vertex.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
