package org.ict.algorithm.leetcode.math;

/**
 * You are given two positive integers n and k.
 * A factor of an integer n is defined as an integer i where n % i == 0.
 *
 * Consider a list of all factors of n sorted in ascending order,
 * return the kth factor in this list
 * or return -1 if n has less than k factors.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12, k = 3
 * Output: 3
 * Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.
 * Example 2:
 *
 * Input: n = 7, k = 2
 * Output: 7
 * Explanation: Factors list is [1, 7], the 2nd factor is 7.
 * Example 3:
 *
 * Input: n = 4, k = 4
 * Output: -1
 * Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should return -1.
 *
 *
 * Constraints:
 *
 * 1 <= k <= n <= 1000
 *
 *
 * Follow up:
 *
 * Could you solve this problem in less than O(n) complexity?
 * @author sniper
 * @date 24 Aug 2023
 * LC1492, Medium
 */
public class TheKthFactorOfN {


    /**
     * Short Explanation:
     *
     * Let's assume n is 12. The divisors of n are: {1, 2, 3, 4, 6, 12}.
     * But now look at them from another angle:
     * 1 * 12 = 12
     * 2 * 6 = 12
     * 3 * 4 = 12
     *
     * If i is a divisor of n, then n / i is a divisor of n as well.
     * Consequently, we can find all the divisors of n only by processing the numbers that are smaller or equal to the square root of n.
     * Long Explanation:
     *
     * Let's divide all the divisors of n into 2 parts:
     * divisors smaller than the square root of n
     * divisors bigger than the square root of n.
     * Note: There's an edge case when the square root of n is an integer (for example when n is 9 or 16) which we'll discuss later.
     *
     * FIrst for loop
     * We start at 1 and check every number to see whether or not it's a divisor of n.
     * If it is, we deduct one from k (because we found one of the k divisors we were looking for).
     * Then continue until we reach the square root of n.
     * If during this process k becomes 0, it means that we have successfully found the first k divisors of n.
     * So we can jump for joy, cheer loudly, and return the current number.
     *
     * Second for loop
     * Otherwise, we will continue from the square root of n and go down to 1.
     * However, this time, instead of checking if the current number (i) is a divisor of n (something that we have already checked),
     * we check if n/i is a divisor of n.
     * If k becomes zero, we do what we are supposed to do.
     * Note for person who will comment: "n % i == 0 and n % (n/i) == 0 has the same results." I know :) but it's easier to understand.
     *
     * Edge case
     * When the square root of n is an integer.
     * We want to make sure that, on the one hand,
     * we don't forget to count it, and on the other hand we don't count it twice.
     * Here's the trick: we don't count it in the first for loop: i < Math.sqrt(n).
     * For the second for loop we start from: int i = (int) Math.sqrt(n).
     * If the square root of n is an integer, i will be the square root.
     * For example, if n is 9, i will start from 3.
     * Otherwise, it will be floored to the first integer.
     * For example, if n is 8, the square root is 2.8 so i will be 2
     * If after all those efforts k is still bigger than zero, we return -1 after cursing.
     * @author navid
     * @see <a href="https://leetcode.com/problems/the-kth-factor-of-n/solutions/959372/7-line-java-o-sqrt-n-time-o-1-space-not-a-typical-explanation/"></a>
     * @param n
     * @param k
     * @return
     */
    public int kthFactor(int n, int k) {
        for (int i = 1; i < Math.sqrt(n); i++) {
            if (n % i == 0 && --k == 0) {
                return i;
            }
        }
        for (int i = (int)Math.sqrt(n); i >= 1; i--) {
            if (n % (n / i) == 0 && --k == 0) {
                return n / i;
            }
        }
        return -1;
    }
}
