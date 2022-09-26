package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * Given an array nums of n integers,
 * return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * LC18
 */
public class FourSum {
	

	/**
	 *  Basic idea is using sub-functions for 3sum and 2sum,
	 *  and keeping throwing all impossible cases. 
	 *  O(n^3) time complexity, 
	 *  O(1) extra space complexity.
	 */
	public List<List<Integer>> fourSumV1(int[] nums, int target) {
	    ArrayList<List<Integer>> ans = new ArrayList<>();
	    if (null == null || nums.length < 4) {
	    	return ans;
		}
	    Arrays.sort(nums);
	    for(int i = 0; i < nums.length - 3; i++){
	        if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
	        	break; //first candidate too large, search finished
	        }
	        if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) {
	        	continue; //first candidate too small
	        }
	        if (i > 0 && nums[i] == nums[i-1]) {
	        	continue; //prevents duplicate result in ans list
	        }
	        for (int j = i+1; j < nums.length-2; j++) {
	            if(nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) {
	            	break; //second candidate too large
	            }
	            if(nums[i] + nums[j] + nums[nums.length-1] + nums[nums.length-2] < target) {
	            	continue; //second candidate too small
	            }
	            if(j > i+1 && nums[j] == nums[j-1]) {
	            	continue; //prevents duplicate results in ans list
	            }
	            int low = j+1, high = nums.length-1;
	            while (low < high) {
	                int sum = nums[i] + nums[j] + nums[low] + nums[high];
	                if (sum == target) { 
	                    ans.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
	                    while(low < high && nums[low] == nums[low+1])low++; //skipping over duplicate on low
	                    while(low < high && nums[high] == nums[high-1])high--; //skipping over duplicate on high
	                    low++; 
	                    high--;
	                } else if (sum < target) {
	                	//move window
	                	low++; 
	                } else {
	                	//move window
	                	high--;
	                }
	            }
	        }
	    }
	    return ans;
	}


	/**
	 *
	 * [-2,-1,0,0,1,2], target=0
	 * [2,2,2,2,2], target=8
	 *
	 * [0,0,0,0], target=0
	 *
	 * [-5,-4,-3,-2,1,3,3,5], target=-11
	 *  i  j  low        high
	 *
	 *
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (null == nums || nums.length < 4) {
			return res;
		}
		if (target < 0 && nums[0] > 0) {
			return res;
		}
		Arrays.sort(nums);
		Set<List<Integer>> set = new HashSet<>();
		for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i+1; j < nums.length - 2; j++) {
				int low = j+1;
				int high = nums.length - 1;
				while (low < high) {
					int sum = nums[i] + nums[j] + nums[low] + nums[high];
					if (sum == target) {
						/**
						 * short j++, k-- to one line.
						 */
						set.add(Arrays.asList(nums[i], nums[j], nums[low++], nums[high--]));
					} else if (sum > target) {
						high--;
					} else if (sum < target) {
						low++;
					}
				}
			}
		}

		return new ArrayList<>(set);
	}
}

