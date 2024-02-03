package multithread.completablefutureexamples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class CompletableFutureDemo {
    private static final int MILLISECONDS_TO_FINISH_A_TASK = 1000;


    public static void main(String... args) throws Exception {
        System.out.println("Program started.");
        runApp();
        System.out.println("Program ended.");
    }

    private static void runApp() throws InterruptedException, ExecutionException {
        Future<String> completableFuture = calculateAsync();
        System.out.println("got completableFuture: " + completableFuture);
        System.out.println("got completableFuture.isDone(): " + completableFuture.isDone());
        String result = completableFuture.get();
        System.out.println("got completableFuture.isDone(): " + completableFuture.isDone());
        assertEquals("Hello", result);
    }

    private static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("Doing some work in the thread now..");
            Thread.sleep(1000);
            System.out.println("Almost done working in the thread now..");
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    static class Worker implements Runnable {
        private String workerName;

        public Worker(String workerName) {
            this.workerName = workerName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " starting worker: " + workerName);
            doWork();
            System.out.println(Thread.currentThread().getName() + " ended for worker: " + workerName);
        }

        private void doWork() {
            try {
                Thread.sleep(MILLISECONDS_TO_FINISH_A_TASK);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
