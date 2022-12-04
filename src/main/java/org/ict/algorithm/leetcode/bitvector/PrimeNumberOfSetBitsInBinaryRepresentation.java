package org.ict.algorithm.leetcode.bitvector;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers left and right,
 * return the count of numbers in the inclusive range [left, right] having a prime number of set bits in their binary representation.
 *
 * Recall that the number of set bits an integer has is the number of 1's present when written in binary.
 *
 * For example, 21 written in binary is 10101, which has 3 set bits.
 *
 *
 * Example 1:
 *
 * Input: left = 6, right = 10
 * Output: 4
 * Explanation:
 * 6  -> 110 (2 set bits, 2 is prime)
 * 7  -> 111 (3 set bits, 3 is prime)
 * 8  -> 1000 (1 set bit, 1 is not prime)
 * 9  -> 1001 (2 set bits, 2 is prime)
 * 10 -> 1010 (2 set bits, 2 is prime)
 * 4 numbers have a prime number of set bits.
 * Example 2:
 *
 * Input: left = 10, right = 15
 * Output: 5
 * Explanation:
 * 10 -> 1010 (2 set bits, 2 is prime)
 * 11 -> 1011 (3 set bits, 3 is prime)
 * 12 -> 1100 (2 set bits, 2 is prime)
 * 13 -> 1101 (3 set bits, 3 is prime)
 * 14 -> 1110 (3 set bits, 3 is prime)
 * 15 -> 1111 (4 set bits, 4 is not prime)
 * 5 numbers have a prime number of set bits.
 *
 *
 * Constraints:
 *
 * 1 <= left <= right <= 10^6
 * 0 <= right - left <= 10^4
 * @author sniper
 * @date 04 Dec, 2022
 * LC762
 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {

    public int countPrimeSetBitsV5(int left, int right) {
        return 0;
    }

    public int countPrimeSetBitsV4(int left, int right) {
        return 0;
    }

    public int countPrimeSetBitsV3(int left, int right) {
        return 0;
    }

    public int countPrimeSetBitsV2(int left, int right) {
        return 0;
    }

    public int countPrimeSetBitsV1(int left, int right) {
        return 0;
    }

    /**
     * Time Cost 22ms
     * @param left
     * @param right
     * @return
     */
    public int countPrimeSetBits(int left, int right) {
        int res = 0;
        Map<Integer, Boolean> memo = new HashMap<>();
        for (int i = left; i <= right; i++) {
            int cnt = hammingWeight(i);
            if (memo.containsKey(cnt) && memo.get(cnt)) {
                res++;
                continue;
            }
            if (isPrime(cnt)) {
                res++;
                memo.put(cnt, true);
            }
        }
        return res;
    }


    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            /**
             * if n = 1100, n-1=1011
             * n & (n - 1) = 1000
             * It set the right most 1 in to 0.
             * final effect: 1100 --> 1000
             */
            n = n & (n - 1);
            cnt++;
        }
        return cnt;
    }

    public boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num / 2; ++i) {
            // condition for non-prime number
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


}
