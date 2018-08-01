/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thrift.test;

/**
 *
 * @author root
 */
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class MultiplicationClient {
     public static void main(String [] args) {

   
    try {
      TTransport transport;
     
      transport = new TSocket("localhost", 9090);
      transport.open();

      TProtocol protocol = new  TBinaryProtocol(transport);
      MultiplicationService.Client client = new MultiplicationService.Client(protocol);

      perform(client);
      perform2(client);

      transport.close();
    } catch (TException x) {
      x.printStackTrace();
    } 
  }

  private static void perform(MultiplicationService.Client client) throws TException
  {
   
    int product = client.multiply(3,7);
    System.out.println("3*7=" + product);
  }
  
  private static void perform2(MultiplicationService.Client client) throws TException{
      int product = client.multiply(2, 3);
      System.out.println("2*3=" + product);

  }
}
