package multithread;

/** This is a cool and small program to show that threads don't run in the order that you can control, it's all scheduled by the thing called
 * Thread Scheduler.*/

public class ThreadIsCool implements Runnable{

	public static void main(String [] args){
		ThreadIsCool threadIsCool = new ThreadIsCool();
		Thread thread1 = new Thread(threadIsCool);
		Thread thread2 = new Thread(threadIsCool);
		Thread thread3 = new Thread(threadIsCool);
		thread1.setName("Thread Andrew");
		thread2.setName("Thread James");
		thread3.setName("Thread Steve");
		System.out.println("Now the three threads kick off:");
		
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
		System.out.println("Now the Program ended.");
	}
	
	@Override
	public void run(){
		for(int i = 0; i < 10; i++){
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " is running!");
		}
		System.out.println(Thread.currentThread().getName() + " is sleeping for 3 seconds");
		try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		System.out.println(Thread.currentThread().getName() + " run finished.");
	}
	
}
