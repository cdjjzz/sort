package com.sort;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledTest {
	static ScheduledExecutorService executorService=Executors.newScheduledThreadPool(2);
	private static AtomicInteger atomicInteger=new AtomicInteger(0);
	public static void main(String[] args) {
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("ÂÞÊ¢·á"+atomicInteger.getAndIncrement());
			}
		},1000,1000, TimeUnit.MILLISECONDS);
	}
	

}
