package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array representing a row of seats
 * where seats[i] = 1 represents a person sitting in the ith seat,
 * and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 *
 * There is at least one empty seat, and at least one person sitting.
 *
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 * Return that maximum distance to the closest person.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: seats = [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 *
 * Input: seats = [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 *
 * Example 3:
 * Input: seats = [0,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 2 <= seats.length <= 2 * 10^4
 * seats[i] is 0 or 1.
 * At least one seat is empty.
 * At least one seat is occupied.
 * @author sniper
 * @date 26 Aug 2023
 * LC849, Medium, frequency=8
 */
public class MaximizeDistanceToClosestPerson {

    /**
     * Two Pointers Solution
     * Time Cost 2ms
     * @param seats
     * @return
     */
    public int maxDistToClosestV2(int[] seats) {
        int n = seats.length;
        int res = 0;
        int start = -1;

        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                res = (start == -1 ? i : Math.max(res, (i - start) / 2));
                start = i;
            }
        }
        return Math.max(res, n - start - 1);
    }


    /**
     * Understanding the following solution
     * Two Pointers Solution:
     * Time Cost 2ms
     * start points at the start position of consecutive 0.
     * i points at the current position with 1
     *
     * @param seats
     * @return
     */
    public int maxDistToClosestV1(int[] seats) {
        int n = seats.length;
        int start = 0;
        int res = 0;

        for (int i = 0; i < n; ++i) {
            if (seats[i] != 1) {
                continue;
            }
            if (start == 0) {
                res = Math.max(res, i - start);
            } else {
                res = Math.max(res, (i - start + 1) / 2);
            }
            start = i + 1;
        }

        res = Math.max(res, n - start);
        return res;
    }

    /**
     * Time Cost 6ms
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int res = 0;
        /**
         * At first, recording all the 1's position index into an array.
         */
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                nums.add(i);
            }
        }
        /**
         *
         * Two cases:
         *
         * 1.the first position
         * e.g. seats = [0,0,0,1], Alex should sit on the first seat.
         * The max distance from the closest person is max(0, 3)=3
         *
         * 2.other positions except the first, Alex should sit on the position with index-2
         * e.g.seats = [1,0,0,0,1,0,1]
         *              0 1 2 3 4 5 6
         * The max distance from the closest person is max(0, (4 - 0)/ 2) = 2
         */
        for (int i = 0; i < nums.size(); i++) {
            if (i == 0) {
                res = Math.max(res, nums.get(0));
            } else {
                res = Math.max(res, (nums.get(i) - nums.get(i - 1)) / 2);
            }
        }
        /**
         * If the right-most is a wall, Alex should sit on the last seat:
         * e.g.seats = [1,0,0,0,1,0,1,0,0,0,0]
         *              0 1 2 3 4 5 6 7 8 9 10
         * The max distance from the closest person is max(2, 10 - 6) = 4
         */
        if (!nums.isEmpty()) {
            res = Math.max(res, n - 1 - nums.get(nums.size() - 1));
        }

        return res;
    }
}
