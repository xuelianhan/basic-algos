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
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−2^31, 2^31 − 1].
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

    /**
     * Integer.MIN_VALUE - 1 = Integer.MAX_VALUE
     * Integer.MAX_VALUE + 1 = Integer.MIN_VALUE
     * @param args
     */
    public static void main(String[] args) {
        //int[] arr = {-2147483648, 2};
        //int[] arr = {15, 2};
        //int[] arr = {2147483647, 3};
        //int[] arr = {-2147483648, 1};
        int[] arr = {1, 1};
        /**
         * -2147483648 - 1 = -2^31 - 1 = 2^31 - 1, because it occurs overflow.
         */
        System.out.println("arr[0] - arr[1]:" + (arr[0] - arr[1]));
        int dividend = arr[0];
        int divisor = arr[1];

        DivideTwoIntegers instance = new DivideTwoIntegers();
        int result = instance.divideV4(dividend, divisor);
        System.out.println(result);
        //testDemo();
    }


    public static void testDemo() {
        System.out.println(Integer.MAX_VALUE);//0x7fffffff, 2147483647 = 2^31-1
        System.out.println(Integer.MIN_VALUE);//0x80000000, -2147483648 = -2^31
        System.out.println(0xc0000000);//-1073741824 = -2^31 / 2 = -2^30

        /**
         * Validate the shift operator precedence and its associativity.
         * e.g. (b << x << 1)
         * At first it will calculate b << x, let's mark its result as middle:
         * middle = b << x, and then we start to calculate middle << 1.
         */
        int b = 1;
        int x = 2;
        System.out.println("b << x:" + (b << x));
        System.out.println("x << 1:" + (x << 1));
        int y = (b << x << 1);
        System.out.println("y:" + y);
        System.out.println("1 << 4:" + (1 << 4));

        x = 1 << 31;
        /**
         * x:-2147483648 = -2^31
         */
        System.out.println("x:" + x);

        /**
         * y:-2147483648 = -2^31 = 2^31 + 1
         * Integer.MIN_VALUE - 1 = Integer.MAX_VALUE
         * Integer.MAX_VALUE + 1 = Integer.MIN_VALUE
         */
        y = Integer.MAX_VALUE + 1;
        System.out.println("y:" + y);
    }


    /**
     * Time Cost 2ms
     * Bit manipulation solution
     * Time Complexity O(32)
     *
     * dividend = (quotient) * divisor + remainder
     *
     * a - b >= 0 will utilize the result of overflow, but a >= b won't do that. Let's consider an case:
     * a = -2^31, b = 1
     * a - b = -2^31 - 1 overflow to 2^31 - 1.
     *
     * On the other hand, Integer.MAX_VALUE = 0x7fffffff.
     * 0x7fffffff - (-1) = 0x7fffffff + 1, the result will overflow to Integer.MIN_VALUE=0x80000000
     * Based on above analysis, we can get an conclusion, overflow of Integer in Java can make a circle,
     * MAX overflow result by 1 is MIN, MIN overflow result by 1 is MAX
     *
     * @author lee215
     * @param dividend
     * @param divisor
     * @return
     */
    public int divideV6(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        int res = 0;
        for (int x = 31; x >= 0; x--) {
            /**
             * Use unsigned right shift, try from high bit to low bit.
             * we can't use signed right shift >> in here. Why?
             * Consider a case:
             * dividend: -2147483648
             * divisor:1
             * expected: -2147483648, if you use >>, you will get -1 at last. Why?
             * when x > 0 and x <= 31, the result ((a >> x) - b)is a negative.
             * when x == 0, ((a >> x) - b) = (-2147483648 >> 0 - 1) = -2147483648 - 1, it overflows to 2147483647.
             * res = 0 + (1 << 0) = 1, a = a - b << 0 = a - b = -2147483648 - 1, it's also 2147483647.
             * because the sign is negative, so finally return -res, that's -1 we get.
             */
            if ((a >>> x) - b >= 0) {
                res += 1 << x;
                a -= b <<x;
            }
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }

    /**
     * Time Cost 2ms.
     * Solution same as divideV4, only difference is using for-loop to replace inner-while-loop.
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divideV5(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        int res = 0;

        while (a - b >= 0) {
            int x = 0;
            for (;(a - (b << x << 1)) >= 0; x++);

            a -= b << x;
            res += 1 << x;
        }

        return (dividend > 0) == (divisor > 0) ? res : -res;
    }

    /**
     * Time Cost 3ms.
     * The description note that:
     * "Assume we are dealing with an environment,
     * which could only store integers within the 32-bit signed integer range: [−2^31, 2^31 − 1]."
     * the most of the solutions use "long" integer, so I share my solution here.
     *
     * Only one corner case is -2^31 / 1 and I deal with it at the first line.
     * This solution has O(logN^2) time complexity.
     *
     * dividend = (quotient) * divisor + remainder
     * @author lee215
     * @param dividend
     * @param divisor
     * @return
     */
    public int divideV4(int dividend, int divisor) {
        if (dividend == 1 << 31 && divisor == -1) {
            /**
             * Utilize the overflow here:
             * (1 << 31) = -2^31
             * (1 << 31) - 1 = -2^31 - 1 overflow to 2^31 - 1
             */
            return (1 << 31) - 1;
        }
        /**
         * Utilize overflow again! because Math.abs(-2^31) = -2^31
         */
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        int res = 0;
        /**
         * Utilize overflow the third time.
         * a - b >= 0, other than a >= b.
         * e.g. -2^31 - 1 overflow to 2^31 - 1, which is greater than zero, so it can go into the while-loop.
         * If being wrote as a >= b, -2^31 >= 1 is false, it never goes into the while-loop.
         *
         */
        while (a - b >= 0) {
            /**
             * operator << calculate from left to right,
             * b << x firstly, then its result << 1.
             * when x == 0, b << x is still b, b << x << 1 turns out b << 1,
             * 1 << x is 1, res = res + 1
             * b << x is b, a = a - b.
             *
             * (b << x << 1) effects equals b << (x + 1), because we are not allowed to use addition, so
             * using (b << x << 1) to replace b << (x + 1)
             * x is the final maximum shift bits count which satisfy the (a - b << (x + 1));
             */
            int x = 0;
            while (a - (b << x << 1) >= 0) {
                x++;
            }
            res += 1 << x;
            a -= b << x;
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }


    /**
     * Time Cost 2ms
     *
     * Understanding this solution.
     *
     * The description provided by pprabu49:
     *
     * Basic mathematical intuition:
     * (dividend / divisor) = quotient + (remainder / divisor)
     *
     * Programmatic Rule:
     * Left Shift (<<) shall be considered as multiplication by 2^N
     * Similarly, Right Shift (>>) shall be considered as division by 2^N
     * Since the environment only allows to accommodate integer data type,
     * overflow cases are the corner cases.
     *
     * First of all, As we all know already the basic idea for solving the problem
     * without the mod operator is simply identifying the number of times the divisor can fully
     * SUBSTRACT the dividend as a whole but iterations are so costly and it will not work for large numbers.
     * The obvious secret is to identify effective way to reduce the number of substractions.
     *
     * The java solution is very elegant and nicely handles the overflow!
     * Didn't realize that a-b >= 0 is not the same as a >= b when overflow happens.
     *
     * @author dontsovigor1
     * @see <a href="https://leetcode.com/problems/divide-two-integers/solutions/1327339/java-0ms-100-faster-obeys-all-conditions">pprabu49</a>
     * @param dividend
     * @param divisor
     * @return
     */
    public int divideV3(int dividend, int divisor) {
        if (Integer.MIN_VALUE == dividend && -1 == divisor) {
            return Integer.MAX_VALUE;
        }
        int result = 0;
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        /**
         * e.g.1. a = 1, b = 1, so we need >= , other than >
         * e.g.2. a = -2147483648, b = 1, so we need a - b >= 0, other than a >= b
         * Math.abs(-2147483648) = -2147483648, it's still a negative number due to overflow.
         * so a >= b can represent as -2147483648 >= 1, it's not true, while-loop ends, and return 0;
         * but if you use (a - b >= 0), x = a - b = -2147483648 - 1 = 2147483647, it's the overflow result.
         * Integer.MIN_VALUE is 0x80000000, -2147483648, when it overflows, it turns out to be Integer.MAX_VALUE
         */
        while (a - b >= 0) {
            int temp = b;
            int quotient = 1;
            while ((a - (temp << 1)) >= 0) {
                temp <<= 1;
                quotient <<= 1;
            }
            result += quotient;
            a -= temp;
        }
        return (dividend > 0) == (divisor > 0) ? result : -result;
    }

    /**
     * Time Cost 1ms
     * Understanding this solution.
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divideV2(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean sign = (dividend < 0) == (divisor < 0);
        dividend = (dividend > 0 ? - dividend : dividend);
        divisor = (divisor > 0 ? - divisor : divisor);
        int result = divideHelperV2(dividend, divisor);
        return (sign ? result : - result);
    }

    private int divideHelperV2(int dividend, int divisor) {
        if (divisor < dividend) {
            return 0;
        }
        int sum = divisor;
        int quotient = 1;
        while ((Integer.MIN_VALUE - sum) <= sum && dividend <= (sum + sum)) {
            sum += sum;
            quotient+= quotient;
        }
        return quotient + divideHelperV2(dividend - sum, divisor);
    }


    /**
     * Time Cost 2ms
     * Understanding this solution.
     *
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
     * Time Cost 2ms
     *
     * Understanding this solution.
     *
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
     * Time Complexity O(LogN^2)
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
            dividend -= value;
            result += quotient;
        }
        return result;
    }

    /**
     * Can't process case:
     * dividend == 0x80000000 and divisor == -1
     * dividend == 0x80000000 and divisor == 2
     *
     * Time Complexity O(LogN^2)
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
