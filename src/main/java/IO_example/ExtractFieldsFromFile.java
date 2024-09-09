package IO_example;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtractFieldsFromFile {
    private static final String BASE_FILE = "/Users/Downloads/base_file";
    private static final String UPDATE_FILE = "/Users/Downloads/update_file";

    public static void main(String... args) throws IOException {
        System.out.println("Program started.");
        readAllFieldNames("");
        Set<String> baseSet = readAllFieldNames(BASE_FILE);
        Set<String> updateFileSet = readAllFieldNames(UPDATE_FILE);
        Set<String> baseSetCopy = new HashSet<>(baseSet);
        baseSetCopy.removeAll(updateFileSet);
        System.out.println("baseSetCopy size after removing updateFileSet is: " + baseSetCopy.size());

        Set<String> linesOnlyExistInBaseSet = readLinesMatchingSet(BASE_FILE, baseSetCopy);
//        linesOnlyExistInBaseSet.forEach(System.out::println);
        System.out.println("Found a total of " + linesOnlyExistInBaseSet.size() + " matches.");

        appendLinesToFile(UPDATE_FILE, linesOnlyExistInBaseSet);

        System.out.println("Program finished.");
    }

    private static void appendLinesToFile(String updateFile, Set<String> linesToBeAppended) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(updateFile, true));
        bufferedWriter.append("\n\n\n");
        bufferedWriter.append("#Added below fields from base file ---------\n");
        for (String str : linesToBeAppended) {
            bufferedWriter.append(str);
            bufferedWriter.append("\n");
        }
        bufferedWriter.close();
    }

    private static Set<String> readLinesMatchingSet(String filePath, Set<String> set) throws FileNotFoundException {
        if (filePath.isEmpty()) {
            System.out.println("No file to read, exit.");
            return null;
        }
        Scanner scanner = new Scanner(new File(filePath));
        scanner.useDelimiter("\n");
        Set<String> lines = new HashSet<>();
        int i = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            i++;
            if (!line.isEmpty() && Character.isAlphabetic(line.charAt(0))) {
                String[] parts = line.split("=");
                if (set.contains(parts[0])) {
                    lines.add(line);
                }
            }
        }
        scanner.close();
        System.out.println("A total of " + i + " lines were gone through, and found a total of " + lines.size() + " matches.");
        return lines;
    }

    public static Set<String> readAllFieldNames(String filePath) throws IOException {
        if (filePath.isEmpty()) {
            System.out.println("No file to read, exit.");
            return null;
        }
        Scanner scanner = new Scanner(new File(filePath));
        scanner.useDelimiter("\n");

        assertTrue(scanner.hasNext());
        int i = 0;
        int nonEmptyLines = 0;
        Set<String> fields = new HashSet<>();
        while (scanner.hasNext()) {
            String line = scanner.next();
            i++;
            if (!line.isEmpty() && Character.isAlphabetic(line.charAt(0))) {
                String[] parts = line.split("=");
                fields.add(parts[0]);
                nonEmptyLines++;
            }
        }
        System.out.println("For this file: " + filePath + ": A total of " + i + " lines, in which " + nonEmptyLines + " are non empty.");

        scanner.close();
        return fields;
    }
}
