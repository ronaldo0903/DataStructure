package titi.Learning.Sort;

public class ThreeWayQuickSorter implements Sorter {

	@Override
	public void sort(Comparable[] a) {
		quick_3_way_Sort(a,0,a.length-1);
		assert isSorted(a);
		
	}
	
	private void quick_3_way_Sort(Comparable[] a, int lo, int hi) {
		if(hi<=lo) return;
		int lt = lo, gt = hi, i = lo + 1;
		Comparable v = a[lo];
		while(i<=gt) {
			int cmp = a[i].compareTo(v);
			if(cmp < 0) exchange(a,i++,lt++);
			else if(cmp>0) exchange(a,i,gt--);
			else i++;
		}
		quick_3_way_Sort(a,lo,lt-1);
		quick_3_way_Sort(a,gt+1,hi);
	}

}
