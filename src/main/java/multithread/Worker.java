package multithread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stevesun on 1/29/17.
 */
public class Worker implements Runnable {
    public boolean running = false;

    @Override
    public void run() {
        this.running = true;
        System.out.println("Thread " + Thread.currentThread().getName() + " is running.");

        try {
            //this pauses this spawned thread to sleep for 5 seconds.
            System.out.println("Thread " + Thread.currentThread().getName() + " sleeps for 3 seconds");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            //an InterruptedException should never be swallowed
            Thread.currentThread().interrupt();
        }
        this.running = false;
        System.out.println("Thread " + Thread.currentThread().getName() + " is no longer running.");
    }


    public static void main(String... args) throws InterruptedException {
        Worker mainThreadWorker = new Worker();
        List<Thread> workers = new ArrayList<>();

        System.out.println("Main thread is running with thread ID: " + Thread.currentThread().getName());

        Date start = new Date();

        //let's start 10 workers
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
            Thread thread = new Thread(mainThreadWorker);
            thread.setName(i + "");
            workers.add(thread);
            thread.start();

            //with this join() method, all 10 threads will be invoked in sequence, without it, all 10 threads will run in parallel.
            //thread.join();
        }
        System.out.println("There's a total of " + workers.size() + " threads.");

        //now we need to force the MAIN thread to wait until all Worker threads end, so we could calculate the duration
        for (Thread worker : workers) {
            System.out.println("Checking thread " + worker.currentThread().getName() + " now.");
            System.out.println("worker.isAlive() = " + worker.isAlive());
            while (worker.isAlive()) {
                Thread.sleep(1000);
            }
        }

        Date end = new Date();

        Long duration = end.getTime() - start.getTime();

        System.out.println("This whole process took " + duration / 1000 + " seconds.");
    }
}
