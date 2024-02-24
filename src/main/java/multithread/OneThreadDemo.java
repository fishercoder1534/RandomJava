package multithread;

public class OneThreadDemo {
    static class MeaninglessClass {
        public void meaninglessMethod() {
            System.out.println("In meaninglessMethod method now, current thread name is: " + Thread.currentThread().getName());
        }
    }

    public static void main(String... args) {
        /**This is to show that this program will guarantee to run in just one thread: main, there's no multi-threading here.*/
        MeaninglessClass meaninglessClass = new MeaninglessClass();
        meaninglessClass.meaninglessMethod();
        meaninglessClass.meaninglessMethod();
        meaninglessClass.meaninglessMethod();
        meaninglessClass.meaninglessMethod();
        meaninglessClass.meaninglessMethod();
        System.out.println("Program finished.");
    }
}
