/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.client;

import example.test.TestPerformance;
import example.thrift.Profile;
import example.thrift.ProfileService;
import example.thrift.TGetProfileResult;
import example.util.TTransprotPooling;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

/**
 *
 * @author root
 */
public class ProfileClient {

    public TGetProfileResult getAll() throws TException, InterruptedException, Exception {
        TTransport transport = TTransprotPooling.getInstace().getConnection();
        TProtocol protocol = new TBinaryProtocol(transport);
        ProfileService.Client client = new ProfileService.Client(protocol);
        TGetProfileResult profileRes = client.getAll();
        TTransprotPooling.getInstace().closeConnection(transport);
        return profileRes;

    }

    public TGetProfileResult get(long id) throws TException, InterruptedException, Exception {
        TTransport transport = TTransprotPooling.getInstace().getConnection();
        TProtocol protocol = new TBinaryProtocol(transport);
        ProfileService.Client client = new ProfileService.Client(protocol);
        TGetProfileResult profileRes = client.get(id);
        TTransprotPooling.getInstace().closeConnection(transport);
        return profileRes;
    }

    public boolean insert(Profile profile) throws TException, InterruptedException, Exception {
        //Thread.sleep(6000);
        TestPerformance.getInstance().calTotalNumReq("insert.thrift");
        long start = System.nanoTime();
        TTransport transport = TTransprotPooling.getInstace().getConnection();
        TProtocol protocol = new TBinaryProtocol(transport);
        ProfileService.Client client = new ProfileService.Client(protocol);
        boolean profileRes = client.insert(profile);
        TTransprotPooling.getInstace().closeConnection(transport);
        TestPerformance.getInstance().calTotalTimeProcess("insert.thrift", (System.nanoTime() - start) / 1000);

        return profileRes;
    }

    public boolean update(Profile profile) throws TException, InterruptedException, Exception {
        // Thread.sleep(6000);
        TestPerformance.getInstance().calTotalNumReq("update.thrift");
        long start = System.nanoTime();
        TTransport transport = TTransprotPooling.getInstace().getConnection();
        TProtocol protocol = new TBinaryProtocol(transport);
        ProfileService.Client client = new ProfileService.Client(protocol);
        boolean profileRes = client.update(profile);
        TTransprotPooling.getInstace().closeConnection(transport);
        TestPerformance.getInstance().calTotalTimeProcess("insert.thrift", (System.nanoTime() - start) / 1000);
        return profileRes;
    }

    public boolean remove(long id) throws TException, InterruptedException, Exception {
        //Thread.sleep(6000);
        TTransport transport = TTransprotPooling.getInstace().getConnection();
        TProtocol protocol = new TBinaryProtocol(transport);
        ProfileService.Client client = new ProfileService.Client(protocol);
        boolean profileRes = client.remove(id);
        TTransprotPooling.getInstace().closeConnection(transport);
        return profileRes;
    }

    public static void main(String[] args) throws TException, InterruptedException, Exception {
        ProfileClient client = new ProfileClient();
        client.get(1533271458819l);
    }

}
