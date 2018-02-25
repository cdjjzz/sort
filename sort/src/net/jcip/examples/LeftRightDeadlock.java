package net.jcip.examples;

import java.util.concurrent.TimeUnit;

/**
 * LeftRightDeadlock
 *
 * Simple lock-ordering deadlock
 *
 * @author Brian Goetz and Tim Peierls
 */


/**
 * 在多重锁一定要注意加锁顺序及释放，否则容易死锁。
 * @author pet-lsf
 *
 */
public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight()  throws InterruptedException{
        synchronized (left) {
        	System.out.println(Thread.currentThread().getName()+"get left,try get right");
        	TimeUnit.NANOSECONDS.sleep(1);
            synchronized (right) {
            	System.out.println(Thread.currentThread().getName()+"get left and right");
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
        	System.out.println(Thread.currentThread().getName()+"get right,try get left");
            synchronized (left) {
            	System.out.println(Thread.currentThread().getName()+"get left and right");
                doSomethingElse();
            }
        }
    }

    void doSomething() {
    }

    void doSomethingElse() {
    }
    public static void main(String[] args) {
    	LeftRightDeadlock deadlock=new LeftRightDeadlock();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					deadlock.leftRight();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				deadlock.rightLeft();
			}
		}).start();
	}
}
