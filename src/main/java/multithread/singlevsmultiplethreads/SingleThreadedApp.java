package multithread.singlevsmultiplethreads;

public class SingleThreadedApp {

    private static final int TOTAL_TASKS = 10;
    private static final int MILLISECONDS_TO_FINISH_A_TASK = 1000;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        work(TOTAL_TASKS);
        long end = System.currentTimeMillis();
        System.out.println("Single-threaded app took " + (end - start) / 1000 +
                " seconds to finish a total of " + TOTAL_TASKS +
                " tasks, with each task takes " + MILLISECONDS_TO_FINISH_A_TASK / 1000 + " seconds.");
    }

    private static void work(int n) {
        for (int i = 0; i < n; i++) {
            doWork(i);
        }
    }

    private static void doWork(int workNumber) {
        System.out.println("Task " + workNumber + " is being worked on.");
        try {
            Thread.sleep(MILLISECONDS_TO_FINISH_A_TASK);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
