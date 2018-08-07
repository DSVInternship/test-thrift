/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.server;

import example.model.QueingModel;
import example.test.TestPerformance;
import example.util.TTransprotPooling;

import org.apache.thrift.TException;

/**
 *
 * @author root
 */
public class MainServer {

    public static void main(String[] args) throws InterruptedException, TException {
        // start thrift server. PORT 9090. can run seperate file
        ThriftServer thriftServer = new ThriftServer();
        new Thread() {
            public void run() {
                thriftServer.startServer();
            }
        }.start();

        // start jetty server. PORT 8080
        JettyServer jettyServer = new JettyServer();
        Runnable simple = new Runnable() {
            public void run() {
                jettyServer.startServer();
            }
        };

        new Thread(simple).start();

        // queing model. Yeu cau thrift server da duoc start
        QueingModel queingModel = QueingModel.getInstance();
        new Thread() {
            public void run() {
                try {
                    queingModel.startQueingModel();
                } catch (InterruptedException | TException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();

        // start connection pooling 
        TTransprotPooling.getInstace().config(20, "localhost", 9090);

        System.out.println("number connection pooling: " + TTransprotPooling.getInstace().getNumberFreeConnection());
        
        // for performance testing
        TestPerformance test = TestPerformance.getInstance();
        test.addService("insert.rest");
        test.addService("insert.thrift");
        test.addService("getAll.rest");
        test.addService("update.rest");
        test.addService("update.thrift");
    }
}
