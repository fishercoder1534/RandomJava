package designPatterns.singleton_pattern;

/**
 * Created by stevesun on 10/14/17.
 */
public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    public static ThreadSafeSingleton getInstance() {
        /**
         * Double checked locking principle
         *
         * This is the most optimal approach in singleton pattern:
         * https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
         * 1. It uses lazy initialization (saves resources, such as database connections.)
         * 2. It's thread safe, but also minimizes performance penalty (minimizes the smallest code snippet possible,
         * not synchronizing the whole method.)
         * */
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    public static void work() {
        System.out.println("One instance is here!!!");
    }
}
