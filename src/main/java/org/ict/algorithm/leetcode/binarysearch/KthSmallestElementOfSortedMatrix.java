package org.ict.algorithm.leetcode.binarysearch;

import java.util.PriorityQueue;

/**
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order,
 * return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
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
 * @author sniper
 * @date 25 Nov, 2022
 * LC378
 * Tag:Heap, Binary-Search
 */
public class KthSmallestElementOfSortedMatrix {

    public int kthSmallestV2(int[][] matrix, int k) {

        return 0;
    }

    /**
     * Binary-Search-Solution.
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallestV1(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[m-1][n-1];
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = searchLessEqual(matrix, mid);
            if (cnt < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * Search from the left-down-corner of the matrix
     * @param matrix
     * @param target
     * @return
     */
    private int searchLessEqual(int[][] matrix, int target) {
        int m = matrix.length, i = m - 1, j = 0, res = 0;
        while (i >= 0 && j < m) {
            if (matrix[i][j] <= target) {
                res += i + 1;
                ++j;
            } else {
                --i;
            }
        }
        return res;
    }

    /**
     * Max-Heap Solution
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
