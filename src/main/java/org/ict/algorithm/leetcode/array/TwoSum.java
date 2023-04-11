package org.ict.algorithm.leetcode.array;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 *
 * Constraints:
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 *
 * Only one valid answer exists.
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n^2) time complexity?
 *
 * @author sniper
 * LC1, Easy, High Frequency
 */
public class TwoSum {

    /**
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     *
     * i:0
     * 9-2=7, map not contains 7 yet
     * so put 2,0
     *
     * i:1
     * 9-7=2, map contains 2 yet
     * so res[1] = 1
     *    res[0] = map.get(2)=0
     * return [0, 1] back.
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumV2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[1] = i;
                res[0] = map.get(target - nums[i]);
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /**
     * O(n);
     */
    public int[] twoSum(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return null;
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
