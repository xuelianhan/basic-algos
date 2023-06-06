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
 * LC15, Medium, frequency=123
 */
public class ThreeSum {

	/**
	 * Understanding the following solution
	 * Sorting + Two-Pointer
	 * -----------------------------------------------
	 * class Solution {
	 * public:
	 *     vector<vector<int>> threeSum(vector<int>& nums) {
	 *         int n = nums.size();
	 *         vector<vector<int>> res;
	 *         sort(nums.begin(), nums.end());
	 *
	 *         for (int i = 0; i < n - 2; i++) {
	 *             if (i > 0 && nums[i] == nums[i - 1]) {
	 *                 continue;
	 *             }
	 *             int lo = i + 1;
	 *             int hi = n - 1;
	 *             while (lo < hi) {
	 *                 int sum = nums[i] + nums[lo] + nums[hi];
	 *                 if (sum == 0) {
	 *                     res.push_back({nums[i], nums[lo], nums[hi]});
	 *                     lo++;
	 *                     hi--;
	 *                     while (lo < hi && nums[lo] == nums[lo - 1]) {
	 *                         lo++;
	 *                     }
	 *                     while (lo < hi && nums[hi] == nums[hi + 1]) {
	 *                         hi--;
	 *                     }
	 *                 } else if (sum < 0) {
	 *                     lo++;
	 *                 } else if (sum > 0) {
	 *                     hi--;
	 *                 }
	 *             }
	 *         }
	 *         return res;
	 *     }
	 * };
	 * -------------------------------------------------------
	 * class Solution:
	 *     def threeSum(self, nums: List[int]) -> List[List[int]]:
	 *         n = len(nums)
	 *         nums.sort()
	 *         res = []
	 *         for i in range(n - 2):
	 *             if i > 0 and nums[i] == nums[i - 1]:
	 *                 continue
	 *             lo, hi = i + 1, n - 1
	 *             while lo < hi:
	 *                 sum_t = nums[i] + nums[lo] + nums[hi]
	 *                 if sum_t < 0:
	 *                     lo += 1
	 *                 elif sum_t > 0:
	 *                     hi -= 1
	 *                 else:
	 *                     res.append([nums[i], nums[lo], nums[hi]])
	 *                     lo, hi = lo + 1, hi - 1
	 *                     while lo < hi and nums[lo] == nums[lo - 1]:
	 *                         lo += 1
	 *                     while lo < hi and nums[hi] == nums[hi + 1]:
	 *                         hi -= 1
	 *         return res
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSumV4(int[] nums) {
		if (nums == null || nums.length < 3) {
			return new ArrayList<>();
		}
		Arrays.sort(nums);
		int n = nums.length;
		List<List<Integer>> res = new ArrayList<>();

		for (int i = 0; i < n - 2; i++) {
			/**
			 * Skip duplicated items
			 */
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int lo = i + 1;
			int hi = n - 1;
			while (lo < hi) {
				int sum = nums[i] + nums[lo] + nums[hi];
				if (sum == 0) {
					res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
					lo++;
					hi--;
					/**
					 * A little tuning, skip duplicated items
					 */
					while (lo < hi && nums[lo] == nums[lo - 1]) {
						lo++;
					}
					while (lo < hi && nums[hi] == nums[hi + 1]) {
						hi--;
					}
				} else if (sum < 0) {
					lo++;
				} else if (sum > 0) {
					hi--;
				}
			}
		}
		return res;
	}


	/**
	 * Understanding the following solution
	 * Sorting + Two-Pointer
	 * ---------------------------------------
	 *
	 * Time Cost 49ms
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSumV3(int[] nums) {
		if (nums == null || nums.length < 3) {
			return new ArrayList<>();
		}
		Set<List<Integer>> set = new HashSet<>();
		Arrays.sort(nums);
		int n = nums.length;
		for (int i = 0; i < n - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int lo = i + 1;
			int hi = n - 1;
			while (lo < hi) {
				int sum = nums[i] + nums[lo] + nums[hi];
				if (sum == 0) {
					set.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
					lo++;
					hi--;
					/**
					 * A little tuning
					 */
					while (lo < hi && nums[lo] == nums[lo - 1]) {
						lo++;
					}
					while (lo < hi && nums[hi] == nums[hi + 1]) {
						hi--;
					}
				} else if (sum < 0) {
					lo++;
				} else if (sum > 0) {
					hi--;
				}
			}
		}
		return new ArrayList<>(set);
	}

	/**
	 * Understanding the following solution
	 * Sorting + Two-Pointer
	 * -------------------------------------------------
	 *
	 * Time Cost 47ms
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSumV2(int[] nums) {
		if (nums == null || nums.length < 3) {
			return null;
		}
		Set<List<Integer>> set = new HashSet<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			/**
			 * Skip continuous duplicated items.
			 */
			if (i > 0 && nums[i] == nums[i-1]) {
				continue;
			}
			/**
			 * Fixed one element nums[i], and search for another two elements: nums[lo], nums[hi]
			 */
			int lo = i + 1;
			int hi = nums.length - 1;
			while (lo < hi) {
				int sum = nums[i] + nums[lo] + nums[hi];
				if (sum == 0) {
					/**
					 * short j++, k-- to one line.
					 */
					set.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
					lo++;
					hi--;
				} else if (sum > 0) {
					hi--;
				} else if (sum < 0) {
					lo++;
				}
			}
		}

		return new ArrayList<>(set);
	}


	/**
	 * Time Cost 683ms
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
	 * Time Cost 984ms
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
		int n = nums.length;
		/**
		 * Use Set to remove duplicated triplets.
		 */
		Set<List<Integer>> set = new HashSet<>();
		Arrays.sort(nums);
		for (int i = 0; i < n - 2; i++) {
			/**
			 * target = -z
			 */
			int target = -nums[i];
			Map<Integer, Integer> map = new HashMap<>();
			for (int j = i + 1; j < n; j++) {
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
