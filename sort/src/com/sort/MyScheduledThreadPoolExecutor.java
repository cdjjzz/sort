package com.sort;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
	
	public MyScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize);
	}
	/**
	 * 传入Callable对象 返回值
	 */
	@Override
	public <V> RunnableScheduledFuture<V> decorateTask(Callable<V> callable,
			RunnableScheduledFuture<V> task) {
		MyScheduledTask<V> myScheduledTask=new MyScheduledTask<V>(callable, null, task, this);
		return myScheduledTask;
	}
	/**
	 * 传入Runnable对象返回值
	 */
	@Override
	protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable,
			RunnableScheduledFuture<V> task) {
		MyScheduledTask<V> myScheduledTask=new MyScheduledTask<V>(runnable, null, task, this);
		return myScheduledTask;
	}
	/**
     * Method that schedule in the executor a periodic tasks. It calls the method of its parent class using
     * the super keyword and stores the period of the task.
     */
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ScheduledFuture<?> task= super.scheduleAtFixedRate(command, initialDelay, period, unit);
        MyScheduledTask<?> myTask=(MyScheduledTask<?>)task;
        myTask.setPeriod(TimeUnit.MILLISECONDS.convert(period,unit));
        return task;
    }
}
