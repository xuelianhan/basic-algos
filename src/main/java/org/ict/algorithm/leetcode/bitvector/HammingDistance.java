package org.ict.algorithm.leetcode.bitvector;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, return the Hamming distance between them.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 1, y = 4
 * Output: 2
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * The above arrows point to positions where the corresponding bits are different.
 * Example 2:
 *
 * Input: x = 3, y = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 0 <= x, y <= 2^31 - 1
 * @author sniper
 * @date 03 Dec, 2022
 *
 * LC461, same as LC2220
 *
 */
public class HammingDistance {


    public int hammingDistance(int x, int y) {
        int n = x ^ y;
        return hammingWeight(n);
    }

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            /**
             * if n = 1100, n-1=1011
             * n & (n - 1) = 1000
             * It set the right most 1 in to 0.
             * final effect: 1100 --> 1000
             */
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
