package org.ict.algorithm.leetcode.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2^31 - 1
 * @author sniper
 * @date 24 Sep, 2022
 */
public class HappyNumber {

    /**
     * We can solve this problem without using extra space and that technique can be used in some other similar problems yet.
     * If we treat every number as a node and replacement by square sum digit as a link,
     * then this problem is same as finding a loop in a linklist :
     *
     * So as a proposed solution from the above link,
     * we will keep two numbers slow and fast both initialize from a given number,
     * slow is replaced one step at a time and fast is replaced two steps at a time.
     * If they meet at 1, then the given number is Happy Number otherwise not.
     *
     *
     * e.g.19
     *
     * 19 --> 82 --> 68 --> 100 --> 1 --> 1 --> 1 --> 1 --> 1....
     * slow  slow   slow    slow   slow
     * fast          fast          fast        fast        fast
     *
     *
     * e.g.20
     *                                                            circle point
     * 20 --> 4 --> 8 --> 64 --> 52 --> 29 --> 85 --> 145 --> 42 --> 20 --> 4 --> 8 --> 64 --> 52 --> 29 --> 85 --> 145 --> 42 ...
     * You can see 20 --> 4 --> 8 --> 64 --> 52 --> 29 --> 85 --> 145 --> 42 --> 20 as a circle,
     * so slow and fast will come across definitely, but the cross point is not 1.
     *
     * @param n
     * @return
     */
    public boolean isHappyV2(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = squareSum(slow);
            fast = squareSum(fast);
            fast = squareSum(fast);
        } while (slow != fast);
        if (slow == 1) {
            return true;
        }
        return false;
    }


    /**
     * A number will not be a Happy Number when it makes a loop in its sequence that is it touches a number in sequence which already been touched.
     * So to check whether a number is happy or not, we can keep a set,
     * if the same number occurs again we flag result as not happy.
     * A simple function on the above approach can be written as below:
     *
     * Time Complexity O(n*log(n)).
     * Space Complexity O(n)
     *
     * e.g.
     * n = 19
     * 1^2 + 9^2 = 1 + 81 = 82
     * 8^2 + 2^2 = 64 + 4 = 68
     * 6^2 + 8^2 = 36 + 64 = 100
     * 1^2 + 0^2 + 0^2 = 1
     * so 19 is a happy number.
     *
     * e.g.
     * n = 20
     * 2^2 + 0^2 = 4 + 0 = 4
     * 4^2 = 8
     * 8^2 = 64
     * 6^2 + 4^2 = 36 + 16 = 52
     * 5^2 + 2^2 = 25 + 4 = 29
     * 2^2 + 9^2 = 4 + 81 = 85
     * 8^2 + 5^2 = 64 + 25 = 89
     * 8^2 + 9^2 = 64 + 81 = 145
     * 1^2 + 4^2 +5^2 = 1 + 16 + 25 = 42
     * 4^2 + 2^2 = 16 + 4 = 20
     * 20 appears before, loop starts here
     *
     * So we can use HashSet to check the number has appeared before.
     *
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            n = squareSum(n);
            if (n == 1) {
                return true;
            }
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
        }
    }

    /**
     * Calculate number n's square sum
     * e.g
     * n = 123
     * squareSum = 3^2 + 2^2 + 1^2
     *           = 9 + 4 + 1
     *           = 14
     *
     * @param n
     * @return
     */
    public int squareSum(int n) {
        int squareSum = 0;
        while (n != 0) {
            int mod = n % 10;
            squareSum += mod * mod;
            n /= 10;
        }
        return squareSum;
    }

    /**
     * @see <a href="https://mathworld.wolfram.com/HappyNumber.html"></a>
     * Let the sum of the squares of the digits of a positive integer s_0 be represented by s_1.
     * In a similar way, let the sum of the squares of the digits of s_1 be represented by s_2, and so on.
     *
     * Iterating this sum-of-squared-digits map always eventually reaches one of the 10 numbers 0, 1, 4, 16, 20, 37, 42, 58, 89,
     * or 145 (OEIS A039943; Porges 1945).
     *
     * If s_i=1 for some i>=1, then the original integer s_0 is said to be happy.
     * For example, starting with 7 gives the sequence 7, 49, 97, 130, 10, 1, so 7 is a happy number.
     *
     * The first few happy numbers are 1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100, ... (OEIS A007770).
     * These are also the numbers whose 2-recurring digital invariant sequences have period 1.
     * The numbers of iterations required for these to reach 1 are 0, 5, 1, 2, 4, 3, 3, 2, 3, 4, 4, 2, 5, ... (OEIS A090425).
     *
     * The numbers of happy numbers less than or equal to 1, 10^1, 10^2, ... are given by 1, 3, 20, 143, 1442, 14377, 143071, ... (OEIS A068571).
     *
     * The first few consecutive happy numbers (n,n+1) have n=31, 129, 192, 262, 301, 319, 367, 391, ... (OEIS A035502).
     * Similarly, the first few happy triplets start with 1880, 4780, 4870, 7480, 7839, ... (OEIS A072494).
     *
     * The first few happy primes are 7, 13, 19, 23, 31, 79, 97, 103, 109, 139, ... (OEIS A035497).
     *
     * Once it is known whether a number is happy (or not), then any number in the sequence s_1, s_2, s_3, ... will also be happy (or not).
     * A number that is not happy is called unhappy. Unhappy numbers have eventually periodic sequences of s_i which never reach 1.
     *
     * Any permutation of the digits of an unhappy or happy number must also be unhappy or happy.
     * This follows from the fact that addition is commutative.
     */
}
