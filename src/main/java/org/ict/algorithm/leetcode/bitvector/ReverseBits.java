package org.ict.algorithm.leetcode.bitvector;

import java.util.Arrays;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Note:
 *
 * Note that in some languages, such as Java, there is no unsigned integer type.
 * In this case, both input and output will be given as a signed integer type.
 * They should not affect your implementation,
 * as the integer's internal binary representation is the same, whether it is signed or unsigned.
 *
 * In Java, the compiler represents the signed integers using 2's complement notation.
 * Therefore, in Example 2 above,
 * the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 *
 *
 * Example 1:
 *
 * Input: n = 00000010100101000001111010011100
 * Output:    964176192 (00111001011110000010100101000000)
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
 * so return 964176192 which its binary representation is 00111001011110000010100101000000.
 * Example 2:
 *
 * Input: n = 11111111111111111111111111111101
 * Output:   3221225471 (10111111111111111111111111111111)
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
 * so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 *
 *
 * Constraints:
 *
 * The input must be a binary string of length 32
 *
 *
 * Follow up: If this function is called many times, how would you optimize it?
 * @author sniper
 * @date 23 Sep, 2022
 * LC190
 */
public class ReverseBits {

    public static void main(String[] args) {
        String s = "00000010100101000001111010011100";
        int n = Integer.parseUnsignedInt(s, 2);
        int n1 = Integer.parseInt(s, 2);
        System.out.println(n);
        System.out.println(n1);
        int[] res = toReverseBinaryBits(n);
        System.out.println(Arrays.toString(res));
        StringBuilder sb = new StringBuilder();
        for(int i : res) {
            sb.append(i);
        }
        System.out.println(sb.toString());
        System.out.println(sb.reverse().toString().equals(s));
    }

    /**
     * Understand the following method.
     *
     * Improvement's version of reverseBits
     *
     *
     * @param n
     * @return
     */
    public int reverseBitsV1(int n) {
        if (n == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            /**
             * shift left first to provide space for the next bit.
             */
            result <<= 1;
            /**
             * get the next bit.
             */
            if ((n & 1) == 1) {
                result++;
            }
            /**
             * unsigned right shift.
             */
            n >>>= 1;
        }
        return result;
    }


    /**
     * Understand the following method.
     *
     * variable sum must be declared long to prevent int overflow
     * if sum declares as int, you will get the following result.
     * e.g.
     * Input:
     *   11111111111111111111111111111101
     * Output:
     *   2147483647 (01111111111111111111111111111111)
     * Expected:
     *   3221225471 (10111111111111111111111111111111)
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int[] res = new int[32];
        for (int i = 0; i < 32; i++) {
            /**
             * calculates the binary bit of n from right to left(low to high) order,
             * and store low to index 0, store high to index 31
             * so array res is the right reverse bits of n's binary representation.
             */
            res[i] = n & 1;
            /**
             * using >>> (unsigned right shift)
             */
            n = n >>> 1;
        }
        /**
         * We get res in reverse binary bit(res[0]...res[31]) of n
         * Now we calculate the int value based the binary array res.
         */
        long sum = 0;
        for (int i = res.length - 1; i >= 0; i--) {
            sum += res[i] * Math.pow(2, 31 - i);
        }
        return (int)sum;
    }

    /**
     * you need treat n as an unsigned value
     * @param n
     * @return
     */
    public static int[] toReverseBinaryBits(int n) {
        int[] res = new int[32];
        for (int i = 0; i < 32; i++) {
            res[i] = n & 1;
            /**
             * using >>> (unsigned right shift)
             */
            n = n >>> 1;
        }
        return res;
    }
}
