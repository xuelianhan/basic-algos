package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given an integer array nums, find the subarray
 * with the largest sum, and return its sum.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -104 <= nums[i] <= 10^4
 *
 * Follow up: If you have figured out the O(n) solution,
 * try coding another solution using the divide and conquer approach,
 * which is more subtle.
 *
 * LC53, Medium, frequency=152
 *
 */
public class MaximumSubArray {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * Understanding the following solution
	 * Dynamic Programming Solution
	 * ---------------------------------
	 * class Solution:
	 *     def maxSubArray(self, nums: List[int]) -> int:
	 *       f, res, i, n = nums[0], nums[0], 1, len(nums)
	 *       while i < n:
	 *         f = nums[i] + max(f , 0)
	 *         res = max(res, f)
	 *         i += 1
	 *       return res
	 * ----------------------------------
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV7(int[] nums) {
		int f = nums[0];
		int res = nums[0];
		for (int i = 1; i < nums.length; i++) {
			f = nums[i] + Math.max(f, 0);
			res = Math.max(res, f);
		}
		return res;
	}

	public int maxSubArrayV6(int[] nums) {
		int n = nums.length;
		/**
		 * dp[i] means the maximum sub-array ending with nums[i];
		 */
		int[] dp = new int[n];
		dp[0] = nums[0];
		int max = dp[0];

		for(int i = 1; i < n; i++){
			dp[i] = nums[i] + Math.max(dp[i - 1], 0);
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	/**
	 * Dynamic Programming
	 * 
	 * Analysis of this problem:
	 * Apparently, this is a optimization problem, which can be usually solved by DP
	 * So when it comes to DP, the first thing for us to figure out is the format of the sub problem(or the state of each sub problem). 
	 * The format of the sub problem can be helpful when we are trying to come up with the recursive relation
	 * 
	 * At first, I think the sub problem should look like: 
	 * maxSubArray(int A[], int i, int j), which means the maxSubArray for A[i: j]
	 * In this way, our goal is to figure out what maxSubArray(A, 0, A.length - 1) is.
	 * However, if we define the format of the sub problem in this way, 
	 * it's hard to find the connection from the sub problem to the original problem(at least for me). 
	 * In other words, I can't find a way to divide the original problem into the sub problems
	 * and use the solutions of the sub problems to somehow create the solution of the original one
	 * 
	 * So I change the format of the sub problem into something like: 
	 * maxSubArray(int A[], int i), which means the maxSubArray for A[0:i ] which must has A[i] as the end element
	 * Note that now the sub problem's format is less flexible and less powerful than the previous one 
	 * because there's a limitation that A[i] should be contained in that sequence,
	 * and we have to keep track of each solution of the sub problem to update the global optimal value
	 * However, now the connection between the sub problem & the original one becomes clearer:
	 * maxSubArray(A, i) = maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0 + A[i]; 
	 * 
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV5(int[] nums) {
		int n = nums.length;
		/**
		 * dp[i] means the maximum subarray ending with A[i];
		 */
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        
        for(int i = 1; i < n; i++){
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        
        return max;
	}
	
	
	/**
	 * The idea is to find the largest difference between the sums when you summing up the array from left to right. 
	 * The largest difference corresponds to the sub-array with largest sum. 
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV4(int[] nums) {
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
	 * This problem was discussed by Jon Bentley (Sep. 1984 Vol. 27 No. 9 Communications of the ACM P885)
	 * The paragraph below was copied from his paper (with a little modifications)
	 * algorithm that operates on arrays: it starts at the left end (element A[1]) and scans through to the right end (element A[n]), 
	 * keeping track of the maximum sum subvector seen so far.
	 * The maximum is initially A[0]
	 * Suppose we've solved the problem for A[1 .. i - 1]; how can we extend that to A[1 .. i]? 
	 * The maximum sum in the first I elements is either the maximum sum in the first i - 1 elements (which we'll call MaxSoFar), 
	 * or it is that of a subvector that ends in position i (which we'll call MaxEndingHere),
	 * MaxEndingHere is either A[i] plus the previous MaxEndingHere, or just A[i], whichever is larger.
	 *  
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV3(int[] nums) {
		int maxSoFar = nums[0], maxEndingHere = nums[0];
		for (int i = 1; i < nums.length; i++) {
			maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
			maxSoFar = Math.max(maxEndingHere, maxSoFar);
		}
		return maxSoFar;
	}

	/**
	 * Kadane’s Algorithm
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV2(int[] nums) {
		int maxEndingHere = 0;
		int maxSoFar = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			maxEndingHere += nums[i];
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
			maxEndingHere = Math.max(0, maxEndingHere);
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

	/**
	 * Kadane’s Algorithm:
	 * The idea of Kadane’s algorithm is to maintain a variable max_ending_here that stores the maximum sum contiguous subarray
	 * ending at current index and a variable max_so_far stores the maximum sum of contiguous subarray found so far,
	 * Everytime there is a positive-sum value in max_ending_here compare it with max_so_far and update max_so_far
	 * if it is greater than max_so_far.
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		int maxEndingHere = 0;
		int maxSoFar = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			maxEndingHere += nums[i];
			if (maxSoFar < maxEndingHere) {
				maxSoFar = maxEndingHere;
			}
			if (maxEndingHere < 0) {
				maxEndingHere = 0;
			}
		}
		return maxSoFar;
	}
}
