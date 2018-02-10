package net.jcip.examples;

import java.util.concurrent.TimeUnit;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
        	System.out.println(ready);
            while (!ready){
                Thread.yield();//runing ------------------------->runable ���»�ȡcpuִ��Ȩ
            }
            System.out.println(number);
        }
    }
    //new ------->runable----------->runing--------------->blocked--------------------->dead
    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        TimeUnit.SECONDS.sleep(10);//runing ------------>blocked ����ָ��ʱ��ȴ�------->runable---------->runing��ȡcpuִ��Ȩ
        try {
			
		} catch (Exception e) {
			TimeUnit.SECONDS.sleep(2);
		}
        number = 42;
        ready = true;
    }
}
