package org.ict.algorithm.fundamentals;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;

/**
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * You may assume that each input would have exactly on solution, and you may
 * not use the same element twice.
 * 
 * Compilation:javac org/ict/algorithm/fundamentals/TwoSum.java
 * Execution: java org/ict/algorithm/fundamentals/TwoSum  ../resources/1Kints.txt
=======
/**
>>>>>>> 6c9635f8... change package location
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
     * 
     * Complexity Analysis
     * Time complexity: O(n^2)
     * Space complexity: O(1)
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
    
    /**
     * To improve our run time complexity, we need a more efficient way to check if the complement exists in the array.
     * If the complement exists, we need to look up its index. What is the best way to maintain a mapping of each element
     * in the array to its index?A hash table.
     * We reduce the look up time from O(n) to O(1) by trading space for speed. A hash table is built exactly for this
     * purpose,it supports fast look up in near constant time. I say "near" because if a collision occurred, a look up could
     * degenerate to O(n) time. But look up in hash table should be amortized O(1) time as long as the hash function was chosen
     * carefully.
     * A simple implementation u ses two iterations. In the first iteration, we add each element's value and its index to the
     * table.Then, in the second iteration we check if each element's complement(target - nums[i]) exists in the table.
     * Beware that the complement must not be nums[i] itself!
     * 
     * Complexity Analysis
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int[] twoSumPair2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum pair solution");
    }
    
    /**
     * It turns out we can do it in one-pass.While we iterate and inserting elements into the table,
     * We also look back to check if current element's complement already exists in the table.If it
     * exists, we have found a solution and return immediately.
     * Complexity Analysis
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int[] twoSumPair3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
            map.put(nums[i], i);
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
        int[] pair2 = twoSumPair2(a, 0);
        StdOut.println(Arrays.toString(pair2));
        int[] pair3 = twoSumPair3(a, 0);
        StdOut.println(Arrays.toString(pair3));
	}
}
