package org.ict.algorithm.leetcode.math;

import java.math.BigInteger;

/**
 * Check a number is prime or not.
 * @author sniper
 * @date 04 Dec, 2022
 */
public class PrimeChecker {

    public static void main(String[] args) {
        PrimeChecker instance = new PrimeChecker();
        int[] arr = {2, 3, 31, 71, 101, 103, 107, 131, 151, 181, 191, 199};
        for (int num : arr) {
            if (instance.isPrimeV1(num)) {
                System.out.println(num + " is a prime");
            } else {
                System.out.println(num + " is not prime");
            }
        }
    }

    /**
     * If a positive integer is a composite number,
     * then there exists a number T that is divisible by N, where 2 <= T <= sqrt(N).
     * While N <= 10^12, we can use the following method to check whether a number is a prime or not.
     *  However, using Miller-Rabin instead while N > 10^12.
     *
     * Time Complexity O(sqrt(N))
     * @param num
     * @return
     */
    public boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            /**
             * condition for non-prime number
             */
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrimeV1(int num) {
        BigInteger x = BigInteger.valueOf(num);
        return x.isProbablePrime(1);
    }
}
