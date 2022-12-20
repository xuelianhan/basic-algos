package org.ict.algorithm.leetcode.math;

/**
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 * Example 3:
 *
 * Input: n = 1
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= n <= 5 * 10^6
 * @author sniper
 * @date 20 Oct, 2022
 * LC204
 */
public class CountPrimes {

    /**
     * The following solution is easy understood and short enough for interview.
     *
     * Checking all the integers in the range [1, n - 1] is not efficient.
     * Think about a better approach.
     * Since most of the numbers are not primes, we need a fast approach to exclude the non-prime integers.
     * An efficient solution is to use Sieve of Eratosthenes to find all primes up to the given limit.
     * Then we compute a prefix array to store counts till every value before limit.
     * Once we have a prefix array,
     * we can answer queries in O(1) time.
     * We just need to return prefix[R] – prefix[L-1].
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                res++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        return res;
    }
}
