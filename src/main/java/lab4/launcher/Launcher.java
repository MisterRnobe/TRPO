package lab4.launcher;

import lab4.servlet.EventServlet;
import lab4.servlet.MainServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Launcher {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(80);
        server.addConnector(connector);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        server.setHandler(contextHandler);



        contextHandler.addServlet(EventServlet.class,"/event/");
        contextHandler.addServlet(MainServlet.class, "/");

        server.start();
        server.join();
    }
}
