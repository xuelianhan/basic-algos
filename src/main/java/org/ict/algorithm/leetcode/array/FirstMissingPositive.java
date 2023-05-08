package org.ict.algorithm.leetcode.array;

/**
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 *
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 *
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * LC41, Hard, frequency=28
 */
public class FirstMissingPositive {
	
	public static void main(String[] args) {
		int[] nums = {7, 8, 9, 11, 12};

		FirstMissingPositive instance = new FirstMissingPositive();
		int res = instance.firstMissingPositive(nums);
		System.out.println(res);
	}
	
	/**
	 * The basic idea is to traversal and try to move the current value to position whose index is exactly the value (swap them). 
	 * Then traversal again to find the first unusual value, which can not be corresponding to its index
	 * @param nums
	 * @return
	 */
	public int firstMissingPositiveV2(int[] nums) {
		int i = 0, n = nums.length;
		while (i < n) {
			// If the current value is in the range of (0,length) and it's not at its correct position,
			// swap it to its correct position.
			// Else just continue;
			if (nums[i] >= 0 && nums[i] < n && nums[nums[i]] != nums[i]) {
				swap(nums, i, nums[i]);
			} else {
				i++;
			}
		}
		int k = 1;
		// Check from k=1 to see whether each index and value can be corresponding.
		while (k < n && nums[k] == k) {
			k++;
		}
		// If it breaks because of empty array or reaching the end. K must be the first missing number.
		if (n == 0 || k < n) {
			return k;
		} else {
			// If k is hiding at position 0, K+1 is the number.
			return nums[0] == k ? k + 1 : k;
		}
	}


	/**
	 * The key here is to use swapping to keep constant space
	 * and also make use of the length of the array,
	 * which means there can be at most n positive integers.
	 * So each time we encounter an valid integer,
	 * find its correct position and swap.
	 * Otherwise, we continue.
	 *
	 * @param nums
	 * @return
	 */
	public int firstMissingPositiveV1(int[] nums) {
		int i = 0;
		int n = nums.length;
		while (i < n) {
			if (nums[i] == i + 1 || nums[i] <= 0 || nums[i] > n) {
				i++;
			} else if (nums[nums[i] - 1] != nums[i]) {
				swap(nums, i, nums[i] - 1);
			} else {
				i++;
			}
		}
		i = 0;
		while(i < n && nums[i] == i + 1) {
			i++;
		}
		return i + 1;
    }

	/**
	 * Understanding the following solution
	 *
	 * Put each number in its right place.
	 * For example:
	 * When we find 5, then swap it with A[4].
	 * At last, the first place where its number is not right, return the place + 1.
	 * @author makuiyu
	 * @see <a href="https://leetcode.com/problems/first-missing-positive/solutions/17071/my-short-c-solution-o-1-space-and-o-n-time"></a>
	 * @param nums
	 * @return
	 */
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; ++i) {
			// why use while in here? not if statement?
			while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
				swap(nums, nums[i], nums[nums[i] - 1]);
			}
		}
		for (int i = 0; i < n; ++ i) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		return n + 1;
	}


	public void swap(int[] A, int i, int j){
		if (i != j) {
			A[i] ^= A[j];
			A[j] ^= A[i];
			A[i] ^= A[j];
		}
	}
}
