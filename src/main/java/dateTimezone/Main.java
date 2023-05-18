package dateTimezone;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class Main {

    public static void main(String... args) {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("currentTime is: " + currentTime);
        LocalDateTime utcTime = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println("utcTime is: " + utcTime);
        //use below method to convert a LocalDateTime object to its UTC version
        LocalDateTime utcTimeConverted = currentTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime(); // UTC
        System.out.println("utcTimeConverted is: " + utcTimeConverted);
    }
}
