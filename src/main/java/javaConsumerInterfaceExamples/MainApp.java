package javaConsumerInterfaceExamples;

import java.util.function.Consumer;

public class MainApp {
    public static void main(String... args) {
        Consumer<String> print = x -> System.out.println(x);
        print.accept("java 8 consumer interface");   // java
    }
}
