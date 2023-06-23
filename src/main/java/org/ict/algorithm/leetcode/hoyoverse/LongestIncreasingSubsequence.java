package org.ict.algorithm.leetcode.hoyoverse;


import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly increasing-subsequence
 * .
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 * @author sniper
 * @date 09 Jan, 2023
 * LC300, Medium, High Frequency, Top-150
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        LongestIncreasingSubsequence instance = new  LongestIncreasingSubsequence();
        int result = instance.lengthOfLISV2(nums);
        System.out.println(result);
    }

    public int lengthOfLISV6(int[] nums) {
        return 0;
    }

    public int lengthOfLISV5(int[] nums) {
        return 0;
    }

    public int lengthOfLISV4(int[] nums) {
        return 0;
    }

    public int lengthOfLISV3(int[] nums) {
        return 0;
    }

    /**
     * DP + Binary Search Solution
     * @author monkeyGoCrazy
     * @see <a href="https://leetcode.com/problems/longest-increasing-subsequence/solutions/74897/fast-java-binary-search-solution-with-detailed-explanation"></a>
     * @param nums
     * @return
     */
    public int lengthOfLISV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int len = 0;
        for (int i = 1; i < n; i++) {
            int pos = binarySearchV2(dp, len, nums[i]);
            if (nums[i] < dp[pos]) {
                dp[pos] = nums[i];
            }
            if (pos > len) {
                len = pos;
                dp[len] = nums[i];
            }
        }
        return len + 1;
    }

    private int binarySearchV2(int[] dp, int len, int val) {
        int left = 0;
        int right = len;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (dp[mid] == val) {
                return mid;
            } else if (dp[mid] < val) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (dp[right] < val) {
            return len + 1;
        } else if (dp[left] >= val) {
            return left;
        } else {
            return right;
        }
    }



    /**
     * DP + Binary-Search Solution
     * Time Complexity O(N*LogN)
     * @param nums
     * @return
     */
    public int lengthOfLISV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //todo
        int n = nums.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (dp[mid] < nums[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }

                if (right >= len) {
                    dp[len] = nums[i];
                    len++;
                } else {
                    dp[right] = nums[i];
                }
            }
        }
        return len;
    }

    /**
     * Brute-Forth Dynamic Programming Solution.
     * Time Cost 74ms
     * Time Complexity O(N^2)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        /**
         * dp[i] means length of LIS end with nums[i]
         */
        int[] dp = new int[n];
        /**
         * Each element itself occupies length of 1
         */
        Arrays.fill(dp, 1);
        int res = 0;
        /**
         * e.g. nums = [0], expect output 1
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
