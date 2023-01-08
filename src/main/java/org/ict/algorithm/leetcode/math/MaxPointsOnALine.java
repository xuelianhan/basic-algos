package org.ict.algorithm.leetcode.math;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane,
 * return the maximum number of points that lie on the same straight line.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3
 * Example 2:
 *
 *
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -10^4 <= xi, yi <= 10^4
 * All the points are unique.
 * @author sniper
 * @date 08 Jan, 2023
 * LC149, Hard
 */
public class MaxPointsOnALine {

    /**
     * Why we are calculating GCD here?
     * Because some slopes will not be integers,
     * and thus you will have issues checking for equality with floats or doubles.
     * @param points
     * @return
     */
    public int maxPointsV2(int[][] points) {

        return 0;
    }

    /**
     * Similar with maxPoints, but making improvement at area calculate function, and corner-case of points.length <= 2.
     * Time Cost 16ms
     * @param points
     * @return
     */
    public int maxPointsV1(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }
        int res = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                int total = 2;
                for (int k = 0; k != i && k != j && k < n; k++) {
                    int x3 = points[k][0];
                    int y3 = points[k][1];
                    if (areaV1(x1, y1, x2, y2, x3, y3) == 0) {
                        total++;
                    }
                }
                res = Math.max(res, total);
            }
        }
        return res;
    }

    public int areaV1(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
    }

    /**
     * Brute-Force-Approach
     * Time Cost 35ms
     * Using Area of triangle
     * S = (1/2) * (x1*y2 + x2*y3 + x3*y1 - x1*y3 - x2*y1 - x3*y2)
     * When S == 0, then (x1, y1), (x2, y2), (x3, y3) are in one line.
     * So we can check Z = (x1*y2 + x2*y3 + x3*y1 - x1*y3 - x2*y1 - x3*y2) is zero or not.
     * if Z is zero, then the three points are in one line.
     * @see LargestTriangleArea
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        if (points.length == 1) {
            return 1;
        }
        int res = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                int total = 2;
                for (int k = 0; k != i && k != j && k < n; k++) {
                    int x3 = points[k][0];
                    int y3 = points[k][1];
                    if (area(x1, y1, x2, y2, x3, y3) == 0) {
                        total++;
                    }
                }
                res = Math.max(res, total);
            }
        }
        return res;
    }

    public int area(int x1, int y1, int x2, int y2, int x3, int y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)));
    }
}
