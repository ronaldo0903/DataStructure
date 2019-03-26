package titi.Learning.Sort;

public class HeapSorter implements Sorter {

	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		/*
		 * 建立最大堆
		*/		 
		int bIndex = (N -2) >> 1;
		for(int i=bIndex; i>=0; i--) {
			heapify(i,N-1,a);
		}
		//利用最大堆实现排序
		for(int i=N-1; i>0; i--) {
			exchange(a, 0, i);
			heapify(0, i-1, a);
		}
		assert isSorted(a);
		
	}
	
	private void heapify(int index, int len, Comparable[] arr) {
		int lc = index << 1 + 1;
		int rc = lc + 1;
		int cMax = lc;
		if(lc > len) return;
		if(rc <= len && less(arr[lc],arr[rc])) cMax = rc;
		if(less(arr[index], arr[cMax])) {
			exchange(arr, index, cMax);
			heapify(cMax, len, arr);
		}
		
	}

}
