package org.ict.algorithm.leetcode.prefixsum;

/**
 * You are given a 0-indexed integer array nums of length n.
 *
 * The average difference of the index i is the absolute difference between the average of the first i + 1 elements of nums,
 * and the average of the last n - i - 1 elements.
 * Both averages should be rounded down to the nearest integer.
 *
 * Return the index with the minimum average difference. If there are multiple such indices, return the smallest one.
 *
 * Note:
 *
 * The absolute difference of two numbers is the absolute value of their difference.
 * The average of n elements is the sum of the n elements divided (integer division) by n.
 * The average of 0 elements is considered to be 0.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,3,9,5,3]
 * Output: 3
 * Explanation:
 * - The average difference of index 0 is: |2 / 1 - (5 + 3 + 9 + 5 + 3) / 5| = |2 / 1 - 25 / 5| = |2 - 5| = 3.
 * - The average difference of index 1 is: |(2 + 5) / 2 - (3 + 9 + 5 + 3) / 4| = |7 / 2 - 20 / 4| = |3 - 5| = 2.
 * - The average difference of index 2 is: |(2 + 5 + 3) / 3 - (9 + 5 + 3) / 3| = |10 / 3 - 17 / 3| = |3 - 5| = 2.
 * - The average difference of index 3 is: |(2 + 5 + 3 + 9) / 4 - (5 + 3) / 2| = |19 / 4 - 8 / 2| = |4 - 4| = 0.
 * - The average difference of index 4 is: |(2 + 5 + 3 + 9 + 5) / 5 - 3 / 1| = |24 / 5 - 3 / 1| = |4 - 3| = 1.
 * - The average difference of index 5 is: |(2 + 5 + 3 + 9 + 5 + 3) / 6 - 0| = |27 / 6 - 0| = |4 - 0| = 4.
 * The average difference of index 3 is the minimum average difference so return 3.
 * Example 2:
 *
 * Input: nums = [0]
 * Output: 0
 * Explanation:
 * The only index is 0 so return 0.
 * The average difference of index 0 is: |0 / 1 - 0| = |0 - 0| = 0.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * @author sniper
 * @date 04 Dec, 2022
 * LC2256
 */
public class MinimumAverageDifference {

    public static void main(String[] args) {
        double x = Math.pow(10, 10);
        //x:1.0E10 > 2147483647:true
        System.out.println("x:" + x + " > " + Integer.MAX_VALUE + ":" + (x > Integer.MAX_VALUE));
    }


    public int minimumAverageDifferenceV1(int[] nums) {
        return 0;
    }

    /**
     * e.g. nums = [2,5,3,9,5,3]
     * 0 1 2 3 4 5
     * 2 5 3 9 5 3
     *
     * prefix:
     * prefix[0] = nums[0]
     * prefix[1] = nums[0] + nums[1]
     *           = nums[1] + prefix[0]
     * prefix[2] = nums[0] + nums[1] + nums[2]
     *           = nums[2] + prefix[1]
     * prefix[3] = nums[0] + nums[1] + nums[2] + nums[3]
     *           = nums[3] = prefix[2]
     * prefix[4] = nums[0] + nums[1] + nums[2] + nums[3] + nums[4]
     *           = nums[4] + prefix[3]
     * prefix[5] = nums[0] + nums[1] + nums[2] + nums[3] + nums[4] + nums[5]
     *           = nums[5] + prefix[4]
     *
     * so, we can induce a formula of prefix sum:
     * prefix[i] = nums[i] + prefix[i-1]
     *
     *
     *
     * @param nums
     * @return
     */
    public int minimumAverageDifference(int[] nums) {
        int res = 0, n = nums.length;
        long min = Long.MAX_VALUE;
        /**
         * Use long instead of int to prevent prefix sum overflow.
         * e.g. 10^5*10^5 = 10^10 = (2^3 + 2)^10 > 2^31 - 1
         */
        long[] prefix = new long[n];
        prefix[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = nums[i] + prefix[i - 1];
        }

        for (int i = 0; i < n; i++) {
            /**
             * suffix sum = prefix[n - 1] - prefix[i]
             */
            long k = Math.abs((prefix[i] / (i + 1)) - ((prefix[n - 1] - prefix[i]) / Math.max(n - i - 1, 1)));
            if (k < min) {
                min = k;
                res = i;
            }
        }
        return res;
    }
}
