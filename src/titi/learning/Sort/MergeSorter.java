package titi.learning.Sort;

public class MergeSorter implements Sorter {

	protected Comparable[] aux;
	protected void merge(Comparable[] a, int lo, int mid, int hi) {
		int i=lo, j=mid +1;
		for(int ii=lo; ii<=hi; ii++) {
			aux[ii] = a[ii]; 
		}
		for(int k=lo; k<=hi; k++) {			
			if(i>mid) a[k] = aux[j++];
			else if(j>hi) a[k] = aux[i++];
			else if(less(aux[j],aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
		
	}
	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		aux = new Comparable[a.length];
		sort(a, 0, a.length -1);
		
	}
	private void sort(Comparable[] a, int low, int high) {
		// TODO Auto-generated method stub
		if(high <= low) return;
		int mid = (low + high)/2;
		sort(a, low, mid);
		sort(a, mid+1, high);
		merge(a, low, mid, high);		
	}
}