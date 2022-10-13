package org.ict.algorithm.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 *
 *
 *
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 *
 * Example 2:
 *
 *
 * Input: heights = [2,4]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= heights.length <= 10^5
 * 0 <= heights[i] <= 10^4
 * @author sniper
 * @date 11 Oct, 2022
 * LC84
 */
public class LargestRectangleInHistogram {

    public int largestRectangleAreaV1(int[] heights) {
        if (null == heights || heights.length == 0) {
            return 0;
        }

        int res = 0;
        return res;
    }

    /**
     * Time Cost 60ms
     *
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (null == heights || heights.length == 0) {
            return 0;
        }

        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        /**
         * Why i up to heights.length?
         */
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length ? 0 : heights[i]);
            /**
             *
             */
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int start = (stack.isEmpty() ? -1 : stack.peek());
                int area = height * (i - start - 1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }
}
