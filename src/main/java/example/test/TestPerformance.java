/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.test;

import java.util.HashMap;
import java.util.Map;
import example.test.StatisticDTO;

/**
 *
 * @author root
 */
public class TestPerformance {
    private Map<String, StatisticDTO> statistic = new HashMap<String, StatisticDTO>();
    private static TestPerformance instance;
    
    private TestPerformance(){}
    
    synchronized public static TestPerformance getInstance(){
        if(instance == null)
            instance = new TestPerformance();
        return instance;
    }
    
    public Map<String, StatisticDTO> getStatistic() {
        return statistic;
    }

    public void setStatistic(Map<String, StatisticDTO> statistic) {
        this.statistic = statistic;
    }
    
    public void addService(String serviceName){
        StatisticDTO dto = new StatisticDTO();
        dto.setServiceName(serviceName);
        statistic.put(serviceName, dto);
    }
    
    synchronized public void calTotalNumReq(String serviceName){
       StatisticDTO dto = statistic.get(serviceName);
       dto.setTotalRequest(dto.getTotalRequest() + 1);
    }
    
    synchronized public void calTotalTimeProcess(String serviceName, long timeForReq){
       StatisticDTO dto = statistic.get(serviceName);
       dto.setTotalTimeProcess(dto.getTotalTimeProcess() + timeForReq);
    }
    
    public void printStatistic(){
        for(StatisticDTO dto: statistic.values())
            System.out.println(dto);
    }
    
    public static void main(String[] args){
        TestPerformance test = TestPerformance.getInstance();
        test.addService("insert");
        test.addService("update");
        
        test.calTotalNumReq("insert");
        test.calTotalNumReq("insert");
        
        test.calTotalTimeProcess("insert", 10l);
        test.calTotalTimeProcess("insert", 23l);
        
        test.printStatistic();
    }
}
