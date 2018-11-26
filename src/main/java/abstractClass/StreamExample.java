package abstractClass;

import java.util.ArrayList;
import java.util.List;

/**
 * created by stevesun on 11/26/18/.
 */
public class StreamExample {
  public static void main(String... args) {

    /**This simple example shows that it's safe to run stream operation on an empty list.*/
    List<Integer> list = new ArrayList<>();
    list.stream()
        .map(i -> i + 1);
    System.out.println("Finished.");
  }
}
