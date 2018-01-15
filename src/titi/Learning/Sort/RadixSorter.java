package titi.Learning.Sort;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * ��������(10����)
 */
public class RadixSorter {
	private static final int[] NUMBERS = {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
	public static void radixSort(int[] array) {
		//��ȷ�������������Ϊ���������Ԫ�ص�λ��
		int max = array[0];
		for(int i=1; i<array.length; i++) {
			if(max < array[i]) max = array[i];			
		}
		int rounds = 0;
		while(max > 0) {
			max /= 10;
			rounds ++;
		}
		//����10�����У����ж���i���ŵ�ǰ���������λΪi������Ԫ��
		ArrayList<ArrayList<Integer>> queue = new ArrayList<>();
		for(int i=0; i<10; i++) {
			queue.add(new ArrayList<>());
		}
		//����rounds�����ݷ�����ռ�
		for(int i=0; i<rounds; i++) {
			for(int element : array) {
				//��ȡelement�ĵ�(i+1)λ������λΪ��һλ��ʮλΪ�ڶ�λ��...
				int curDigit = (int) (element % Math.pow(10, i+1) / Math.pow(10, i));
				queue.get(curDigit).add(element);				
			}
			//���ݵ�ǰ�ֵ�10��������������ԭ����
			int count = 0;
			for(int k=0; k<10; k++) {
				while(queue.get(k).size() > 0) {
					array[count++] = queue.get(k).get(0);
					queue.get(k).remove(0);
				}
			}
			System.out.printf("��%d����������������Ԫ��:%s \n", i+1, Arrays.toString(array));
		}
	}
	public static void main(String[] args) {
		System.out.printf("����ǰ������Ԫ��:%s \n", Arrays.toString(NUMBERS));
		radixSort(NUMBERS);
	}
}
