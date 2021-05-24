package demo.impl;

import demo.Demo;
import graph.DirectedGraph;
import graph.impl.NonSynchronizedDirectedGraph;
import graph.impl.SynchronizedDirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ConcurrentPathFinding implements Demo {
    @Override
    public long run() {
        DirectedGraph<Integer> graph = new SynchronizedDirectedGraph<>();
        Random random = new Random();

        for (int i = 0; i < 100_000; i++) {
            graph.addVertex(i);
        }

        for (int i = 0; i < 200_000; i++) {
            int randomSourceVertex = random.nextInt(60_000);
            int randomDestinationVertex = random.nextInt(60_000);

            graph.addEdge(randomSourceVertex, randomDestinationVertex);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(16);

        long start = System.currentTimeMillis();

        List<List<Integer>> paths = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int randomSourceVertex = random.nextInt(100_000);
            int randomDestinationVertex = random.nextInt(100_000);

            executorService.submit(() -> graph.findPath(randomSourceVertex, randomDestinationVertex).ifPresentOrElse(
                    paths::add,
                    () -> {
                    }
            ));
        }

        try {
            executorService.awaitTermination(10_000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            executorService.shutdown();
        }
        long end = System.currentTimeMillis();

        System.out.printf("concurrently found %d%n", paths.size());
        System.out.printf("time: %d ms%n", end - start);
        return end - start;
    }
}
