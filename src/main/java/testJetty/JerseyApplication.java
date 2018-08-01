package testJetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;


import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;
public class JerseyApplication {
	 private static final Logger logger = LoggerFactory.getLogger(JerseyApplication.class);

	    public static void main(String[] args) {
	    	int PORT = 8080;
	        Server server = new Server(PORT);

	        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);

	        servletContextHandler.setContextPath("/");
	        server.setHandler(servletContextHandler);

	        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, "/api/*");
	        servletHolder.setInitOrder(0);
	        servletHolder.setInitParameter(
	                "jersey.config.server.provider.packages",
	                "testJetty.rest"
	        );

	        try {
	            server.start();
	            System.out.println("server start at port: " +  PORT);
	            server.join();
	        } catch (Exception ex) {
	            logger.error("Error occurred while starting Jetty", ex);
	            System.exit(1);
	        }

	        finally {
	            server.destroy();
	        }
	    }
}
