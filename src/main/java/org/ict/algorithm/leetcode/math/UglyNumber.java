package org.ict.algorithm.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return true if n is an ugly number.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6
 * Output: true
 * Explanation: 6 = 2 × 3
 * Example 2:
 *
 * Input: n = 1
 * Output: true
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 * Example 3:
 *
 * Input: n = 14
 * Output: false
 * Explanation: 14 is not ugly since it includes the prime factor 7.
 *
 *
 * Constraints:
 *
 * -2^31 <= n <= 2^31 - 1
 * @author sniper
 * @date 20 Oct, 2022
 * LC263
 */
public class UglyNumber {

    public boolean isUglyV3(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        return n == 1;
    }

    public boolean isUglyV2(int n) {
        if (n <= 0) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        for (int i : list) {
            n = maxDivider(n, i);
        }
        return n == 1;
    }

    public boolean isUglyV1(int n) {
        if (n <= 0) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        for (int i : list) {
            while (n % i == 0) {
                n /= i;
            }
        }
        return n == 1;
    }

    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        n = maxDivider(n, 2);
        n = maxDivider(n, 3);
        n = maxDivider(n, 5);
        return n == 1;
    }

    public int maxDivider(int a, int b) {
        while (a % b == 0) {
            a = a / b;
        }
        return a;
    }
}
