package titi.Learning.Sort;

public class QuickSorter implements Sorter {

	@Override
	public void sort(Comparable[] a) {
		quickSort(a,0,a.length-1);
		assert isSorted(a);
	}
	
	private int getMiddle(Comparable[] a, int low, int high) {
		Comparable temp = a[low];
		while(low<high) {
			while(low < high && less(temp,a[high])) high--;
			a[low] = a[high];
			while(low < high && less(a[low], temp)) low++;
			a[high] = a[low];
		}
		a[low] = temp;
		return low;
	}
	
	private void quickSort(Comparable[] a, int low, int high) {
		if(low<high) {
			int mid = getMiddle(a,low,high);
			quickSort(a,low,mid-1);
			quickSort(a,mid+1, high);
		}
	}

}
