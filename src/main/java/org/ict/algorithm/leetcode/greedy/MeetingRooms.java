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
 * LC252
 */
public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals[0].length == 0) {
            return false;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] prev;
        for (int[] interval : intervals) {

        }
        return false;
    }
}
