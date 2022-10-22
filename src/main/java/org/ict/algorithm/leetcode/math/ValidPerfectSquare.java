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
     * This is a math problem：
     * 1 = 1
     * 4 = 1 + 3
     * 9 = 1 + 3 + 5
     * 16 = 1 + 3 + 5 + 7
     * 25 = 1 + 3 + 5 + 7 + 9
     * 36 = 1 + 3 + 5 + 7 + 9 + 11
     * ....
     * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = n*n.
     * @param num
     * @return
     */
    public boolean isPerfectSquareV5(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    /**
     * See SqrtX.java Newton-Raphson Iteration Method
     * @param num
     * @return
     */
    public boolean isPerfectSquareV4(int num) {
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
    public boolean isPerfectSquareV3(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x)  / 2;
        }
        return x * x == num;
    }
    public boolean isPerfectSquareV2(int num) {
        long low = 1;
        long high = num;
        while (low <= high) {
            long mid = low + ((high - low) >> 1);
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public boolean isPerfectSquareV1(int num) {
        long low = 1;
        long high = num;
        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public boolean isPerfectSquare(int num) {
        double p = Math.sqrt(num);
        return (p - (int)p) == 0;
    }
}
