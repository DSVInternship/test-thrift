/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.client;

import example.thrift.Profile;
import example.thrift.ProfileService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *
 * @author root
 */
public class ProfileClient {
    
     public List<Profile> getAll() throws TException {
        long start = System.currentTimeMillis();
        try (TTransport transport = new TSocket("localhost", 9090)){
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            ProfileService.Client client = new ProfileService.Client(protocol);
            System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

            List<Profile> profileRes = client.getAll();
            return profileRes;
        }
    }

    public Profile get(long id) throws TException {
        long start = System.currentTimeMillis();
        try (TTransport transport = new TSocket("localhost", 9090)){
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            ProfileService.Client client = new ProfileService.Client(protocol);
            System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

            Profile profileRes = client.get(id);
            return profileRes;
        }
    }

    public boolean insert(Profile profile) throws TException {
        long start = System.currentTimeMillis();
        try (TTransport transport = new TSocket("localhost", 9090)) {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            ProfileService.Client client = new ProfileService.Client(protocol);
            System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

            boolean profileRes = client.insert(profile);
            Thread.sleep(3000);
            return profileRes;
        } catch (InterruptedException ex) {
            Logger.getLogger(ProfileClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(Profile profile) throws TException {
        long start = System.currentTimeMillis();
        try (TTransport transport = new TSocket("localhost", 9090)) {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            ProfileService.Client client = new ProfileService.Client(protocol);
            System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

            boolean profileRes = client.update(profile);
            Thread.sleep(3000);
            return profileRes;
        } catch (InterruptedException ex) {
            Logger.getLogger(ProfileClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean remove(long id) throws TException {
        long start = System.currentTimeMillis();
        try (TTransport transport = new TSocket("localhost", 9090)) {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            ProfileService.Client client = new ProfileService.Client(protocol);
            System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

            boolean profileRes = client.remove(id);
            Thread.sleep(3000);
            return profileRes;
        } catch (InterruptedException ex) {
            Logger.getLogger(ProfileClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main (String[] args) throws TException{
        ProfileClient client = new ProfileClient();
        client.get(1533271458819l);
    }

}
