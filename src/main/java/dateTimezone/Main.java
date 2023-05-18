package dateTimezone;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Main {

    public static void main(String... args) {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("currentTime is: " + currentTime);
        LocalDateTime utcTime = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println("utcTime is: " + utcTime);
    }
}
