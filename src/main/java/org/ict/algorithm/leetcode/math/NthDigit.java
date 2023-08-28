package org.ict.algorithm.leetcode.math;

/**
 * Given an integer n,
 * return the n-th digit of the infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 3
 * Example 2:
 *
 * Input: n = 11
 * Output: 0
 * Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2^31 - 1
 * @author sniper
 * @date 28 Aug 2023
 * LC400, Medium
 */
public class NthDigit {

    /**
     * e.g.
     * 1 ~ 9
     * 10 ~ 99
     * 100 ~ 999
     * 1000 ~ 9999
     * 10000 ~ 99999
     * ...
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int len = 1;
        int start = 1;
        long cnt = 9;

        while (n > len * cnt) {
            n -= len * cnt;
            len++;
            cnt *= 10;
            start *= 10;
        }
        int target = start + (n - 1) / len;
        int index = (n - 1) % len;
        return String.valueOf(target).charAt(index) - '0';
    }
}
