package org.ict.algorithm.leetcode.math;

/**
 * Given an integer num, return three consecutive integers (as a sorted array) that sum to num.
 * If num cannot be expressed as the sum of three consecutive integers,
 * return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 33
 * Output: [10,11,12]
 * Explanation: 33 can be expressed as 10 + 11 + 12 = 33.
 * 10, 11, 12 are 3 consecutive integers, so we return [10, 11, 12].
 * Example 2:
 *
 * Input: num = 4
 * Output: []
 * Explanation: There is no way to express 4 as the sum of 3 consecutive integers.
 *
 *
 * Constraints:
 *
 * 0 <= num <= 1015
 * @author sniper
 * @date 15 Jan, 2023
 * LC2177, Medium
 */
public class FindThreeConsecutiveIntegersThatSumToGivenNumber {

    /**
     * Notice that if a solution exists, we can represent them as x-1, x, x+1.
     * What does this tell us about the number?
     * x - 1 + x + x + 1 = num,
     * 3 * x = num,
     * this can induce that if num can be divided by 3, then x exists.
     * @param num
     * @return
     */
    public long[] sumOfThree(long num) {
        if (num % 3 != 0) {
            /**
             * empty array
             */
            return new long[]{};
        }
        long mid = num / 3;
        return new long[] {mid - 1, mid, mid + 1};
    }
}
