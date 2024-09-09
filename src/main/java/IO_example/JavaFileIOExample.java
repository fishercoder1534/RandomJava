package IO_example;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaFileIOExample {

    public static void main(String... args) throws IOException {
        System.out.println("Program started.");
        readFileOnDisk();
        findUniqueCityNames();
        System.out.println("Program finished.");
    }

    public static void readFileOnDisk() throws IOException {
        String file = "src/test/resources/sample_input.txt";
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(" ");

        assertTrue(scanner.hasNext());
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }

        scanner.close();
    }

    private static void findUniqueCityNames() throws IOException {
        String file = "src/test/resources/city_names.csv";
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(",");
        Map<String, Integer> map = new HashMap<>();
        while (scanner.hasNext()) {
            String city = scanner.next();
            map.put(city, map.getOrDefault(city, 0) + 1);
        }
        scanner.close();
        System.out.println("Unique city names are: ");
        for (String city : map.keySet()) {
            if (map.get(city) == 1) {
                System.out.println(city);
            }
        }
    }
}
