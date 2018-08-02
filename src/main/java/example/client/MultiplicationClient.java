/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.client;

/**
 *
 * @author root
 */
import example.thrift.MultiplicationService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class MultiplicationClient {

    public static void main(String[] args) throws InterruptedException {
        MultiplicationClient client = new MultiplicationClient();
        try {
            client.multiple(1, 2);
            client.add(3, 4);
            client.add(3, 5);
            client.add(3, 6);
            client.add(3, 7);

        } catch (TException x) {
            x.printStackTrace();
        }
    }

    public int multiple(int a, int b) throws TException, InterruptedException {
        long start = System.currentTimeMillis();
        try (TTransport transport = new TSocket("localhost", 9090)) {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            MultiplicationService.Client client = new MultiplicationService.Client(protocol);
            System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

            int product = client.multiply(a, b);
            Thread.sleep(3000);
            System.out.println("client" + a + "*" + b + "=" + product);
            return product;
        }
    }

    public int add(int a, int b) throws TException, InterruptedException {
        long start = System.currentTimeMillis();
        try (TTransport transport = new TSocket("localhost", 9090)) {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            MultiplicationService.Client client = new MultiplicationService.Client(protocol);
            System.err.println("time to create socket: " + (System.currentTimeMillis() - start));
            int sum = client.add(a, b);
            Thread.sleep(3000);
            System.out.println("client" + a + "+" + b + "=" + sum);
            return sum;
        }
    }

}
