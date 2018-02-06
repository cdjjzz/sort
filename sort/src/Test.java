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
            rwl.readLock().unlock();//写锁与读锁互斥，必需释放rwl的读锁后才能上rwl的写锁
            rwl.writeLock().lock();
            try {
                if (isOk()) {// 得到写锁后再判断，防止被其它线程抢先更改
                    log("is writing ...");
                    data += 3;// 进行写操作
                    //Thread.sleep(200000);//可以看到，加上写锁将不可再读
                    Thread.sleep(2);
                } else {
                    log("发现已被抢先更新，无需再更新。");
                }
            } finally {
                log("write over.");
                rwl.readLock().lock();// 在释放写锁之前最好先先获取读锁，以防止使用之前被其他写线程改变了数据状态
                rwl.writeLock().unlock(); // 释放写锁，但上面一句依旧保留了读锁，可防止写锁介入
            }
        }
        try {
            log("读取并使用数据：" + data--);
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
