package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals,
 * where intervals[i] = [start-i, end-i] represent the start and the end of the ith interval,
 * and intervals sorted in ascending order by start-i.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by start-i,
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 *
 * Constraints:
 *
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= start-i <= end-i <= 10^5
 * intervals sorted by start-i in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 10^5
 * @author sniper
 * @date 16 Jan, 2023
 * LC57, Medium
 */
public class InsertInterval {

    public int[][] insertV2(int[][] intervals, int[] newInterval) {
        return null;
    }

    public int[][] insertV1(int[][] intervals, int[] newInterval) {
        return null;
    }

    /**
     * Time Cost 1ms
     * e.g.intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]]
     * newInterval = [4,8]
     * expected = [[1,2],[3,10],[12,16]]
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int m = intervals.length;
        int start = newInterval[0];
        int end = newInterval[1];
        /**
         * Add all the intervals ending before newInterval's start
         */
        int i = 0;
        while (i < m && intervals[i][1] < start) {
            list.add(intervals[i]);
            i++;
        }

        /**
         * Merge all overlapping intervals.
         */
        int[] range = new int[2];
        while (i < m && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        range[0] = start;
        range[1] = end;
        list.add(range);

        /**
         * Add the rest elements
         */
        while (i < m) {
            list.add(intervals[i]);
            i++;
        }
        return list.toArray(new int[list.size()][]);
    }
}
