package lab3.servlet;

import lab3.data.Person;
import lab3.data.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lab3.servlet.OutputServlet.getFile;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        List<String> file = getFile("delete");
        List<String> table = getTable();
        file.addAll(42 ,table);
        resp.getWriter().write(file.stream().collect(Collectors.joining("\n")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterMap().get("ids[]");
        Storage.getInstance().removeAll(
                Arrays.stream(ids).mapToInt(Integer::parseInt).toArray()
        );
    }

    private static List<String> getTable()
    {
        List<String> strings = new ArrayList<>();
        for(Map.Entry<Integer, Person> e: Storage.getInstance().getPeople().entrySet())
        {
            Person p = e.getValue();
            strings.add("<tr>");
            strings.add("<td>"+p.getName()+"</td>");
            strings.add("<td>"+p.getSurname()+"</td>");
            strings.add("<td>"+p.getPatronymic()+"</td>");
            strings.add("<td>"+p.getAge()+"</td>");
            strings.add("<td>"+(p.getSex()? "Male":"Female")+"</td>");
            strings.add("<td><input type=\"checkbox\" name = \""+e.getKey()+"\"></td>");
            strings.add("</tr>");
        }
        return strings;
    }
}
