package org.ict.algorithm.leetcode.coupang;

import java.util.Arrays;

/**
 * Give you array: [0,1,1,0,0,0,1,0,0,1,1,1,1,1,1,1],
 * you can replace any 0 with a 1 up to k times,
 * how long is the longest continuous sub-array of all 1s you can get.
 * @author sniper
 * @date 17 Aug 2023
 */
public class LongestContinuousSubArrayOnes {

    public static void main(String[] args) {
        int[] arr = {0,1,1,0,0,0,1,0,0,1,1,1,1,1,1,1};
        int k = 2;
        LongestContinuousSubArrayOnes instance = new LongestContinuousSubArrayOnes();
        int res = instance.findLongestSubArrayOnes(arr, k);
        System.out.println(res);
    }


    /**
     * This code first creates a table to store the maximum length of the continuous sub-array of all 1s ending at index i.
     * The code then iterates over the array and updates the table accordingly.
     *
     * The code finally returns the maximum value in the table.
     * @param arr
     * @param k
     * @return
     */
    public int findLongestSubArrayOnes(int[] arr, int k) {
        int n = arr.length;

        // Create a table to store the maximum length of the continuous subarray of all 1s ending at index i.
        int[] dp = new int[n];
        dp[0] = arr[0] == 1 ? 1 : 0;

        // Iterate over the array.
        for (int i = 1; i < n; i++) {
            if (arr[i] == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                if (k > 0) {
                    dp[i] = Math.max(dp[i - 1], dp[i - 1] + 1);
                    k--;
                } else {
                    dp[i] = 0;
                }
            }
        }

        // Return the maximum value in the table.
        return Arrays.stream(dp).max().getAsInt();
    }
}
