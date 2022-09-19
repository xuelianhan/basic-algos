package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 *
 *
 *
 * Example 1:
 *
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 *
 * Input: numRows = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= numRows <= 30
 * @author sniper
 * @date 19 Sep, 2022
 * LC118
 */
public class PascalTriangle {

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> res = generate(numRows);
        System.out.println(res);
    }

    /**
     * ---0--1--2--3--4--5
     * 0| 1
     * 1| 1  1
     * 2| 1  2  1
     * 3| 1  3  3  1
     * 4| 1  4  6  4  1
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int level = 0; level < numRows; level++) {
            List<Integer> list = new ArrayList<>();
            /**
             * Every level has (level + 1) elements.
             * Both the first and the last one are 1.
             * others can sum from last level.
             * sum relations as following:
             * curLevel[j] = lastLevel[j] + lastLevel[j-1]
             */
            for (int j = 0; j < level + 1; j++) {
                if (j == 0 || j == level) {
                    list.add(1);
                } else {
                    List<Integer> lastLevel = res.get(level-1);
                    int sum = lastLevel.get(j) + lastLevel.get(j-1);
                    list.add(sum);
                }
            }
            res.add(list);
        }
        return res;
    }
}
