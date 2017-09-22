package com.sort;

public class Test_common {
  
	
	public void insertSort(int[] srcSort){
		int size=srcSort.length;
		for (int i = 0; i <size; i++) {
			int temp=srcSort[i];
			int j=i;
			while(srcSort[j-1]>temp&&j>0){
				srcSort[j]=srcSort[j-1];//向后移一步
				j--;
			}
			srcSort[j]=temp;
		}
	}
	
	public void  halfInsertSort(int[] srcSort){
		int size=srcSort.length;
		for (int i = 0; i < size; i++) {
			int temp=srcSort[i];
			int start=0;
			int end=size-1;
			while(start<=end){
				int mid=(start+end)/2;
				if(temp>srcSort[mid]){
					start=mid+1;
				}else{
					end=mid-1;
				}
			}
			for(int j=i;j>start;j--){
				srcSort[j]=srcSort[j-1];//向后移动
			}
			srcSort[start]=temp;
		}
	}
	public void shellSort(int[] srcSort){
		int h=srcSort.length;
		while(h>0){
			h=h/3;
		}
		for (int i = 0; i <h; i++) {
			for (int j = i+h;j<srcSort.length; j++) {
				int temp=srcSort[i];
				int x=j-h;
				for(;x>=0&&srcSort[x]>temp;x-=h){
					srcSort[x+h]=srcSort[x];
				}
				srcSort[x+h]=temp;
			}
		}
	}
	public void selectSort(int[] srcSort){
		int siez=srcSort.length;
		for(int i=0;i<siez-1;i++){
			int position=i;
			int temp=srcSort[position];
			for(int j=i+1;j<siez;j++){
				if(temp>srcSort[j]){
					temp=srcSort[j];
					position=j;
				}
			}
			srcSort[position]=srcSort[i];
			srcSort[i]=temp;
		}
	}
}
