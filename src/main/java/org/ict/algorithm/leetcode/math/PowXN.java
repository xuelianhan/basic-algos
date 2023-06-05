package org.ict.algorithm.leetcode.math;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
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
 * Either x is not zero or n > 0.
 * -10^4 <= x^n <= 10^4
 *
 * @author sniper
 * @date 28 Mar, 2023
 * LC50, Medium, frequency=165
 */
public class PowXN {

    /**
     * Understanding the following solution.
     * e.g. x = 2.0, n = 5
     * i:5, i % 2 != 0, res:2.0, x = 4.0
     * i:2, i % 2 == 0, x = 16.0
     * i:1, i % 2 != 0, res:2.0*16.0=32.0, x=256.0
     * i:0, i == 0, exit-for-loop
     * n > 0, return res:32.0
     * @param x
     * @param n
     * @return
     */
    public double myPowV2(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }


    /**
     * Time Complexity logN, based on 2.
     * Understanding the following solution.
     * @param x
     * @param n
     * @return
     */
    public double myPowV1(double x, int n) {
        if (n == 0) {
            return 1;
        }
        long m = n;
        if (m < 0) {
            m = -m;
            x = 1 / x;
        }
        /**
         * Binary Exponentiation
         */
        double res = 1;
        for (; m > 0; m >>= 1) {
            if ((m & 1) > 0) {
                res *= x;
            }
            x *= x;
        }
        return res;
    }


    /**
     * Understanding the following solution
     * If the exponent n is negative, we need to change it to positive exponent −n and make the base to 1/x,
     * then apply Binary Exponentiation.
     * @see <a href="https://leetcode.com/problems/powx-n/solutions/19544/5-different-choices-when-talk-with-interviewers"></a>
     * e.g. x:2.0, n:-2147483648, if you use int, it may overflow while change n to -n
     * -----------------------------------------------
     * class Solution:
     *     def myPow(self, x: float, n: int) -> float:
     *         m = n
     *         if m < 0:
     *             m = -m
     *             x = 1 / x
     *         res = 1.0
     *         while m > 0:
     *             if m & 1 > 0:
     *                 res *= x
     *             x *= x
     *             m >>= 1
     *         return res
     * -----------------------------------------------
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        /**
         * To prevent m = -m overflow, we use long type m to replace the int type n.
         */
        long m = n;
        if (m < 0) {
            m = -m;
            x = 1 / x;
        }
        /**
         * Binary Exponentiation
         */
        double res = 1;
        while (m > 0) {
            /**
             * e.g. m = 7 = Binary(111)
             * m & 1 = 7 & 1 = B(111) & B(001) = B(001) = 1
             * It means get the lowest bit of m's binary form.
             * m >> 1 can discard the lowest bit.
             * If we combine m & 1 and m >> 1, we can traverse all the binary digits of m.
             */
            if ((m & 1) > 0) {// equals m % 2 != 0, m & 1 > 0 means m is an odd number
                res *= x;
            }
            x *= x;
            m >>= 1;// equals m / 2
        }
        return res;
    }
}
