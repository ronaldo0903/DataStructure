package titi.learning.Sort;

public class InsertionXSorter implements Sorter {

	@Override
	public void sort(Comparable[] a) {
		/*Improved Insertion Sort */
		int N = a.length;
		int exchanges = 0;
		for(int i=N-1; i>0; i--) {
			if(less(a[i], a[i-1])) {
				exchange(a, i, i-1);
				exchanges ++;
			}
		}
		if(exchanges == 0) return;
		for(int i=2; i<N; i++) {
			Comparable v = a[i];
			int j=i;
			while(less(v,a[j-1])) {
				a[j] = a[j-1];
				j--;
			}
			a[j] = v;
		}
		assert isSorted(a);
	}

}
