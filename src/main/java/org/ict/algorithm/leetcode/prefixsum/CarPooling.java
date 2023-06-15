package org.ict.algorithm.leetcode.prefixsum;

import java.util.TreeMap;

/**
 * There is a car with capacity empty seats.
 * The vehicle only drives east (i.e., it cannot turn around and drive west).
 * You are given the integer capacity and an array trips where trips[i] = [numPassengers-i, from-i, to-i] indicates
 * that the ith trip has numPassengers-i passengers
 * and the locations to pick them up and drop them off are from-i and to-i respectively.
 * The locations are given as the number of kilometers due east from the car's initial location.
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 * Example 1:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 *
 * Example 2:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 *
 *
 * Constraints:
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengers-i <= 100
 * 0 <= from-i < toi <= 1000
 * 1 <= capacity <= 10^5
 *
 * @author sniper
 * @date 15 Jun 2023
 * LC1094, Medium, frequency=10
 * {@link org.ict.algorithm.leetcode.array.MeetingRoomsII}
 */
public class CarPooling {

    public boolean carPoolingV1(int[][] trips, int capacity) {
        /**
         * Notice using TreeMap instead of HashMap here.
         */
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] trip : trips) {
            int numberOfPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            map.merge(from, numberOfPassengers, Integer::sum);
            map.merge(to, -numberOfPassengers, Integer::sum);
        }

        int cnt = 0;
        for (int numberOfPassengers : map.values()) {
            cnt += numberOfPassengers;
            if (cnt > capacity) {
                return false;
            }
        }
        return true;
    }

    /**
     * Understanding the following solution
     * Time Cost 1ms
     * -------------------------------------
     * class Solution {
     * public:
     *     bool carPooling(vector<vector<int>>& trips, int capacity) {
     *         vector<int> prefix(1001);
     *         for (auto &trip : trips) {
     *             prefix[trip[1]] += trip[0];
     *             prefix[trip[2]] -= trip[0];
     *         }
     *
     *         int cnt = 0;
     *         for (int i = 0; i < 1001; i++) {
     *             cnt += prefix[i];
     *             if (cnt > capacity) {
     *                 return false;
     *             }
     *         }
     *         return true;
     *     }
     * };
     * ------------------------------------
     * class Solution:
     *     def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
     *         prefix = [0] * 1001
     *         for x, f, t in trips:
     *             prefix[f] += x
     *             prefix[t] -= x
     *         return all(s <= capacity for s in accumulate(prefix))
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] prefix = new int[1001];
        for (int[] trip : trips) {
            prefix[trip[1]] += trip[0];
            prefix[trip[2]] -= trip[0];
        }

        int cnt = 0;
        for (int i = 0; i < 1001; i++) {
            cnt += prefix[i];
            if (cnt > capacity) {
                return false;
            }
        }
        return true;
    }

}
