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
        String s = Long.toBinaryString(t);
        System.out.println(s);
    }

    public int findComplementV10(int num) {
        if (num == 0) {
            return 1;
        }
        int n = 0;
        while (n < num) {
            n = (n << 1) | 1;
        }
        return n - num;
    }

    /**
     * e.g num = 5
     * 5 -> 101
     * 7 -> 111
     * 2 -> 010
     * 2 = 7 - 5
     *
     *
     *
     * @param num
     * @return
     */
    public int findComplementV9(int num) {
        if (num == 0) {
            return 1;
        }
        int i = 0;
        int j = 0;
        while (i < num) {
            i += Math.pow(2, j);
            j++;
        }
        return i - num;
    }

    public int findComplementV8(int num) {
       return 0;
    }

    public int findComplementV7(int num) {
       return 0;
    }

    public int findComplementV6(int num) {
        int mask = num;
        mask |= mask >> 1;
        mask |= mask >> 2;
        mask |= mask >> 4;
        mask |= mask >> 8;
        mask |= mask >> 16;
        return num ^ mask;
    }

    /**
     * 0111 1111 1111 1111 1111 1111 1111 1111 // num
     * 1000 0000 0000 0000 0000 0000 0000 0000 // ~num
     * 0100 0000 0000 0000 0000 0000 0000 0000 // Integer.highestOneBit(num)
     * 1000 0000 0000 0000 0000 0000 0000 0000 // Integer.highestOneBit(num) << 1
     *
     * Can someone explain the meaning of + operator here?
     * If num = 5, ~5 would be -2147483648.
     * I know highestOneBit is retrieving the highest bit,
     * but why -2147483648 + 8 is equal to 2?
     * because it overflows. Don't think it as signed number, look at the bits.
     * @param num
     * @return
     */
    public int findComplementV5(int num) {
        return ~num & (Integer.highestOneBit(num) - 1);
    }


    public int findComplementV4(int num) {
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        num = ~num;
        return num & mask;
    }


    public int findComplementV3(int num) {
        int mask = 1;
        while (mask < num) mask = (mask << 1) | 1;
        return ~num & mask;
    }


    public int findComplementV2(int num) {
        if (num == 0) {
            return 1;
        }
        int res = 0, x = 0;
        for (int i = 0; num > 0; i++) {
            if (num % 2 == 0) {
                x = 1;
            } else {
                x = 0;
            }
            res += Math.pow(2, i) * x;
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
