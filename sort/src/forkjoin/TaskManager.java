package forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
/**
 * 管理全部任务
 * @author pet-lsf
 *
 */
public class TaskManager {
	
	private List<ForkJoinTask<Integer>> tasks;
	
	public  TaskManager(List<ForkJoinTask<Integer>> tasks){
		this.tasks=tasks;
	}
	
	public void addTask(ForkJoinTask<Integer> task){
		tasks.add(task);
	}
	public void cancelTasks(ForkJoinTask<Integer> cancelTask){
		for (ForkJoinTask<Integer> task :tasks) {
			if (task!=cancelTask) {
				task.cancel(true);
				//((SearchNumberTask)task).writeCancelMessage();
			}
	  }
	}

}
