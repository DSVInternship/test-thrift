/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.test;

import example.db.KyotoCabinetConnection;
import example.rest.response.ProfileResponse;
import example.rest.response.ProfileResultResponse;
import example.thrift.Profile;
import example.util.ByteConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import kyotocabinet.Cursor;
import kyotocabinet.DB;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
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
            System.out.println(json);
            com.google.gson.Gson gson = new com.google.gson.Gson();
            ProfileResultResponse response = gson.fromJson(json, ProfileResultResponse.class);
            return response;
        } catch (IOException ex) {
            System.err.println(json);
        }
        return null;
    }

    public void httpPost(String url) {
        try {
            com.google.gson.Gson gson = new com.google.gson.Gson();
            // mock size: 2000 bytes
            ProfileResponse mock = new ProfileResponse(System.currentTimeMillis(), "l articles, such as Ramchander Varadarajan's \"Question of the Week No. 107\" (Sun Microsystems, September 2000) and Tony Sintes's \"Memory Matters\" (JavaWorld, December 2001), detail that idea. Unfortunately, the former article's solution fails because the implementation employs a wrong Runtime method, while the latter article's solution has its own imperfections:Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32", 2000);
            String mockString = gson.toJson(mock, ProfileResponse.class);
            // System.out.println("Mock size: " + ByteConverter.convertToBytes(mock).length);
            // create
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost requestCreate = new HttpPost(url);
            StringEntity params = new StringEntity(mockString);
            requestCreate.addHeader("content-type", "application/json");
            requestCreate.setEntity(params);
            HttpResponse result = httpClient.execute(requestCreate);
            String postRes = EntityUtils.toString(result.getEntity(), "UTF-8");
            System.out.println(postRes);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserEmulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserEmulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Long> getListKey(int size) throws IOException, ClassNotFoundException {
        List<Long> results = new ArrayList<>();
        Profile tmp;
        DB db = KyotoCabinetConnection.getConnection("profile.kch", "r");
        Cursor cur = db.cursor();
        cur.jump();

        byte[][] recByte;

        while ((recByte = cur.get(true)) != null) {
            tmp = (Profile) ByteConverter.convertFromBytes(recByte[1]);
            results.add(tmp.getId());
            //System.out.println((ByteConverter.convertFromBytes(recByte[0])) + ":" + ByteConverter.convertFromBytes(recByte[1]));
        }
        cur.disable();
        KyotoCabinetConnection.closeConnection(db);
        return results;
    }

    public void httpPut(String url, List<Long> listId) {
        try {
            com.google.gson.Gson gson = new com.google.gson.Gson();
            // mock size: 2000 bytes
            ProfileResponse mock = new ProfileResponse(0l, "l articles, such as Ramchander Varadarajan's \"Question of the Week No. 107\" (Sun Microsystems, September 2000) and Tony Sintes's \"Memory Matters\" (JavaWorld, December 2001), detail that idea. Unfortunately, the former article's solution fails because the implementation employs a wrong Runtime method, while the latter article's solution has its own imperfections:Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32", 2000);
            // random change size
            double salt = Math.random();
            long selectedId = (long) Math.ceil(salt * listId.size());
            int subString = (int) Math.ceil(salt * mock.getName().length());
            mock.setId(selectedId);
            mock.setName(mock.getName() + mock.getName().substring(subString));
            String mockString = gson.toJson(mock, ProfileResponse.class);

            // Update
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPut requestCreate = new HttpPut(url);
            StringEntity params = new StringEntity(mockString);
            requestCreate.addHeader("content-type", "application/json");
            requestCreate.setEntity(params);
            HttpResponse result = httpClient.execute(requestCreate);
            String putRes = EntityUtils.toString(result.getEntity(), "UTF-8");
            System.out.println(putRes);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserEmulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserEmulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserEmulator emulator = new UserEmulator();
        String url = "http://localhost:9000/api/profile";
        int size = 300000;
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < size; i++) {
            //while(true){
            Thread.sleep(10);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //test get 
                    emulator.httpGet(url + "/1");

                    // test post 
                    //emulator.httpPost(url);
                }
            });
        }
        executor.shutdown();
    }
}
