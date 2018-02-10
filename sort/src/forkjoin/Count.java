package forkjoin;

import java.util.Random;
/**
 * 计数类
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
             * 线程让步 让步一半的时间
             */
            Thread.yield();
        }
        return (count = ++temp);
    }
    public synchronized int vlaue(){return count;}
}