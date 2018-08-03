/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.handler;

import example.thrift.MultiplicationService;
import org.apache.thrift.TException;

/**
 *
 * @author root
 */
public class MultiplicationHandler implements MultiplicationService.Iface {
    @Override
    public int multiply(int n1, int n2) throws TException {
        System.out.println("Handler Multiply(" + n1 + "," + n2 + ")");
        return n1 * n2;
    }

    @Override
    public int add(int n1, int n2) throws TException {
        // TODO Auto-generated method stub
        System.out.println("Hanlder Add(" + n1 + "," + n2 + ")");
        return n1 + n2;
    }
}
