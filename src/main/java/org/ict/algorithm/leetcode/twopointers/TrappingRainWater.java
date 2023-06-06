package org.ict.algorithm.leetcode.twopointers;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation:
 * The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 *
 * Constraints:
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 * @author sniper
 * @date 19 Sep, 2022
 * LC42, Hard, frequency=134
 */
public class TrappingRainWater {

    /**
     * Understanding the following solution
     *
     * Two-Pointer Solution
     *
     * Idea and Solution provided by mcrystal.
     *
     * Instead of calculating area by height*width, we can think it in a cumulative way.
     * In other words, sum water amount of each bin(width=1).
     * Search from left to right and maintain a max height of left and right separately,
     * which is like a one-side wall of partial container.
     * Fix the higher one and flow water from the lower part.
     * For example, if current height of left is lower,we fill water in the left bin.
     * Until left meets right, we filled the whole container.
     *
     * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 0  1  0  2  1  0  1  3  2  1   2   1
     * -------------------------------------
     * 0  1  2  3  4  5  6  7  8  9  10  11
     * -------------------------------------
     * left                             right
     *
     * --------------------------------------
     * Time Complexity O(N)
     * Space Complexity O(1)
     *
     * @see <a href="https://leetcode.com/problems/trapping-rain-water/solutions/17357/sharing-my-simple-c-code-o-n-time-o-1-space/"></a>
     * @author mcrystal
     * @param height
     * @return
     */
    public int trapV5(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int res = 0;
        int maxLeft = 0;
        int maxRight = 0;
        while (left <= right) {
            /**
             * Shorter bar will determine volume of water.
             * when height[left] less than or equal to height[right],
             * the amount of water can be trapped is determined by left highest.
             * No matter how the bar[k] between[left, right] look like.
             */
            if (height[left] <= height[right]) {
                /**
                 * The left bin is lower, so we fill water in the left bin.
                 */
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    res += maxLeft - height[left];
                }
                left++;
            } else {
                /**
                 * The right bin is lower, so we fill water in the right bin.
                 */
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    res += maxRight - height[right];
                }
                right--;
            }
        }
        return res;
    }


    public int trap(int[] height) {
        return 0;
    }
}
