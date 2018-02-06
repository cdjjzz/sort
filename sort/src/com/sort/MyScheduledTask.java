package com.sort;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
	
	private RunnableScheduledFuture<V> task;
	
	private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
	
	private long period;
	
	private long startDate;

	public MyScheduledTask(Callable<V> callable,V result,RunnableScheduledFuture<V> task,ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
		super(task, result);
		this.task=task;
		this.scheduledThreadPoolExecutor=scheduledThreadPoolExecutor;
	}
	public MyScheduledTask(Runnable runnable,V result,RunnableScheduledFuture<V> task,ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
		super(task, result);
		this.task=task;
		this.scheduledThreadPoolExecutor=scheduledThreadPoolExecutor;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		if(isPeriodic()){
			return task.getDelay(unit);
		}else{
			if(startDate==0){
				return task.getDelay(unit);
			}else{
				Date date=new Date();
				return unit.convert(startDate-date.getTime(),TimeUnit.MILLISECONDS);
			}
		}
	}

	@Override
	public int compareTo(Delayed o) {
		return task.compareTo(o);
	}
	//判断当前定时器是否是连续
	/**
	 * 定时器:Delayed :延时指定时间执行一次，Periodic:间隔指定时间重复执行任务
	 */
	@Override
	public boolean isPeriodic() {
		return task.isPeriodic();
	}
	@Override
	public void run() {
		  if (isPeriodic() && (!scheduledThreadPoolExecutor.isShutdown())) {
	            Date now=new Date();
	            startDate=now.getTime()+period;
	            scheduledThreadPoolExecutor.getQueue().add(this);
	        }
	        System.out.printf("Pre-MyScheduledTask: %s\n",new Date());
	        System.out.printf("MyScheduledTask: Is Periodic: %s\n",isPeriodic());
	        super.runAndReset();//运行并重置状态为NEW
	        System.out.printf("Post-MyScheduledTask: %s\n",new Date());
	}
	/**
     * Method that establish the period of the task for periodic tasks
     * @param period
     */
    public void setPeriod(long period) {
        this.period=period;
    }

}
