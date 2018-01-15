package titi.Learning.Sort;

import java.util.Random;

import org.apache.commons.lang3.time.StopWatch;

public class SorterCompare {
	private static Random r = new Random();
	public static double time(String alg, Comparable[] a) {
		StopWatch timer = new StopWatch();
		timer.start();
		switch(alg) {
			case "Insertion":				
				new InsertionSorter().sort(a);
				break;
			case "InsertionX":				
				new InsertionXSorter().sort(a);
				break;
			case "BinaryInsertion":				
				new BinaryInsertionSorter().sort(a);
				break;
			case "Selection":
				new SelectionSorter().sort(a);
				break;
			case "Shell":
				new ShellSorter().sort(a);
				break;
			case "Merge":
				new MergeSorter().sort(a);
				break;
			case "MergeBU":
				new MergeBUSorter().sort(a);
				break;
			case "Quick":
				new QuickSorter().sort(a);
				break;
			case "Heap":
				new HeapSorter().sort(a);
				break;
			case "Bubble":
				new BubbleSorter().sort(a);
				break;
		}
		timer.stop();
		return timer.getTime();
	}
	public static double timeRandomInput(String alg, int N, int T) {
		double total = 0.0;
		Double[] a = new Double[N];
		for(int t=0; t<T; t++) {
			for(int i=0; i<N; i++) {
				a[i]= r.nextDouble();
			}
			total += time(alg, a);
		}
		return total;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String alg1 = args[0];
		String alg2 = args[1];
		int N = Integer.parseInt(args[2]);
		int T = Integer.parseInt(args[3]);
		double t1 = timeRandomInput(alg1, N, T);
		double t2 = timeRandomInput(alg2, N, T);
		System.out.printf("For %d random doubles\n %s takes %.4f seconds", N, alg1, t1/1000/T);
		System.out.printf(", %s takes %.4f seconds\n ", alg2, t2/1000/T);
	}
}
