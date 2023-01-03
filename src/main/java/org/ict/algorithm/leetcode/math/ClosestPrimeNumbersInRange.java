package org.ict.algorithm.leetcode.math;

import java.util.Arrays;

/**
 * Given two positive integers left and right,
 * find the two integers num1 and num2 such that:
 *
 * left <= nums1 < nums2 <= right .
 * nums1 and nums2 are both prime numbers.
 * nums2 - nums1 is the minimum amongst all other pairs satisfying the above conditions.
 * Return the positive integer array ans = [nums1, nums2].
 * If there are multiple pairs satisfying these conditions,
 * return the one with the minimum nums1 value or [-1, -1] if such numbers do not exist.
 *
 * A number greater than 1 is called prime if it is only divisible by 1 and itself.
 *
 *
 *
 * Example 1:
 *
 * Input: left = 10, right = 19
 * Output: [11,13]
 * Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
 * The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
 * Since 11 is smaller than 17, we return the first pair.
 * Example 2:
 *
 * Input: left = 4, right = 6
 * Output: [-1,-1]
 * Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.
 *
 *
 * Constraints:
 * 1 <= left <= right <= 10^6
 * @author sniper
 * @date 03 Jan, 2023
 * LC2523
 */
public class ClosestPrimeNumbersInRange {

    public int[] closestPrimesV2(int left, int right) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        //todo
        return res;
    }

    /**
     * Time Cost 145ms
     * @param left
     * @param right
     * @return
     */
    public int[] closestPrimesV1(int left, int right) {
        boolean[] primes = new boolean[right + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i * i <= right; i++) {
            if (!primes[i]) {
                continue;
            }
            for (int p = i * i; p <= right; p += i) {
                primes[p] = false;
            }
        }

        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        for (int i = left, last = -1, best = Integer.MAX_VALUE; i <= right; i++) {
            if (!primes[i]) {
                continue;
            }
            if (last > 0 && i - last < best) {
                best = i - last;
                res[0] = last;
                res[1] = i;
            }
            last = i;
        }
        return res;
    }

    /**
     * Time Cost 231ms
     * Time Complexity O(N*log(logN))
     * Space Complexity O(N)
     * @param left
     * @param right
     * @return
     */
    public int[] closestPrimes(int left, int right) {
        int[] primes = new int[right + 1];
        Arrays.fill(primes, 1);
        primes[0] = 0;
        primes[1] = 0;
        /**
         * Find primes in ranage[1, n]
         */
        for (int i = 2; i <= Math.sqrt(right); i++) {
            if (primes[i] == 0) {
                continue;
            }
            for (int x = i * i; x <= right; x += i) {
                primes[x] = 0;
            }
        }

        /**
         * Iterate over primes, check (current - last)
         */
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        for (int i = left, last = -1, best = Integer.MAX_VALUE; i <= right; i++) {
            if (primes[i] == 0) {
                continue;
            }
            if (last > 0 && i - last < best) {
                best = i - last;
                res[0] = last;
                res[1] = i;
            }
            last = i;
        }
        return res;
    }
}
