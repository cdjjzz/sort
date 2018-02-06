package com.sort;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestSelfPool {
	
    static	ExecutorService executorService=null;
	
	public static void main(String[] args) {
		SelfPool pool=new SelfPool(0, 1, 1, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<Runnable>(1));
		pool.setThreadFactory(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread=new Thread(r);
				thread.setName("康师傅");
				return thread;
			}
		});
		pool.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				while(true){
					try {
						pool.getQueue().put(new Runnable() {
							@Override
							public void run() {
								System.out.println("你好-了沙发发生了发顺丰卡刷卡费");
							}
						});
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		executorService=pool;
		Future<String> future1=executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("罗盛丰");
				TimeUnit.MILLISECONDS.sleep(10);
				return "罗盛丰";
			}
		});
		Future<String> future=executorService.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("张胜男");
			}
		}, "李四");
		try {
			System.out.println(future.get());
			System.out.println(future1.get());
		} catch (Exception e2) {
		}
		System.out.println(pool.getCompletedTaskCount());
		executorService.shutdown();
		ConcurrentHashMap<String, Long> concurrentHashMap=pool.getMap();
		System.out.println(concurrentHashMap);
	}
	
	

}
