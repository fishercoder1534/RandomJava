package java8_for_the_really_impatient;

import java.util.Arrays;
import java.util.stream.Stream;

public class Random1 {

  public static void main(String... args) {
    String[] arr1 = {"abc", "bcd", "cdef", "defgh"};
    String[] arr2 = {"af", "fg", "gh"};
    Stream<String> stream1 = Stream.of(arr1);
    Stream<String> stream2 = Stream.of(arr2);

    Stream<String> stream3 = Stream.concat(stream1.filter(x -> x.length() < 4), stream2);
    String[] arr = stream3.toArray(String[]::new);
    System.out.println(Arrays.toString(arr));
  }
}
