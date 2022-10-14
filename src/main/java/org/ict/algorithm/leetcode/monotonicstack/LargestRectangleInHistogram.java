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

    /**
     * Solution provided by <a href="https://leetcode.com/anton4/">anton4</a>
     * @param heights
     * @return
     */
    public int largestRectangleAreaV2(int[] heights) {
        return 0;
    }



    /**
     * Solution provided by <a href="https://leetcode.com/legendaryengineer/">legendaryengineer</a>
     *
     * This solution seems a bit tricky especially at the line i--.
     *
     * @param heights
     * @return
     */
    public int largestRectangleAreaV1(int[] heights) {
        if (null == heights || heights.length == 0) {
            return 0;
        }

        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0 ; i <= heights.length; i++) {
            int h = ( i == heights.length ? 0 : heights[i]);
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int height = heights[stack.pop()];
                int area = height * (stack.isEmpty() ? i : (i - 1 - stack.peek()));
                res = Math.max(res, area);
                i--;
            }
        }
        return res;
    }

    /**
     * Under standing the following solution.
     *
     * Time Cost 60ms
     * e.g.
     * append one element at the last of the original array, and its height is zero.
     * 0 1 2 3 4 5
     * 2 1 5 6 2 3
     *
     * 0 1 2 3 4 5 6
     * 2 1 5 6 2 3 0
     *
     * i:0, h:2, stack is empty, push 0 into the stack, stack:0
     * i:1, h:1, stack:0, heights[0] > h, pop 0 from the stack, height=heights[0]=2, start:-1, area=2*(1 - (-1) - 1)=2*1=2, res=max(0,2)=2
     *      push 1 into the stack, stack:1
     * i:2, h:5, stack:1, heights[1] < h, push 2 into the stack, stack:1,2
     * i:3, h:6, stack:1,2, heights[2] < h, push 3 into the stack, stack:1,2,3
     * i:4, h:2, stack:1,2,3, heights[3] > h, pop 3 from the stack, height=heights[3]=6, start:2, area=6*(4 - 2 - 1)=6, res=max(2, 6)=6
     *           stack:1,2, heights[2] > h, pop 2 from the stack, height=heights[2]=5, start:1, area=5*(4 - 1 - 1)=10, res=max(6, 10)=10
     *           stack:1, heights[1] < h
     *      push 4 into the stack, stack:1,4
     * i:5, h:3, stack:1,4, heights[4] < h, push 5 into the stack, stack:1,4,5
     * i:6, h:0, stack:1,4,5, heights[5] > h, height=heights[5]=3, start=4, area=3*(6 - 4 - 1)=3, res=max(10,3)=10
     *           stack:1,4, heights[4] > h, heights=heights[4]=2, start=1, area=2*(6 - 1 - 1)=8, res=max(10,8)=10
     *           stack:1, heights[1] > h, heights=heights[1]=1, start=-1, area=1*(6 - (-1) - 1)=6, res=max(10, 6)=10
     *           stack is empty
     * i:7, for-loop-end
     * return the res=10
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
         * because we must let the stack empty absolutely, so we add one more loop and make its height to zero.
         * Or you can add a sentinel node zero to the original array at the last position.
         */
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length ? 0 : heights[i]);
            /**
             * Bottom-Top Increasing Monotonic Stack.
             * When we encounter an element less than top ele of stack, we need to pop elements from the stack and calculate
             * the area.
             */
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int start = (stack.isEmpty() ? -1 : stack.peek());
                /**
                 * Why we use (i - start - 1)?
                 * Because the current i-th element is less than the top element of stack.
                 * the action of subtracting one will exclude the current i-th element.
                 * e.g.
                 * 0 1 2 3 4 5 6
                 * 2 1 5 6 2 3 0
                 * If we iterate at the 4-th element 2 now, for series(1 5 6 2), we need to
                 * calculate the series (1 5 6)'s width, and expel 2 out, because 2 is a decreasing element for
                 * series (1 5 6 2)
                 *
                 * for 6, the element in left-most and less than 6(index-3) is 5(index-2), its index-2 is in top of stack after we pop the index-3.
                 * area at 6 is 6*(4 - 2 - 1)=6
                 * area at 5 is 5*(4 - 1 - 1)=10
                 */
                int area = height * (i - start - 1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }
}
