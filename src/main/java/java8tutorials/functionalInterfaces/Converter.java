package java8tutorials.functionalInterfaces;

/**
 * Created by stevesun on 4/16/17.
 */
public interface Converter<F, T> {
    T convert(F from);
}
