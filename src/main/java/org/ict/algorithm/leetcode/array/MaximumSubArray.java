package org.ict.algorithm.leetcode.array;

/**
 * Given an integer array nums, 
 * find the contiguous subarray (containing at least one number) 
 * which has the largest sum and return its sum.
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * If you have figured out the O(n) solution, 
 * try coding another solution using the divide and conquer approach, 
 * which is more subtle.
 *
 * LC53
 *
 */
public class MaximumSubArray {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * The idea is to find the largest difference between the sums when you summing up the array from left to right. 
	 * The largest difference corresponds to the sub-array with largest sum. 
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV3(int[] nums) {
		int sum = 0, min = 0, result = nums[0];
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sum - min > result) {
				result = sum - min;
			}
			if (sum < min) {
				min = sum;
			}
		}
		return result;
	}
	
	/**
	 * this problem was discussed by Jon Bentley (Sep. 1984 Vol. 27 No. 9 Communications of the ACM P885)
	 * the paragraph below was copied from his paper (with a little modifications)
	 * algorithm that operates on arrays: it starts at the left end (element A[1]) and scans through to the right end (element A[n]), 
	 * keeping track of the maximum sum subvector seen so far. The maximum is initially A[0].
	 * Suppose we've solved the problem for A[1 .. i - 1]; how can we extend that to A[1 .. i]? 
	 * The maximum sum in the first I elements is either the maximum sum in the first i - 1 elements (which we'll call MaxSoFar), 
	 * or it is that of a subvector that ends in position i (which we'll call MaxEndingHere).
	 * MaxEndingHere is either A[i] plus the previous MaxEndingHere, or just A[i], whichever is larger.
	 *  
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV2(int[] nums) {
		int maxSoFar = nums[0], maxEndingHere = nums[0];
		for (int i = 1; i < nums.length; i++) {
			maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
			maxSoFar = Math.max(maxEndingHere, maxSoFar);
		}
		return maxSoFar;
	}
 	
	/**
	 * Kadane's algorithm with the cases for the local maximum explicitly spelled out
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV1(int[] nums) {
		int max = Integer.MIN_VALUE, sum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (sum < 0) {
				sum = nums[i];
			} else {
				sum += nums[i];
			}
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}
}
