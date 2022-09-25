package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * 
 * Given an integer array nums,
 * return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k,
 * and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 *
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 *
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 *
 * LC15
 */
public class ThreeSum {


	/**
	 * Understand this solution.
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSumV1(int[] nums) {
		if (nums == null || nums.length < 3) {
			return null;
		}
		/**
		 * 1.Use Set to remove duplicated triplets.
		 */
		Set<List<Integer>> set = new HashSet<>();
		/**
		 * 2.Sort before using two-pointer.
		 */
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			/**
			 * 3.Process like binary search.
			 */
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				if (sum == 0) {
					set.add(Arrays.asList(nums[i], nums[j], nums[k]));
					j++;
					k--;
				} else if (sum > 0) {
					k--;
				} else if (sum < 0) {
					j++;
				}
			}
		}
		/**
		 * 4.Convert set back to list.
		 */
		return new ArrayList<>(set);
	}


	/**
	 * A basic idea is to convert it to Two-Sum problem.
	 * x + y + z = 0
	 * x + y = -z
	 * target is -z, we need to find two numbers add up to -z in the array.
	 *
	 *
	 * Input: nums = [-1,0,1,2,-1,-4]
	 * After sort: = [-4,-1,-1,0,1,2]
	 *
	 * Expected: [[-1,-1,2],[-1,0,1]]
	 *
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		if (nums == null || nums.length < 3) {
			return null;
		}
		/**
		 * Use Set to remove duplicated triplets.
		 */
		Set<List<Integer>> set = new HashSet<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length-2; i++) {
			/**
			 * target = -z
			 */
			int target = -nums[i];
			Map<Integer, Integer> map = new HashMap<>();
			for (int j = i + 1; j < nums.length; j++) {
				/**
				 * y = -z - x
				 */
				int complement = target - nums[j];
				if (map.containsKey(complement)) {
					List<Integer> list = new ArrayList<>();
					/**
					 * x = nums[j]
					 * y = complement
					 * z = -target
					 */
					list.add(nums[j]);
					list.add(complement);
					list.add(-target);

					set.add(list);
				}
				/**
				 * x = nums[j]
				 */
				map.put(nums[j], j);
			}
		}
		/**
		 * Convert set back to list.
		 */
		return new ArrayList<>(set);
	}

	

}
