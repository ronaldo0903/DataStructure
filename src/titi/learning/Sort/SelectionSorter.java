package titi.learning.Sort;

public class SelectionSorter implements Sorter {

	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		int N = a.length;
		for(int i=0; i<N; i++) {
			int min = i;
			for(int j=i+1; j<N; j++) {
				if(less(a[j], a[min])) min = j;
			}
			exchange(a, i, min);
		}
		assert isSorted(a);
	}

}
