/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.util;

import java.util.LinkedList;

/**
 *
 * @author root
 */
public class LRU {
    private LinkedList<Integer> cache;
    
    private static LRU instance;
    private final int MAX_FRAME = 3;
    
    private LRU(){
        cache = new LinkedList<Integer>();
    }
    
    public static LRU getInstace(){
        if(instance == null)
            instance = new LRU();
        return instance;
    }
    
    public void refer(int x){
        boolean removeX = cache.remove((Integer)x);
        
        //neu X ko ton tai trong cache 
        if(!removeX){
            // neu cache day thi remove phan tu cuoi cung 
            if(cache.size() == MAX_FRAME)
                cache.removeLast();
        }
        
        cache.addFirst(x);
    }
    
    public void display() {
    	for(Integer x : cache) {
    		System.out.print(x + " ");
    	}
    	System.out.println();
    }
    
    public static void main(String[] args) {
		LRU lru = LRU.getInstace();
		//1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5
		lru.refer(1);
		lru.display();
		
		lru.refer(2);
		lru.display();
		
		lru.refer(3);
		lru.display();
		
		lru.refer(4);
		lru.display();
		
		lru.refer(1);
		lru.display();
		
		lru.refer(2);
		lru.display();
		
		lru.refer(5);
		lru.display();
		
		lru.refer(1);
		lru.display();
		
		lru.refer(2);
		lru.display();
		
		lru.refer(3);
		lru.display();
		
		lru.refer(4);
		lru.display();
		
		lru.refer(5);
		lru.display();
		
	}
}
