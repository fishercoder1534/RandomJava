package java_volatile;

public class TaskRunner {
    /**
     * This is following the examples from https://www.baeldung.com/java-volatile
     */
    private static int number;
    private static boolean ready;

    private static class Reader extends Thread {
        @Override
        public void run() {
            System.out.println("ready is: " + ready);
            while (!ready) {
                System.out.println("It's yielding now..");
                Thread.yield();
            }
            System.out.println("number is: " + number);
        }
    }

    public static void main(String[] args) {
        System.out.println("Program started.");
        new Reader().start();
        number = 42;
        ready = true;
        System.out.println("Program finished.");
    }
}
