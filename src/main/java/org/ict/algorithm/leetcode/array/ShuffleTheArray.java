package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
 *
 * Return the array in the form [x1,y1,x2,y2,...,xn,yn].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,1,3,4,7], n = 3
 * Output: [2,3,5,4,1,7]
 * Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
 * Example 2:
 *
 * Input: nums = [1,2,3,4,4,3,2,1], n = 4
 * Output: [1,4,2,3,3,2,4,1]
 * Example 3:
 *
 * Input: nums = [1,1,2,2], n = 2
 * Output: [1,2,1,2]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 500
 * nums.length == 2n
 * 1 <= nums[i] <= 10^3
 * @author sniper
 * @date 19 Aug, 2022
 * LC1470
 */
public class ShuffleTheArray {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5};
        int n = 3;
        int[] res = shuffle(nums, n);
        System.out.println(Arrays.toString(res));
    }

    public static int[] shuffleV2(int[] nums, int n) {
        int[] res = new int[2 * n];
        for(int i = 0; i < n; i++){
            res[2 * i] = nums[i];
            res[2 * i + 1] = nums[n + i];
        }
        return res;
    }


    /**
     * 0, 1, 2, 3, 4, 5
     * 0, 3, 1, 4, 2, 5
     * we can see nums as two arrays
     * nums1 = [0, 1, 2]
     * nums2 = [3, 4, 5]
     * res = [0, 0, 0, 0, 0, 0]
     *
     * even in low part of num
     * odd in high part of num
     * [0,...,n-1, n, ..., 2n-1]
     * i = 0, j = 0
     * i = 1, j = n
     * i = 2, j = 1
     * i = 3, j = n + 1
     * i = 4, j = 2
     * i = 5, j = n + 2;
     * i = 6, j = 3
     *
     * @param nums
     * @param n
     * @return
     */
    public static int[] shuffle(int[] nums, int n) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                res[i] = nums[i/2];
            } else {
                res[i] = nums[i/2 + n];
            }
        }
        return res;
    }
}
