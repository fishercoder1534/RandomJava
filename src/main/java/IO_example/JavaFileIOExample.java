package IO_example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaFileIOExample {

    public static void main(String... args) throws IOException {
        System.out.println("Program started.");
        readFileOnDisk();
        System.out.println("Program finished.");
    }

    private static void readFileOnDisk() throws IOException {
        String file = "src/test/resources/sample_input.txt";
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(" ");

        assertTrue(scanner.hasNext());
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }

        scanner.close();
    }
}
