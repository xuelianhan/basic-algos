package org.ict.algorithm.leetcode.prefixsum;

/**
 * @author sniper
 * @date 30 May 2023
 */
public class PrefixSumAndSuffixSumExample {

    public void test(int[] A) {
        int n = A.length;
        int[] prefix = new int[n];
        prefix[0] = A[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + A[i];
        }

        int[] suffix = new int[n];
        suffix[0] = A[n - 1];
        for (int i = 1; i < n; i++) {
            suffix[i] = suffix[i - 1] + A[n - i - 1];
        }
    }
}
