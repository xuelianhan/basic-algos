package org.ict.algorithm.leetcode.twopointers;

/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container,
 * such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 * @author sniper
 * @date 04 Jan, 2023
 * LC11
 */
public class ContainerWithMostWater {

    /**
     * Time Cost 11ms
     * Two-Pointer Solution
     * @param height
     * @return
     */
    public int maxAreaV1(int[] height) {
        int res = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            int h = Math.min(height[l], height[r]);
            res = Math.max(res, h * (r - l));
            while (l < r && h == height[l]) {
                l++;
            }
            while (l < r && h == height[r]) {
                r--;
            }
        }
        return res;
    }


    /**
     * Time Cost 10ms
     * Two-Pointer Solution
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int res = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            res = Math.max(res, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }

}
