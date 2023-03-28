package org.ict.algorithm.leetcode.math;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
 *
 *
 * Constraints:
 *
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * n is an integer.
 * -10^4 <= xn <= 10^4
 *
 * @author sniper
 * @date 28 Mar, 2023
 * LC50, Medium
 */
public class PowXN {

    public double myPowV3(double x, int n) {
        double res = 0;
        return res;
    }

    public double myPowV2(double x, int n) {
        double res = 0;
        return res;
    }

    public double myPowV1(double x, int n) {
        double res = 0;
        return res;
    }


    /**
     * If the exponent n is negative, we need to change it to positive exponent −n and make the base to 1/x,
     * then apply Binary Exponentiation.
     * @see <a href="https://leetcode.com/problems/powx-n/solutions/19544/5-different-choices-when-talk-with-interviewers"></a>
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1 / x;
        }
        /**
         * Binary Exponentiation
         */
        double res = 1;
        if (N == 0) {
            return 1;
        }
        while (N > 0) {
            if ((N & 1) > 0) {
                res *= x;
            }
            x *= x;
            N >>= 1;
        }
        return res;
    }
}
