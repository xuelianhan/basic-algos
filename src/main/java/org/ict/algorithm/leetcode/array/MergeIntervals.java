package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * Given an array of intervals where intervals[i] = [start-i, end-i],
 * merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * Constraints:
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 * @author sniper
 * @date 06 Jan, 2023
 * LC56, Medium, frequency=350
 */
public class MergeIntervals {

    public int[][] mergeV2(int[][] intervals) {
        return null;
    }

    /**
     * Time Cost 21ms
     * @param intervals
     * @return
     */
    public int[][] mergeV1(int[][] intervals) {
        if (intervals == null || intervals[0].length == 0) {
            return null;
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        int[] newInterval = intervals[0];
        list.add(newInterval);
        for (int[] interval : intervals) {
            if (newInterval[1] < interval[0]) {
                /**
                 * The last interval in list has no intersection with the current interval.
                 * Add the current interval to the list.
                 */
                newInterval = interval;
                list.add(newInterval);
            } else {
                /**
                 * Merge last interval in list with the current interval.
                 */
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        /**
         * Convert list to array.
         */
        return list.toArray(new int[list.size()][]);
    }


    /**
     * Time Cost 24ms
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals[0].length == 0) {
            return null;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Deque<int[]> stack = new ArrayDeque<>();
        stack.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (stack.peek()[1] < intervals[i][0]) {
                /**
                 * The last interval in list has no intersection with the current interval.
                 */
                stack.push(intervals[i]);
            } else {
                /**
                 * Merge last interval in list with the current interval.
                 */
                int[] last = stack.pop();
                int maxEnd = Math.max(last[1], intervals[i][1]);
                last[1] = maxEnd;
                stack.push(last);
            }
        }
        int n = stack.size();
        int i = n - 1;
        int[][] res = new int[n][2];
        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            res[i--] = top;
        }
        return res;
    }
}
