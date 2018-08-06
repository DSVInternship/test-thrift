package example.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

public class JettyServer {

    private final Logger logger = LoggerFactory.getLogger(JettyServer.class);

    public void startServer() {
        int PORT = 9000;
        Server server = new Server(PORT);

        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);

        servletContextHandler.setContextPath("/");
        server.setHandler(servletContextHandler);

        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, "/api/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(
                "jersey.config.server.provider.packages",
                "example.rest"
        );
        
        //servletHolder.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        try {
            server.start();
            System.out.println("start jetty server at port: " + PORT);
            server.join();
        } catch (Exception ex) {
            logger.error("Error occurred while starting Jetty", ex);
            System.exit(1);
        } finally {
            server.destroy();
        }
    }
}
