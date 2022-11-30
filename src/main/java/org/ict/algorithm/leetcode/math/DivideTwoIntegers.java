package org.ict.algorithm.leetcode.math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1].
 * For this problem, if the quotient is strictly greater than 2^31 - 1, then return 2^31 - 1,
 * and if the quotient is strictly less than -2^31, then return -2^31.
 *
 *
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = 3.33333.. which is truncated to 3.
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 *
 *
 * Constraints:
 *
 * -2^31 <= dividend, divisor <= 2^31 - 1
 * divisor != 0
 * @author sniper
 * @date 30 Nov, 2022
 * LC29
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        DivideTwoIntegers instance = new DivideTwoIntegers();
        int x =  0x80000000;
        System.out.println(x);
        System.out.println(Integer.MAX_VALUE);//0x7fffffff
        System.out.println(Integer.MIN_VALUE);//0x80000000
        int dividend = -2147483648;
        int divisor = 2;
        int result = instance.divide(dividend, divisor);
        System.out.println(result);
    }

    public int divideV3(int dividend, int divisor) {
        return 0;
    }

    public int divideV2(int dividend, int divisor) {
        return 0;
    }

    public int divideV1(int dividend, int divisor) {
        return 0;
    }

    public int divide(int dividend, int divisor) {
        int sign = (dividend < 0) ^ (divisor < 0) ? - 1 : 1;

        /**
         * Note that if the argument is equal to the value of
         * Integer.MIN_VALUE, the most negative representable
         * value, the result is that same value, which is
         * negative.
         */
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        int quotient = 0;
        while (dividend >= divisor) {
            dividend -= divisor;
            quotient++;
        }

        if (sign == -1) {
            quotient = - quotient;
        }
        return quotient;
    }
}
