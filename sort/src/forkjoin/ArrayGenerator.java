package forkjoin;

import java.util.concurrent.ThreadLocalRandom;

/**
 * ����һ������
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
