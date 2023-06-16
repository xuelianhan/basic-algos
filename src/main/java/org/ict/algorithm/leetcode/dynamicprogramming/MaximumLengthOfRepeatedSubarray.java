package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.Arrays;

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
        int[] nums1 = {1,2,2};
        int[] nums2 = {3,1,2};
        MaximumLengthOfRepeatedSubarray instance = new MaximumLengthOfRepeatedSubarray();
        int res = instance.findLengthV7(nums1, nums2);
        System.out.println(res);
    }

    /**
     * One-Dimension DP
     * Time complexity: O(m*n)
     * Space complexity: O(n)
     *
     * e.g. nums1 = [1,2,2], nums2 = [3,1,2]
     * dp:
     * [0, 0, 0, 0]
     * [0, 0, 1, 0]
     * [0, 0, 1, 0]
     * [0, 0, 1, 2]
     * [0, 0, 0, 2]
     * [0, 0, 0, 2]
     * [0, 0, 0, 1]
     * [0, 0, 0, 1]
     * [0, 0, 0, 1]
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV8(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        /**
         * dp[j] is the length of the longest common subarray ending with nums1[i-1], nums2[j-1]
         */
        int[] dp = new int[n + 1];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[j + 1] = dp[j] + 1;
                } else {
                    dp[j + 1] = 0;
                }
                System.out.println(Arrays.toString(dp));
                res = Math.max(res, dp[j + 1]);
            }
        }
        return res;
    }

    /**
     * One-Dimension DP
     * Time Cost 29ms
     * Time complexity: O(m*n)
     * Space complexity: O(n)
     * We are comparing each element of vector 1 (starting from left boundary) to each element of vector 2 (starting from right boundary).
     * If at any point they are equal,
     * we increase the value of dp array at point index after adding one to previous index value in dp.
     * -----------------------------------------------
     * e.g. nums1 = [1,2,2], nums2 = [3,1,2]
     * dp:
     * [0, 0, 0, 0]
     * [0, 0, 1, 0]
     * [0, 0, 1, 0]
     * [0, 0, 1, 2]
     * [0, 0, 0, 2]
     * [0, 0, 0, 2]
     * [0, 0, 0, 1]
     * [0, 0, 0, 1]
     * [0, 0, 0, 1]
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV7(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        /**
         * dp[j] is the length of the longest common subarray ending with nums1[i-1], nums2[j-1]
         */
        int[] dp = new int[n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = n; j > 0; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }
                System.out.println(Arrays.toString(dp));
                res = Math.max(res, dp[j]);
            }
        }
        return res;
    }

    /**
     * One-Dimension DP
     * Time Cost 38ms
     *
     * Time complexity: O(m*n)
     * Space complexity: O(n)
     * ------------------------
     * e.g. nums1 = [1,2,2], nums2 = [3,1,2]
     * dp:
     * [0, 0, 0, 0], subarray1:[2], subarray2:[3]
     * [0, 0, 0, 0], subarray1:[2], subarray2:[3, 1]
     * [0, 0, 1, 0], subarray1:[2], subarray2:[3, 1, 2]
     * [0, 0, 1, 0], subarray1:[2],
     * [0, 0, 1, 0]
     * [0, 0, 1, 0]
     * [0, 0, 1, 0]
     * [0, 2, 1, 0]
     * [0, 2, 0, 0]
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV6(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] dp = new int[n + 1];
        int res = 0;
        for (int i = m - 1; i >= 0; i--) {
            /**
             * The order is very important
             */
            for (int j = 0; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[j] = dp[j + 1] + 1;
                } else {
                    dp[j] = 0;
                }
                System.out.println(Arrays.toString(dp));
                res = Math.max(res, dp[j]);
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Two-Dimension DP
     * Time Cost 27ms
     * Time complexity: O(m*n)
     * Space complexity: O(m*n)
     * ----------------
     * dp[i][j] := max length of common prefix of nums1[i:] and nums2[j:]
     * e.g. nums1 = [1,2,2], nums2 = [3,1,2]
     * dp[4][4]
     *     3 1 2
     * ------------
     *   0 2 0 0
     * 1 0 0 1 0
     * 2 0 0 1 0
     * 2 0 0 0 0
     * ---------------------------
     * dp[3][j]==dp[i][3]==0
     * ---------------------------
     * dp[2][2]=1,
     * it means the largest common prefix of nums1[2:]=[2] and nums2[2:]=[2] is [2], which has length of 1.
     *
     * dp[1][2]=1,
     * it means the largest common prefix of nums1[1:]=[2, 2] and nums2[2:]=[2] is [2], which has length of 1.
     *
     * dp[0][1]=2,
     * it means the largest common prefix of nums1[0:]=[1, 2, 2] and nums2[1:]=[1, 2] is [1, 2], which has length of 2.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV5(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        /**
         * dp[i][j] := max length of common prefix of nums1[i:] and nums2[j:]
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
        System.out.println(Arrays.deepToString(dp));
        return res;
    }

    /**
     * Understanding the following solution
     * Two-Dimension DP
     * Time Cost 33ms
     * Time complexity: O(m*n)
     * Space complexity: O(m*n)
     *
     * It's the classic Longest Common Substring problem, can be used by using Dynamic Programming.
     * Let dp[i][j] is the longest common suffix between nums1[0..i-1] and nums2[0..j-1].
     *
     * dp[i][j] := the longest common suffix between nums1[0..i-1] and nums2[0..j-1]
     * dp[i][j] = dp[i – 1][j – 1] + 1 if nums1[i - 1] == nums2[j - 1] else 0
     * Time complexity: O(m*n)
     * Space complexity: O(m*n) -> O(n)
     * --------------------------------------------------
     * e.g. nums1 = [1,2,2], nums2 = [3,1,2]
     * dp[4][4]
     *     3 1 2
     * ------------
     *   0 0 0 0
     * 1 0 0 1 0
     * 2 0 0 0 2
     * 2 0 0 0 1
     * ----------
     * dp[1][2]=1,
     * it means first-1 numbers of nums1:[1], and the first-2 numbers of nums2:[3,1], have the longest common suffix [1] with length of 1
     *
     * dp[2][3]=2,
     * it means first-2 numbers of nums1:[1,2], and the first-3 numbers of nums2:[3,1,2], have the longest common suffix [1,2] with length of 2
     *
     * dp[3][3]=1,
     * it means first-3 numbers of nums1:[1,2,2], and the first-3 numbers of nums2:[3,1,2] have the longest common suffix [2] with length of 1
     * --------------------------------
     * class Solution:
     *     def findLength(self, nums1: List[int], nums2: List[int]) -> int:
     *       m, n = len(nums1), len(nums2)
     *       dp = [[0] * (n + 1) for _ in range(0, m + 1)]
     *       res = 0
     *       for i in range(1, m + 1):
     *         for j in range(1, n + 1):
     *           if nums1[i - 1] == nums2[j - 1]:
     *             dp[i][j] = dp[i - 1][j - 1] + 1
     *             res = max(res, dp[i][j])
     *       return res
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        /**
         * dp[i][j] denotes the length of the longest suffix of nums1[0...i-1] and nums2[0...j-1]
         */
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
        //System.out.println(Arrays.deepToString(dp));
        return res;
    }

    /**
     * Understanding the following solution
     * Two-Dimension DP
     * Time Cost 31ms
     * Time complexity: O(m*n)
     * Space complexity: O(m*n)
     * ----------------------------------
     * dp[i][j] denotes the length of the longest suffix of the first i numbers of array A and the first j numbers of array B.
     * If dp[i][j] is not 0, then the i-th array in A and the j-th number in B must be equal.
     * e.g. nums1 = [1,2,2], nums2 = [3,1,2]
     * dp[4][4]
     *     3 1 2
     * ------------
     *   0 0 0 0
     * 1 0 0 1 0
     * 2 0 0 0 2
     * 2 0 0 0 1
     * ----------
     * dp[1][2]=1,
     * it means first-1 numbers of nums1:[1], and the first-2 numbers of nums2:[3,1], have the longest common suffix [1] with length of 1
     *
     * dp[2][3]=2,
     * it means first-2 numbers of nums1:[1,2], and the first-3 numbers of nums2:[3,1,2], have the longest common suffix [1,2] with length of 2
     *
     * dp[3][3]=1,
     * it means first-3 numbers of nums1:[1,2,2], and the first-3 numbers of nums2:[3,1,2] have the longest common suffix [2] with length of 1
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        /**
         * dp[i][j] denotes the length of the longest suffix of nums1[0...i-1] and nums2[0...j-1]
         */
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                } else {
                    /**
                     * If you are looking for longest common sequence,
                     * then you put dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);  here.
                     * However, this problem is looking for subarray,
                     * since both character are not equal, which means we need to break it here.
                     * hence, set dp[i][j] to 0.
                     * dp is an int array, default is zero, so this branch can be ignored.
                     */
                    dp[i][j] = 0;
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        return res;
    }

    /**
     * Understanding the following solution
     * Brute-Force Solution
     * Time Cost 34ms
     * -------------------
     * maxLen[i][j] --> the max length of common subarray ending at A[i] and B[j]
     * => 0 , if A[i] != A[j]
     * => maxLen[i-1][j-1] + 1, if A[i] = B[j]
     * So maxLen[i][j] only depends on maxLen[i-1][j-1],
     * what you only need is to keep maxLen[i-1][j-1] which is one element.
     * At the following codes,  we use maxLenEnding to keep maxLen[i-1][j-1],
     * and traverse the matrix diagonally
     * ------------------------------------
     * e.g. nums1 = [1,2,2], nums2 = [3,1,2]
     *
     *
     * @see <a href="https://leetcode.com/problems/maximum-length-of-repeated-subarray/solutions/109040/java-o-mn-time-o-1-space"></a>
     * @author seanzhou1023
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV2(int[] nums1, int[] nums2) {
        int res = 0;
        int m = nums1.length;
        int n = nums2.length;
        for (int k = 0; k < m; k++) {
            int maxLenEnding = 0;
            for (int i = k, j = 0; i < m && j < n; i++, j++) {
                if (nums1[i] != nums2[j]) {
                    maxLenEnding = 0;
                } else {
                    maxLenEnding++;
                    res = Math.max(res, maxLenEnding);
                }
            }
        }

        for (int k = 0; k < n; k++) {
            int maxLenEnding = 0;
            for (int i = 0, j = k; i < m && j < n; i++, j++) {
                if (nums1[i] != nums2[j]) {
                    maxLenEnding = 0;
                } else {
                    maxLenEnding++;
                    res = Math.max(res, maxLenEnding);
                }
            }
        }

        return res;
    }

    /**
     * Understanding the following solution
     * Brute-Force Solution
     * Time Cost 34ms
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
         */
        for (int k = 0; k < m; k++) {
            int cnt = 0;
            for (int i = k, j = 0; i < m && j < n; i++, j++) {
                if (nums1[i] == nums2[j]) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                res = Math.max(res, cnt);
            }
        }

        /**
         * Fixed the start point in nums2, compare it one by one with elements in nums1
         */
        for (int k = 0; k < n; k++) {
            int cnt = 0;
            for (int i = 0, j = k; i < m && j < n; i++, j++) {
                if (nums1[i] == nums2[j]) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                res = Math.max(res, cnt);
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
    public int findLengthV0(int[] nums1, int[] nums2) {
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
