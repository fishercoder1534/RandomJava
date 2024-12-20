package randomExamples;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class OpenCSVExample {
    /**
     * This is a good example to show that this popular CSV library that can handle fields that contain comma in CSV files well.
     * Look at this file: values_with_comma_inside.csv, some fields have comma in them, which are enclosed with double quotes,
     * if you use string.split(","), that field will be broken into parts which is wrong,
     * whereas using this library will help take care of this case very well.
     */
    public static void main(String[] args) throws CsvValidationException {
        String file = "src/test/resources/values_with_comma_inside.csv";
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                System.out.println("nextLine.length is: " + nextLine.length);
                for (int i = 0; i < nextLine.length; i++) {
                    System.out.println("nextLine[" + i + "] is: " + nextLine[i]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
