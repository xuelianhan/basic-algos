package org.ict.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums, return the number of good pairs.
 *
 * A pair (i, j) is called good if nums[i] == nums[j] and i < j.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1,1,3]
 * Output: 4
 * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
 * Example 2:
 *
 * Input: nums = [1,1,1,1]
 * Output: 6
 * Explanation: Each pair in the array are good.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 * @author sniper
 * @date 20 Sep, 2022
 * LC1512
 */
public class NumberOfGoodPairs {

    public int numIdenticalPairsV2(int[] nums) {
        //todo
        return 0;
    }


    /**
     * todo
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int cnt = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            Integer v = entry.getValue();
            if (v > 1) {
                cnt += (v - 1) * v / 2;
            }
        }
        return cnt;
    }
}
