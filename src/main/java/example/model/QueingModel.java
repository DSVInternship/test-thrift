/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.model;

import example.client.MultiplicationClient;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.TException;

/**
 *
 * @author root
 */
public class QueingModel {

    private static QueingModel instance = null;
    private BlockingQueue<Job> jobQueue;// = new ArrayBlockingQueue<Job>(5);
    private MultiplicationClient worker;// = new MultiplicationClient();
    // private List<Worker> list ;

    private QueingModel() {
        jobQueue = new ArrayBlockingQueue<Job>(5);
        worker = new MultiplicationClient();
    }

    synchronized public static QueingModel getInstance() {
        if (instance == null) {
            instance = new QueingModel();
        }
        return instance;
    }

    public Job getJob() throws InterruptedException {
        Job job = jobQueue.poll(5, TimeUnit.MILLISECONDS);
        if (job == null) {
            System.out.println("blocking queue is empty");
            synchronized (this) {
                wait();
            }
        }
        return job;
    }

    public boolean putJob(Job job) throws InterruptedException {
        boolean result = jobQueue.offer(job, 5, TimeUnit.MILLISECONDS);
        synchronized (this) {
            notify();
        }
        if (!result) {
            System.out.println("blocking queue is full");
        }
        return result;
    }

    public void startQueingModel() throws InterruptedException, TException {
        String method;
        Object data;
        while (true) {
            Job job = getJob();
            if (job == null) {
                continue;
            }
            System.err.println("number element in queue: " + jobQueue.size());
            method = job.getMethod();
            data = job.getData();
            switch (method) {
                case "add":
                    UserDTO dtoAdd = (UserDTO) data;
                    worker.add((int)dtoAdd.getId(), dtoAdd.getAge());
                    break;
                case "multiply":
                    UserDTO dtoMul = (UserDTO) data;
                    worker.multiple((int)dtoMul.getId(), dtoMul.getAge());
                    break;
                default:
                    System.out.println("Default case");
            }
        }
    }
}
