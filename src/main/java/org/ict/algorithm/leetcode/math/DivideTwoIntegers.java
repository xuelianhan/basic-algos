package org.ict.algorithm.leetcode.math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note:
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1].
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
        System.out.println(Integer.MAX_VALUE);//0x7fffffff, 2147483647 = 2^31-1
        System.out.println(Integer.MIN_VALUE);//0x80000000, -2147483648 = -2^31
        System.out.println(0xc0000000);//-1073741824 = -2^31 / 2 = -2^30

        //int dividend = -2147483648;
        //int divisor = 2;

        //int dividend = 15;
        //int divisor = 2;

        int dividend = 2147483647;
        int divisor = 3;

        DivideTwoIntegers instance = new DivideTwoIntegers();
        int result = instance.divide(dividend, divisor);
        System.out.println(result);
        System.out.println("result:" + result + " == " + 0xc0000000 + ":" + (result == 0xc0000000));
    }



    public int divideV3(int dividend, int divisor) {

        return 0;
    }

    public int divideV2(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

        return 0;
    }

    /**
     * @param dividend
     * @param divisor
     * @return
     */
    public int divideV1(int dividend, int divisor) {
        /**
         * Corner case:
         * e.g. dividend = -2^31, divisor = -1
         */
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        /**
         * Check sign
         */
        int negative = 2;
        /**
         * Convert positive numbers to negative
         * e.g. dividend = -2^31, divisor = 1
         * If we convert -2^31 to positive, it will be overflow, because the biggest positive number is 2^31-1.
         */
        if (dividend > 0) {
            dividend = - dividend;
            negative--;
        }
        if (divisor > 0) {
            divisor = - divisor;
            negative--;
        }
        /**
         * Notice here, the dividend and divisor are both negative numbers.
         */
        int result = divideHelper(dividend, divisor);
        /**
         * dividend:+, divisor:-, result:-
         * dividend:-, divisor:+, result:-
         * dividend:-, divisor:-, result:+
         * dividend:+, divisor:+, result:+
         */
        if (negative == 1) {
            result = - result;
        }
        return result;
    }

    /**
     * e.g.
     * dividend = -2147483648, divisor = 1
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        /**
         * Corner case:
         * e.g. dividend = -2^31, divisor = -1
         */
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        /**
         * Check sign
         */
        int sign = (dividend < 0) ^ (divisor < 0) ? - 1 : 1;
        /**
         * Convert positive numbers to negative
         * e.g. dividend = -2^31, divisor = 1
         * If we convert -2^31 to positive, it will be overflow, because the biggest positive number is 2^31-1.
         */
        if (dividend > 0) {
            dividend = - dividend;
        }
        if (divisor > 0) {
            divisor = - divisor;
        }
        /**
         * Notice here, the dividend and divisor are both negative numbers.
         */
        int result = divideHelper(dividend, divisor);
        if (sign == -1) {
            result = - result;
        }
        return result;
    }

    /**
     * Java Integer range:-2^31 ~ 2^31-1
     * hexadecimal
     * 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15
     * 0,1,2,3,4,5,6,7,8,9,A, B, C, D, E, F
     * 2^31: 1000 0000 0000 0000 0000 0000 0000 0000
     *       0111 1111 1111 1111 1111 1111 1111 1111
     * -2^31 1000 0000 0000 0000 0000 0000 0000 0000
     * -2^31 0x8   0    0    0    0   0    0    0
     *
     * 2^30: 0100 0000 0000 0000 0000 0000 0000 0000
     *       1011 1111 1111 1111 1111 1111 1111 1111
     * -2^30 1100 0000 0000 0000 0000 0000 0000 0000
     * -2^30 0x C   0    0    0    0    0    0    0
     * dividend:-2^31, divisor:-1
     * value: -1, quotient:1, value > -2^30 and dividend < -2
     * value: -2, quotient:1, value > -2^30 and dividend < -4
     *
     * e.g. dividend = -15, divisor = -2
     * value:-2, value > -2^30 and dividend < (-2 + -2), value:-4, quotient:2
     * value:-4, value > -2^30 and dividend < (-4 + -4), value:-8, quotient:4
     * value:-8, value > -2^30 and dividend > (-8 + -8), inner-while-loop-end
     * result: 0 + 4 = 4, dividend = dividend - (-8) = -15 + 8 = -7
     *
     * dividend:-7, -7 < -2
     * value:-2, value > -2^30 and dividend < (-2 + -2), value:-4, quotient:2
     * value:-4, value > -2^30 and dividend > (-4 + -4), inner-while-loop-end
     * result: 4 + 2 = 6, dividend = dividend - (-4) = -7 + 4 = -3
     *
     * dividend:-3, -3 < -2
     * value:-2, value > -2^30 and dividend > (-2 + -2), inner-while-loop-end
     * result: 6 + 1 = 7, dividend = dividend - (-2) = -3 + 2 = -1
     *
     * dividend:-1 > divisor:-2, outer-while-loop-end
     * result:7
     *
     * @param dividend
     * @param divisor
     * @return
     */
    private int divideHelper(int dividend, int divisor) {
        int result = 0;
        while (dividend <= divisor) {
            int value = divisor;
            /**
             * quotient initialized as 1, min initialized as 0xc0000000.
             * 0xc0000000 = Integer.MIN_VALUE / 2, we are not allowed to use divide here, so we use 0xc0000000 directly.
             * Why 0xc0000000? because binary partition up to the half of the target is enough, among all the divisors,
             * the 2 is the minimum, 1 has been considered as corner case at the beginning.
             * So the value double limit is Integer.MIN_VALUE / 2, not Integer.MIN_VALUE / 3, or others.
             */
            int quotient = 1;
            int min = 0xc0000000;
            /**
             * (-2^31) / (-1) = 2^31 lead to overflow, so we must control the lower bound of value.
             * If we don't do this, we will get Time Limit Exceed error.
             * value >= -2^31 can't be written as value > Integer.MIN_VALUE, why?
             * Consider case: dividend = 2147483647, divisor = 3, expected 715827882
             * if using value > Integer.MIN_VALUE, then output 1073741824, not satisfy the expected value 715827882.
             *
             */
            while (value >= min && dividend <= (value + value)) {
                value += value;
                quotient += quotient;
            }
            result += quotient;
            dividend -= value;
        }
        return result;
    }

    /**
     * Can't process case:
     * dividend == 0x80000000 and divisor == -1
     * dividend == 0x80000000 and divisor == 2
     * @param dividend
     * @param divisor
     * @return
     */
    public int divideWrong(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int sign = (dividend < 0) ^ (divisor < 0) ? - 1 : 1;

        /**
         * Note that if the argument is equal to the value of
         * Integer.MIN_VALUE, the most negative representable
         * value, the result is that same value, which is
         * negative.
         * e.g. Math.abs(-2^31) = -2^31
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
