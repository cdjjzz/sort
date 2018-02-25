package forkjoin;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

	private static final Exchanger<String> exgr = new Exchanger<String>();

	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {

		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String A = "银行流水B";// A录入银行流水数据
					exgr.exchange(A);
					String B = exgr.exchange(A);//阻塞线程 等待双方线程同时交换唤醒线程 //获取其他线程发布的数据
					System.out.println("123A和B数据是否一致：" + A.equals(B) + ",A录入的是："
							+ A + ",B录入是：" + B);
				} catch (Exception e) {
				}
			}
		});
		try{
		threadPool.submit(new Runnable() {
			@Override
			public void run() {
				try {
					String B = "银行流水B";// B录入银行流水数据
					exgr.exchange(B);//发布出去
					String A = exgr.exchange(B);//阻塞线程 等待双方线程同时交换唤醒线程
					System.out.println("234A和B数据是否一致：" + A.equals(B) + ",A录入的是："
							+ A + ",B录入是：" + B);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}catch(Exception e){
		
	}

		threadPool.shutdown();

	}
}