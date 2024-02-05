package java_collections;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {

    private static final int NUMBER_OF_SET_ENTRIES = 2;

    public static void main(String... args) {
        System.out.println("Program started.");
        HashSetDemo hashSetDemo = new HashSetDemo();
        hashSetDemo.understandHashSetInternalWorkings();
        System.out.println("Program finished.");
    }

    private void understandHashSetInternalWorkings() {
        /**
         * 1. Internally, Java uses a HashMap to implement HashSet, it just inserts a dummy object as value into the map: private static final Object PRESENT = new Object();
         *      you can step into the java.util.HashSet library to see this:
         *      public boolean add(E e) {
         *         return map.put(e, PRESENT)==null;
         *     }
         * 2. https://medium.com/javarevisited/internal-working-of-hashset-in-java-interview-question-129bdd31fc60 for more references/
         *     */
        Set<String> set = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_SET_ENTRIES; i++) {
            set.add(i + "");
        }
        System.out.println("Method finishes.");
    }
}
