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
     * 1 ~ 9, len:1, cnt:9, start:1
     * 10 ~ 99, len:2, cnt:99, start:10
     * 100 ~ 999, len:3, cnt:999, start:100
     * 1000 ~ 9999, len:4, cnt:9999, start:1000
     * 10000 ~ 99999, len:5, cnt:99999, start:10000
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
        /**
         * row: (n - 1) / len
         * col: (n - 1) % len
         * e.g. n = 11
         * 11 > len * cnt --> 11 > 1 * 9,
         * n = n - len * cnt = 11 - 9 = 2
         * len++ --> len:2
         * cnt *= 10 --> cnt:99
         * start *= 10 --> start:10
         *
         * target = 10 + (2 - 1) / 2 = 10
         * index = (2 - 1) % 2 = 1
         */
        int target = start + (n - 1) / len;
        int index = (n - 1) % len;
        return String.valueOf(target).charAt(index) - '0';
    }
}
