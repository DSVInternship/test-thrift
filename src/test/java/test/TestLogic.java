package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import example.rest.response.ProfileResponse;
import example.rest.response.ProfileResultResponse;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

public class TestLogic {

    @Test
    public void getProfile() {
        String url = "http://localhost:9000/api/profile/1";

        ProfileResultResponse response = httpGet(url);
        List<ProfileResponse> profiles = new ArrayList<ProfileResponse>();
        profiles.add(new ProfileResponse(1l, "Ac", 200));
        ProfileResultResponse expected = new ProfileResultResponse();
        expected.setErr(0);
        expected.setProfile(profiles);
        assertThat(0l, is(response.getErr()));
        assertThat(expected, is(response));
    }

    @Test
    public void getNullProfile() {
        String url = "http://localhost:9000/api/profile/34634562345124";
        ProfileResultResponse response = httpGet(url);
        ProfileResultResponse expected = new ProfileResultResponse();
        expected.setErr(-1);
        // expected.setProfile(profiles);
        assertThat(-1l, is(response.getErr()));
        assertThat(expected, is(response));
    }

    public ProfileResultResponse httpGet(String url) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");

            com.google.gson.Gson gson = new com.google.gson.Gson();
            ProfileResultResponse response = gson.fromJson(json, ProfileResultResponse.class);
            return response;
        } catch (IOException ex) {
        }
        return null;
    }

    @Test
    public void testInsertUpdateDelete() throws UnsupportedEncodingException, IOException, InterruptedException {
        String url = "http://localhost:9000/api/profile";
        com.google.gson.Gson gson = new com.google.gson.Gson();
        ProfileResponse mock = new ProfileResponse(1234567l, "zxcv", 2000);
        String mockString = gson.toJson(mock, ProfileResponse.class);

        // create 
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost requestCreate = new HttpPost(url);
        StringEntity params = new StringEntity(mockString);
        requestCreate.addHeader("content-type", "application/json");
        requestCreate.setEntity(params);
        HttpResponse result = httpClient.execute(requestCreate);
        String postRes = EntityUtils.toString(result.getEntity(), "UTF-8");
        Thread.sleep(1000);
        System.out.println("postRes");
        // test create 
        ProfileResultResponse resultCreate = httpGet(url + "/1234567");
        ProfileResultResponse expectedCreate = new ProfileResultResponse();
        expectedCreate.setErr(0);
        expectedCreate.setProfile(Arrays.asList(mock));

        assertThat(expectedCreate, is(resultCreate));

        // update 
        mock.setName("update name");
        mockString = gson.toJson(mock, ProfileResponse.class);
        HttpPut requestUpdate = new HttpPut(url + "/1234567");
        StringEntity paramsUpdate = new StringEntity(mockString);
        requestUpdate.addHeader("content-type", "application/json");
        requestUpdate.setEntity(paramsUpdate);
        httpClient.execute(requestUpdate);
        Thread.sleep(1000);

        // test update
        ProfileResultResponse resultUpdate = httpGet(url + "/1234567");
        ProfileResultResponse expectedUpdate = new ProfileResultResponse();
        expectedUpdate.setErr(0);
        expectedUpdate.setProfile(Arrays.asList(mock));

        assertThat(expectedUpdate, is(resultUpdate));

        // delete 
        HttpDelete requestDelete = new HttpDelete(url + "/1234567");
        httpClient.execute(requestDelete);
        Thread.sleep(1000);

        //test deletes
        ProfileResultResponse resultDelete = httpGet(url + "/1234567");
        ProfileResultResponse expectedDelete = new ProfileResultResponse();
        expectedDelete.setErr(-1);
        // expected.setProfile(profiles);
        assertThat(-1l, is(resultDelete.getErr()));
        assertThat(resultDelete, is(expectedDelete));
    }
}
