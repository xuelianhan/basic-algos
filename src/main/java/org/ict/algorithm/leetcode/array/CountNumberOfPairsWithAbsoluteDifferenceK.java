package org.ict.algorithm.leetcode.array;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author sniper
 * @date 15 Oct, 2022
 * LC2006
 */
public class CountNumberOfPairsWithAbsoluteDifferenceK {


    public int countKDifferenceV1(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> frequency = new TreeMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        //todo
        return res;
    }

    /**
     * Brute Force Solution
     * @param nums
     * @param k
     * @return
     */
    public int countKDifference(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1;  j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    res++;
                }
            }
        }
        return res;
    }
}
