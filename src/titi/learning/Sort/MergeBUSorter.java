package titi.learning.Sort;

public class MergeBUSorter extends MergeSorter {
	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for(int sz=1; sz < N; sz *= 2) {
			for(int lo=0; lo < N-sz; lo += sz) {
				merge(a,lo, lo+sz-1, Math.min(lo+sz*2-1, N-1));
			}
		}
		assert isSorted(a);
	}
}
