package com.sort;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorServiceTest {

	public static void main(String[] args) {
		ScheduleExecutorServiceTest test = new ScheduleExecutorServiceTest();
		test.testWithFixedDelay();
		test.testAtFixedRate();
	}
	
	private ScheduledExecutorService executor;
	
	public ScheduleExecutorServiceTest() {
		executor = Executors.newScheduledThreadPool(4);
	}
	
	public void testAtFixedRate() {
		executor.scheduleAtFixedRate(new Runnable() {
			
			public void run() {
				System.out.println("====");
				try {
					Thread.sleep(10000);
					System.out.println("执行完毕2");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 1000, 3000, TimeUnit.MILLISECONDS);
	}
	
	public void testWithFixedDelay() {
		executor.scheduleWithFixedDelay(new Runnable() {
			
			public void run() {
				System.out.println("====");
				try {
					Thread.sleep(10000);
					System.out.println("执行完毕1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}, 1000, 3000, TimeUnit.MILLISECONDS);
	}

}
