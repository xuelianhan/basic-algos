package org.ict.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are given an integer array nums with the following properties:
 *
 * nums.length == 2 * n.
 * nums contains n + 1 unique elements.
 * Exactly one element of nums is repeated n times.
 * Return the element that is repeated n times.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,1,2,5,3,2]
 * Output: 2
 * Example 3:
 *
 * Input: nums = [5,1,5,2,5,3,5,4]
 * Output: 5
 *
 *
 * Constraints:
 *
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 104
 * nums contains n + 1 unique elements and one of them is repeated exactly n times.
 * @author sniper
 * @date 27 Aug 2023
 * LC961, Easy, frequency=1
 */
public class NRepeatedElementInSize2NArray {

    public int repeatedNTimesV3(int[] nums) {
        for (int i = 0; i + 2 < nums.length; i++) {
            if (nums[i] == nums[i + 1] || nums[i] == nums[i + 2]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    public int repeatedNTimesV2(int[] nums) {
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] || nums[i] == nums[i - 2]) {
                return nums[i];
            }
        }
        return nums[0];
    }

    public int repeatedNTimesV1(int[] nums) {
        int res = -1;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            if (freq.get(num) > 1) {
                res = num;
                break;
            }
        }
        return res;
    }

    public int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                res = nums[i];
                break;
            }
            set.add(nums[i]);
        }
        return res;
    }
}
