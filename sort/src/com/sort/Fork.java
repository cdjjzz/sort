package com.sort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

import javax.sql.PooledConnection;


public class Fork {
		
	private ForkJoinPool forkJoinPool;
	
	private static class Cla extends RecursiveTask<Long>{
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private long[] numbers;
        private int from;
        private int to;

        public Cla(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // 当需要计算的数字小于6时，直接计算结果
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            // 否则，把任务一分为二，递归计算
            } else {
                int middle = (from + to) / 2;
                Cla taskLeft = new Cla(numbers, from, middle);
                Cla taskRight = new Cla(numbers, middle+1, to);
                taskLeft.fork();//开启新线程或重用空闲线程处理
                taskRight.fork();
                return taskLeft.join() + taskRight.join();//join  等待线程执行完毕   获取线程值  futrue.get();
            }
        }
    }
	 public Fork() {
	        // 也可以使用公用的 ForkJoinPool：
	        // pool = ForkJoinPool.commonPool()
		 forkJoinPool = new ForkJoinPool(2);
		 System.out.println(forkJoinPool.getParallelism());
		 System.out.println(forkJoinPool.getPoolSize());
	    }

	    public long sumUp(long[] numbers) {
	        return forkJoinPool.invoke(new Cla(numbers, 0, numbers.length-1));
	    }

	   public static void main(String[] args) {
		  long  nasTime=System.nanoTime();
		  long numbes[]=new long[100]; 
		  for (int i = 0; i < numbes.length; i++) {
			numbes[i]=ThreadLocalRandom.current().nextLong();
		  }
		long total=0;//new Fork().sumUp(numbes);
		long t=0;
		for (int i = 0; i < numbes.length; i++) {
			t+=numbes[i];
		}
		System.out.println(total);
		System.out.println("------------------------------------------");
		System.out.println(t);
		System.out.println(System.nanoTime()-nasTime);
	 }

}
