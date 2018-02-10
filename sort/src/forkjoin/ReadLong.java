package forkjoin;

import java.util.concurrent.TimeUnit;

public class ReadLong {
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		 long number=1000000000L;
		 System.out.println(Long.toBinaryString(number));
		 System.out.println(Integer.toBinaryString(2222));
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(number);
			}
		}).start();
		TimeUnit.SECONDS.sleep(2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(number);
			}
		}).start();
	}
	

}
