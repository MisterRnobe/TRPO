package lab1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task1 {
    public static void main(String[] args){

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Scanner s = new Scanner(System.in);
        Date d = null;
        while (d == null)
        {
            try{
                d = date.parse(s.nextLine());
            } catch (ParseException e) {
                System.out.println("Wrong date format! Try again.");
            }
        }
        System.out.println("OK");
        System.out.println("Parsed: "+date.format(d));
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        //Next day
        Calendar cal1 = (Calendar) cal.clone();
        cal1.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println("Next day is: "+date.format(cal1.getTime()));
        //Beginning and end of the week
        Calendar cal2 = (Calendar) cal.clone();
        int dayOfWeek = cal2.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0)
            dayOfWeek = 7;
        cal2.add(Calendar.DAY_OF_YEAR, -(dayOfWeek - 1));
        System.out.println("The specified week stars at: "+date.format(cal2.getTime()));
        cal2.add(Calendar.DAY_OF_YEAR, 6);
        System.out.println("The specified week ends at: "+date.format(cal2.getTime()));
        //Next month
        Calendar cal3 = (Calendar) cal.clone();
        cal3.add(Calendar.MONTH, 1);
        cal3.add(Calendar.DAY_OF_YEAR, -(cal3.get(Calendar.DAY_OF_MONTH) - 1));
        System.out.println("Next month start at: "+date.format(cal3.getTime()));

    }
}
