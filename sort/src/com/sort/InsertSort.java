package com.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * @author pet-lsf
 *
 */
public class InsertSort {
	
	private static int length=5;
	
	private static int[] sortSrc=new int[length];
	
	static{
		for (int i = 0; i <length; i++) {
			Random random=new Random();
			sortSrc[i]=random.nextInt();
		}
	}
	
	public static void main(String[] args) {
		//print(sortSrc);
		//halfSort();
		//shellSort();
		//sort();
		//print(sortSrc);
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
		long startTime=System.currentTimeMillis();
		for(int i=1;i<sortSrc.length;i++){
			int temp=sortSrc[i];
			int j=i;
			while(j>0&&sortSrc[j-1]>temp){//在已排好序的数组中插入
				sortSrc[j]=sortSrc[j-1];
				j--;
			}
			sortSrc[j]=temp;
		}
		System.out.println(System.currentTimeMillis()-startTime);
	}
	/**
	 * 折半插入排序
	 * @Title: halfSort 
	 * @Description: TODO
	 * @param 
	 * @return void 
	 * @throws
	 */
	public static void halfSort(){
		long startTime=System.currentTimeMillis();
		for(int i=1;i<sortSrc.length;i++){
			int temp=sortSrc[i];
			int begin=0;
			int end=i-1;
			while(begin<=end){//在已排好序的数组中二分查找应该插入的位置
				int mid=(begin+end)>>1;
				if(temp>sortSrc[mid]){
					begin=mid+1;
				}else{
					end=mid-1;
				}
			}
			//移动i 到begin之间的数据
			 for (int j = i; j > begin; j--) {
				 sortSrc[j] = sortSrc[j - 1];
	         }
			 sortSrc[begin]=temp;
		}
		System.out.println(System.currentTimeMillis()-startTime);
	}
	/**
	 * 时间复杂度和稳定性
	 * @Title: shellSort 
	 * @Description: TODO
	 * @param 
	 * @return void 
	 * @throws
	 */
	public static void shellSort(){
		int h=1;//增量
		while(h<=sortSrc.length/4){
			h=h*4+1;
		}
		while(h>0){
			System.out.println("当前h的值为："+h);
			for(int i=h;i<sortSrc.length;i++){
				int temp=sortSrc[i];
				int j=i-h;
				if(temp<sortSrc[j]){
					for(;j>=0&&sortSrc[j]>temp;j-=h){
						sortSrc[j+h]=sortSrc[j];
					}
					sortSrc[j + h] = temp;
				}
			}
			h = (h - 1) / 4;
		}
	}
	/**
	 * 选择排序
	 * @Title: selectSort 
	 * @Description: TODO
	 * @param 
	 * @return void 
	 * @throws
	 */
	public static void selectSort(){
		for (int i = 0; i < sortSrc.length-1; i++) {
			int position=i;
			int key=sortSrc[i];
			for(int j=i+1;i<sortSrc.length;i++){
				if(sortSrc[j]<key){
					key=sortSrc[j];
					position=j;
				}
			}
			sortSrc[position]=sortSrc[i];
			sortSrc[i]=key;
		}
		
	}
	/**
	 * 
	 * @Title: sheSort 
	 * @Description: TODO
	 * @param 
	 * @return void 
	 * @throws
	 */
	public  static void sheSort(){
		int d=sortSrc.length;
		while(d>0){
			d=d/3;
			for(int i=0;i<d;i++){//组数
				for(int j=i+d;j<sortSrc.length;j++){//组中的元素
					int x=j-d;//取出最后一位有序元素位置
					int temp=sortSrc[j];//要插入的元素
					for(;x>=0&&sortSrc[x]>temp;x-=d){//比较有序元素是否大于要添加的元素，不大就前一位有序元素。
						sortSrc[x+d]=sortSrc[x];//后移
					}
					sortSrc[x+d]=temp;
				}
			}
		} 
	}
	
	public void mergeSort(){
		
	}
	
	
	
	public static  void  print(int[] sortSrc){
		for (int i = 0; i <sortSrc.length; i++) {
			System.out.print(sortSrc[i]+"\t");
		}
		System.out.println();
		
	}
	
}
