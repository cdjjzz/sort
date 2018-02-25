package forkjoin;

import java.util.concurrent.CyclicBarrier;

/**
 * 
 * @author pet-lsf
 *
 */
public class cyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("�߳���ִ�н���");
            }
        });
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i,cyclicBarrier)).start();
        }
        //CyclicBarrier �����ظ����ã�
        //�����CountDownLatch��������
        for (int i = 11; i < 16; i++) {
            new Thread(new readNum(i,cyclicBarrier)).start();
        }
    }
    static class readNum  implements Runnable{
        private int id;
        private CyclicBarrier cyc;
        public readNum(int id,CyclicBarrier cyc){
            this.id = id;
            this.cyc = cyc;
        }
        @Override
        public void run() {
            synchronized (this){
                System.out.println("id:"+id);
                try {
                    cyc.await();//��1���ȴ�++=���캯��ֵ
                    System.out.println("�߳�������" + id + "�����������������");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
