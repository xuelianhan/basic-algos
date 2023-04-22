package org.ict.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Description
 * Given an array of meeting time intervals where intervals[i] = [start-i, end-i],
 * return the minimum number of conference rooms required.
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 *
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 *
 * Constraints:
 * 1 <= intervals.length <= 10^4
 * 0 <= start-i < end-i <= 10^6
 * @author sniper
 * @date 20 Apr, 2023
 * LC253, Medium, frequency=106
 */
public class MeetingRoomsII {

    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        MeetingRoomsII instance = new MeetingRoomsII();
        int res = instance.minMeetingRoomsV1(intervals);
        System.out.println(res);
    }
    public int minMeetingRoomsV3(int[][] intervals) {
        //todo
        return 0;
    }

    /**
     * e.g.intervals = [[0,30],[5,10],[15,20]]
     *
     * @param intervals
     * @return
     */
    public int minMeetingRoomsV2(int[][] intervals) {
        int n = 1_000_010;
        int[] delta = new int[n];
        for (int[] a : intervals) {
            delta[a[0]]++;
            delta[a[1]]--;
        }

        int res = delta[0];
        for (int i = 1; i < n; i++) {
            delta[i] += delta[i - 1];
            res = Math.max(res, delta[i]);
        }
        return res;
    }


    /**
     * e.g.intervals = [[0,30],[5,10],[15,20]]
     * starts:0, 5, 15
     * ends:10, 20, 30
     * i:0, j:0, starts[0] < ends[0], res:1, i++
     * i:1, j:0, starts[1] < ends[0], res:2, i++
     * i:2, j:0, starts[2] > ends[0], j++, i++
     * i:3, j:1, for-loop-ended
     * return res:2
     *
     * @param intervals
     * @return
     */
    public int minMeetingRoomsV1(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (starts[i] < ends[j]) {
                res++;
            } else {
                j++;
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * The whole process likes lock and unlock, the most consecutive lock numbers is the answer.
     *
     * Those with time conflicts will need to be arranged in a separate meeting room,
     * while those without time conflicts can share the meeting room.
     * Iterate through the time interval,
     * for the start time, the mapping value increases by 1,
     * for the end time, the mapping value decreases by 1,
     * then define the result variable res,
     * and the number of rooms, iterate through the TreeMap,
     * time from small to large,
     * the number of rooms each time plus the mapping value,
     * and then update the result res, encounter the start time,
     * the mapping is positive, the number of rooms will increase,
     * if a time is the end time of a meeting,
     * but also the start time of another meeting,
     * the mapping value first subtracted and then added is still 0,
     * and do not need to allocate a new room,
     * and the mapping value of the end time is a negative number will not increase the number of rooms
     * e.g.intervals = [[0,30],[5,10],[15,20]]
     * TreeMap:
     * 0, 1
     * 5, 1
     * 10, -1
     * 15, 1
     * 20, -1
     * 30, -1
     * The key point is the sorting by key in TreeMap.
     * res:0, rooms:1, res = max(res, rooms) = 1
     * res:1, rooms:2, res = max(res, rooms) = 2
     * res:2, rooms:1, res = max(res, rooms) = 2
     * res:2, rooms:2, res = max(res, rooms) = 2
     * res:2, rooms:1, res = max(res, rooms) = 2
     * res:2, rooms:0, res = max(res, rooms) = 2
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] a : intervals) {
            int start = a[0];
            int end = a[1];
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
        }
        int res = 0;
        int rooms = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            rooms += entry.getValue();
            res = Math.max(res, rooms);
        }
        return res;
    }
}
