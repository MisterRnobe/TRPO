import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Starter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXX");//.withLocale(Locale.US);

    public static void main(String[] args){
        String date = "2018-02-14T20:18:00+0900";
        ZonedDateTime dateTime = ZonedDateTime.parse(date, formatter);
        System.out.println(dateTime);
        System.out.println(DateTimeFormatter.ISO_ZONED_DATE_TIME.toString());
        System.out.println(formatter.toString());
    }

}
