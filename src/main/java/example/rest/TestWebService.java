package example.rest;

import example.client.MultiplicationClient;
import example.model.Job;
import example.model.QueingModel;
import example.model.UserDTO;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import jersey.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.thrift.TException;
import org.glassfish.jersey.process.JerseyProcessingUncaughtExceptionHandler;
import org.glassfish.jersey.server.ManagedAsync;

@Path("/test")
public class TestWebService {

    MultiplicationClient thrifClient = new MultiplicationClient();

    @GET
    @Path("/available")
    @Produces(MediaType.TEXT_PLAIN)
    public String available() {
        return "yes";
    }

    @GET
    @Path("/add")
    public String add(@QueryParam("a") int a,
            @QueryParam("b") int b) throws TException, InterruptedException {
        QueingModel queingModel = QueingModel.getInstance();
        UserDTO dto = new UserDTO(a, b);
        Job job = new Job("add", dto);
        queingModel.putJob(job);
        return "Sent!";
    }

    @GET
    @Path("/multiply")
    public String multiply(@QueryParam("a") int a,
            @QueryParam("b") int b) throws TException, InterruptedException {
        QueingModel queingModel = QueingModel.getInstance();
        UserDTO dto = new UserDTO(a, b);
        Job job = new Job("multiply", dto);
        queingModel.putJob(job);
        return "Sent!";
    }

    @GET
    @Path("/async")
    @ManagedAsync
    public String testAsync(@QueryParam("a") int a,
            @QueryParam("b") int b) throws InterruptedException {
        // final AsyncResponse asyncResponse = suspended.take();
        QueingModel queingModel = QueingModel.getInstance();
        UserDTO dto = new UserDTO(a, b);
        Job job = new Job("add", dto);
        queingModel.putJob(job);
        return "Sent!";
    }
}
