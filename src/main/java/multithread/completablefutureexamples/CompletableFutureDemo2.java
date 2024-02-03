package multithread.completablefutureexamples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static multithread.completablefutureexamples.CompletableFutureDemo2.WorkerPool.TIME_FOR_A_COMPUTATION_JOB_IN_MS;

public class CompletableFutureDemo2 {

    private static final int POOL_SIZE = 10;
    private static final int NUMBER_OF_COMPUTATION_JOBS = 20;

    public static void main(String... args) throws ExecutionException, InterruptedException {
        System.out.println("Program started.");
        long start = System.currentTimeMillis();
        WorkerPool workerPool = new WorkerPool(POOL_SIZE);
        int finalResult = 0;
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_COMPUTATION_JOBS; i++) {
            Future<String> completableFuture = workerPool.executeAsync(i);
            System.out.println("i = " + i + " and completableFuture.isDone() is: " + completableFuture.isDone());
            futureList.add(completableFuture);
        }
        for (Future<String> future : futureList) {
            String result = future.get();
            finalResult += Integer.parseInt(result);
        }
        long end = System.currentTimeMillis();
        System.out.println("end time in millis: " + end);
        System.out.println("start time in millis: " + start);
        System.out.println("It took " + (end - start) / 1000
                + " seconds to complete computation, final result: " + finalResult
                + ", a total of " + NUMBER_OF_COMPUTATION_JOBS + " computation jobs "
                + "have been completed, total pool worker size is: " + POOL_SIZE
                + ", and each job took " + TIME_FOR_A_COMPUTATION_JOB_IN_MS / 1000 + " second(s)."
        );
        System.out.println("Program ended.");
    }

    static class WorkerPool {
        static final long TIME_FOR_A_COMPUTATION_JOB_IN_MS = 1000l;
        int poolSize;
        ExecutorService executorService;

        public WorkerPool(int poolSize) {
            this.poolSize = poolSize;
            this.executorService = Executors.newFixedThreadPool(poolSize);
        }

        public Future<String> executeAsync(int input) {
            final CompletableFuture completableFuture = new CompletableFuture<>();
            this.executorService.submit(() -> doWork(completableFuture, input));
            return completableFuture;
        }

        private void doWork(CompletableFuture completableFuture, int input) {
            int result = 0;
            try {
                System.out.println(Thread.currentThread().getName() + " is doing some real work now that'll take 1 second to complete.");
                Thread.sleep(TIME_FOR_A_COMPUTATION_JOB_IN_MS);
                result += input * 2;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            completableFuture.complete("" + result);
        }
    }

}
