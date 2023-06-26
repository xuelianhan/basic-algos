package org.ict.algorithm.leetcode.math;

/**
 * There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.
 *
 * The rules of the game are as follows:
 * Start at the 1st friend.
 * Count the next k friends in the clockwise direction including the friend you started at.
 * The counting wraps around the circle and may count some friends more than once.
 * The last friend you counted leaves the circle and loses the game.
 * If there is still more than one friend in the circle,
 * go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
 * Else, the last friend in the circle wins the game.
 *
 * Given the number of friends, n, and an integer k, return the winner of the game.
 *
 *
 * Example 1:
 * Input: n = 5, k = 2
 * Output: 3
 * Explanation: Here are the steps of the game:
 * 1-->2-->3-->4-->5-->1, start from 1, go 2 steps
 * 1-->x-->3-->4-->5-->1, stop on 2, so 2 is killed.
 * 1-->x-->3-->x-->5-->1, start from 3 and stop at 4, so 4 is killed.
 * x-->x-->3-->x-->5-->x, start from 5 and stop at 1, so 1 is killed.
 * x-->x-->3-->x-->x-->x, start from 3 and stop at 5, so 5 is killed.
 *
 * 1) Start at friend 1.
 * 2) Count 2 friends clockwise, which are friends 1 and 2.
 * 3) Friend 2 leaves the circle. Next start is friend 3.
 * 4) Count 2 friends clockwise, which are friends 3 and 4.
 * 5) Friend 4 leaves the circle. Next start is friend 5.
 * 6) Count 2 friends clockwise, which are friends 5 and 1.
 * 7) Friend 1 leaves the circle. Next start is friend 3.
 * 8) Count 2 friends clockwise, which are friends 3 and 5.
 * 9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner.
 *
 * Example 2:
 * Input: n = 6, k = 5
 * Output: 1
 * Explanation: The friends leave in this order: 5, 4, 6, 2, 3. The winner is friend 1.
 *
 * Constraints:
 * 1 <= k <= n <= 500
 *
 *
 * Follow up:
 * Could you solve this problem in linear time with constant space?
 * @author sniper
 * @date 26 Jun 2023
 * LC1823, Medium, Josephus problem
 * @see <a href="https://en.wikipedia.org/wiki/Josephus_problem"></a>
 * @see <a href="https://www.geeksforgeeks.org/josephus-problem/"></a>
 * @see <a href="https://mathworld.wolfram.com/JosephusProblem.html"></a>
 */
public class FindTheWinnerOfTheCircularGame {


    /**
     * Understanding the following solution
     * Josephus problem
     * Time Complexity O(N)
     * Space Complexity O(1)
     * ------------------------------------
     * class Solution:
     *     def findTheWinner(self, n: int, k: int) -> int:
     *         i, res = 1, 0
     *         while i <= n:
     *             res = (res + k) % i
     *             i += 1
     *         return res + 1
     * ---------------------------------
     * class Solution {
     * public:
     *     int findTheWinner(int n, int k) {
     *         int res = 0;
     *         int i = 1;
     *         while (i <= n) {
     *             res = (res + k) % i;
     *             i++;
     *         }
     *         return res + 1;
     *     }
     * };
     * @param n
     * @param k
     * @return
     */
    public int findTheWinnerV2(int n, int k) {
        int i = 1;
        int res = 0;
        while (i <= n) {
            res = (res + k) % i;
            i++;
        }
        return res + 1;
    }

    /**
     * Josephus problem
     * Time Complexity O(N)
     * Space Complexity O(1) if you don't care the space used in recursion call stack
     * @param n
     * @param k
     * @return
     */
    public int findTheWinnerV1(int n, int k) {
        /**
         * Converts back to 1-indexed
         */
        return f1(n, k) + 1;
    }

    /**
     * e.g. n = 4, k = 2
     * By using 0-indexed notation, we have the following circle:
     * 0-->1-->2-->3-->0
     *     x
     *         0-->1-->2-->0
     * After the first round, 1 is removed.
     * So, 2 becomes 0, 3 becomes 1, and 0 becomes 2.
     * Let's denote that oldIndex = f(n, k) and newIndex = f(n - 1, k).
     * By observation, we know f(n, k) = (f(n - 1, k) + k) % n
     * @param n
     * @param k
     * @return
     */
    private int f1(int n, int k) {
        /**
         * f1(1, k)
         */
        int res = 0;
        /**
         * Compute f1(i, k) based on f1(i - 1, k)
         */
        for (int i = 2; i <= n; i++) {
            res = (res + k) % i;
        }
        return res;
    }

    /**
     * Josephus problem
     * Time Complexity O(N)
     * Space Complexity O(1) if you don't care the space used in recursion call stack
     * ----------------------
     * class Solution {
     * public:
     *     int findTheWinner(int n, int k) {
     *         if (n <= 1) {
     *             return n;
     *         }
     *         int res = (findTheWinner(n - 1, k) + k) % n;
     *         return res == 0 ? n : res;
     *     }
     * };
     * ------------------------------------
     * class Solution:
     *     def findTheWinner(self, n: int, k: int) -> int:
     *         if n <= 1:
     *             return n
     *         res = (self.findTheWinner(n - 1, k) + k) % n
     *         return n if res == 0 else res
     * @param n
     * @param k
     * @return
     */
    public int findTheWinner(int n, int k) {
        if (n <= 1) {
            return n;
        }
        int res = (findTheWinner(n - 1, k) + k) % n;
        return res == 0 ? n : res;
    }

}
