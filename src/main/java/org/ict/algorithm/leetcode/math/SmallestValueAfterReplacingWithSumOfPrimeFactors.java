package org.ict.algorithm.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a positive integer n.
 *
 * Continuously replace n with the sum of its prime factors.
 *
 * Note that if a prime factor divides n multiple times, it should be included in the sum as many times as it divides n.
 * Return the smallest value n will take on.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 15
 * Output: 5
 * Explanation: Initially, n = 15.
 * 15 = 3 * 5, so replace n with 3 + 5 = 8.
 * 8 = 2 * 2 * 2, so replace n with 2 + 2 + 2 = 6.
 * 6 = 2 * 3, so replace n with 2 + 3 = 5.
 * 5 is the smallest value n will take on.
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: Initially, n = 3.
 * 3 is the smallest value n will take on.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^5
 * @author sniper
 * @date 20 Dec, 2022
 * LC2507
 */
public class SmallestValueAfterReplacingWithSumOfPrimeFactors {

    /**
     * Time Cost 324ms
     * @param n
     * @return
     */
    public int smallestValue(int n) {
        int size = 100001;
        boolean[] notPrime = new boolean[size];
        List<Integer> prime = new ArrayList<>();

        for (int i = 2; i < size; i++) {
            if (notPrime[i] == false) {
                prime.add(i);
                for (int j = i * 2; j < size; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        int res = n;
        int temp = 0;
        while (true) {
            temp = 0;
            int cur = n;
            while (n > 1) {
                for (int x : prime) {
                    while (n % x == 0) {
                        n = n / x;
                        temp += x;
                    }
                }
            }

            if (cur == temp) {
                break;
            }
            n = temp;
            res = Math.min(res, temp);
        }
        return res;
    }
}
