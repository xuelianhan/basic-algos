package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * A school is trying to take an annual photo of all the students.
 * The students are asked to stand in a single file line in non-decreasing order by height.
 * Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.
 * You are given an integer array heights representing the current order that the students are standing in.
 * Each heights[i] is the height of the ith student in line (0-indexed).
 * Return the number of indices where heights[i] != expected[i].
 *
 * Example 1:
 * Input: heights = [1,1,4,2,1,3]
 * Output: 3
 * Explanation:
 * heights:  [1,1,4,2,1,3]
 * expected: [1,1,1,2,3,4]
 * Indices 2, 4, and 5 do not match.
 *
 * Example 2:
 * Input: heights = [5,1,2,3,4]
 * Output: 5
 * Explanation:
 * heights:  [5,1,2,3,4]
 * expected: [1,2,3,4,5]
 * All indices do not match.
 *
 * Example 3:
 * Input: heights = [1,2,3,4,5]
 * Output: 0
 * Explanation:
 * heights:  [1,2,3,4,5]
 * expected: [1,2,3,4,5]
 * All indices match.
 *
 * Constraints:
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 * @author sniper
 * @date 16 May 2023
 * LC1051, Easy, frequency=20
 */
public class HeightChecker {

    /**
     * Counting Sort Solution
     * Time Cost 0ms
     * -------------------------------------
     * class Solution:
     *     def heightChecker(self, heights: List[int]) -> int:
     *         res = 0
     *         currentHeight = 1
     *         count = [0] * 101
     *         for height in heights:
     *             count[height] += 1
     *         for height in heights:
     *             while count[currentHeight] == 0:
     *                 currentHeight += 1
     *             if currentHeight != height:
     *                 res += 1
     *             count[currentHeight] -= 1
     *         return res
     * -------------------------------------
     * class Solution {
     * public:
     *     int heightChecker(vector<int>& heights) {
     *         int res = 0;
     *         int currentHeight = 1;
     *         vector<int> count(101);
     *         for (int height : heights) {
     *             count[height]++;
     *         }
     *         for (int height : heights) {
     *             while (count[currentHeight] == 0) {
     *                 currentHeight++;
     *             }
     *             if (currentHeight != height) {
     *                 res++;
     *             }
     *             count[currentHeight]--;
     *         }
     *         return res;
     *     }
     * };
     * @param heights
     * @return
     */
    public int heightCheckerV1(int[] heights) {
        int res = 0;
        int currentHeight = 1;
        int[] count = new int[101];

        for (int height : heights) {
            count[height]++;
        }

        for (int height: heights) {
            while (count[currentHeight] == 0) {
                ++currentHeight;
            }
            if (height != currentHeight) {
                res++;
            }
            count[currentHeight]--;
        }
        return res;
    }

    /**
     * Time Cost 1ms
     * ------------------
     * class Solution {
     * public:
     *     int heightChecker(vector<int>& heights) {
     *         int res = 0;
     *         int n = heights.size();
     *         //Using assignment operator to copy one vector to another.
     *         vector<int> copy = heights;
     *         sort(copy.begin(), copy.end());
     *         for (int i = 0; i < n; i++) {
     *             if (copy[i] != heights[i]) {
     *                 res++;
     *             }
     *         }
     *         return res;
     *     }
     * };
     * ------------------
     * class Solution:
     *     def heightChecker(self, heights: List[int]) -> int:
     *         dist = heights.copy()
     *         dist.sort()
     *         res = 0
     *         for i in range(len(heights)):
     *             if heights[i] != dist[i]:
     *                 res += 1
     *         return res
     * @param heights
     * @return
     */
    public int heightChecker(int[] heights) {
        int n = heights.length;
        int[] dist = new int[n];
        System.arraycopy(heights, 0, dist, 0, n);
        Arrays.sort(dist);
        int res = 0;
        for (int i = 0; i< n; i++) {
            if (heights[i] != dist[i]) {
                res++;
            }
        }
        return res;
    }
}
