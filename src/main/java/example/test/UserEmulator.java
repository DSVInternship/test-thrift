/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.test;

import example.rest.response.ProfileResultResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author root
 */
public class UserEmulator {

    public ProfileResultResponse httpGet(String url) {
         String json = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            json = EntityUtils.toString(result.getEntity(), "UTF-8");
            
            com.google.gson.Gson gson = new com.google.gson.Gson();
            ProfileResultResponse response = gson.fromJson(json, ProfileResultResponse.class);
            return response;
        } catch (IOException ex) {
            System.err.println(json);
        }
        return null;
    }

    public static void main(String[] args) {
        UserEmulator emulator = new UserEmulator();
        String url = "http://localhost:9000/api/profile";
        int size = 1;
        ExecutorService executor = Executors.newFixedThreadPool(size);
        for(int i = 0; i < size; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    emulator.httpGet(url);
                }
            });
        }
        executor.shutdown();
    }
}
