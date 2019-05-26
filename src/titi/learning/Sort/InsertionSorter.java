package titi.learning.Sort;

public class InsertionSorter implements Sorter {

	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		/* Elementrary Insertion Sort */
		for(int i=1; i<N; i++) {
			for(int j=i; j>0 && less(a[j], a[j-1]); j--) {
				exchange(a, j, j-1);
			}
		}
		assert isSorted(a);
	}
	
}
