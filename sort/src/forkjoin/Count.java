package forkjoin;

import java.util.Random;
/**
 * ������
 * @author Administrator
 *
 */
public class Count {
    private int count = 0;
    private Random rang = new Random(47);
    public synchronized int increment(){
        int temp = count;
        if(rang.nextBoolean()){
            /**
             * �߳��ò� �ò�һ���ʱ��
             */
            Thread.yield();
        }
        return (count = ++temp);
    }
    public synchronized int vlaue(){return count;}
}