package org.ict.algorithm.leetcode.array;

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
        int res = instance.minMeetingRooms(intervals);
        System.out.println(res);
    }
    public int minMeetingRoomsV3(int[][] intervals) {
        //todo
        return 0;
    }

    public int minMeetingRoomsV2(int[][] intervals) {
        //todo
        return 0;
    }


    public int minMeetingRoomsV1(int[][] intervals) {
        //todo
        return 0;
    }

    /**
     * Those with time conflicts will need to be arranged in a separate meeting room,
     * while those without time conflicts can share the meeting room.
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
