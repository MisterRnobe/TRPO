package lab1;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    private static final String dateRegex = "[0-9]{4}-((1[02]|0[13578])-(0[1-9]|[12][0-9]|3[0-1])|(0[469]|11)-(0[1-9]|1[0-9]|2[0-9]|30)|02-(0[1-9]|1[0-9]|2[0-8]))";
    public static void main(String[] args)
    {
        System.out.println("Enter:\n\t1 - to check date\n\t2 - to check date and time\n\t3 - to check mail");
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter string: ");
        String text = scanner.nextLine();
        switch (n) {
            case 1: checkDate(text); break;
            case 2: checkDateAndTime(text); break;
            case 3: checkMail(text); break;
        }
        scanner.close();
    }
    private static void checkDate(String text)
    {
        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(text);
        while(matcher.find())
            System.out.println("Found: "+matcher.group(0));
    }
    private static void checkDateAndTime(String text)
    {
        String dateTimeRegex = dateRegex +
                "T([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])";
        Pattern pattern = Pattern.compile(dateTimeRegex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find())
            System.out.println("Found: "+matcher.group(0));
    }
    private static void checkMail(String text)
    {
        String regex = "[-\\w.]+@[A-z0-9][-A-z0-9]+\\.([A-z0-9][-A-z0-9]+\\.)*[A-z]{2,4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find())
            System.out.println("Found: " + matcher.group(0));
    }
}
