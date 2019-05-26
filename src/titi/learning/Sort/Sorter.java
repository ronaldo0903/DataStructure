package titi.learning.Sort;

interface Sorter{

	default boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	default void exchange(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	default void show(Comparable[] a) {
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");			
		}
		System.out.println();
	}
	default boolean isSorted(Comparable[] a) {
		for(int i=0; i< a.length - 1; i++) {
			if(less(a[i+1], a[i])) return false;
		}
		return true;
	}	
	void sort(Comparable[] a);

}
