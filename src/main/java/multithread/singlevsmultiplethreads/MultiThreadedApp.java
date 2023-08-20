package multithread.singlevsmultiplethreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This folder has two classes which is a good illustration to show the power of multithreading:
 * it dramatically improves throughput and speeds up workload!
 */
public class MultiThreadedApp {
    private static final int THREAD_POOL_SIZE = 5;
    private static final int TOTAL_TASKS = 10;
    private static final int MILLISECONDS_TO_FINISH_A_TASK = 1000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < TOTAL_TASKS; i++) {
            Runnable worker = new Worker("Worker" + i);
            Future<?> future = executorService.submit(worker);
            futures.add(future);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        long end = System.currentTimeMillis();
        System.out.println("Multi-threaded app finished, it took " + (end - start) / 1000 +
                " seconds for a thread pool of size " + THREAD_POOL_SIZE + " to finish " +
                TOTAL_TASKS + " tasks, with each task takes " + MILLISECONDS_TO_FINISH_A_TASK / 1000 + " seconds.");
        executorService.shutdown();
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
