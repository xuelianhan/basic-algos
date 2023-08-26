package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 *
 * @see <a href="https://leetcode.ca/all/252.html"></a>
 * @author sniper
 * @date 06 Jan, 2023
 * LC252, Easy
 */
public class MeetingRooms {

    public boolean canAttendMeetingsV2(int[][] intervals) {
        if (intervals == null || intervals[0].length == 0) {
            return false;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] prev = null;
        for (int[] interval : intervals) {
            if (prev == null) {
                continue;
            }
            if (prev[1] > interval[0]) {
                return false;
            }
            prev = interval;
        }
        return true;
    }

    /**
     * First, give all the intervals in order,
     * using the starting time of the order,
     * and then start from the second interval,
     * if the start time is earlier than the end time of the previous interval,
     * it means that there is a conflict in the meeting time, return false,
     * after the completion of the traversal there is no conflict,
     * then return true
     * ----------------------------------------------------------------
     * def canAttendMeetings(self, intervals: List[List[int]]) -> bool:
     *     intervals.sort()
     *
     *     for i in range(1, len(intervals)):
     *       if intervals[i - 1][1] > intervals[i][0]:
     *         return False
     *
     *     return True
     *
     * @param intervals
     * @return
     */
    public boolean canAttendMeetingsV1(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1][1] > intervals[i][0]) {
                return false;
            }
        }
        return true;
    }

    /**
     * def canAttendMeetings(self, intervals: List[List[int]]) -> bool:
     *         intervals.sort()
     *         return all(a[1] <= b[0] for a, b in pairwise(intervals))
     *
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] a = intervals[i - 1];
            int[] b = intervals[i];
            if (a[1] > b[0]) {
                return false;
            }
        }
        return true;
    }
}
