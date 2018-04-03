package lab3.launcher;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

/**
 * Starts jetty-server on the specified port
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        int port = 80;
        try {
            if (args.length > 0) {
                port = Integer.parseInt(args[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Server server = new Server(port);

        ProtectionDomain domain = Launcher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.setContextPath("/srv");
//        context.setResourceBase(location.toExternalForm());
//        context.addServlet(new ServletHolder(new HelloServlet()),"/*");
        //server.setHandler(context);

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/app");
        webapp.setWar(location.toExternalForm());
        webapp.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        //server.setHandler(webapp);

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { webapp });
        server.setHandler(contexts);

        server.start();
        server.join();
    }
}
