package org.ict.algorithm.leetcode.bitvector;

/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
 * ans[i] is the number of 1's in the binary representation of i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * Example 2:
 *
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 *
 * Constraints:
 *
 * 0 <= n <= 105
 *
 *
 * Follow up:
 *
 * It is very easy to come up with a solution with a runtime of O(n log n).
 * Can you do it in linear time O(n) and possibly in a single pass?
 * Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 * @author sniper
 * @date 23 Sep, 2022
 * LC338
 */
public class CountingBits {

    /**
     * Improvement version of countBitsV2
     *
     * for every i, we have to count 1's bits in its binary form.
     * 	0 --> 0
     * 	1 --> 1
     * 	2 --> 10
     * 	3 --> 11
     * 	4 --> 100
     * 	5 --> 101
     * 	6 --> 110
     * 	7 --> 111
     * 	8 --> 1000
     * 	9 --> 1001
     * 	10 --> 1010
     * 	11 --> 1011
     * 	12 --> 1100
     * 	13 --> 1101
     * 	14 --> 1110
     * 	15 --> 1111
     *
     *
     *
     * 	Output: [0,1,1,2,1,2]
     *
     * Time Complexity O(N)
     *
     * F[0] = 0
     * F[i] = F[i/2] + i%2, i>=1,
     *
     *
     * @param n
     * @return
     */
    public int[] countBitsV3(int n) {
        int[] res = new int[n+1];
        res[0] = 0;
        for (int i = 1; i <= n; i++) {
            /**
             * i/2 --> i >> 1
             * i%2 --> i & (2-1) --> i & 1
             */
            res[i] = res[i>>1] + (i & 1);
        }
        return res;
    }


    /**
     * Understand the following method.
     *
     * @param n
     * @return
     */
    public int[] countBitsV2(int n) {
        int[] res = new int[n+1];
        res[0] = 0;
        for (int i = 1; i <= n; i++) {
            res[i] = res[i/2] + i % 2;
        }
        return res;
    }


    /**
     * Understand the following method.
     *
     * Normal solution usage of LC191 NumberOf1Bits.java
     * Time Complexity O(N*logN)
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        for (int i = 0; i <= n; i++) {
            res[i] = hammingWeight(i);
        }
        return res;
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
