/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.test;

/**
 *
 * @author root
 */
public class StatisticDTO {
    private String serviceName;
    private long totalRequest;
    private long totalTimeProcess;
    private double ProcessRate;
    private double requestRate;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getTotalRequest() {
        return totalRequest;
    }

    public void setTotalRequest(long totalRequest) {
        this.totalRequest = totalRequest;
    }

    public long getTotalTimeProcess() {
        return totalTimeProcess;
    }

    public void setTotalTimeProcess(long totalTimeProcess) {
        this.totalTimeProcess = totalTimeProcess;
    }

    public double getProcessRate() {
        return ProcessRate;
    }

    public void setProcessRate(double ProcessRate) {
        this.ProcessRate = ProcessRate;
    }

    public double getRequestRate() {
        return requestRate;
    }

    public void setRequestRate(double requestRate) {
        this.requestRate = requestRate;
    }

    @Override
    public String toString() {
        return "StatisticDTO{" + "serviceName=" + serviceName + ", totalRequest=" + totalRequest + ", totalTimeProcess=" + totalTimeProcess + ", ProcessRate=" + ProcessRate + ", requestRate=" + requestRate + '}';
    }
    
}
