package org.ict.algorithm.leetcode.bitvector;

/**
 * The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
 *
 * For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
 * Given an integer num, return its complement.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 * Example 2:
 *
 * Input: num = 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 *
 *
 * Constraints:
 *
 * 1 <= num < 2^31
 *
 *
 * Note: This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 * @author sniper
 * @date 02 Dec, 2022
 * LC476
 */
public class NumberComplement {

    public static void main(String[] args) {
        int num = 5;
        long x = (long)Math.pow(2, 31);
        long t = num ^ x;
        System.out.println(t);
    }


    public int findComplementV4(int num) {
        return 0;
    }


    public int findComplementV3(int num) {
        return 0;
    }


    public int findComplementV2(int num) {
        int res = 0, x = 0;
        for (int i = 0; num > 0; i++) {
            if (num % 2 == 0) {
                x = 1;
            } else {
                x = 0;
            }
            res += Math.pow(2, i)*x;
            num /= 2;
        }
        return res;
    }

    public int findComplementV1(int num) {
        String s = Integer.toBinaryString(num);
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                arr[i] = '1';
            } else {
                arr[i] = '0';
            }
        }
        return Integer.parseInt(new String(arr), 2);
    }

    public int findComplement(int num) {
        if (num == 1) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int mod = num % 2;
            num /= 2;
            sb.append(mod);
        }
        char[] arr = sb.reverse().toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                arr[i] = '1';
            } else {
                arr[i] = '0';
            }
        }
        return Integer.parseInt(new String(arr), 2);
    }


}
