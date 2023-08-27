package org.ict.algorithm.leetcode.math;

/**
 * A perfect number is a positive integer that is equal to the sum of its positive divisors,
 * excluding the number itself.
 * A divisor of an integer x is an integer that can divide x evenly.
 *
 * Given an integer n, return true if n is a perfect number, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 28
 * Output: true
 * Explanation: 28 = 1 + 2 + 4 + 7 + 14
 * 1, 2, 4, 7, and 14 are all divisors of 28.
 * Example 2:
 *
 * Input: num = 7
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= num <= 10^8
 * @author sniper
 * @date 27 Aug 2023
 * LC507, Easy
 */
public class PerfectNumber {

    /**
     * Time Cost 2ms
     * @param num
     * @return
     */
    public boolean checkPerfectNumberV1(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i + num / i;
            }
        }
        return sum == num;
    }


    /**
     * Time Cost 2ms
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i + num / i;
            }
        }
        return sum == num;
    }
}
