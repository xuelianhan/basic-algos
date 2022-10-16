package org.ict.algorithm.leetcode.array;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given an integer array nums and an integer k,
 * return the number of pairs (i, j) where i < j such that |nums[i] - nums[j]| == k.
 *
 * The value of |x| is defined as:
 *
 * x if x >= 0.
 * -x if x < 0.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,1], k = 1
 * Output: 4
 * Explanation: The pairs with an absolute difference of 1 are:
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * Example 2:
 *
 * Input: nums = [1,3], k = 3
 * Output: 0
 * Explanation: There are no pairs with an absolute difference of 3.
 * Example 3:
 *
 * Input: nums = [3,2,1,5,4], k = 2
 * Output: 3
 * Explanation: The pairs with an absolute difference of 2 are:
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 1 <= k <= 99
 * @author sniper
 * @date 15 Oct, 2022
 * LC2006
 */
public class CountNumberOfPairsWithAbsoluteDifferenceK {

    /**
     * Same as countKDifferenceV4 but use TreeMap to count frequency.
     *
     * @param nums
     * @param k
     * @return
     */
    public int countKDifferenceV5(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> count = new TreeMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0)+1);
        }

        /**
         * Because nums[i] >= 1, and count.get(i-k), so i start with k+1.
         * TreeMap guarantee the sequence and the distance with k
         */
        for (int i = k + 1; i < 101; i++) {
            res += count.getOrDefault(i, 0) * count.getOrDefault(i - k, 0);
        }
        return res;
    }

    /**
     * Understand the following solution
     *
     * Solution provided by Vlad votrubac
     * cost 2ms
     *
     * Notice the condition: 1 <= nums[i] <= 100.
     *
     * We can count each number using an array.
     * Now, we can sweep the counts, and accumulate the product of k-apart counts.
     *
     * e.g.
     * nums = [1,2,2,1], k = 1
     * count:
     * 0 1 2 3 ...
     * 0 2 2 0 ...
     *
     * e.g.
     * nums = [3,2,1,5,4], k = 2
     * count:
     * 0 1 2 3 4 5 6 ...
     * 0 1 1 1 1 1 0
     *
     * @param nums
     * @param k
     * @return
     */
    public int countKDifferenceV4(int[] nums, int k) {
        int res = 0;
        int[] count = new int[101];
        for (int num : nums) {
            count[num]++;
        }

        /**
         * Because nums[i] >= 1, and count[i-k], so i start with k+1.
         */
        for (int i = k + 1; i < 101; i++) {
            res += count[i] * count[i - k];
        }
        return res;
    }

    /**
     * Using HashMap frequency count
     * cost 17ms
     *
     * @param nums
     * @param k
     * @return
     */
    public int countKDifferenceV3(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> frequency = new TreeMap<>();
        for (int num : nums) {
            res += frequency.getOrDefault(num + k, 0) + frequency.getOrDefault(num - k, 0);
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        return res;
    }

    /**
     * Using HashMap frequency count
     * cost 20ms
     * @param nums
     * @param k
     * @return
     */
    public int countKDifferenceV2(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> frequency = new TreeMap<>();
        for (int num : nums) {
            res += frequency.getOrDefault(num + k, 0);
            res += frequency.getOrDefault(num - k, 0);
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        return res;
    }


    /**
     * Understand the following solution
     * Using HashMap frequency count
     * cost 9ms
     *
     * a - b = k, a = b + k
     * b - a = k, a = b - k
     * @param nums
     * @param k
     * @return
     */
    public int countKDifferenceV1(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> frequency = new TreeMap<>();
        for (int num : nums) {
            if (frequency.containsKey(num + k)) {
                res += frequency.get(num + k);
            }
            if (frequency.containsKey(num - k)) {
                res += frequency.get(num - k);
            }
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        return res;
    }

    /**
     * Brute Force Solution
     * cost 14ms
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
