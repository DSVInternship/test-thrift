package example.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class TTransprotPooling {
	private static TTransprotPooling instance;
	private BlockingQueue<TTransport> usedConnection = new LinkedBlockingQueue<>();
	private BlockingQueue<TTransport> availableConnection = new LinkedBlockingQueue<>();
	private int MAX_CONNECTION;
	private int port;
	private String host;
	private boolean isConfig = false;
	
	private TTransprotPooling() {}
	
	synchronized public static TTransprotPooling getInstace() {
		if (instance == null) {
			instance = new TTransprotPooling();
		}
		return instance;
	}
	
	public void config(int maxConnection, String host, int port) throws InterruptedException, TTransportException {
		if(isConfig)
			return;
		this.MAX_CONNECTION = maxConnection;
		this.port = port;
		
		// create connection 
		for(int i= 0; i < MAX_CONNECTION; i++) {
			availableConnection.offer(createTransport(host, port), 1, TimeUnit.MILLISECONDS);
		}
		isConfig = true;
	}
	
	private TTransport createTransport(String host, int port) throws TTransportException {
		TTransport transport = new TSocket(host, port);
		transport.open();
		return transport;
	}
	
	public int getNumberFreeConnection() {
		return this.availableConnection.size();
	}
	
	public TTransport getConnection() throws InterruptedException {
		TTransport connection = availableConnection.poll(1, TimeUnit.MILLISECONDS);
		if(connection == null) {
			System.out.println("All connection are used.");
			return null;
		}else {
			usedConnection.offer(connection, 3, TimeUnit.MILLISECONDS);
			return connection;
		}
	}
	
	public boolean closeConnection(TTransport connection) throws InterruptedException {
		if(connection != null) {
			usedConnection.remove(connection);
			availableConnection.offer(connection, 5, TimeUnit.MILLISECONDS);
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws InterruptedException, TTransportException {
		TTransprotPooling pool = TTransprotPooling.getInstace();
		pool.config(5, "localhost", 9090);
		TTransport con1 = pool.getConnection();
		TTransport con2 = pool.getConnection();
		System.out.println(pool.getNumberFreeConnection());
		TTransport con3 = pool.getConnection();
		TTransport con4 = pool.getConnection();
		TTransport con5 = pool.getConnection();
		TTransport con6 = pool.getConnection();
		TTransport con7 = pool.getConnection();

		System.out.println(pool.getNumberFreeConnection());
		pool.closeConnection(con1);
		pool.closeConnection(con2);
		pool.closeConnection(con4);
		System.out.println(pool.getNumberFreeConnection());
	}
}
