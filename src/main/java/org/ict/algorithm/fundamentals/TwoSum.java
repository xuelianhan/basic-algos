package org.ict.algorithm.fundamentals;

import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;
import java.util.Arrays;

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

    /**
     * Given an array of integers, return indices of the two numbers such that they
     * add up to a specific target.
     * Assume that each input would have exactly one solution, and do not use the same
     * element twice.
     * The brute-force approach is simple.Loop through each element x and find if there
     * is another value that equals to target -x.
     */
    public static int[] twoSumPair(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] == target - a[i]) {
					StdOut.println(a[i] + " " + a[j]);
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum pair solution");
    }
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		int[] a = in.readAllInts();
        int count = countAndOutput(a);
        StdOut.println(count);
        int[] pair = twoSumPair(a, 0);
        StdOut.println(Arrays.toString(pair));
	}
}
