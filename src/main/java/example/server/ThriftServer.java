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
import example.thrift.MultiplicationService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class ThriftServer {

    public MultiplicationHandler handler;

    public MultiplicationService.Processor processor;

    public void startServer() {
        try {
            handler = new MultiplicationHandler();
            processor = new MultiplicationService.Processor(handler);
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

    private void simpleServer(MultiplicationService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting thrift server at port 9090");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
