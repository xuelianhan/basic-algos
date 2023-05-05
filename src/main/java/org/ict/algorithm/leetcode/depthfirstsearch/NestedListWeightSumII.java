package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.*;

/**
 * Description
 * You are given a nested list of integers nestedList.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * The depth of an integer is the number of lists that is inside of it.
 * For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 * Let maxDepth be the maximum depth of any integer.
 *
 * The weight of an integer is maxDepth - (the depth of the integer) + 1.
 * Return the sum of each integer in nestedList multiplied by its weight.
 *
 * Example 1:
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 8
 * Explanation: Four 1's with a weight of 1, one 2 with a weight of 2.
 * 1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
 *
 * Example 2:
 * Input: nestedList = [1,[4,[6]]]
 * Output: 17
 * Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1.
 * 1*3 + 4*2 + 6*1 = 17
 *
 * Constraints:
 * 1 <= nestedList.length <= 50
 * The values of the integers in the nested list is in the range [-100, 100].
 * The maximum depth of any integer is less than or equal to 50.
 * @author sniper
 * @date 01 May 2023
 * LC364, Medium, frequency=48
 */
public class NestedListWeightSumII {

    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();

        NestedListWeightSumII instance = new NestedListWeightSumII();
        int res = instance.depthSumInverseV2(nestedList);
        System.out.println(res);
    }

    /**
     * todo
     * @param nestedList
     * @return
     */
    public int depthSumInverseV2(List<NestedInteger> nestedList) {
        int unweighted = 0;
        int weighted = 0;
        Queue<List<NestedInteger>> queue = new ArrayDeque<>();
        queue.offer(nestedList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<NestedInteger> cur = queue.poll();
                for (NestedInteger item : cur) {
                    if (item.isInteger()) {
                        unweighted += item.getInteger();
                    } else if (!item.getList().isEmpty()){
                        queue.offer(item.getList());
                    }
                }
            }
            weighted += unweighted;
        }

        return weighted;
    }

    /**
     * todo
     * @param nestedList
     * @return
     */
    public int depthSumInverseV1(List<NestedInteger> nestedList) {
        int unweighted = 0;
        int weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger item : nestedList) {
                if (item.isInteger()) {
                    unweighted += item.getInteger();
                } else {
                    nextLevel.addAll(item.getList());
                }
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }


    /**
     * -----------------------------------------------------------------------
     * class Solution:
     *     def depthSumInverse(self, nestedList: List[NestedInteger]) -> int:
     *         def max_depth(nestedList):
     *             depth = 1
     *             for item in nestedList:
     *                 if item.isInteger():
     *                     continue
     *                 depth = max(depth, max_depth(item.getList()) + 1)
     *             return depth
     *
     *         def dfs(nestedList, max_depth):
     *             depth_sum = 0
     *             for item in nestedList:
     *                 if item.isInteger():
     *                     depth_sum += item.getInteger() * max_depth
     *                 else:
     *                     depth_sum += dfs(item.getList(), max_depth - 1)
     *             return depth_sum
     *
     *         depth = max_depth(nestedList)
     *         return dfs(nestedList, depth)
     * @param nestedList
     * @return
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDepth = maxDepth(nestedList);
        return dfs(nestedList, maxDepth);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int depthSum = 0;
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                depthSum += item.getInteger() * depth;
            } else {
                depthSum += dfs(item.getList(), depth - 1);
            }
        }
        return depthSum;
    }

    private int maxDepth(List<NestedInteger> nestedList) {
        int depth = 1;
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                continue;
            }
            depth = Math.max(depth, 1 + maxDepth(item.getList()));
        }
        return depth;
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
