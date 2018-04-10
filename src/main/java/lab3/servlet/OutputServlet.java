package lab3.servlet;

import lab3.data.Person;
import lab3.data.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OutputServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        List<String> file = getFile("output");
        List<String> table = getTable();
        file.addAll(16 ,table);
        resp.getWriter().write(file.stream().collect(Collectors.joining("\n")));
    }
    public static List<String> getFile(String file)
    {
        List<String> list = new ArrayList<>();
        try(Scanner scanner = new Scanner(new FileInputStream("src/main/webapp/WEB-INF/pages/"+file+".html")))
        {
            while (scanner.hasNextLine())
                list.add(scanner.nextLine());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    private static List<String> getTable()
    {
        List<String> strings = new ArrayList<>();
        for(Map.Entry<Integer, Person> e: Storage.getInstance().getPeople().entrySet())
        {
            Person p = e.getValue();
            strings.add("<tr>");

            strings.add("<td>"+e.getKey()+"</td>");
            strings.add("<td>"+p.getName()+"</td>");
            strings.add("<td>"+p.getSurname()+"</td>");
            strings.add("<td>"+p.getPatronymic()+"</td>");
            strings.add("<td>"+p.getAge()+"</td>");
            strings.add("<td>"+(p.getSex()? "Male":"Female")+"</td>");
            strings.add("</tr>");
        }
        return strings;
    }
}
