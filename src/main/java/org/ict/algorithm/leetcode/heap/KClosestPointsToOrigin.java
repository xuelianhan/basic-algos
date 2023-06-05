package org.ict.algorithm.leetcode.heap;

import java.util.PriorityQueue;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 * return the k-closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)^2 + (y1 - y2)^2).
 * You may return the answer in any order.
 * The answer is guaranteed to be unique (except for the order that it is in).
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 *
 *
 * Constraints:
 * 1 <= k <= points.length <= 10^4
 * -10^4 < xi, yi < 10^4
 * @author sniper
 * @date 27 Mar, 2023
 * LC973, Medium, frequency=183
 * Amazon OA
 */
public class KClosestPointsToOrigin {

    /**
     * Similar with kClosest but more concise.
     *
     * Euclidean distance's square:
     * (x1 - x2)^2 + (y1 - y2)^2
     * = x1^2 - 2*x1*x2 + x2^2 + y1^2 - 2*y1*y2 + y2^2
     * = x1^2 + x2^2 + y1^2 + y2^2 - 2*x1*x2 - 2*y1*y2
     * While (x2, y2) is (0, 0), the above statement can be simplified as following:
     * = x1^2 + y1^2
     * As we compare distance to original point of two points, if we want to in descendant order
     * we do like this:
     * (p1, p2) -> (p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1])
     * ----------------------------------------------------------------------------
     * class Solution {
     * public:
     *     vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
     *         vector<vector<int>> res;
     *         priority_queue<pair<int, int>> pq;
     *         for (int i = 0; i < points.size(); ++i) {
     *             int t = points[i][0] * points[i][0] + points[i][1] * points[i][1];
     *             pq.push({t, i});
     *             if (pq.size() > k) {
     *                 pq.pop();
     *             }
     *         }
     *         while (!pq.empty()) {
     *             auto t = pq.top();
     *             pq.pop();
     *             res.push_back(points[t.second]);
     *         }
     *         return res;
     *     }
     * };
     * ------------------------------------------------------------------------------
     *
     * @see <a href="https://leetcode.com/problems/k-closest-points-to-origin/solutions/220235/java-three-solutions-to-this-classical-k-th-problem/"></a>
     * @param points
     * @param k
     * @return
     */
    public int[][] kClosestV1(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (p1, p2) -> (p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1])
        );
        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] res = new int[k][2];
        while (k > 0) {
            res[--k] = maxHeap.poll();
        }
        return res;
    }

    /**
     * The advantage of this solution is it can deal with real-time(online) stream data.
     * It does not have to know the size of the data previously.
     * The disadvantage of this solution is it is not the most efficient solution.
     * --------------------------------------------------
     * import heapq
     *
     * class Solution:
     *     def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
     *         heap = []
     *         for (x, y) in points:
     *             dist = -(x*x + y*y)
     *             if len(heap) == k:
     *                 heapq.heappushpop(heap, (dist, x, y))
     *             else:
     *                 heapq.heappush(heap, (dist, x, y))
     *         return [(x, y) for (dist, x, y) in heap]
     * ---------------------------------------------------
     *
     * @param points
     * @param k
     * @return
     */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(k, (p1, p2) -> {
            double d1 = getDistance(p1);
            double d2 = getDistance(p2);

            if (d1 == d2) {
                return 0;
            }
            return (d1 < d2 ? 1 : -1);
        });
        for (int[] point : points) {
            Point p = new Point(point[0], point[1]);
            maxHeap.offer(p);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        int[][] res = new int[k][2];
        int i = 0;
        for (Point p : maxHeap) {
            res[i][0] = p.x;
            res[i][1] = p.y;
            i++;
        }
        return res;
    }

    private double getDistance(Point p){
        return Math.sqrt(p.x * p.x + p.y * p.y);
    }


    private static class Point {
        private int x;
        private int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return ("(" + x + " ," + y + ")");
        }
    }
}
