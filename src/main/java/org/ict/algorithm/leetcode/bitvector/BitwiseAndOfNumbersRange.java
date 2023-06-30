package org.ict.algorithm.leetcode.bitvector;

/**
 * Given two integers left and right that represent the range [left, right],
 * return the bitwise AND of all numbers in this range, inclusive.
 *
 *
 *
 * Example 1:
 *
 * Input: left = 5, right = 7
 * Output: 4
 * Example 2:
 *
 * Input: left = 0, right = 0
 * Output: 0
 * Example 3:
 *
 * Input: left = 1, right = 2147483647
 * Output: 0
 *
 *
 * Constraints:
 * 0 <= left <= right <= 2^31 - 1
 * @author sniper
 * @date 30 Jun 2023
 * LC201, Medium, High-Frequency, Top-150
 */
public class BitwiseAndOfNumbersRange {

    /**
     * Time Cost 6ms
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAndV3(int left, int right) {
        while (left < right) {
            /**
             * Removing the lowest bit at each time.
             */
            right &= (right - 1);
        }
        return right;
    }

    /**
     * Time Cost 6ms
     * e.g. left:10, right:11
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAndV2(int left, int right) {
        return (right > left) ? (rangeBitwiseAnd(left / 2, right / 2) << 1) : left;
    }

    /**
     * Time Cost 6ms
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAndV1(int left, int right) {
        int shiftBits = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            ++shiftBits;
        }
        return left << shiftBits;
    }

    /**
     * Time Cost 6ms
     * e.g. left:26, right:30,
     * 26:11010
     * 27:11011
     * 28:11100
     * 29:11101
     * 30:11110
     * Expected:11000
     * We need only to find the left common part of the binary numbers.
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd(int left, int right) {
        int mask = Integer.MAX_VALUE;
        while ((left & mask) != (right & mask)) {
            mask <<= 1;
        }
        return left & mask;
    }
}
