package org.ict.algorithm.leetcode.array;

import java.util.Map;
import java.util.HashMap;

/**
 * p1
 * tag:array
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, 
 * where index1 must be less than index2. 
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2 
 * LC1
 * @author sniper
 */
public class TwoSum {
  
  /**
   * O(n);
   *
   */
  public static int[] twoSum(int[] nums, int target) {
    if (nums.length == 0) {
        throw new IllegalArgumentException("input nums must not be empty");
    }
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement) && map.get(complement) != i) {
            return new int[]{i, map.get(complement)};
        }
        map.put(nums[i], i);
    }
    return new int[]{0};
  }
  
}
