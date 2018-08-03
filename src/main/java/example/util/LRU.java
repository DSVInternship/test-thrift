/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.util;

import example.thrift.Profile;
import java.util.LinkedList;


/**
 *
 * @author root
 */
public class LRU<T> {
    private LinkedList<T> cache;
    
    private static LRU instance;
    private final int MAX_FRAME = 2;
    
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
    
    public void refer(T oldObject, T updatedObject){
        cache.remove(oldObject);
        refer(updatedObject);
    }
    
    public T find(T object) {
    	for(T tmp : cache) {
    		if(tmp.equals(object))
    			return tmp;
    	}
    	return null;
    }
    
    public boolean remove(T object){
        return cache.remove(object);
    }
    
    public void display() {
    	for(T x : cache) {
    		System.out.print(x + " ");
    	}
    	System.out.println();
    }
    
    
    
    public LinkedList<T> getCache() {
		return cache;
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
		Profile dto1 = new Profile(1, "Ac",0);
		Profile dto2 = new Profile(2,"a",0);
		Profile dto3 = new Profile(3,"b",0);
		Profile dto4 = new Profile(4,"d",0);
		Profile dto5 = new Profile(1,"a",0);
		Profile dto6 = new Profile(2,"a",0);
		Profile dto7 = new Profile(5,"bd",0);
		Profile dto8 = new Profile(1,"cd",0);
		Profile dto9 = new Profile(2,"a",0);
		Profile dto10 = new Profile(3,"cd",0);
		Profile dto11 = new Profile(4,"a",0);
		Profile dto12 = new Profile(5,"a",0);
		
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
