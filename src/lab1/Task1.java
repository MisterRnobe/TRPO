package lab1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) throws Exception{
        doTask();
        //2013-02-25T18:25+0300
    }
    static void doTask() throws ParseException
    {
        LocalDate date = LocalDate.parse("2011-12-03T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        System.out.println(date.format());
    }
    static void test() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        Date date = dateFormat.parse("01-02");
        System.out.println(dateFormat.format(date));
    }
}
