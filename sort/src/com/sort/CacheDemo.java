package com.sort;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {
	    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	    private final Lock w=rwl.writeLock();
	    
	    private final Lock  r=rwl.readLock();
	    
	    int data = 0;  
	    public static void main(String[] args) {
	    	final CacheDemo cacheDemo=new CacheDemo();
	    	for (int i = 0; i < 5; i++) {
	    		new Thread(new Runnable() {
	    			@Override
	    			public void run() {
	    				try {
	    					for(;;)
							cacheDemo.get();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
	    			}
	    		},"read"+i).start();
			}
//	        new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						cacheDemo.get();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			},"write").start();
	        
	    }
	    public void get() throws InterruptedException{
	        r.lock();
	        log("is reading ...");  
	        if(data==0){
        		r.unlock();
        		w.lock();
        		try {
        			if(data==0){
        				log("is writing ...");
        				data += 3;// 进行写操作
        				Thread.sleep(2);
        			}
				}finally{
					r.lock();
					w.unlock();
				}
        	}
	        try{
	        	log("读取并使用数据：" + data--);
	            Thread.sleep(1000);
	        }finally{
	        	log("read over.");  
	        	r.unlock();
	        }
	    }
	    void log(String msg) {  
	        System.out.println(Thread.currentThread().getName() + " " + msg);  
	    }  
}
