package example.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.thrift.TException;
import org.glassfish.jersey.server.ManagedAsync;

import com.sun.research.ws.wadl.Application;

import example.client.ProfileClient;
import example.model.Job;
import example.model.QueingModel;
import example.thrift.Profile;
import example.util.LRU;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.PathParam;

@Path("/profile")
public class ProfileREST {

    ProfileClient profileClient = new ProfileClient();

    @GET
    @Path("/available")
    @Produces(MediaType.TEXT_PLAIN)
    public String available() {
        return "yes";
    }

//    @GET
//    @Path("/add")
//    public String add(@QueryParam("a") int a,
//            @QueryParam("b") int b) throws TException, InterruptedException {
//        QueingModel queingModel = QueingModel.getInstance();
//        Profile dto = new Profile(a,"nhat", b);
//        Job job = new Job("add", dto);
//        queingModel.putJob(job);
//        return "Sent!";
//    }
//
//    @GET
//    @Path("/multiply")
//    public String multiply(@QueryParam("a") int a,
//            @QueryParam("b") int b) throws TException, InterruptedException {
//        QueingModel queingModel = QueingModel.getInstance();
//        Profile dto = new Profile(a,"nhat", b);
//        Job job = new Job("multiply", dto);
//        queingModel.putJob(job);
//        return "Sent!";
//    }
    @GET
    @Path("/{id}")
    public String get(@PathParam("id") long id) throws TException, InterruptedException {
        Profile profile = null; //profileClient.get(id);
        LinkedList<Profile> cache = LRU.getInstace().getCache();
        // neu tim thay trong cache
        for (Profile tmp : cache) {
            if (tmp.getId() == id) {
                System.out.println("lay tu LRU cache");
                profile = tmp;
                LRU.getInstace().refer(tmp); // cap nhat lai vi tri cua tmp
                break;
            }
        }

        if (profile == null) {
            System.out.println("lay tu database");
            profile = profileClient.get(id);
            LRU.getInstace().refer(profile);
        }
        if (profile != null) {
            return profile.toString();
        } else {
            return "Not found!";
        }
    }

    @GET
    public String getAll() throws TException, InterruptedException {
        List<Profile> profile = profileClient.getAll();
        if (profile != null) {
            return profile.toString();
        } else {
            return "Not found!";
        }
    }

    @POST
    public String add(@FormParam("name") String name, @FormParam("age") int age)
            throws TException, InterruptedException {
        QueingModel queingModel = QueingModel.getInstance();
        Profile dto = new Profile(System.currentTimeMillis(), name, age);
        Job job = new Job("profile.insert", dto);
        queingModel.putJob(job);
        return "Sent!";
    }

    @POST
    @Path("/test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public String add(Profile x) throws TException, InterruptedException {
        if (x != null) {
            return x.toString();
        } else {
            return "null object";
        }
    }

//    @GET
//    @Path("/insert")
//    public String add(@QueryParam("name") String name,
//            @QueryParam("age") int age) throws TException, InterruptedException {
//        QueingModel queingModel = QueingModel.getInstance();
//        Profile dto = new Profile(System.currentTimeMillis(), name, age);
//        Job job = new Job("profile.insert", dto);
//        queingModel.putJob(job);
//        return "Sent!";
//    }
    /**
     * truong hop profile duoc luu tren LRU va bi goi update => phai update lai
     * profile tren ca database va LRU.
     */
    @PUT
    @Path("/{id}")
    public String update(@PathParam("id") long id, @FormParam("name") String name, @FormParam("age") int age)
            throws TException, InterruptedException {
        QueingModel queingModel = QueingModel.getInstance();
        Profile oldDto = profileClient.get(id);
        Profile updatedDto = new Profile(id, name, age);
        LRU.getInstace().refer(oldDto, updatedDto);
        Job job = new Job("profile.update", updatedDto);
        queingModel.putJob(job);
        return "Sent!";
    }

    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") long id) throws TException, InterruptedException {
        Profile oldDto = profileClient.get(id);
        LRU.getInstace().remove(oldDto);
        QueingModel queingModel = QueingModel.getInstance();
        Job job = new Job("profile.remove", id);
        queingModel.putJob(job);
        return "Sent!";
    }
}
