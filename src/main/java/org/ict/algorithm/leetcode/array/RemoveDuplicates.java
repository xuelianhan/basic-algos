package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * 
 * Given a sorted array nums, 
 * remove the duplicates in-place such that each element appear only once 
 * and return the new length.
 * Do not allocate extra space for another array, 
 * you must do this by modifying the input array in-place with O(1) extra memory.
 * Example 1:
 * Given nums = [1,1,2],
 * Your function should return length = 2, 
 * with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 * Your function should return length = 5, 
 * with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
 * It doesn't matter what values are set beyond the returned length.
 * Clarification:
 * Confused why the returned value is an integer but your answer is an array?
 * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
 * Internally you can think of this:
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeDuplicates(nums);
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 *   print(nums[i]);
 * }
 *
 * LC26
 */
public class RemoveDuplicates {
	
	public static void main(String[] args) {
		int[] A = {0, 0, 1, 2};
		System.out.println(removeDuplicatesV1(A));
		//System.out.println(removeDuplicatesV2(A));

	}
	
	public static int removeDuplicatesV1(int[] A) {
		int n = A.length;
        if(n < 2) return n;
        int id = 1;
        for(int i = 1; i < n; ++i) {
        	if(A[i] != A[i-1]) {
        		A[id++] = A[i];
        	}
        	System.out.println("i=" + i + ",id="+ id + ",A:" + Arrays.toString(A));
        }
        System.out.println("id="+ id + ",A:" + Arrays.toString(A));
        return id;
    }

	public static int removeDuplicatesV2(int[] nums) {
	    int i = 0;
	    for (int e : nums) {
	    	if (i == 0 || e > nums[i-1]) {
	    		nums[i++] = e;
	    	}
	    	System.out.println("i=" + i + ",nums:" + Arrays.toString(nums));
	    }
		System.out.println("i=" + i + ",nums:" + Arrays.toString(nums));

	    return i;
	}
}
