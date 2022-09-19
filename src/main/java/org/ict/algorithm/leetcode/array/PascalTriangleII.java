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

    public List<Integer> getRowV2(int rowIndex) {
        //todo
        return null;
    }

    /**
     * ---0--1--2--3--4--5
     * 0| 1
     * 1| 1  1
     * 2| 1  2  1
     * 3| 1  3  3  1
     * 4| 1  4  6  4  1
     *
     * The time complexity is O(N^2)
     * The space complexity is O(N).
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        int[] res = new int[rowIndex+1];
        res[0] = 1;
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i; j > 0; j--) {
               res[j] += res[j-1];
            }
        }
        return IntStream.of(res).boxed().collect(Collectors.toList());
    }
}
