package org.ict.algorithm.fundamentals;

import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;

/**
 * Compilation:javac org/ict/algorithm/fundamentals/TwoSum.java
 * Execution: java org/ict/algorithm/fundamentals/TwoSum  ../resources/1Kints.txt
 * Reads n integers and counts the number of pairs that sum to zero.
 * Running time: n^2
 *
 */
public class TwoSum {

	public static int countAndOutput(int[] a) {
		int count = 0; 
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				if (a[i] + a[j] == 0) {
					count++;
					StdOut.println(a[i] + " " + a[j]);
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		int[] a = in.readAllInts();
        int count = countAndOutput(a);
        StdOut.println(count);
	}
}
