package net.jcip.examples;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountingSheep
 * <p/>
 * Counting sheep
 *
 * @author Brian Goetz and Tim Peierls
 */
public class CountingSheep {
    volatile boolean asleep;
    AtomicInteger atomicInteger=new AtomicInteger(0);
    int a=0;
    void tryToSleep() {
        while (!asleep)
            countSomeSheep();
    }
    void countSomeSheep() {
    	if(a==100)asleep=true;
    		System.out.println(a++);
        // One, two, three...
    }
    public static void main(String[] args) {
    	CountingSheep countingSheep=new CountingSheep();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countingSheep.tryToSleep();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countingSheep.tryToSleep();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countingSheep.tryToSleep();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countingSheep.tryToSleep();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countingSheep.tryToSleep();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countingSheep.tryToSleep();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countingSheep.tryToSleep();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countingSheep.tryToSleep();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countingSheep.tryToSleep();
			}
		}).start();
	}
}








