package titi.Learning.Sort;

public class BubbleSorter implements Sorter {

	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		for(int i=0; i<N-1; i++) {
			for(int j=0; j<N-1-i; j++) {
				if(less(a[j+1], a[j])) {
					exchange(a, j+1, j);
				}
			}
		}
		assert isSorted(a); 		
	}

}
