package com.sort;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SelfPool extends ThreadPoolExecutor{
	
	private  ConcurrentHashMap<String, Long> concurrentHashMap=new ConcurrentHashMap<String, Long>();
		
	public SelfPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
			TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	@Override
	public void setThreadFactory(ThreadFactory threadFactory) {
		super.setThreadFactory(threadFactory);
	}  
	@Override
	public void setRejectedExecutionHandler(RejectedExecutionHandler handler) {
		// TODO Auto-generated method stub
		super.setRejectedExecutionHandler(handler);
	}
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		System.out.println(concurrentHashMap);
		concurrentHashMap.putIfAbsent(Thread.currentThread().getName(),System.currentTimeMillis());
		super.afterExecute(r, t);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		concurrentHashMap.put(t.getName(),System.currentTimeMillis());
		System.out.println(t.getName());
		System.out.println("===beforeExecute===");
		super.beforeExecute(t, r);
	}
	public  ConcurrentHashMap<String, Long> getMap(){
		return concurrentHashMap;
	}
}
