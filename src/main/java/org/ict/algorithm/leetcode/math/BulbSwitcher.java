package org.ict.algorithm.leetcode.math;

/**
 * There are n bulbs that are initially off.
 * You first turn on all the bulbs,
 * then you turn off every second bulb.
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the ith round, you toggle every i bulb.
 * For the nth round, you only toggle the last bulb.
 * Return the number of bulbs that are on after n rounds.
 *
 * Example 1:
 * Input: n = 3
 * Output: 1
 * Explanation: At first, the three bulbs are [off, off, off].
 * After the first round, the three bulbs are [on, on, on].
 * After the second round, the three bulbs are [on, off, on].
 * After the third round, the three bulbs are [on, off, off].
 * So you should return 1 because there is only one bulb is on.
 *
 * Example 2:
 * Input: n = 0
 * Output: 0
 *
 * Example 3:
 * Input: n = 1
 * Output: 1
 *
 * Constraints:
 * 0 <= n <= 10^9
 * @author sniper
 * @date 27 Apr, 2023
 * LC319, Medium
 */
public class BulbSwitcher {

    /**
     * def bulbSwitch(self, n: int) -> int:
     *        return int(math.sqrt(n))
     * @param n
     * @return
     */
    public int bulbSwitchV1(int n) {
        return (int)Math.sqrt(n);
    }

    /**
     * e.g. n = 5
     * init:   0 0 0 0 0
     * round1: 1 1 1 1 1
     * round2: 1 0 1 0 1
     * round3: 1 0 0 0 1
     * round4: 1 0 0 1 1
     * round5: 1 0 0 1 0
     * So for n == 5, the result is 2
     * Finally we find that after five iterations, only bulbs 1 and 4 are lit,
     * and coincidentally they are both square numbers.
     * For the nth bulb, the state of the bulb can be changed only if the number of times is a factor of n,
     * i.e., n is divisible by the current number of times,
     * e.g., when n is 36, its factors are (1,36), (2,18), (3,12), (4,9), (6,6),
     * and we can see that the factors appearing in pairs in the first four brackets are different,
     * and the number in front of the brackets changes the state of the bulb,
     * and the number behind it changes back,
     * which means that the state of the bulb does not change.
     * Only the last one (6,6), which changed its state once at the time of number 6,
     * does not correspond to any other state that can change it back,so the light bulb is always lit.
     * So all squares have such an equal pair of factors that all squares of the bulb will be lit.
     * The problem is reduced to finding the number of perfect squares between 1 and n.
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        int res = 1;
        while (res * res <= n) {
            res++;
        }
        return res - 1;
    }

}
