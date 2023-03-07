package org.ict.algorithm.leetcode.binarysearch;


/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Return the kth positive integer that is missing from this array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * Example 2:
 *
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 *
 *
 * Follow up:
 *
 * Could you solve this problem in less than O(n) complexity?
 * @author sniper
 * @date 06 Mar, 2023
 * LC1539, Easy
 */
public class KthMissingPositiveNumber {

    public int findKthPositiveV1(int[] arr, int k) {
        int n = arr.length;
        int res = 0;
        for (int i = 0; i < n; i++) {

        }
        return res;
    }

    /**
     * e.g. arr = [2,3,4,7,11], k = 5, expect 9
     * e.g. arr = [1, 3], k = 1, expect 2
     * e.g. arr = [3,10], k = 2, expect 2
     * e.g. arr = [5,6,7,8,9], k = 9, expect 14
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        if (arr[n - 1] == n) {
            return (n + k);
        }
        int res = 0;
        for (int i = 0; i < n;) {
            if (arr[i] - (i + 1) < k) {
                i++;
            } else if (arr[i] - (i + 1) == k){
                if (i == 0) {
                    res = k;
                } else {
                    res = arr[i] - k;
                }
                break;
            } else {
                if (i == 0) {
                    res = k;
                } else {
                    res = arr[i - 1] + k - (arr[i - 1] - i);
                }
                break;
            }
        }
        //todo
        return res;
    }
}
