package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane.
 * The balloons are represented as a 2D integer array points
 * where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend.
 * You do not know the exact y-coordinates of the balloons.
 *
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
 * There is no limit to the number of arrows that can be shot.
 * A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 *
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 * Example 2:
 *
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
 * Example 3:
 *
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
 * - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 10^5
 * points[i].length == 2
 * -2^31 <= xstart < xend <= 2^31 - 1
 * @author sniper
 * @date 05 Jan, 2023
 * LC452
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    public int findMinArrowShotsV2(int[][] points) {
        return 0;
    }

    public int findMinArrowShotsV1(int[][] points) {
        //todo
        return 0;
    }

    /**
     * Understanding the following solution.
     *
     * Greedy Solution.
     * Overlapping Interval Problem
     * {@link LC56 LC435 LC453 LC252 LC253 LC1094}
     * Time Cost 121 ms
     *
     * 1.Sort intervals/pairs in increasing order of the start position.
     *
     * 2.Scan the sorted intervals, and maintain an "active set" for overlapping intervals.
     * At most times, we do not need to use an explicit set to store them.
     * Instead, we just need to maintain several key parameters,
     * e.g. the number of overlapping intervals (count),
     * the minimum ending point among all overlapping intervals (minEnd).
     *
     * 3.If the interval that we are currently checking overlaps with the active set,
     * which can be characterized by cur.start > minEnd,
     * we need to renew those key parameters or change some states.
     *
     * 4.If the current interval does not overlap with the active set,
     * we just drop current active set,
     * record some parameters,
     * and create a new active set that contains the current interval.
     *
     * e.g.points = [[10, 16], [2, 8], [1, 6], [7, 12]]
     * sort:[[1, 6], [2, 8], [7, 12], [10, 16]]
     * - - - - - -
     *   - - - - - - -
     *             - - - - - -
     *                   - - - - - - -
     * point:[1,6], minEnd:Integer.MAX_VALUE, point[0]:1, 1 < minEnd, minEnd = min(minEnd, 6) = 6, intersect range:[]
     * point:[2,8], minEnd:6, point[0]:2, 2 < 6, minEnd = min(6, 8) = 6, intersect range:[2, 6]
     * point:[7,12], minEnd:6, point[0]:7, 7 > 6, res++ --> res:1, minEnd = point[1] = 12, intersect range:[]
     * point:[10, 16], minEnd:12, point[0]:10, 10 < 12, minEnd = min(12, 16) = 12, intersect range:[10, 12]
     * for-loop-end
     * return res + 1 = 1 + 1 = 2
     *
     * @author wangxinbo
     * @see <a href="https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/93735/a-concise-template-for-overlapping-interval-problem"></a>
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points == null || points[0].length == 0) {
            return 0;
        }
        int res = 0;
        int minEnd = Integer.MAX_VALUE;
        /**
         * point = (head, tail)
         * Sort points by the head of each point.
         */
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        for (int[] point : points) {
            if (point[0] > minEnd) {
                res++;
                minEnd = point[1];
            } else {
                /**
                 * because we find the intersection with the current interval,
                 * so we choose min function.
                 */
                minEnd = Math.min(minEnd, point[1]);
            }
        }
        /**
         * [1, 6] and [2, 8], intersect range:[2, 6]
         * [2, 6] and [7, 12] no intersect
         * [7, 12] and [10, 16] intersect range:[10, 12]
         */
        return res + 1;
    }

}
