package com.sort;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the example. Creates a MyScheduledThreadPoolExecutor and
 * executes a delayed task and a periodic task in it.
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
    	ScheduledThreadPoolExecutor scheduledThreadPoolExecutor=new ScheduledThreadPoolExecutor(3);
    	
        /*
         * Create a MyScheduledThreadPool object
         */
        MyScheduledThreadPoolExecutor executor=new MyScheduledThreadPoolExecutor(2);

        /*
         * Create a task object  
         */

        /*
         * Write the start date of the execution
         */
        System.out.printf("Main: %s\n",new Date());

        /*
         * Send to the executor a delayed task. It will be executed after 1 second of delay
         */
        ScheduledFuture<?> scheduledFuture=scheduledThreadPoolExecutor.schedule(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("ÄãºÃ");
			}
		}, 1, TimeUnit.MILLISECONDS);
        /*
         * Send to the executor a delayed task. It will be executed after 1 second of delay
         */
        ScheduledFuture<String> scheduledFuture2=scheduledThreadPoolExecutor.schedule(new Callable<String>() {
			@Override
			public String call() {
				System.out.println("ÄãºÃ1");
				return "ÂÞÊ¢·á";
			}
		}, 1, TimeUnit.MILLISECONDS);
        System.out.println(scheduledFuture2.get());

        /*
         * Sleeps the thread three seconds 
         */
        TimeUnit.SECONDS.sleep(3);

//        /*
//         * Create another task
//         */
//        task=new Task();
//
//        /*
//         * Write the actual date again
//         */
//        System.out.printf("Main: %s\n",new Date());
//
//        /*
//         * Send to the executor a delayed task. It will begin its execution after 1 second of dealy
//         * and then it will be executed every three seconds
//         */
//        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
//
//        /*
//         * Sleep the thread during ten seconds
//         */
//        TimeUnit.SECONDS.sleep(10);
//
//        /*
//         * Shutdown the executor
//         */
//        executor.shutdown();
//
//        /*
//         * Wait for the finalization of the executor
//         */
//        executor.awaitTermination(1, TimeUnit.DAYS);
//
//        /*
//         * Write a message indicating the end of the program
//         */
//        System.out.printf("Main: End of the program.\n");
    }

}