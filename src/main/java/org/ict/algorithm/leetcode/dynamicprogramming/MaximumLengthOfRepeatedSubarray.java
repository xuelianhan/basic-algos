package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 *
 * Example 1:
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 *
 * Example 2:
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 * Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 *
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 * @author sniper
 * @date 06 May 2023
 * LC718, Medium, frequency=31
 */
public class MaximumLengthOfRepeatedSubarray {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};
        MaximumLengthOfRepeatedSubarray instance = new MaximumLengthOfRepeatedSubarray();
        int res = instance.findLength(nums1, nums2);
        System.out.println(res);
    }

    /**
     * One-Dimension DP
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV6(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     * One-Dimension DP
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV5(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     * Two-Dimension DP
     * Time Cost 27ms
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        /**
         * dp[i][j] := max length of nums1[i:] and nums2[j:]
         */
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    /**
     * Two-Dimension DP
     * Time Cost 33ms
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    /**
     * Two-Dimension DP
     * Time Cost 31ms
     * dp[i][j] denotes the length of the longest subarray of the first i numbers of array A and the first j numbers of array B.
     * If dp[i][j] is not 0, then the i-th array in A and the j-th number in B must be equal.
     * e.g. nums1 = [1,2,2], nums2 = [3,1,2]
     *   3 1 2
     * 1 0 1 0
     * 2 0 0 2
     * 2 0 0 1
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                } else {
                    // dp is int array, default is zero, so this branch can be ignored.
                    dp[i][j] = 0;
                }
            }
        }
        return res;
    }

    /**
     * Brute-Force Solution
     * Time Cost 49ms
     * e.g. nums1 = [0,0,0,0,1], nums2 = [1,0,0,0,0]
     * e.g. nums1 = [0,1,1,1,1], nums2 = [1,0,1,0,1]
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV1(int[] nums1, int[] nums2) {
        int res = 0;
        int m = nums1.length;
        int n = nums2.length;
        /**
         * Fixed the start point in nums1, compare it one by one with elements in nums2
         * Don't separate i++ and j++ out from condition nums1[i++] == nums2[j++]
         */
        for (int k = 0; k < m; k++) {
            for (int i = k, j = 0; i < m && j < n;) {
                int cnt = 0;
                while (i < m && j < n && nums1[i++] == nums2[j++]) {
                    cnt++;
                }
                res = Math.max(res, cnt);
            }
        }

        /**
         * Fixed the start point in nums2, compare it one by one with elements in nums1
         * Don't separate i++ and j++ out from condition nums1[i++] == nums2[j++]
         */
        for (int k = 0; k < n; k++) {
            for (int i = 0, j = k; i < m && j < n;) {
                int cnt = 0;
                while (i < m && j < n && nums1[i++] == nums2[j++]) {
                    cnt++;
                }
                res = Math.max(res, cnt);
            }
        }

        return res;
    }

    /**
     * Brute-Force Solution
     * Time Cost 2771ms
     * Time Complexity : O(M * N * min(M,N))
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int res = 0;
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = 0;
                while (i + len < m && j + len < n && nums1[i + len] == nums2[j + len]) {
                    len++;
                }
                res = Math.max(res, len);
            }
        }
        return res;
    }



}
