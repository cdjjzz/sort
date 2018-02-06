import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Account {
    int data = 0;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    void processCachedData() throws Exception {
        rwl.readLock().lock();
        log("is reading ...");
        if (isOk()) {
            rwl.readLock().unlock();//д����������⣬�����ͷ�rwl�Ķ����������rwl��д��
            rwl.writeLock().lock();
            try {
                if (isOk()) {// �õ�д�������жϣ���ֹ�������߳����ȸ���
                    log("is writing ...");
                    data += 3;// ����д����
                    //Thread.sleep(200000);//���Կ���������д���������ٶ�
                    Thread.sleep(2);
                } else {
                    log("�����ѱ����ȸ��£������ٸ��¡�");
                }
            } finally {
                log("write over.");
                rwl.readLock().lock();// ���ͷ�д��֮ǰ������Ȼ�ȡ�������Է�ֹʹ��֮ǰ������д�̸߳ı�������״̬
                rwl.writeLock().unlock(); // �ͷ�д����������һ�����ɱ����˶������ɷ�ֹд������
            }
        }
        try {
            log("��ȡ��ʹ�����ݣ�" + data--);
            Thread.sleep(1000);
        } finally {
            log("read over.");
            rwl.readLock().unlock();
        }
    }
    boolean isOk() {
        return data == 0;
    }
    void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " " + msg);
    }
}
public class Test {
    public static void main(String[] args) {
//        final Account account = new Account();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                    	for(;;)
//                        account.processCachedData();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//        executorService.shutdown();
    	int c=17;
    	 int n = c - 1;
         n |= n >>> 1;
         n |= n >>> 2;
         n |= n >>> 4;
         n |= n >>> 8;
         n |= n >>> 16;
         System.out.println(n);
    }
}
