package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * Description
 * Given an array of meeting time intervals where intervals[i] = [start-i, end-i],
 * determine if a person could attend all meetings.
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 *
 * Constraints:
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= start-i < end-i <= 10^6
 * @author sniper
 * @date 20 Apr, 2023
 * LC252, Easy
 */
public class MeetingRooms {

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
