package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 *
 *
 *
 * Example 1:
 *
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 * Example 2:
 *
 * Input: rowIndex = 0
 * Output: [1]
 * Example 3:
 *
 * Input: rowIndex = 1
 * Output: [1,1]
 *
 *
 * Constraints:
 *
 * 0 <= rowIndex <= 33
 *
 *
 * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 * @author sniper
 * @date 19 Sep, 2022
 * LC119
 */
public class PascalTriangleII {

    public static void main(String[] args) {
        int rowIndex = 4;
        getRow(rowIndex);
    }

    public List<Integer> getRowV3(int rowIndex) {
        //todo
        return null;
    }

    public List<Integer> getRowV2(int rowIndex) {
        Integer[] res = new Integer[rowIndex+1];
        Arrays.fill(res, 1);
        for (int level = 0; level <= rowIndex; level++) {
            for (int col = level; col > 0; col--) {
                res[col] = res[col] + res[col-1];
            }
        }
        return Arrays.asList(res);
    }

    /**
     * ---0--1--2--3--4--5
     * 0| 1  0  0  0  0
     * 1| 1  1  0  0  0
     * 2| 1  2  1  0  0
     * 3| 1  3  3  1  0
     * 4| 1  4  6  4  1
     *
     * The time complexity is O(N^2)
     * The space complexity is O(N).
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        /**
         * Every rowIndex has (rowIndex+1) elements.
         * So outer-for-loop condition is 0 <= i <= rowIndex.
         * Notice the fact that res[i] initialized as 0, where i>0, res[0]=1.
         *
         * Firstly, we calculate the value on the main diagonal line from left-top-corner to right-bottom-corner
         * level0: res[0] = 1
         * level1: res[1] = level0.res[1] + level0.res[0] = 0 + 1 = 1
         * level2: res[2] = level1.res[2] + level1.res[1] = 0 + 1 = 1
         * level3: res[3] = level2.res[3] + level2.res[2] = 0 + 1 = 1
         * level4: res[4] = level3.res[4] + level3.res[3] = 0 + 1 = 1
         *
         * Secondly,we calculate res from right to left, top to down cumulatively.
         *
         * level2: res[1] = level1.res[1] + level1.res[0] = 1 + 1 = 2
         *
         * level3: res[2] = level2.res[2] + level2.res[1] = 1 + 2 = 3
         *         res[1] = level2.res[1] + level2.res[0] = 2 + 1 = 3
         *
         * level4: res[3] = level3.res[3] + level3.res[2] = 1 + 3 = 4
         *         res[2] = level3.res[2] + level3.res[1] = 3 + 3 = 6
         *         res[1] = level3.res[1] + level3.res[0] = 3 + 1 = 4
         *
         */
        int[] res = new int[rowIndex+1];
        res[0] = 1;
        for (int level = 0; level <= rowIndex; level++) {
            for (int col = level; col > 0; col--) {
               res[col] += res[col-1];
            }
            System.out.println(Arrays.toString(res));
        }
        return IntStream.of(res).boxed().collect(Collectors.toList());
    }
}
