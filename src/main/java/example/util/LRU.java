/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.util;

import java.util.LinkedList;

import org.eclipse.jetty.security.UserDataConstraint;

import example.model.UserDTO;

/**
 *
 * @author root
 */
public class LRU<T> {
    private LinkedList<T> cache;
    
    private static LRU instance;
    private final int MAX_FRAME = 3;
    
    private LRU(){
        cache = new LinkedList<T>();
    }
    
    public static <T>LRU getInstace(){
        if(instance == null)
            instance = new LRU();
        return instance;
    }
    
    public void refer(T object){
        boolean removeX = cache.remove((T)object);
        
        //neu X ko ton tai trong cache 
        if(!removeX){
            // neu cache day thi remove phan tu cuoi cung 
            if(cache.size() == MAX_FRAME)
                cache.removeLast();
        }
        
        cache.addFirst(object);
    }
    
    public void display() {
    	for(T x : cache) {
    		System.out.print(x + " ");
    	}
    	System.out.println();
    }
    
    public static void main(String[] args) {
		LRU lru = LRU.getInstace();
//		//1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5
//		lru.refer(1);
//		lru.display();
//		
//		lru.refer(2);
//		lru.display();
//		
//		lru.refer(3);
//		lru.display();
//		
//		lru.refer(4);
//		lru.display();
//		
//		lru.refer(1);
//		lru.display();
//		
//		lru.refer(2);
//		lru.display();
//		
//		lru.refer(5);
//		lru.display();
//		
//		lru.refer(1);
//		lru.display();
//		
//		lru.refer(2);
//		lru.display();
//		
//		lru.refer(3);
//		lru.display();
//		
//		lru.refer(4);
//		lru.display();
//		
//		lru.refer(5);
//		lru.display();
		
		//1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5
		UserDTO dto1 = new UserDTO(1, "Ac",0);
		UserDTO dto2 = new UserDTO(2,"a",0);
		UserDTO dto3 = new UserDTO(3,"b",0);
		UserDTO dto4 = new UserDTO(4,"d",0);
		UserDTO dto5 = new UserDTO(1,"a",0);
		UserDTO dto6 = new UserDTO(2,"a",0);
		UserDTO dto7 = new UserDTO(5,"bd",0);
		UserDTO dto8 = new UserDTO(1,"cd",0);
		UserDTO dto9 = new UserDTO(2,"a",0);
		UserDTO dto10 = new UserDTO(3,"cd",0);
		UserDTO dto11 = new UserDTO(4,"a",0);
		UserDTO dto12 = new UserDTO(5,"a",0);
		
		lru.refer(dto1);
		lru.refer(dto2);
		lru.refer(dto3);
		lru.refer(dto4);
		lru.refer(dto5);
		lru.refer(dto6);
		lru.display();
		lru.refer(dto7);
		lru.display();
		lru.refer(dto8);
		lru.display();
		lru.refer(dto9);
		lru.display();
		lru.refer(dto10);
		lru.display();
		lru.refer(dto11);
		lru.display();
		lru.refer(dto12);
		lru.display();
		
		
		
	}
}
