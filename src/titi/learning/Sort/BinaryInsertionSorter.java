package titi.learning.Sort;

public class BinaryInsertionSorter implements Sorter {

	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		for(int i=1; i<N; i++) {
			Comparable v = a[i];
			int low=0, high = i;
			while(low < high) {
				int mid = (low + high)/2;
				if(less(v, a[mid])) high = mid;
				else low = mid+1;
			}
			for(int j=i; j>low; j--) {
				a[j] = a[j-1];
			}
			a[low] = v;
		}
		assert isSorted(a);
		
	}

}
