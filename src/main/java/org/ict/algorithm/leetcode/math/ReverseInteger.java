package org.ict.algorithm.leetcode.math;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 * Example 1:
 * Input: x = 123
 * Output: 321
 *
 * Example 2:
 * Input: x = -123
 * Output: -321
 *
 * Example 3:
 * Input: x = 120
 * Output: 21
 *
 *
 * Constraints:
 * -2^31 <= x <= 2^31 - 1
 * @author sniper
 * @date 29 Mar, 2023
 * LC7, Medium
 */
public class ReverseInteger {

    public static void main(String[] args) {
        int x = 1000000003;
        ReverseInteger instance = new ReverseInteger();
        int res = instance.reverseV2(x);
        System.out.println(res);
        boolean f1 = (x >= Integer.MIN_VALUE) && (x <= Integer.MAX_VALUE);
        System.out.println(f1);
        long y = 3000000001L;
        boolean f2 = (y >= Integer.MIN_VALUE) && (y <= Integer.MAX_VALUE);
        System.out.println(f2);
    }

    /**
     * Similar with reverseV2, but no need to subtract 10.
     *
     * @param x
     * @return
     */
    public int reverseV2(int x) {
        int res = 0;
        while (x != 0) {
            int remainder = x % 10;
            int newRes = res * 10 + remainder;
            /**
             * If newRes overflow, newRes != res, so return zero here
             * By the way, divided by 10 automatically discard the remainder digit.
             */
            if ((newRes / 10) != res) {
                return 0;
            }
            res = newRes;
            x = x / 10;
        }
        return res;
    }


    /**
     * e.g. x = 123
     * x:123, remainder:3, res:0, newRes=0+3=3, res:3, x=x/10=12
     * x:12, remainder:2, res:3, newRes=3*10+2=32, res:32, x=x/10=1
     * x:1, remainder:1, res:32, newRes=32*10+1=321, res:321, x=x/10=0
     * while-loop-end, return res:321
     *
     * e.g. x = 2147483647
     * e.g. x = 1000000003
     * e.g. x = 1534236469
     * newResult may be overflow, for instance, let x = 1000000003,
     * x in range [-2147483648, 2147483647], but if we reverse x, "3000000001",
     * it cannot be converted to int, because it exceeds the range of maximum int: 2147483647.
     * In normal case, newResult = result * 10 + tail,
     * result = (newResult - tail )/10, these two equations
     * are mutual inverse operation, but if overflow occurs, it's a different story.
     * However, if we use (newResult )/10 instead of (newResult - tail) / 10,
     * it won't affect the condition judgement,
     * because divided by 10 automatically discard the remainder tail.
     *
     * @param x
     * @return
     */
    public int reverseV1(int x) {
        int res = 0;
        while (x != 0) {
            int remainder = x % 10;
            int newRes = res * 10 + remainder;
            /**
             * newRes = res * 10 + remainder
             * If newRes overflow, (newRes - remainder)/10 != res, so return zero here
             */
            if ((newRes - remainder) / 10 != res) {
                return 0;
            }
            res = newRes;
            x = x / 10;
        }
        return res;
    }


    /**
     * x in -2147483648～2147483647
     * This problem requires that it won't allow to use long:
     * "Assume the environment does not allow you to store 64-bit integers (signed or unsigned)."
     * So the following solution is not matched, because it has used long type.
     * I put it at here only to provide a normal idea without datatype limitations.
     *
     * x:-2147483648
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        long res = 0;
        long X = x;
        long remainder = 0;
        int sign = 1;
        if (X < 0) {
            X = -X;
            sign = -1;
        }
        while (X > 0) {
            remainder = X % 10;
            res = (res * 10) + remainder;
            X /= 10;
        }
        return res > Integer.MAX_VALUE ? 0 : (int) res * sign;
    }
}
