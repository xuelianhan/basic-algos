package org.ict.algorithm.leetcode.math;

/**
 *
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 *
 * An integer n is a power of three, if there exists an integer x such that n == 3^x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 27
 * Output: true
 * Explanation: 27 = 3^3
 * Example 2:
 *
 * Input: n = 0
 * Output: false
 * Explanation: There is no x where 3^x = 0.
 * Example 3:
 *
 * Input: n = -1
 * Output: false
 * Explanation: There is no x where 3^x = (-1).
 *
 *
 * Constraints:
 *
 * -2^31 <= n <= 2^31 - 1
 *
 *
 * Follow up: Could you solve it without loops/recursion?
 *
 * @author sniper
 * @date 26 Sep, 2022
 * LC326
 */
public class PowerOfThree {

    public boolean isPowerOfThreeV1(int n) {
        return false;
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        return false;
    }

}
