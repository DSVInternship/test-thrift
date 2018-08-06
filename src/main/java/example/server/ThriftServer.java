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
import example.handler.ProfileServiceHandler;
import example.thrift.ProfileService;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;

public class ThriftServer {

    public ProfileServiceHandler handler;

    public ProfileService.Processor processor;

    public void startServer() {
        try {
            handler = new ProfileServiceHandler();
            processor = new ProfileService.Processor(handler);
            threadPoolServer(processor);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private void simpleServer(ProfileService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            //TServer server = new TThreadPoolServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting thrift server at port 9090");

            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void threadPoolServer(ProfileService.Processor processor) throws TTransportException {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            Factory proFactory = new TBinaryProtocol.Factory();
            TThreadPoolServer.Args options = new TThreadPoolServer.Args(serverTransport);
            options.minWorkerThreads = 10;
            options.processor(processor);
            options.protocolFactory(proFactory);

            TServer server = new TThreadPoolServer(options);
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
