package org.ict.algorithm.leetcode.math;

/**
 * Given two positive integers a and b, return the number of common factors of a and b.
 *
 * An integer x is a common factor of a and b if x divides both a and b.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 12, b = 6
 * Output: 4
 * Explanation: The common factors of 12 and 6 are 1, 2, 3, 6.
 * Example 2:
 *
 * Input: a = 25, b = 30
 * Output: 2
 * Explanation: The common factors of 25 and 30 are 1, 5.
 *
 *
 * Constraints:
 *
 * 1 <= a, b <= 1000
 * @author sniper
 * @date 15 Oct, 2022
 * LC2427
 */
public class NumberOfCommonFactors {

    public int commonFactorsV1(int a, int b) {
        int res = 1, hi = gcd(a, b);
        for (int n = 2; n <= hi; ++n) {
            if (a % n == 0 && b % n == 0) {
                res++;
            }
        }
        return res;
    }

    public int gcd(int a, int b) {
        int temp = 0;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int commonFactors(int a, int b) {
        int min = Math.min(a, b);
        int res = 0;
        for (int i = 1; i <= min; i++) {
            if (a % i == 0 && b % i == 0) {
                res++;
            }
        }
        return res;
    }
}
