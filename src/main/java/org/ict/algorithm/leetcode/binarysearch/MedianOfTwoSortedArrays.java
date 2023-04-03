package org.ict.algorithm.leetcode.binarysearch;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 *
 * Constraints:
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 10^6
 * @author sniper
 * @date 03 Apr, 2023
 * LC4, Hard
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArraysV3(int[] nums1, int[] nums2) {
        double res = 0;
        int m = nums1.length;
        int n = nums1.length;

        return res;
    }

    public double findMedianSortedArraysV2(int[] nums1, int[] nums2) {
        double res = 0;
        int m = nums1.length;
        int n = nums1.length;

        return res;
    }

    public double findMedianSortedArraysV1(int[] nums1, int[] nums2) {
        double res = 0;
        int m = nums1.length;
        int n = nums1.length;

        return res;
    }

    /**
     * Not Recommend, Because this problem requires time complexity should be O(log (m+n))
     * Brute-Force Solution
     * Time Complexity O(M + N)
     * Space Complexity O(M + N)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] aux = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        for (; i < m && j < n; k++) {
            if (nums1[i] <= nums2[j]) {
                aux[k] = nums1[i++];
            } else {
                aux[k] = nums2[j++];
            }
        }
        while (i < m) {
            aux[k++] = nums1[i++];
        }
        while (j < n) {
            aux[k++] = nums2[j++];
        }

        int lo = 0;
        int hi = m + n - 1;
        int mid = lo + (hi - lo) / 2;
        double res = 0;
        if ((m + n) % 2 == 0) {
            res = (aux[mid] + aux[mid + 1]) / 2.0;
        } else {
            res = aux[mid];
        }
        return res;
    }
}
