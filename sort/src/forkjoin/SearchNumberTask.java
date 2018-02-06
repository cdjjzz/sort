package forkjoin;

import java.util.concurrent.RecursiveTask;

public class SearchNumberTask extends RecursiveTask<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numbers[];
	private int start,end,number;
	
	private  TaskManager taskManager;
	
	private final static int NOT_FOUND=-1;

	

	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		return null;
	}

}
