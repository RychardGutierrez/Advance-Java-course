import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CalendarVsLocalDateTime {

    // Dates
    Calendar c = Calendar.getInstance();

    LocalDate d = LocalDate.of(2024, Month.JANUARY, 1);


    // Times
    LocalTime t = LocalTime.of(2, 30);

    public void dateTimeExmaple() {
        // DateTime it is use the own objects
        //   private final LocalDate date;
        //    /**
        //     * The time part.
        //     */
        //    private final LocalTime time;
        LocalDateTime dt = LocalDateTime.of(d, t);

        LocalDateTime lastWeek = dt.minusDays(6);

        String formatDate = dt.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(formatDate);
    }
}
