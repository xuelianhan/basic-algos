package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Description
 * You are given a nested list of integers nestedList.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * The depth of an integer is the number of lists that this integer is inside of it.
 * For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 * Return the sum of each integer in nestedList multiplied by its depth.
 *
 * Example 1:
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 10
 * 2 inside of one: [2]
 * 1 inside of two: [[1]]
 * Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
 *
 * Example 2:
 * Input: nestedList = [1,[4,[6]]]
 * Output: 27
 * 1 inside of one:[1]
 * 4 inside of two:[[4]]
 * 6 inside of three:[[[6]]]
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
 *
 *
 * Example 3:
 * Input: nestedList = [0]
 * Output: 0
 * 0 inside of one:[0]
 * 0*1=0
 *
 *
 * Constraints:
 * 1 <= nestedList.length <= 50
 * The values of the integers in the nested list is in the range [-100, 100].
 * The maximum depth of any integer is less than or equal to 50.
 * @author sniper
 * @date 19 Apr, 2023
 * LC339, Medium, frequency=134
 */
public class NestedListWeightSum {

    public int depthSumV2(List<NestedInteger> nestedList) {
        /**
         * At here, res can be replaced with a global variable.
         */
        int[] res = new int[1];
        dfsV2(nestedList, 1, res);
        return res[0];
    }

    private void dfsV2(List<NestedInteger> nestedList, int depth, int[] res) {
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                res[0] += item.getInteger() * depth;
            } else {
                dfsV2(item.getList(), depth + 1, res);
            }
        }
    }

    /**
     * Understanding the following solution
     *
     * Depth-First-Search Solution
     *
     * e.g. nestedList = [1,[4,[6]]]
     *
     * @param nestedList
     * @return
     */
    public int depthSumV1(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                sum += item.getInteger() * depth;
            } else {
                sum += dfs(item.getList(), depth + 1);
            }
        }
        return sum;
    }

    /**
     * Understanding the following solution
     *
     * Breadth-First-Search
     *
     * @param nestedList
     * @return
     */
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new ArrayDeque<>();
        for (NestedInteger item : nestedList) {
            queue.offer(item);
        }

        int res = 0;
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            for (int size = queue.size(); size > 0; size--) {
                NestedInteger cur = queue.poll();
                if (cur.isInteger()) {
                    res += cur.getInteger() * depth;
                } else {
                    for (NestedInteger item : cur.getList()) {
                        queue.offer(item);
                    }
                }
            }
        }
        return res;
    }

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *     // Constructor initializes an empty nested list.
     *     public NestedInteger();
     *
     *     // Constructor initializes a single integer.
     *     public NestedInteger(int value);
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // Set this NestedInteger to hold a single integer.
     *     public void setInteger(int value);
     *
     *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
     *     public void add(NestedInteger ni);
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return empty list if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
}
