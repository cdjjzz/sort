package com.sort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculator {
    private ForkJoinPool pool;

    private static class SumTask extends RecursiveTask<Long> {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // ����Ҫ���������С��6ʱ��ֱ�Ӽ�����
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            // ���򣬰�����һ��Ϊ�����ݹ����
            } else {
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle+1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }

    public ForkJoinCalculator() {
        // Ҳ����ʹ�ù��õ� ForkJoinPool��
        // pool = ForkJoinPool.commonPool()
        pool = new ForkJoinPool();
    }

    public long sumUp(long[] numbers) {
        return pool.invoke(new SumTask(numbers, 0, numbers.length-1));
    }
}