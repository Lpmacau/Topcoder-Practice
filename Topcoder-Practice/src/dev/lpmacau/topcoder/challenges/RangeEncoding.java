package dev.lpmacau.topcoder.challenges;

public class RangeEncoding {
	public static void main(String args[]) {
		
		int[] a = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
		int[] b = {1,6,10,20,32,49};
		int[] c = {2,4,5,6,8,9,10,11,12,15};
		

		System.out.println(minRanges(a));
		System.out.println(minRanges(b));
		System.out.println(minRanges(c));
	}

	private static int minRanges(int[] arr) {
		int n = arr.length;
		int prev = arr[0];
		int nBreaks = 1;
		
		for(int i = 1; i<n;i++) {
			if(arr[i] > prev +1) nBreaks++;
			prev = arr[i];
		}
		return nBreaks;
		
		
	}
	
}
