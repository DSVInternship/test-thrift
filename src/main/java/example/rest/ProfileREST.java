package example.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.thrift.TException;

import example.client.ProfileClient;
import example.model.Job;
import example.model.QueingModel;
import example.rest.response.ProfileResponse;
import example.rest.response.ProfileResultResponse;
import example.thrift.ErrorType;
import example.thrift.Profile;
import example.thrift.TGetProfileResult;
import example.util.LRU;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

@Path("/profile")
public class ProfileREST {

    ProfileClient profileClient = new ProfileClient();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProfileResultResponse get(@PathParam("id") long id) throws TException, InterruptedException, Exception {
        Profile profile = null; //profileClient.get(id);
        TGetProfileResult tProfileResult = null;
        LinkedList<Profile> cache = LRU.getInstace().getCache();
        // neu tim thay trong cache
        for (Profile tmp : cache) {
            if (tmp.getId() == id) {
                System.out.println("class ProfileRest: lay tu LRU cache");
                profile = tmp;
                tProfileResult = new TGetProfileResult();
                tProfileResult.setErr(ErrorType.SUCCESS.getValue());
                tProfileResult.setProfile(Arrays.asList(profile));
                LRU.getInstace().refer(tmp); // cap nhat lai vi tri cua tmp
                break;
            }
        }

        if (profile == null) {
            System.out.println("class ProfileRest: lay tu database");
            tProfileResult = profileClient.get(id);
            if (tProfileResult.getErr() == ErrorType.SUCCESS.getValue()) {
                LRU.getInstace().refer(tProfileResult.getProfile().get(0));
            }
        }

        return ProfileResultResponse.parseFromTGetProfileResult(tProfileResult);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ProfileResultResponse getAll() throws TException, InterruptedException, Exception {
        TGetProfileResult profile = profileClient.getAll();
        ProfileResultResponse res = new ProfileResultResponse();
        res.setErr(profile.getErr());
        List<ProfileResponse> profileResponses = new ArrayList<ProfileResponse>();
        for (Profile x : profile.getProfile()) {
            profileResponses.add(new ProfileResponse(x.getId(), x.getName(), x.getAge()));
        }
        res.setProfile(profileResponses);
        return res;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(ProfileResponse dtoRequest)
            throws TException, InterruptedException {
        QueingModel queingModel = QueingModel.getInstance();
        Profile dto = new Profile(dtoRequest.getId() == 0 ? System.currentTimeMillis() : dtoRequest.getId(), dtoRequest.getName(), dtoRequest.getAge());
        Job job = new Job("profile.insert", dto);
        queingModel.putJob(job);
        return "Sent!";
    }

    /**
     * truong hop profile duoc luu tren LRU va bi goi update => phai update lai
     * profile tren ca database va LRU.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String update(@PathParam("id") long id, ProfileResponse dtoRequest)
            throws TException, InterruptedException, Exception {
        QueingModel queingModel = QueingModel.getInstance();
        TGetProfileResult oldResultResponse = profileClient.get(id);
        if(oldResultResponse.getErr() == ErrorType.NOT_FOUND.getValue())
            return "Item is not exist to update";
        Profile oldDto = oldResultResponse.getProfile().get(0);
        Profile updatedDto = new Profile(id, dtoRequest.getName(), dtoRequest.getAge());
        LRU.getInstace().refer(oldDto, updatedDto);
        Job job = new Job("profile.update", updatedDto);
        queingModel.putJob(job);
        return "Sent!";
    }

    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") long id) throws TException, InterruptedException, Exception {
        TGetProfileResult oldResultResponse = profileClient.get(id);
        if(oldResultResponse.getErr() == ErrorType.NOT_FOUND.getValue())
            return "Item is not exist to delete";
        Profile oldDto = oldResultResponse.getProfile().get(0);
        LRU.getInstace().remove(oldDto);
        QueingModel queingModel = QueingModel.getInstance();
        Job job = new Job("profile.remove", id);
        queingModel.putJob(job);
        return "Sent!";
    }
}
