package forkjoin;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 生成一个数组
 * @author pet-lsf
 *
 */
public class ArrayGenerator {
		public  int[] generateArray(int size){
			int array[]=new int[size];
			for (int i = 0; i < array.length; i++) {
				array[i]=ThreadLocalRandom.current().nextInt();
			}
			return array;
		}
}
