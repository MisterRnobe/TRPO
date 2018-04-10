package lab3.servlet;

import com.alibaba.fastjson.JSON;
import lab3.data.Person;
import lab3.data.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        InputStream stream = new FileInputStream("src/main/webapp/WEB-INF/pages/input.html");
        copyStream(stream, response.getOutputStream());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //response.setContentType("application/json");
        //System.out.println();
        //response.setStatus(HttpServletResponse.SC_OK);
        Map<String, String> person = request.getParameterMap().entrySet().stream().
                collect(Collectors.toMap(Map.Entry::getKey, e1->e1.getValue()[0]));

        Storage.getInstance().addPerson(new Person(person));
    }

    public static void copyStream(InputStream input, OutputStream output)
            throws IOException
    {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine())
        {
            builder.append(scanner.nextLine());
            builder.append("\n");
        }
        output.write(builder.toString().getBytes());
    }
}
