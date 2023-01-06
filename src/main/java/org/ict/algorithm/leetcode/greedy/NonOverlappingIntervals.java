package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 * @author sniper
 * @date 06 Jan, 2023
 * LC435
 */
public class NonOverlappingIntervals {

    public int eraseOverlapIntervalsV1(int[][] intervals) {
        return 0;
    }

    /**
     * Greedy Solution.
     * Overlapping Interval Problem
     * {@link MinimumNumberOfArrowsToBurstBalloons}
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (null == intervals || intervals[0].length == 0) {
            return 0;
        }
        int res = 0;
        int minEnd = Integer.MAX_VALUE;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int[] interval : intervals) {
            /**
             * Notice here we use greater than or equal to(>=) instead of greater than(>)
             */
            if (interval[0] >= minEnd) {
                res++;
                minEnd = interval[1];
            } else {
                minEnd = Math.min(minEnd, interval[1]);
            }
        }

        return intervals.length - (res + 1);
    }
}
