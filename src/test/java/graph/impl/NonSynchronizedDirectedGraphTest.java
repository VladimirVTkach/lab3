package graph.impl;

import graph.DirectedGraph;
import graph.impl.NonSynchronizedDirectedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NonSynchronizedDirectedGraphTest {
    @Test
    public void testAddVertexWhenSingleVertexIsAdded() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();
        graph.addVertex(1);

        Assertions.assertTrue(graph.containsVertex(1));
    }

    @Test
    public void testAddVertexWhenMultipleVerticesAreAdded() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();
        graph.addVertex(5);
        graph.addVertex(12);

        Assertions.assertTrue(graph.containsVertex(5));
        Assertions.assertTrue(graph.containsVertex(12));
    }

    @Test
    public void testAddEdgeWhenSingleEdgeIsAdded() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        graph.addVertex(-3);
        graph.addVertex(21);

        graph.addEdge(-3, 21);

        Assertions.assertTrue(graph.containsEdge(-3, 21));
    }

    @Test
    public void testAddEdgeWhenMultipleEdgesAreAdded() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        graph.addVertex(0);
        graph.addVertex(18);
        graph.addVertex(15);
        graph.addVertex(4);

        graph.addEdge(0, 18);
        graph.addEdge(4, 15);

        Assertions.assertTrue(graph.containsEdge(0, 18));
        Assertions.assertTrue(graph.containsEdge(4, 15));
    }

    @Test
    public void testRemoveVertexWhenItDoesntHaveAdjacentEdges() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        graph.addVertex(18);
        graph.addVertex(15);
        graph.addVertex(4);

        graph.removeVertex(15);

        Assertions.assertTrue(graph.containsVertex(18));
        Assertions.assertTrue(graph.containsVertex(4));
        Assertions.assertFalse(graph.containsVertex(15));
    }

    @Test
    public void testRemoveVertexWhenItHaveAdjacentEdges() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);

        graph.removeVertex(3);

        Assertions.assertTrue(graph.containsEdge(1, 2));
        Assertions.assertFalse(graph.containsEdge(2, 3));
        Assertions.assertFalse(graph.containsEdge(1, 3));
    }

    @Test
    public void testRemoveEdge() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);

        graph.removeEdge(2, 3);

        Assertions.assertTrue(graph.containsEdge(1, 2));
        Assertions.assertTrue(graph.containsEdge(1, 3));
        Assertions.assertFalse(graph.containsEdge(2, 3));
    }

    @Test
    public void testGetVerticesCountWhenGraphIsEmpty() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        int expectedVerticesCount = 0;
        int actualVerticesCount = graph.getVerticesCount();

        Assertions.assertEquals(expectedVerticesCount, actualVerticesCount);
    }

    @Test
    public void testGetVerticesCountWhenGraphIsNotEmpty() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        int expectedVerticesCount = 5;
        int actualVerticesCount = graph.getVerticesCount();

        Assertions.assertEquals(expectedVerticesCount, actualVerticesCount);
    }

    @Test
    public void testGetEdgesCountWhenGraphIsEmpty() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        int expectedEdgesCount = 0;
        int actualEdgesCount = graph.getEdgesCount();

        Assertions.assertEquals(expectedEdgesCount, actualEdgesCount);
    }

    @Test
    public void testGetEdgesCountWhenGraphIsNotEmpty() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(5, 4);

        int expectedEdgesCount = 3;
        int actualEdgesCount = graph.getEdgesCount();

        Assertions.assertEquals(expectedEdgesCount, actualEdgesCount);
    }

    @Test
    public void testFindPathWhenItDoesntExist() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(5, 4);

        Assertions.assertTrue(graph.findPath(1, 5)
                .isEmpty());
    }

    @Test
    public void testFindPathWhenItExists() {
        DirectedGraph<Integer> graph = new NonSynchronizedDirectedGraph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(5, 4);
        graph.addEdge(2, 5);

        List<Integer> expectedPath = List.of(1, 2, 5);
        List<Integer> actualPath = graph.findPath(1, 5).get();

        Assertions.assertEquals(expectedPath, actualPath);
    }
}
