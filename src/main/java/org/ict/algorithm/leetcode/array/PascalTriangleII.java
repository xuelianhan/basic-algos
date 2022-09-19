package org.ict.algorithm.leetcode.array;

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

    /**
     * 1.Initialization:
     * ---0--1--2--3--4--5
     * 0| 0  0  0  0  0
     * 1| 0  0  0  0  0
     * 2| 0  0  0  0  0
     * 3| 0  0  0  0  0
     * 4| 0  0  0  0  0
     *
     *
     * 2.res[0] = 1:
     * ---0--1--2--3--4--5
     * 0| 1  0  0  0  0
     * 1| 1  0  0  0  0
     * 2| 1  0  0  0  0
     * 3| 1  0  0  0  0
     * 4| 1  0  0  0  0
     *
     * 3.Calculate from top to down, from right to left.
     * take rowIndex=3 for example.
     * level=1:
     * col=1: res[col]= res[1] + res[0] = 0 + 1 = 1
     * ---0--1--2--3--4--5
     * 0| 1  0  0  0  0
     * 1| 1  1  0  0  0
     * 2| 1  0  0  0  0
     * 3| 1  0  0  0  0
     * 4| 1  0  0  0  0
     * ---------------------------------------------
     * level=2:
     * col=2: res[col] = res[2] + res[1] = 0 + 1 = 1
     * ---0--1--2--3--4--5
     * 0| 1  0  0  0  0
     * 1| 1  1  0  0  0
     * 2| 1  0  1  0  0
     * 3| 1  0  0  0  0
     * 4| 1  0  0  0  0
     *
     * level=2:
     * col=1: res[col] = res[1] + res[0] = 1 + 1 = 2
     * ---0--1--2--3--4--5
     * 0| 1  0  0  0  0
     * 1| 1  1  0  0  0
     * 2| 1  2  1  0  0
     * 3| 1  0  0  0  0
     * 4| 1  0  0  0  0
     * ---------------------------------------------
     * level=3:
     * col=3:  res[col] = res[3] + res[2] = 0 + 1 = 1
     * ---0--1--2--3--4--5
     * 0| 1  0  0  0  0
     * 1| 1  1  0  0  0
     * 2| 1  2  1  0  0
     * 3| 1  0  0  1  0
     * 4| 1  0  0  0  0
     *
     * level=3:
     * col=2: res[col] = res[2] + res[1] = 1 + 2 = 3
     * ---0--1--2--3--4--5
     * 0| 1  0  0  0  0
     * 1| 1  1  0  0  0
     * 2| 1  2  1  0  0
     * 3| 1  0  3  1  0
     * 4| 1  0  0  0  0
     *
     * level=3:
     * col=1: res[col] = res[1] + res[0] = 2 + 1 = 3
     * ---0--1--2--3--4--5
     * 0| 1  0  0  0  0
     * 1| 1  1  0  0  0
     * 2| 1  2  1  0  0
     * 3| 1  3  3  1  0
     * 4| 1  0  0  0  0
     * ---------------------------------------------
     *
     *
     *
     */
    public List<Integer> getRowV3(int rowIndex) {
        /**
         * Here res initialized all elements with 0.
         * Every layer has rowIndex + 1 elements. You can draw a picture to validate this point.
         */
        int[] res = new int[rowIndex+1];
        res[0] = 1;
        /**
         * Due to level 0 has only one element res[0], it has been set to 1.
         * So level starts at 1 here, not start at 0, so condition in outer-loop is level=1
         * col ends at 1 because res[0] has set to 1, so condition int inner-loop is col>=1
         * res[col-1] condition also requires col >= 1 because array index minimum is zero.
         *
         * Why col start from right to left, not from left to right?
         * We cannot do it from left to right. You can try it. The result is not matching our target.
         *
         */
        for (int level = 1; level <= rowIndex; level++) {
            /**
             * Notice col is start from right to left.
             */
            for (int col = level; col >= 1; col--) {
                res[col] += res[col-1];
            }
        }
        return IntStream.of(res).boxed().collect(Collectors.toList());
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
         * we calculate the value from right to left line by line from top to down.
         * 1.level start from 0, and level++ control the process from top to down.
         * 2.level <= rowIndex, col = level, col > 0, this three conditions assure the process from right to left.
         *
         * For each line, process likes the following:
         * 1.Firstly, we calculate the value on the main diagonal line on right.
         * 3.Secondly,we calculate res from right to left.
         *
         * level0: res[0] = 1
         *
         * level1: res[1] = level1.res[1] + level1.res[0] = 0 + 1 = 1
         *
         * level2: res[2] = level2.res[2] + level1.res[1] = 0 + 1 = 1
         *         res[1] = level1.res[1] + level2.res[0] = 1 + 1 = 2
         *
         *
         *
         * level3: res[3] = level3.res[3] + level2.res[2] = 0 + 1 = 1
         *         res[2] = level2.res[2] + level2.res[1] = 1 + 2 = 3
         *         res[1] = level2.res[1] + level3.res[0] = 2 + 1 = 3
         *
         *
         * level4: res[4] = level4.res[4] + level3.res[3] = 0 + 1 = 1
         *         res[3] = level3.res[3] + level3.res[2] = 1 + 3 = 4
         *         res[2] = level3.res[2] + level3.res[1] = 3 + 3 = 6
         *         res[1] = level3.res[1] + level4.res[0] = 3 + 1 = 4
         *
         */
        int[] res = new int[rowIndex+1];
        /**
         * ---0--1--2--3--4--5
         * 0| 0  0  0  0  0
         * 1| 0  0  0  0  0
         * 2| 0  0  0  0  0
         * 3| 0  0  0  0  0
         * 4| 0  0  0  0  0
         */
        res[0] = 1;
        /**
         * ---0--1--2--3--4--5
         * 0| 1  0  0  0  0
         * 1| 1  0  0  0  0
         * 2| 1  0  0  0  0
         * 3| 1  0  0  0  0
         * 4| 1  0  0  0  0
         */
        for (int level = 0; level <= rowIndex; level++) {
            for (int col = level; col > 0; col--) {
               res[col] += res[col-1];
            }
            System.out.println(Arrays.toString(res));
        }
        return IntStream.of(res).boxed().collect(Collectors.toList());
    }
}
