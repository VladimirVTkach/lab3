import demo.Demo;
import demo.impl.ConcurrentPathFinding;
import demo.impl.SequentialPathFinding;

public class Main {
    public static void main(String[] args) {
        Demo sequentialPathFinding = new SequentialPathFinding();
        Demo concurrentPathFinding = new ConcurrentPathFinding();

        sequentialPathFinding.run();

        System.out.println("============");

        concurrentPathFinding.run();
    }
}
