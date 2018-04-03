package lab3.servlet;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet
{
    private String greeting="Hello World";

    public HelloServlet()
    {
    }

    public HelloServlet(String greeting)
    {
        this.greeting=greeting;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        // response.getWriter().println("<h1>"+greeting+"</h1>");
        // response.getWriter().println("session=" + request.getSession(true).getId());
        // response.getWriter().println("pathInfo=" + request.getPathInfo());
        // response.getWriter().println("pathInfo=" + request.getPathInfo());

        //InputStream stream = servletContext.getResourceAsStream("src/main/webapp/WEB-INF/pages/hello.html");
        InputStream stream = new FileInputStream("src/main/webapp/WEB-INF/pages/hello.html");
        copyStream(stream, response.getOutputStream());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        System.out.println();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");


        response.getWriter().print(request.getParameter("field"));
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
