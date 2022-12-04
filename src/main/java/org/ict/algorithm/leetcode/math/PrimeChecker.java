package org.ict.algorithm.leetcode.math;

/**
 * Check a number is prime or not.
 * @author sniper
 * @date 04 Dec, 2022
 */
public class PrimeChecker {

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
