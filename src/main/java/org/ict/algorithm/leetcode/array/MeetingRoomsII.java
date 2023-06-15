package org.ict.algorithm.leetcode.array;

import java.util.*;

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
        int res = instance.minMeetingRoomsV3(intervals);
        System.out.println(res);
    }

    /**
     * Understanding the following solution
     *
     * Min-Heap Solution
     *
     * First sort all the time intervals by start time,
     * then create a new minimum heap and start traversing the time intervals.
     * If the heap is not empty and the first element is less than or equal to the start time of the current interval,
     * the first element of the heap is removed,
     * and the end time of the current interval is pressed into the heap,
     * because the smallest element is in front of the heap,
     * so if the first element is less than or equal to the start time,
     * it means that the last meeting has ended and the next meeting can be started with that meeting room,
     * so there is no need to allocate a new meeting room,
     * and the number of elements in the heap is the number of rooms needed after the traversal is completed.
     *
     * e.g.intervals = [[0,30],[5,10],[15,20]]
     * After sorting by start time: [[0,30],[5,10],[15,20]]
     * iterate [0,30], push 30 into minHeap, minHeap:30
     * iterate [5,10], minHeap is not empty, peek:30, peek > 5, push 10 into minHeap, minHeap:10, 30
     * iterate [15,20], minHeap is not empty, peek:10, peek < 15, poll 10 from minHeap, minHeap:30, push 20 into minHeap, minHeap:20, 30
     * minHeap:20, 30
     * return minHeap.size()=2
     * --------------------------------------
     * class Solution:
     *   def minMeetingRoomsV3(self, intervals: List[List[int]]) -> int:
     *     minHeap = []  # Store end times of each room.
     *
     *     for start, end in sorted(intervals):
     *       # No overlap, we can reuse the same room.
     *       if minHeap and start >= minHeap[0]:
     *         heapq.heappop(minHeap)
     *       heapq.heappush(minHeap, end)
     *
     *     return len(minHeap)
     * --------------------------------------
     * class Solution {
     *  public:
     *   int minMeetingRoomsV3(vector<vector<int>>& intervals) {
     *     sort(begin(intervals), end(intervals));
     *
     *     // Store end times of each room.
     *     priority_queue<int, vector<int>, greater<>> minHeap;
     *
     *     for (const vector<int>& interval : intervals) {
     *       // No overlap, we can reuse the same room.
     *       if (!minHeap.empty() && interval[0] >= minHeap.top())
     *         minHeap.pop();
     *       minHeap.push(interval[1]);
     *     }
     *
     *     return minHeap.size();
     *   }
     * };
     * @param intervals
     * @return
     */
    public int minMeetingRoomsV3(int[][] intervals) {
        /**
         * Sort by start time at first.
         * Both of the following two sorting methods are OK.
         */
        //Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);


        /**
         * The minHeap stores the end-time of each room.
         */
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] interval : intervals) {
            /**
             * if peek of end-time less than or equals to start-time of current interval,
             * so there's no need to allocate a new meeting room, and kick it out of the minHeap.
             */
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll();
            }
            minHeap.offer(interval[1]);
        }

        return minHeap.size();
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
     * -------------------------------------------
     * class Solution {
     * public:
     *     int minMeetingRoomsV2(vector<vector<int>>& intervals) {
     *         map<int, int> m;
     *         for (auto a : intervals) {
     *             ++m[a[0]];
     *             --m[a[1]];
     *         }
     *         int rooms = 0, res = 0;
     *         for (auto it : m) {
     *             res = max(res, rooms += it.second);
     *         }
     *         return res;
     *     }
     * };
     * @param intervals
     * @return
     */
    public int minMeetingRoomsV2(int[][] intervals) {
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

    /**
     * Understanding the following solution.
     *
     * The following is similar with TreeMap in minMeetingRoomsV2.
     * But it may generate some invalid calculation.
     * The advantage of this method is that it is shorter than minMeetingRoomsV2
     *
     * e.g.intervals = [[0,30],[5,10],[15,20]]
     * index:     0, 5, 10, 15, 20, 30
     * prefixSum:[1, 1, -1, 1, -1, -1]
     * i:1, prefixSum[1]=0+1=1, res=max(0, 1)=1
     * i:5, prefixSum[5]=1+1=2, res=max(1, 2)=2
     * i:10,prefixSum[10]=2-1=1, res=max(2, 1)=1
     * i:15,prefixSum[15]=1+1=2, res=max(2, 2)=2
     * i:20,prefixSum[20]=2-1=1, res=max(2, 1)=2
     * i:30,prefixSum[30]=1-1=0, res=max(2, 0)=2
     * --------------------------------------------
     * class Solution:
     *     def minMeetingRooms(self, intervals: List[List[int]]) -> int:
     *         prefixSum = [0] * 1000010
     *         for start, end in intervals:
     *             prefixSum[start] += 1
     *             prefixSum[end] -= 1
     *         return max(accumulate(prefixSum))
     * --------------------------------------------
     * class Solution {
     * public:
     *     int minMeetingRooms(vector<vector<int>>& intervals) {
     *         int n = 1000010;
     *         vector<int> prefixSum(n);
     *         for (auto e : intervals) {
     *             ++prefixSum[e[0]];
     *             --prefixSum[e[1]];
     *         }
     *         for (int i = 0; i < n - 1; ++i) {
     *             prefixSum[i + 1] += prefixSum[i];
     *         }
     *         return *max_element(prefixSum.begin(), prefixSum.end());
     *     }
     * };
     * @param intervals
     * @return
     * {@link org.ict.algorithm.leetcode.prefixsum.CarPooling }
     */
    public int minMeetingRoomsV1(int[][] intervals) {
        /**
         * 0 <= start-i < end-i <= 10^6
         */
        int n = 1_000_010;
        int[] prefixSum = new int[n];
        for (int[] a : intervals) {
            prefixSum[a[0]]++;
            prefixSum[a[1]]--;
        }

        int res = prefixSum[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] += prefixSum[i - 1];
            res = Math.max(res, prefixSum[i]);
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
    public int minMeetingRooms(int[][] intervals) {
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


}
