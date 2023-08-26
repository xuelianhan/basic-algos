package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * You are given an array of n pairs where pairs[i] = [left-i, right-i] and left-i < right-i.
 *
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 *
 * Return the longest length chain which can be formed.
 *
 * You do not need to use up all the given intervals. You can select pairs in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: pairs = [[1,2],[2,3],[3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4].
 * Example 2:
 *
 * Input: pairs = [[1,2],[7,8],[4,5]]
 * Output: 3
 * Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].
 *
 *
 * Constraints:
 *
 * n == pairs.length
 * 1 <= n <= 1000
 * -1000 <= left-i < right-i <= 1000
 * @author sniper
 * @date 26 Aug 2023
 * LC646, Medium, frequency=2
 */
public class MaximumLengthOfPairChain {



    /**
     * Time Cost 8ms
     * ------------------------------------
     * class Solution:
     *     def findLongestChain(self, pairs: List[List[int]]) -> int:
     *         res = 0
     *         prevEnd = -math.inf
     *
     *         for s, e in sorted(pairs, key=lambda x: x[1]):
     *             if s > prevEnd:
     *                 res += 1
     *                 prevEnd = e
     *
     *         return res
     * ---------------------------------------
     * class Solution {
     * public:
     *     int findLongestChain(vector<vector<int>>& pairs) {
     *         int res = 0, end = INT_MIN;
     *         sort(pairs.begin(), pairs.end(), [](vector<int>& a, vector<int>& b) {
     *             return a[1] < b[1];
     *         });
     *         for (auto pair : pairs) {
     *             if (pair[0] > end) {
     *                 ++res;
     *                 end = pair[1];
     *             }
     *         }
     *         return res;
     *     }
     * };
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        int res = 0;
        int prevEnd = Integer.MIN_VALUE;

        /**
         * Notice here sorting by the second element.
         */
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        for (int[] pair : pairs) {
            if (pair[0] > prevEnd) {
                res++;
                prevEnd = pair[1];
            }
        }
        return res;
    }

}
