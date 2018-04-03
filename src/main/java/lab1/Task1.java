package lab1;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Task1 {
    public static void main(String[] args){
        String input = "2018-01-31T13:00:00+0200";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXX");
        OffsetDateTime date = OffsetDateTime.parse(input,
                formatter);
        System.out.println("Current date: "+date.format(formatter));
        //Next day
        date = date.minusHours(date.getHour()).minusMinutes(date.getMinute()).minusSeconds(date.getSecond());
        System.out.println("Next day is "+date.plusDays(1).format(formatter));
        //Start and end of this week
        int dayOfWeek = date.getDayOfWeek().getValue();
        System.out.println("Week starts with: " + date.minusDays(dayOfWeek - 1).format(formatter)+
                "\nWeek ends at: "+date.plusDays(7 - dayOfWeek).format(formatter));
        //Next month
        System.out.println("Next month is: "+date.minusDays(date.getDayOfMonth() - 1).plusMonths(1).format(formatter));
    }
}
