package org.ict.algorithm.leetcode.bitvector;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer num, return a string representing its hexadecimal representation.
 * For negative integers, two’s complement method is used.
 *
 * All the letters in the answer string should be lowercase characters,
 * and there should not be any leading zeros in the answer except for the zero itself.
 *
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 26
 * Output: "1a"
 * Example 2:
 *
 * Input: num = -1
 * Output: "ffffffff"
 *
 *
 * Constraints:
 *
 * -2^31 <= num <= 2^31 - 1
 * @author sniper
 * @date 04 Dec, 2022
 * LC405
 */
public class ConvertNumberToHexadecimal {

    public static void main(String[] args) {
        int num = -1;
        ConvertNumberToHexadecimal instance = new ConvertNumberToHexadecimal();
        String result = instance.toHex(num);
        System.out.println(result);
    }

    /**
     * At each time, we take a look at the last four digits of
     * binary version of the input, and maps that to a hex char
     * shift the input to the right by 4 bits, do it again
     * until input becomes 0
     * @param num
     * @return
     */
    public String toHexV3(int num) {
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if (num == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            res.append(map[num & 15]);
            num = (num >>> 4);
        }
        return res.reverse().toString();
    }

    /**
     * At each time, we take a look at the last four digits of
     * binary version of the input, and maps that to a hex char
     * shift the input to the right by 4 bits, do it again
     * until input becomes 0
     * @param num
     * @return
     */
    public String toHexV2(int num) {
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if (num == 0) {
            return "0";
        }
        String result = "";
        while (num != 0) {
            result = map[(num & 15)] + result;
            num = (num >>> 4);
        }
        return result;
    }

    public String toHexV1(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            int digit = num & 0xf;
            res.append(digit < 10 ? (char)(digit + '0') : (char)(digit - 10 + 'a'));
            num >>>= 4;
        }

        return res.reverse().toString();
    }

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        char[] hex = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder res = new StringBuilder();
        int count = 0;
        while (count < 8 && num != 0) {
            res.append(hex[num & 15]);
            num = num >> 4;
            count++;
        }
        return res.reverse().toString();
    }

}
