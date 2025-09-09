package dateTimezone;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String... args) {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("currentTime is: " + currentTime);
        LocalDateTime utcTime = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println("utcTime is: " + utcTime);
        //use below method to convert a LocalDateTime object to its UTC version
        LocalDateTime utcTimeConverted = currentTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime(); // UTC
        System.out.println("utcTimeConverted is: " + utcTimeConverted);

        convertDateStringIntoALong();
    }

    private static void convertDateStringIntoALong() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // Parse the string into a LocalDate
        LocalDate localDate = LocalDate.parse("20250812", formatter);
        // Use a specific time zone, e.g., system default or UTC
        ZoneId zoneId = ZoneId.systemDefault(); // Or ZoneId.of("UTC")
        Instant instant = localDate.atStartOfDay(zoneId).toInstant();
        long milliseconds = instant.toEpochMilli();

        System.out.println("milliseconds is: " + milliseconds);
    }
}
