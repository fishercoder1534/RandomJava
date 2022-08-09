package multithread;

/**
 * The ThreadLocal class in Java enables you to create variables that can only
 * be read and written by the same thread. Thus, even if two threads are
 * executing the same code, and the code has a reference to a ThreadLocal
 * variable, then the two threads cannot see each other's ThreadLocal variables.
 * <p>
 * Since values set on a ThreadLocal object only are visible to the thread who
 * set the value, no thread can set an initial value on a ThreadLocal using
 * set() which is visible to all threads.
 * Instead you can specify an initial value for a ThreadLocal object by
 * subclassing ThreadLocal and overriding the initialValue() method. Here is how
 * that looks:
 * <p>
 * private ThreadLocal myThreadLocal = new ThreadLocal<String>() {
 *
 * @Override protected String initialValue() { return "This is the initial value"; }
 * };
 */

public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            Integer value = (int) (Math.random() * 100D);
            System.out.println("value = " + value);
            threadLocal.set(value);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadLocal.get() is: " + threadLocal.get());
        }

    }

    public static void main(String... args) {
        System.out.println("Program started.");
        MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread t1 = new Thread(sharedRunnableInstance);
        Thread t2 = new Thread(sharedRunnableInstance);
        t1.start();
        t2.start();
        System.out.println("Program finished.");
    }

}
