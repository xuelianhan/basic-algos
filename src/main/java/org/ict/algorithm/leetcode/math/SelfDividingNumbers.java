package org.ict.algorithm.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * A self-dividing number is a number that is divisible by every digit it contains.
 *
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 * A self-dividing number is not allowed to contain the digit zero.
 *
 * Given two integers left and right, return a list of all the self-dividing numbers in the range [left, right].
 *
 *
 *
 * Example 1:
 *
 * Input: left = 1, right = 22
 * Output: [1,2,3,4,5,6,7,8,9,11,12,15,22]
 * Example 2:
 *
 * Input: left = 47, right = 85
 * Output: [48,55,66,77]
 *
 *
 * Constraints:
 *
 * 1 <= left <= right <= 104
 * @author sniper
 * @date 01 Nov, 2022
 * LC728
 */
public class SelfDividingNumbers {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDivided(i)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * e.g. num = 128
     * x: 128, digit = x % 10 = 8, x = 128 / 10 = 12
     * x:12, digit = x % 10 = 2, x = 12 / 10 = 1
     * x:1, digit = x % 10 = 1, x = 1 / 10 = 0
     * x:0, while-loop ended.
     *
     * @param num
     * @return
     */
    public boolean isSelfDivided(int num) {
        /**
         * Exclude multiple of ten to hasten
         */
        if (num % 10 == 0) {
            return false;
        }
        int x = num;
        while (x > 0) {
            int digit = x % 10;
            if (digit == 0 || num % digit != 0) {
                return false;
            }
            x = x / 10;
        }
        return true;
    }
}
