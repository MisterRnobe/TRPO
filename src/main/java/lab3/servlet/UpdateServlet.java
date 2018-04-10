package lab3.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lab3.data.Person;
import lab3.data.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static lab3.servlet.OutputServlet.getFile;

public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        List<String> file = getFile("update");
        List<String> table = getTable();
        file.addAll(61 ,table);
        resp.getWriter().write(file.stream().collect(Collectors.joining("\n")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        Map<String, String[]> m = req.getParameterMap();
        Map<String,JSONArray> map = JSON.parseObject(m.get("data")[0], Map.class);
        map.entrySet().stream().map(e -> new AbstractMap.SimpleEntry<>(
                e.getKey(), e.getValue().toArray(new String[5]))).forEach(
                        e-> Storage.getInstance().update(Integer.parseInt(e.getKey()),
                                Person.createOf(e.getValue()))
        );
        //collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

    }

    private static List<String> getTable()
    {
        List<String> strings = new ArrayList<>();

        for(Map.Entry<Integer, Person> e: Storage.getInstance().getPeople().entrySet())
        {
            Person p = e.getValue();
            int row = e.getKey();
            strings.add("<tr>");
            strings.add("<td><input id = \""+row+"1\" type=\"text\" size =\"20\" value=\""+p.getName()+"\"></td>");
            strings.add("<td><input id = \""+row+"2\" type=\"text\" size =\"20\" value=\""+p.getSurname()+"\"></td>");
            strings.add("<td><input id = \""+row+"3\" type=\"text\" size =\"20\" value=\""+p.getPatronymic()+"\"></td>");
            strings.add("<td><input id = \""+row+"4\" type=\"text\" size =\"20\" value=\""+p.getAge()+"\"></td>");
            strings.add("<td><input id = \""+row+"5\" type=\"text\" size =\"20\" value=\""+(p.getSex()? "Male":"Female")+"\"></td>");
            strings.add("<td><input type=\"checkbox\" name = \""+e.getKey()+"\"></td>");
            strings.add("</tr>");
        }
        return strings;
    }
}
