package org.ict.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 *
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 * @author sniper
 * @date 23 Sep, 2022
 * LC169
 */
public class MajorityElement {

    public int majorityElementV1(int[] nums) {

        return 0;
    }


    /**
     * Very Slow, cost 39 ms, the following solution only for reinforcing the understanding of this question.
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        Stream<Map.Entry<Integer, Integer>> sorted = frequency
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(((o1, o2) -> {
                    if (o1 > o2) {
                        return -1;
                    } else if (o1 < o2) {
                        return 1;
                    } else {
                        return 0;
                    }
                })));

        Map.Entry<Integer, Integer> entry = sorted.findFirst().orElse(null);
        if (null == entry) {
            return Integer.MIN_VALUE;
        }
        return entry.getKey();
    }
}
