package org.ict.algorithm.leetcode.binarysearch;

import java.util.PriorityQueue;

/**
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order,
 * return the kth-smallest element in the matrix.
 *
 * Note that it is the kth-smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n^2).
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1, 5, 9],
 *                  [10,11,13],
 *                  [12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * Example 2:
 *
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 *
 * Constraints:
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n^2
 *
 *
 * Follow up:
 *
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * Could you solve the problem in O(n) time complexity?
 * The solution may be too advanced for an interview but you may find reading this paper fun.
 * <a href="http://www.cse.yorku.ca/~andy/pubs/X+Y.pdf">this paper</a>
 *
 * More Simple problem is LC1351
 * @author sniper
 * @date 25 Nov, 2022
 * LC378
 * Tag:Heap, Binary-Search
 */
public class KthSmallestElementOfSortedMatrix {

    /**
     * Binary-Search-Solution.
     * Time Cost 1ms
     * @param matrix
     * @param k
     * @return
     * @author GraceMeng
     */
    public int kthSmallestV3(int[][] matrix, int k) {
        int n = matrix.length, lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        /**
         * Notice less than and equal sign(<=) here between lo and hi
         * This means the search space is two-closed range [left, right]
         */
        while (lo <= hi) {
            int mi = lo + ((hi - lo) >> 1);
            int count = countNonBiggerV2(matrix, mi);
            if (count < k) {
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }

        return lo;
    }

    private static int countNonBiggerV3(int[][] matrix, int target) {
        int m = matrix.length, i = m - 1, j = 0, cnt = 0;
        while (i >= 0 && j < m) {
            if (matrix[i][j] > target) {
                /**
                 * move up
                 */
                i--;
            } else {
                /**
                 * move right, ++j indicates the 1, and plus i to the current res.
                 */
                cnt += i + 1;
                j++;
            }
        }

        return cnt;
    }

    /**
     * Binary-Search-Solution.
     * Time Cost 0ms
     *
     * Search from the left-down-corner(i = m - 1, j = 0) of the matrix
     * e.g.
     * Input: matrix = [[1, 5, 9],
     *                 [10,11,13],
     *                 [12,13,15]], k = 8
     * left:1, right:15, mid:8
     * i = 3 - 1 = 2, j = 0
     * we start searching target 8 from the element matrix[2][0] = 12,
     * 12 > 8, so i--, i:1, j:0
     * i:1, j:0, matrix[1][0] = 10
     * 10 > 8, so i--, i:0, j:0
     * i:0, j:0, matrix[0][0] = 1
     * 1 < 8, res = 0 + 1 = 1, j++, j:0
     * i:0, j:1, matrix[0][1] = 5
     * 5 < 8, res = 1 + (0 + 1) = 2, j++, j:2
     * i:0, j:2, matrix[0][2] = 9
     * 9 > 8, i--, i:-1, break-while-loop, return search cnt:2
     * 2 < 8, left: 8+1=9, left:9 < right:15, mid: 12
     *
     * i:2, j:0, target:12, matrix[2][0] = 12
     * 12 <= 12, res = 2 + 1 = 3, j++, j:1
     * i:2, j:1, matrix[2][1] = 13
     * 13 > 12, i--, i:1, j:1
     * i:1, j:1, matrix[1][1] = 11
     * 11 < 12, res = 3 + (1+1) = 5, j++, j:2
     * i:1, j:2, matrix[1][2] = 13
     * 13 > 12, i--, i:0, j:2
     * i:0, j:2, matrix[0][2] = 9
     * 9 < 12, res = 5 + (0+1) = 6, j++, j:3, break-while-loop, return search cnt:6
     *
     * 6 < 8, left = mid + 1 = 12 + 1 = 13, left < right:15, mid = 14
     *
     * i:2, j:0, target:14, matrix[2][0] = 12
     * 12 < 14, res = 2 + 1 = 3, j++, j:1
     * i:2, j:1, matrix[2][1] = 13
     * 13 < 14, res = 3 + (2+1) = 6, j++, j:2
     * i:2, j:2, matrix[2][2] = 15
     * 15 > 14, i--, i:1, j:2, matrix[1][2] = 13
     * 13 < 14, res = 6 + (1+1) = 8, j++, j:3, break-while-loop, return search cnt:8
     * cnt:8, 8 == k, right=mid:14, left:13, left < right, mid = 13
     *
     * i:2, j:0, target:13, matrix[2][0] = 12
     * 12 < 13, res = 3, j++, j:1
     * i:2, j:1, matrix[2][1] = 13
     * 13 == 13, res = 3 + (2+1) = 6, j++, j:2
     * i:2, j:2,  matrix[2][2] = 15
     * 15 > 13, i--, i:1, j:2, matrix[1][2] = 13
     * 13 == 13, res = 6 + (1+1) = 8, j++, j:3, break-while-loop, return search cnt:8
     * cnt:8, 8 == k, right=mid:13, left:13, outer-while-loop-end, return left:13
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallestV2(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[m-1][n-1];
        /**
         * Notice less than sign(<) here between left and right
         * This means the search space is not include the right [left, right), it's a right-half-opened range.
         */
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = countNonBiggerV2(matrix, mid);
            if (cnt < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int countNonBiggerV2(int[][] matrix, int target) {
        int m = matrix.length, i = m - 1, j = 0, res = 0;
        while (i >= 0 && j < m) {
            if (matrix[i][j] <= target) {
                /**
                 * move right, ++j indicates the 1, and plus i to the current res.
                 */
                res += i + 1;
                ++j;
            } else {
                /**
                 * move up
                 */
                --i;
            }
        }
        return res;
    }

    /**
     * Max-Heap Solution
     * Time Cost 47ms
     * Time Complexity O(M * N * logK)
     * Space Complexity O(logK), space for the heap which stores up to k elements.
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallestV1(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> Integer.compare(o2, o1)));
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxHeap.offer(matrix[i][j]);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }

        return maxHeap.peek();
    }

    /**
     * Max-Heap Solution
     * Time Cost 21ms
     * Time Complexity O(M * N * logK)
     * Space Complexity O(logK), space for the heap which stores up to k elements.
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> {
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            } else {
                return 0;
            }
        }));
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxHeap.offer(matrix[i][j]);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }

        return maxHeap.peek();
    }
}
