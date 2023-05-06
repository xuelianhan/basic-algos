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
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV6(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV5(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV4(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV3(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV2(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthV1(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     * Brute-Force Solution
     * Time Cost 49ms
     * e.g. nums1 = [0,0,0,0,1], nums2 = [1,0,0,0,0]
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int res = 0;
        int m = nums1.length;
        int n = nums2.length;
        /**
         * Fixed the start point in nums1, compare it one by one with elements in nums2
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

}
