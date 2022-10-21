package org.ict.algorithm.leetcode.math;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Follow up: Do not use any built-in library function such as sqrt.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 16
 * Output: true
 * Example 2:
 *
 * Input: num = 14
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= num <= 2^31 - 1
 * @author sniper
 * @date 21 Oct, 2022
 * LC367
 */
public class ValidPerfectSquare {

    /**
     * See SqrtX.java Newton-Raphson Iteration Method
     * @param num
     * @return
     */
    public boolean isPerfectSquareV2(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }

    /**
     * See SqrtX.java Newton-Raphson Iteration Method
     * @param num
     * @return
     */
    public boolean isPerfectSquareV1(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x)  / 2;
        }
        return x * x == num;
    }

    public boolean isPerfectSquare(int num) {
        double p = Math.sqrt(num);
        return (p - (int)p) == 0;
    }
}
