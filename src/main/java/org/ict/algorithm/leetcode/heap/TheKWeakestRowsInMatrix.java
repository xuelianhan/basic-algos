package org.ict.algorithm.leetcode.heap;

import java.util.PriorityQueue;

/**
 * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
 * The soldiers are positioned in front of the civilians.
 * That is, all the 1's will appear to the left of all the 0's in each row.
 *
 * A row i is weaker than a row j if one of the following is true:
 *
 * The number of soldiers in row i is less than the number of soldiers in row j.
 * Both rows have the same number of soldiers and i < j.
 *
 * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
 *
 *
 *
 * Example 1:
 *
 * Input: mat =
 * [[1,1,0,0,0],
 *  [1,1,1,1,0],
 *  [1,0,0,0,0],
 *  [1,1,0,0,0],
 *  [1,1,1,1,1]],
 * k = 3
 * Output: [2,0,3]
 * Explanation:
 * The number of soldiers in each row is:
 * - Row 0: 2
 * - Row 1: 4
 * - Row 2: 1
 * - Row 3: 2
 * - Row 4: 5
 * The rows ordered from weakest to strongest are [2,0,3,1,4].
 * Example 2:
 *
 * Input: mat =
 * [[1,0,0,0],
 *  [1,1,1,1],
 *  [1,0,0,0],
 *  [1,0,0,0]],
 * k = 2
 * Output: [0,2]
 * Explanation:
 * The number of soldiers in each row is:
 * - Row 0: 1
 * - Row 1: 4
 * - Row 2: 1
 * - Row 3: 1
 * The rows ordered from weakest to strongest are [0,2,3,1].
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] is either 0 or 1.
 * @author sniper
 * @date 12 Mar, 2023
 * LC1337, Easy
 */
public class TheKWeakestRowsInMatrix {

    public static void main(String[] args) {
        int[][] mat = {{1, 0}, {0, 0}, {1, 0}};
        int k = 2;
        TheKWeakestRowsInMatrix instance = new TheKWeakestRowsInMatrix();
        int oneCount = instance.countOne(mat[1]);
        System.out.println(oneCount);
        //instance.kWeakestRowsV1(mat, k);
    }

    public int[] kWeakestRowsV1(int[][] mat, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(((o1, o2) -> {
            if (o1[0] < o2[0]) {
                return 1;
            } else if (o1[0] > o2[0]) {
                return -1;
            } else {
                return (o1[1] < o2[1] ? 1 : -1);
            }
        }));
        for (int i = 0; i < mat.length; i++) {
            /**
             * [OneCounts, row index]
             */
            int[] pair = new int[] {countOne(mat[i]), i};
            //System.out.println(pair);
            maxHeap.offer(pair);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[] res = new int[k];
        while (k > 0) {
            res[--k] = maxHeap.poll()[1];
        }
        return res;
    }

    /**
     * Using binary search to find the count of one.
     * @param nums
     * @return
     */
    private int countOne(int[] nums) {
        /**
         * Notice here lo starts from zero, hi starts from nums.length
         * because the count of one in the array-nums may not exist(zero in this case),
         * or all one of nums(nums.length in this case).
         */
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == 1) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }


    /**
     * Time Cost 1ms
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(((o1, o2) -> {
            if (o1[0] < o2[0]) {
                return 1;
            } else if (o1[0] > o2[0]) {
                return -1;
            } else {
                return (o1[1] < o2[1] ? 1 : -1);
            }
        }));

        for (int i = 0; i < m; i++) {
            int pos = findZero(mat[i], 0);
            int[] pair = new int[] {pos, i};
            maxHeap.offer(pair);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[] res = new int[k];
        int i = k - 1;
        while (!maxHeap.isEmpty()) {
            res[i] = maxHeap.poll()[1];
            i--;
        }
        return res;
    }

    /**
     * Find the first position of zero using sequence-search
     * @param nums
     * @param x
     * @return
     */
    private int findZero(int[] nums, int x) {
        int pos = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                pos = i;
                break;
            }
        }
        return (pos == -1 ? nums.length : pos);
    }

}
