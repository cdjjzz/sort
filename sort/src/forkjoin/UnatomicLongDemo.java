package forkjoin;
/**
 * 
 * @author pet-lsf
 *
 *
 *
 * 在32位jvm 中读取long ，double 等64位的变量 会读取到半个变量                  加上volatile来保证主内存与线程工作内存可见性。
 *
 *通过加入内存屏障和禁止重排序优化来实现的。
对volatile变量执行写操作时，会在写操作后加入一条store屏障指令

store指令会在写操作后把最新的值强制刷新到主内存中。同时还会禁止cpu对代码进行重排序优化。这样就保证了值在主内存中是最新的。
对volatile变量执行读操作时，会在读操作前加入一条load屏障指令

load指令会在读操作前把内存缓存中的值清空后，再从主内存中读取最新的值。
 */
public class UnatomicLongDemo implements Runnable {

    private static long test = 0;

    private final long val;

    public UnatomicLongDemo(long val) {
        this.val = val;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            test = val;//两个线程同时断写test变量，如果test变量的读写操作是原子性的，那么test只能是-1或者0
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new UnatomicLongDemo(-1));
        Thread t2 = new Thread(new UnatomicLongDemo(0));

        System.out.println(Long.toBinaryString(-1));
        System.out.println(pad(Long.toBinaryString(0), 64));

        t1.start();
        t2.start();

        long switchVal;
        while ((switchVal = test) == -1 || switchVal == 0) {
            //如果test、switchVal的操作是原子性的,那么就应该是死循环，否则就会跳出该循环
            System.out.println("testing...");
        }

        System.out.println(pad(Long.toBinaryString(switchVal), 64));
        System.out.println(switchVal);

        t1.interrupt();
        t2.interrupt();
    }

    //将0补齐到固定长度
   private static String pad(String s, int targetLength) {
        int n = targetLength - s.length();
        for (int x = 0; x < n; x++) {
            s = "0" + s;
        }
        return s;
    }

}