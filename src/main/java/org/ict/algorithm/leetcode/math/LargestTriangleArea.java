package org.ict.algorithm.leetcode.math;

/**
 * Given an array of points on the X-Y plane points where points[i] = [xi, yi],
 * return the area of the largest triangle that can be formed by any three different points.
 * Answers within 10^(-5) of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * Output: 2.00000
 * Explanation: The five points are shown in the above figure.
 * The red triangle is the largest.
 * Example 2:
 *
 * Input: points = [[1,0],[0,0],[0,1]]
 * Output: 0.50000
 *
 *
 * Constraints:
 *
 * 3 <= points.length <= 50
 * -50 <= xi, yi <= 50
 * All the given points are unique.
 * @author sniper
 * @date 12 Oct, 2022
 * LC812
 */
public class LargestTriangleArea {

    /**
     * points = [[4,6],[6,5],[3,1]], expected = 5.5
     * @param points
     * @return
     */
    public double largestTriangleArea(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i+1; j < N; ++j) {
                for (int k = j+1; k < N; ++k) {
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
                }
            }
        }
        return ans;
    }

    public double area(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }
}
