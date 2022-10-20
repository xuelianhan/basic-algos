package org.ict.algorithm.leetcode.math;

/**
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 *
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 16
 * Output: true
 * Example 2:
 *
 * Input: n = 5
 * Output: false
 * Example 3:
 *
 * Input: n = 1
 * Output: true
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
 * LC342
 */
public class PowerOfFour {

    /**
     * The basic idea is from power of 2, We can use "n&(n-1) == 0" to determine if n is power of 2.
     * For power of 4, the additional restriction is that in binary form, the only "1" should always located at the odd position.
     * For example, 4^0 = 1, 4^1 = 100, 4^2 = 10000.
     * So we can use "num & 0x55555555==num" to check if "1" is located at the odd position.
     * 
     * @param num
     * @return
     */
    public boolean isPowerOfFourV1(int num) {
        return isPowerOfTwo(num) && ((num & 0x55555555) == num);
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n - 1) == 0;
    }

    public boolean isPowerOfFour(int n) {
        int a = 4;
        int b = n;
        double p = Math.log10(b)/Math.log10(a);
        return (p - (int)p) == 0;
    }
}
