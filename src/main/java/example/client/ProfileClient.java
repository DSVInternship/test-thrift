/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.client;

import example.thrift.Profile;
import example.thrift.ProfileService;
import example.thrift.TGetProfileResult;
import example.util.TTransprotPooling;

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

	// public TGetProfileResult getAll() throws TException {
	// long start = System.currentTimeMillis();
	// try (TTransport transport = new TSocket("localhost", 9090)){
	// transport.open();
	// TProtocol protocol = new TBinaryProtocol(transport);
	// ProfileService.Client client = new ProfileService.Client(protocol);
	// System.err.println("time to create socket: " + (System.currentTimeMillis() -
	// start));
	//
	// TGetProfileResult profileRes = client.getAll();
	//
	// return profileRes;
	// }
	// }

	public TGetProfileResult getAll() throws TException, InterruptedException {
		long start = System.currentTimeMillis();

		TTransport transport = TTransprotPooling.getInstace().getConnection();
		TProtocol protocol = new TBinaryProtocol(transport);
		ProfileService.Client client = new ProfileService.Client(protocol);
		System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

		TGetProfileResult profileRes = client.getAll();
		//Thread.sleep(5000);

		TTransprotPooling.getInstace().closeConnection(transport);
		return profileRes;

	}

	public TGetProfileResult get(long id) throws TException, InterruptedException {
		long start = System.currentTimeMillis();
		TTransport transport = TTransprotPooling.getInstace().getConnection();
		TProtocol protocol = new TBinaryProtocol(transport);
		ProfileService.Client client = new ProfileService.Client(protocol);
		System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

		TGetProfileResult profileRes = client.get(id);
		TTransprotPooling.getInstace().closeConnection(transport);
		return profileRes;
	}

	public boolean insert(Profile profile) throws TException, InterruptedException {
		long start = System.currentTimeMillis();
		TTransport transport = TTransprotPooling.getInstace().getConnection();
		TProtocol protocol = new TBinaryProtocol(transport);
		ProfileService.Client client = new ProfileService.Client(protocol);
		System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

		boolean profileRes = client.insert(profile);
		Thread.sleep(3000);
		TTransprotPooling.getInstace().closeConnection(transport);
		return profileRes;
	}

	public boolean update(Profile profile) throws TException, InterruptedException {
		long start = System.currentTimeMillis();
		TTransport transport = TTransprotPooling.getInstace().getConnection();
		TProtocol protocol = new TBinaryProtocol(transport);
		ProfileService.Client client = new ProfileService.Client(protocol);
		System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

		boolean profileRes = client.update(profile);
		Thread.sleep(3000);
		TTransprotPooling.getInstace().closeConnection(transport);
		return profileRes;

	}

	public boolean remove(long id) throws TException, InterruptedException {
		long start = System.currentTimeMillis();
		TTransport transport = TTransprotPooling.getInstace().getConnection();
		TProtocol protocol = new TBinaryProtocol(transport);
		ProfileService.Client client = new ProfileService.Client(protocol);
		System.err.println("time to create socket: " + (System.currentTimeMillis() - start));

		boolean profileRes = client.remove(id);
		Thread.sleep(3000);
		TTransprotPooling.getInstace().closeConnection(transport);

		return profileRes;

	}

	public static void main(String[] args) throws TException, InterruptedException {
		ProfileClient client = new ProfileClient();
		client.get(1533271458819l);
	}

}
