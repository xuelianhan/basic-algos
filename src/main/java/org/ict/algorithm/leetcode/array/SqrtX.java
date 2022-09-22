package org.ict.algorithm.leetcode.array;

/**
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 *
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 4
 * Output: 2
 * Example 2:
 *
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 *
 *
 * Constraints:
 *
 * 0 <= x <= 231 - 1
 * @author sniper
 * @date 22 Sep, 2022
 * LC69
 */
public class SqrtX {

    public static void main(String[] args) {
        int x = 8192;
        int res = mySqrt(x);
        System.out.println(res);
    }

    /**
     * Newton's method.
     * A classic algorithm that illustrates many of these concerns is 'Newton-Raphson' method to compute square
     * roots x = √a for a > 0, i.e. to solve x^2 = a.
     * The algorithm starts with some guess x1 > 0 and computes the sequence of improved guesses
     * Xn+1 = (Xn + a/Xn)/2.
     *
     * The intuition is very simple: if Xn is too big (>√a), then a/Xn will be too small (<
     * √a), and so their arithmetic mean xn+1 will be closer to √a.
     *
     *
     * @param x
     * @return
     */
    public static int mySqrtV2(int x) {
        long r = x;
        while (r*r > x) {
            r = (r + x/r) / 2;
        }
        return (int) r;
    }

    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int low = 1, high = x;
        while (low < high) {
            int mid = low + (high - low) / 2;

        }
        return low;
    }
}
