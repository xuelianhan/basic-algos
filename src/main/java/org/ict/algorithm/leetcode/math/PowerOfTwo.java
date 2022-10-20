package org.ict.algorithm.leetcode.math;

/**
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 *
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 *
 * Input: n = 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 *
 * Input: n = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * -2^31 <= n <= 2^31 - 1
 *
 *
 * Follow up: Could you solve it without loops/recursion?
 * @author sniper
 * @date 20 Oct, 2022
 * LC231
 */
public class PowerOfTwo {

    public boolean isPowerOfTwoV4(int n) {
        return n > 0 && (n & n - 1) == 0;
    }

    public boolean isPowerOfTwoV3(int n) {
        if (n <= 0) {
            return false;
        }
        return (n&(n-1)) == 0;
    }

    /**
     * Power of 2 means only one bit of n is '1'.
     * @param n
     * @return
     */
    public boolean isPowerOfTwoV2(int n) {
        if (n <= 0) {
            return false;
        }
        return Integer.bitCount(n) == 1;
    }


    public boolean isPowerOfTwoV1(int n) {
        if (n <= 0) {
            return false;
        }
        return (Math.pow(2,31)%n == 0);
    }

    /**
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        int a = 2;
        int b = n;
        double p = Math.log10(b)/Math.log10(a);
        return (p - (int)p) == 0;
    }
}
