package dateTimezone;

import java.time.LocalDateTime;
import java.util.Date;

public class DateVsLocalDateTime {
    public static void main(String... args) throws InterruptedException {
        Date startDate = new Date();
        LocalDateTime startLocalDateTime = LocalDateTime.now();
        System.out.println("date is: " + startDate);
        System.out.println("localDateTime is: " + startLocalDateTime);
        Thread.sleep(1000);
        Date endDate = new Date();
        LocalDateTime endLocalDateTime = LocalDateTime.now();
        System.out.println("date is: " + endDate);
        System.out.println("localDateTime is: " + endLocalDateTime);

        long duration1 = ((endDate.getTime() - startDate.getTime()) / 1000);
        long duration2 = (endLocalDateTime.getSecond() - startLocalDateTime.getSecond());
        System.out.println("duration1 is: " + duration1);
        System.out.println("duration2 is: " + duration2);
    }
}
