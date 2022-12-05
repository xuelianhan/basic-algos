package org.ict.algorithm.leetcode.bitvector;

/**
 * Given a positive integer n,
 * find and return the longest distance between any two adjacent 1's in the binary representation of n.
 * If there are no two adjacent 1's, return 0.
 *
 * Two 1's are adjacent if there are only 0's separating them (possibly no 0's).
 * The distance between two 1's is the absolute difference between their bit positions.
 * For example, the two 1's in "1001" have a distance of 3.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 22
 * Output: 2
 * Explanation: 22 in binary is "10110".
 * The first adjacent pair of 1's is "10110" with a distance of 2.
 * The second adjacent pair of 1's is "10110" with a distance of 1.
 * The answer is the largest of these two distances, which is 2.
 * Note that "10110" is not a valid pair since there is a 1 separating the two 1's underlined.
 * Example 2:
 *
 * Input: n = 8
 * Output: 0
 * Explanation: 8 in binary is "1000".
 * There are not any adjacent pairs of 1's in the binary representation of 8, so we return 0.
 * Example 3:
 *
 * Input: n = 5
 * Output: 2
 * Explanation: 5 in binary is "101".
 *
 *
 * Constraints:
 *
 * 1 <= n <= 109
 * @author sniper
 * @date 04 Dec, 2022
 * LC868
 */
public class BinaryGap {

    public static void main(String[] args) {
        BinaryGap instance = new BinaryGap();
        int n = 22;
        int res = instance.binaryGap(n);
        System.out.println(res);
    }

    public int binaryGapV5(int n) {
        return 0;
    }


    public int binaryGapV4(int n) {
        return 0;
    }

    public int binaryGapV3(int n) {
        return 0;
    }

    public int binaryGapV2(int n) {
        return 0;
    }

    public int binaryGapV1(int n) {
        return 0;
    }

    /**
     * Time Cost 3ms
     * "10110"
     * @param n
     * @return
     */
    public int binaryGap(int n) {
        String s = toBinaryString(n);
        int length = s.length();
        int res = 0;
        for (int i = 0, j = 0; i < length && j < length;) {
            /**
             * Notice the following codes, the border checking should put before the charAt operation.
             */
            while (i < length && s.charAt(i) != '1') {
                i++;
            }
            j = i + 1;
            while (j < length && s.charAt(j) != '1') {
                j++;
            }
            /**
             * e.g. s = "1000"
             */
            if (j < length && s.charAt(j) == '1') {
                res = Math.max(res, j - i);
            }
            i = j;
            j++;
        }
        return res;
    }

    public String toBinaryString(int x) {
        int dividend = x;
        StringBuilder sb = new StringBuilder();
        while (dividend > 0) {
            int mod = dividend % 2;
            dividend /= 2;
            sb.append(mod);
        }
        return sb.reverse().toString();
    }
}
