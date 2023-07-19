package org.ict.algorithm.leetcode.coupang;

/**
 * Alice and Bob work in a beautiful orchard. There are N apple trees in the orchard.
 * The apple trees are arranged in a row, and they are numbered from 1 to N.
 * Alice is planning to collect all the apples from K consecutive trees,
 * and Bob is planning to collect all the apples from L consecutive trees.
 * They want to choose to disjoint segments
 * (one consisting of K trees of Alice and the other consisting of L trees for Bob) so as not to disturb each other.
 * you should return the maximum number of apples that they can collect.
 *
 * Only $39.9 for the "Twitter Comment System Project Practice" within a limited time of 7 days!
 *
 * WeChat Notes Twitter for more information（WeChat ID jiuzhang104）
 *
 *
 * N is an integer within the range: [2, 600]
 * K and L are integers within the range: [1, N - 1]
 * each element of array A is an integer within the range: [1, 500]
 * Example
 * Example 1:
 *
 * input:
 * A = [6, 1, 4, 6, 3, 2, 7, 4]
 * K = 3
 * L = 2
 * Output:
 * 24
 * Explanation:
 * because Alice can choose tree 3 to 5 and collect 4 + 6 + 3 = 13 apples,
 * and Bob can choose trees 7 to 8 and collect 7 + 4 = 11 apples.Thus, they will collect 13 + 11 = 24.
 * Example 2:
 *
 * Input:
 * A = [10, 19, 15]
 * K = 2
 * L = 2
 * Output:
 * -1
 * Explanation:
 * because it is not possible for Alice and Bob to choose two disjoint intervals.
 * @author sniper
 * @date 18 Jul 2023
 * LintCode1850, Medium
 */
public class PickApples {

    /**
     * @param a: a list of integer
     * @param k: a integer
     * @param l: a integer
     * @return: return the maximum number of apples that they can collect.
     */
    public int pickApples(int[] a, int k, int l) {
        int n = a.length;
        if (n < k + l) {
            return - 1;
        }
        int[] prefixSum = new int[n];
        prefixSum[0] = a[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = a[i] + prefixSum[i - 1];
        }

        // prefixK represents the maximum value of the sum of sub-intervals of length K in the first-i numbers.
        int[] prefixK = new int[n];
        int[] prefixL = new int[n];

        // postfixK represents the maximum value of the sum of sub-intervals of length K in the post-order [i, n - 1].
        int[] postfixK = new int[n];
        int[] postfixL = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == k - 1) {
                prefixK[i] = rangeSum(prefixSum, i - k + 1, i);
            }
            else if (i > k - 1) {
                prefixK[i] = Math.max(rangeSum(prefixSum, i - k + 1, i), prefixK[i - 1]);
            }
            if (i == l - 1) {
                prefixL[i] = rangeSum(prefixSum, i - l + 1, i);
            }
            else if (i > l - 1) {
                prefixL[i] = Math.max(rangeSum(prefixSum, i - l + 1, i), prefixL[i - 1]);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i + k - 1 == n - 1) {
                postfixK[i] = rangeSum(prefixSum, i, i + k - 1);
            }
            else if (i + k - 1 < n - 1) {
                postfixK[i] = Math.max(rangeSum(prefixSum, i, i + k - 1), postfixK[i + 1]);
            }
            if (i + l - 1 == n - 1) {
                postfixL[i] = rangeSum(prefixSum, i, i + l - 1);
            }
            else if (i + l - 1 < n - 1) {
                postfixL[i] = Math.max(rangeSum(prefixSum, i, i + l - 1), postfixL[i + 1]);
            }
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            res = Math.max(res, prefixK[i] + postfixL[i + 1]);
            res = Math.max(res, prefixL[i] + postfixK[i + 1]);
        }
        return res;
    }

    private int rangeSum(int[] prefixSum, int l, int r) {
        if (l == 0) {
            return prefixSum[r];
        }
        return prefixSum[r] - prefixSum[l - 1];
    }
}
