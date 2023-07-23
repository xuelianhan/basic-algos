package org.ict.algorithm.leetcode.prefixsum;

import java.util.Arrays;

/**
 * There is a biker going on a road trip.
 * The road trip consists of n + 1 points at different altitudes.
 * The biker starts his trip on point 0 with altitude equal 0.
 *
 * You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i and i + 1 for all (0 <= i < n).
 * Return the highest altitude of a point.
 *
 *
 *
 * Example 1:
 * Input: gain = [-5,1,5,0,-7]
 * Output: 1
 * Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
 *
 * Example 2:
 * Input: gain = [-4,-3,-2,-1,4,3,2]
 * Output: 0
 * Explanation: The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.
 *
 *
 * Constraints:
 * n == gain.length
 * 1 <= n <= 100
 * -100 <= gain[i] <= 100
 * @author sniper
 * @date 23 Jul 2023
 * LC1732, Easy
 */
public class FindTheHighestAltitude {

    /**
     * Time Cost 0ms
     * -----------------------------
     * class Solution:
     *     def largestAltitude(self, gain: List[int]) -> int:
     *         res, prefix_sum = 0, 0
     *         for i in range(1, len(gain) + 1):
     *             prefix_sum += gain[i - 1]
     *             res = max(res, prefix_sum)
     *         return res
     * ------------------------------
     * class Solution {
     * public:
     *     int largestAltitude(vector<int>& gain) {
     *         int n = gain.size(), prefix_sum = 0, res = 0;
     *         for (int i = 1; i < n + 1; i++) {
     *             prefix_sum += gain[i - 1];
     *             res = max(res, prefix_sum);
     *         }
     *         return res;
     *     }
     * };
     * --------------------------------
     * object Solution {
     *     def largestAltitude(gain: Array[Int]): Int = {
     *         val n = gain.length
     *         var prefixSum = 0
     *         var res = 0
     *         for (i <- 1 to n) {
     *             prefixSum += gain(i - 1)
     *             res = math.max(res, prefixSum)
     *         }
     *         return res
     *     }
     * }
     * --------------------------------------
     * impl Solution {
     *     pub fn largest_altitude(gain: Vec<i32>) -> i32 {
     *         let mut n = gain.len();
     *         let mut prefix_sum = 0;
     *         let mut res = 0;
     *
     *         for i in 1..(n + 1) {
     *             prefix_sum += gain[i - 1];
     *             res = std::cmp::max(res, prefix_sum);
     *         }
     *
     *         return res;
     *     }
     * }
     * @param gain
     * @return
     */
    public int largestAltitudeV2(int[] gain) {
        int n = gain.length;
        int prefixSum = 0;
        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum += gain[i - 1];
            res = Math.max(res, prefixSum);
        }
        return res;
    }


    /**
     * Time Cost 0ms
     * @param gain
     * @return
     */
    public int largestAltitudeV1(int[] gain) {
        int n = gain.length;
        int[] prefix = new int[n + 1];
        int res = prefix[0];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] += prefix[i - 1] + gain[i - 1];
            res = Math.max(res, prefix[i]);
        }
        return res;
    }


    /**
     * Time Cost 1ms
     * @param gain
     * @return
     */
    public int largestAltitude(int[] gain) {
        int n = gain.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] += prefix[i - 1] + gain[i - 1];
        }
        Arrays.sort(prefix);
        return prefix[n];
    }
}
