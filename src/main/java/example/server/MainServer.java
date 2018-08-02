/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.server;

import example.model.QueingModel;
import org.apache.thrift.TException;

/**
 *
 * @author root
 */
public class MainServer {

    public static void main(String[] args) throws InterruptedException, TException {
        // start jetty server. PORT 8080
        JettyServer jettyServer = new JettyServer();
        Runnable simple = new Runnable() {
            public void run() {
                jettyServer.startServer();
            }
        };

        new Thread(simple).start();

        // start thrift server. PORT 9090
        ThriftServer thriftServer = new ThriftServer();
        new Thread() {
            public void run() {
                thriftServer.startServer();
            }
        }.start();
        
        // queing model
        QueingModel queingModel = QueingModel.getInstance();
        queingModel.startQueingModel();
    }
}
