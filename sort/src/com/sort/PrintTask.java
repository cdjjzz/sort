package com.sort;

import java.util.concurrent.RecursiveAction;

public class PrintTask extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void compute() {
		
	}
	
	public static void main(String[] args) {
		System.out.println(System.nanoTime());
		System.out.println(System.currentTimeMillis());
	}

}
