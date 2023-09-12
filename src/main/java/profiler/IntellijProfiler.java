package profiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class IntellijProfiler {
    /**Source: https://github.com/flounder4130/profiler-example/tree/master*/
    public static int update(Deque<Long> events, long nanos, long interval) {
        events.add(nanos);
        events.removeIf(aTime -> aTime < nanos - interval);
        /*
        //noinspection ConstantConditions
        while (events.peekFirst() < nanos - interval) {
            events.removeFirst();
        }
        */
        return events.size();
    }

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        int total = 100_000;
        long interval = TimeUnit.MILLISECONDS.toNanos(100);
        int[] count = new int[total];

        Deque<Long> collection = new ArrayDeque<>();
        for (int counter = 0; counter < count.length; counter++) {
            count[counter] = update(collection, System.nanoTime(), interval);
            Path p = Paths.get("./a/b");
            Files.createDirectories(p);
            /*
            if (!Files.exists(p)) {
                Files.createDirectories(p);
            }
            */
        }
        long spent = System.nanoTime() - start;

        //noinspection OptionalGetWithoutIsPresent
        System.out.println("Average count: " + (int) (Arrays.stream(count).average().getAsDouble()) + " op");
        System.out.println("Spent time: " + TimeUnit.NANOSECONDS.toMillis(spent) + " ms");
    }
}
