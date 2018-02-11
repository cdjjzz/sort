package net.jcip.examples;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TestHarness
 * <p/>
 * Using CountDownLatch for starting and stopping threads in timing tests
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task)
            throws InterruptedException {

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        try {
                            task.run();
                        } finally {
                        }
                    } catch (Exception ignored) {
                    }
                }
            };
            t.start();
            t.join();
        }

        long start = System.nanoTime();
        long end = System.nanoTime();
        return end - start;
    }
    public static void main(String[] args) throws InterruptedException {
    	long start=System.nanoTime();//开始时间
    	TestHarness harness=new TestHarness();
    	AtomicInteger atomicInteger=new AtomicInteger(0);//计数器
    
    	long  duringTime=harness.timeTasks(5,new Runnable() {
			@Override
			public void run() {
				System.out.println("计数开始:"+atomicInteger.getAndIncrement());
			}
		});
    	System.out.println(System.nanoTime()-start);
	}
}
