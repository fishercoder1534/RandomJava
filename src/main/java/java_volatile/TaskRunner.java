package java_volatile;

public class TaskRunner {
    /**
     * This is following the examples from https://www.baeldung.com/java-volatile
     *
     * Volatile keyword is to deal with Java Memory Model cache coherent challenges:
     * To ensure that updates to variables propagate predictably to other threads, we should apply the volatile modifier to those variables.
     * This way, we can communicate with runtime and processor to not reorder any instruction involving the volatile variable.
     * Also, processors understand that they should immediately flush any updates to these variables so that other threads could read the shared variables most up-to-date values.
     */
    private static int number;
    private volatile static boolean ready;

    private static class Reader extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " thread says, ready = " + ready);
            while (!ready) {
                System.out.println(Thread.currentThread().getName() + " is yielding now..");
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + " thread says, number = " + number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " thread started now...");
        new Reader().start();
        System.out.println(Thread.currentThread().getName() + " thread is running now...");
        number = 42;
        Thread.sleep(6);
        System.out.println(Thread.currentThread().getName() + " thread finishes sleeping.");
        ready = true;
        System.out.println(Thread.currentThread().getName() + " thread finished.");
    }
}
