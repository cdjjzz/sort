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
					String A = "������ˮB";// A¼��������ˮ����
					exgr.exchange(A);
					String B = exgr.exchange(A);//�����߳� �ȴ�˫���߳�ͬʱ���������߳� //��ȡ�����̷߳���������
					System.out.println("123A��B�����Ƿ�һ�£�" + A.equals(B) + ",A¼����ǣ�"
							+ A + ",B¼���ǣ�" + B);
				} catch (Exception e) {
				}
			}
		});
		try{
		threadPool.submit(new Runnable() {
			@Override
			public void run() {
				try {
					String B = "������ˮB";// B¼��������ˮ����
					exgr.exchange(B);//������ȥ
					String A = exgr.exchange(B);//�����߳� �ȴ�˫���߳�ͬʱ���������߳�
					System.out.println("234A��B�����Ƿ�һ�£�" + A.equals(B) + ",A¼����ǣ�"
							+ A + ",B¼���ǣ�" + B);
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