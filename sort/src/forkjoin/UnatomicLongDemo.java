package forkjoin;
/**
 * 
 * @author pet-lsf
 *
 *
 *
 * ��32λjvm �ж�ȡlong ��double ��64λ�ı��� ���ȡ���������                  ����volatile����֤���ڴ����̹߳����ڴ�ɼ��ԡ�
 *
 *ͨ�������ڴ����Ϻͽ�ֹ�������Ż���ʵ�ֵġ�
��volatile����ִ��д����ʱ������д���������һ��store����ָ��

storeָ�����д����������µ�ֵǿ��ˢ�µ����ڴ��С�ͬʱ�����ֹcpu�Դ�������������Ż��������ͱ�֤��ֵ�����ڴ��������µġ�
��volatile����ִ�ж�����ʱ�����ڶ�����ǰ����һ��load����ָ��

loadָ����ڶ�����ǰ���ڴ滺���е�ֵ��պ��ٴ����ڴ��ж�ȡ���µ�ֵ��
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
            test = val;//�����߳�ͬʱ��дtest���������test�����Ķ�д������ԭ���Եģ���ôtestֻ����-1����0
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
            //���test��switchVal�Ĳ�����ԭ���Ե�,��ô��Ӧ������ѭ��������ͻ�������ѭ��
            System.out.println("testing...");
        }

        System.out.println(pad(Long.toBinaryString(switchVal), 64));
        System.out.println(switchVal);

        t1.interrupt();
        t2.interrupt();
    }

    //��0���뵽�̶�����
   private static String pad(String s, int targetLength) {
        int n = targetLength - s.length();
        for (int x = 0; x < n; x++) {
            s = "0" + s;
        }
        return s;
    }

}