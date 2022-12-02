package org.ict.algorithm.leetcode.bitvector;

/**
 * You are given an integer n and an integer start.
 *
 * Define an array nums where nums[i] = start + 2 * i (0-indexed) and n == nums.length.
 *
 * Return the bitwise XOR of all elements of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, start = 0
 * Output: 8
 * Explanation: Array nums is equal to [0, 2, 4, 6, 8] where (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8.
 * Where "^" corresponds to bitwise XOR operator.
 * Example 2:
 *
 * Input: n = 4, start = 3
 * Output: 8
 * Explanation: Array nums is equal to [3, 5, 7, 9] where (3 ^ 5 ^ 7 ^ 9) = 8.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 * 0 <= start <= 1000
 * n == nums.length
 * @author sniper
 * @date 02 Dec, 2022
 * LC1486
 */
public class XOROperationInArray {


    public int xorOperationV9(int n, int start) {
        return 0;
    }

    public int xorOperationV8(int n, int start) {
        return 0;
    }

    public int xorOperationV7(int n, int start) {
        return 0;
    }

    public int xorOperationV6(int n, int start) {
        return 0;
    }

    public int xorOperationV5(int n, int start) {
        return 0;
    }

    public int xorOperationV4(int n, int start) {
        return 0;
    }

    public int xorOperationV3(int n, int start) {
        return 0;
    }

    public int xorOperationV2(int n, int start) {
        return 0;
    }


    public int xorOperationV1(int n, int start) {
        return 0;
    }

    /**
     *  nums[i] = start + 2 * i
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
        int res = start;
        for (int i = 1; i < n; i++) {
            res = res ^ (start + 2 * i);
        }
        return res;
    }
}
