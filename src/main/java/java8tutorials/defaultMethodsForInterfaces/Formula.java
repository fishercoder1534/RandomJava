package java8tutorials.defaultMethodsForInterfaces;

/**
 * Created by stevesun on 4/16/17.
 */
public interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
