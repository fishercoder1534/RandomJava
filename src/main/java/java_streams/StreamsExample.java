package java_streams;

import java.util.Arrays;
import java.util.List;

public class StreamsExample {
    public static void main(String... args) {
        /**reference: https://www.baeldung.com/java-when-to-use-parallel-stream*/
        System.out.println("Program started.");
        sequentialStreams();
        parallelStreams();
        System.out.println("Program ended.");
    }

    private static void sequentialStreams() {
        /**By default, any stream operation in Java is processed sequentially, unless explicitly specified as parallel.
         Sequential streams use a single thread to process the pipeline like below:
         */
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        listOfNumbers.stream().forEach(number ->
                System.out.println(number + " from this thread: " + Thread.currentThread().getName())
        );
    }

    private static void parallelStreams() {
        /**
         * Any stream in Java can easily be transformed from sequential to parallel.
         * We can achieve this by adding the parallel method to a sequential stream or by creating a stream using the parallelStream method of a collection:
         * */
        List<Integer> listOfNumbers = Arrays.asList(5, 6, 7, 8);
        listOfNumbers.parallelStream().forEach(number ->
                System.out.println(number + " from this thread: " + Thread.currentThread().getName())
        );
        /**
         * Parallel streams enable us to execute code in parallel on separate cores.
         * The final result is the combination of each individual outcome.
         * However, the order of execution is out of our control.*/

        /**
         * Parallel streams make use of the fork-join framework and its common pool of worker threads.
         * The fork-join framework was added to java.util.concurrent in Java 7 to handle task management between multiple threads.*/
    }
}
