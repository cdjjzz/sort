package com.sort;



/**
 * @author pet-lsf
 *
 */
public class InsertSort {
	
	private static int[] sortSrc={4444,3333,2222,1111};
	
	public static void main(String[] args) {
		print(sortSrc);
		sort();
		print(sortSrc);
		
	}
	
	/**
	 * 稳定 
	 *	空间复杂度O(1) 
	 *	时间复杂度O(n2) 
	 *	最差情况：反序，需要移动n*(n-1)/2个元素 
	 *	最好情况：正序，不需要移动元素
	 * @Title: sort 
	 * @Description: TODO
	 * @param 
	 * @return void 
	 * @throws
	 */
	public static void sort(){
		int count=0;
		for(int i=1;i<sortSrc.length;i++){
			boolean flag=false;
			int temp=sortSrc[i];
			int j=i;
			while(j>0&&sortSrc[j-1]>temp){
				sortSrc[j]=sortSrc[j-1];
				j--;
				count++;
				flag=true;
			}
			if(!flag)
			count++;
			sortSrc[j]=temp;
		}
		System.out.println(count);
	}
	
	
	
	
	public static  void  print(int[] sortSrc){
		for (int i = 0; i <sortSrc.length; i++) {
			System.out.print(sortSrc[i]+"\t");
		}
		System.out.println();
		
	}
	
}
