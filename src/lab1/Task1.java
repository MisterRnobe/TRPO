package lab1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args){
        SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ssZ");
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
        System.out.println("Next day is: ");
    }
}
