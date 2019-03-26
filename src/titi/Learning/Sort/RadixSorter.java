package titi.Learning.Sort;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 基数排序(10进制)
 */
public class RadixSorter {
	private static final int[] NUMBERS = {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
	public static void radixSort(int[] array) {
		//先确定排序的趟数，为数组中最大元素的位数
		int max = array[0];
		for(int i=1; i<array.length; i++) {
			if(max < array[i]) max = array[i];			
		}
		int rounds = 0;
		while(max > 0) {
			max /= 10;
			rounds ++;
		}
		//建立10个队列，其中队列i里存放当前处理的数字位为i的数组元素
		ArrayList<ArrayList<Integer>> queue = new ArrayList<>();
		for(int i=0; i<10; i++) {
			queue.add(new ArrayList<>());
		}
		//进行rounds次数据分配和收集
		for(int i=0; i<rounds; i++) {
			for(int element : array) {
				//获取element的第(i+1)位数，个位为第一位，十位为第二位，...
				int curDigit = (int) (element % Math.pow(10, i+1) / Math.pow(10, i));
				queue.get(curDigit).add(element);				
			}
			//根据当前轮的10个队列重新排序原数组
			int count = 0;
			for(int k=0; k<10; k++) {
				while(queue.get(k).size() > 0) {
					array[count++] = queue.get(k).get(0);
					queue.get(k).remove(0);
				}
			}
			System.out.printf("第%d轮排序结束后的数组元素:%s \n", i+1, Arrays.toString(array));
		}
	}
	public static void main(String[] args) {
		System.out.printf("排序前的数组元素:%s \n", Arrays.toString(NUMBERS));
		radixSort(NUMBERS);
	}
}
