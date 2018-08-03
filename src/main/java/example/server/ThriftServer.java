/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.server;

/**
 *
 * @author root
 */
import example.handler.MultiplicationHandler;
import example.handler.ProfileServiceHandler;
import example.thrift.MultiplicationService;
import example.thrift.ProfileService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class ThriftServer {

    public ProfileServiceHandler handler;

    public ProfileService.Processor processor;

    public void startServer() {
        try {
            handler = new ProfileServiceHandler();
            processor = new ProfileService.Processor(handler);
            simpleServer(processor);
//            Runnable simple = new Runnable() {
//                public void run() {
//                    simpleServer(processor);
//                }
//            };
//
//            new Thread(simple).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private void simpleServer(ProfileService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting thrift server at port 9090");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
      ThriftServer thriftServer = new ThriftServer();
      new Thread() {
          public void run() {
              thriftServer.startServer();
          }
      }.start();
	}
    
}
