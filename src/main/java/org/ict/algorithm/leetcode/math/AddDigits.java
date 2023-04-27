package org.ict.algorithm.leetcode.math;

/**
 * Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 38
 * Output: 2
 * Explanation: The process is
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * Since 2 has only one digit, return it.
 * Example 2:
 *
 * Input: num = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= num <= 2^31 - 1
 *
 *
 * Follow up: Could you do it without any loop/recursion in O(1) runtime?
 * @author sniper
 * @date 20 Oct, 2022
 * LC258
 */
public class AddDigits {

    /**
     * Congruence formula
     * @see <a href="https://en.wikipedia.org/wiki/Digital_root"></a>
     * ---------------------------------------------------------------
     * class Solution:
     *     def addDigits(self, num: int) -> int:
     *          return 0 if num == 0 else 1 + (num - 1) % 9;
     *
     * @param num
     * @return
     */
    public int addDigitsV1(int num) {
        return 1 + (num - 1) % 9;
    }

    /**
     * @see <a href="https://en.wikipedia.org/wiki/Digital_root"></a>
     * @see <a href="https://brilliant.org/wiki/digital-root/#:~:text=The%20digital%20root%20or%20digital,single%2Ddigit%20number%20is%20reached."></a>
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        return (num % 9 == 0 ? 9 : num % 9);
    }
}
