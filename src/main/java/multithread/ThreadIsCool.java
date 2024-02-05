package multithread;

/**
 * This is a cool and small program to show that threads run order could be controlled by using Thread.join() method.
 */

public class ThreadIsCool implements Runnable {

    public static void main(String[] args) {
        ThreadIsCool threadIsCool = new ThreadIsCool();
        Thread thread1 = new Thread(threadIsCool);
        Thread thread2 = new Thread(threadIsCool);
        Thread thread3 = new Thread(threadIsCool);
        Thread thread4 = new Thread(threadIsCool);
        thread1.setName("Thread 1");
        thread2.setName("Thread 2");
        thread3.setName("Thread 3");
        thread4.setName("Thread 4");
        System.out.println("Now all the threads are about to kick off:");

        thread1.start();
        try {
            /* Wait for this thread to die before other invocations*/
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
        try {
            /* Wait for this thread to die before other invocations*/
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread3.start();
        try {
            /* Wait for this thread to die before other invocations*/
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread4.start();
        try {
            /* Wait for this thread to die before other invocations*/
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Now the Program ended.");
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is running!");
        }
        System.out.println(Thread.currentThread().getName() + " is sleeping for 1 second");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " run finished.");
    }

}
