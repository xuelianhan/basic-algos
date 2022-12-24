package org.ict.algorithm.leetcode.dynamicprogramming;


/**
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape.
 * You may rotate these shapes.
 *
 *
 * Given an integer n, return the number of ways to tile an 2 x n board.
 * Since the answer may be very large, return it modulo 10^9 + 7.
 *
 * In a tiling, every square must be covered by a tile.
 * Two tilings are different if and only if there are two 4-directionally adjacent cells
 * on the board such that exactly one of the tilings has both squares occupied by a tile.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: 5
 * Explanation: The five different ways are show above.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 * @author sniper
 * @date 24 Dec, 2022
 * LC790
 */
public class DominoAndTrominoTiling {

    /**
     * dp[i] means that there are dp[i] ways to completely covert the i*2 board.
     * n:0, res:1
     * n:1, res:1
     * n:2, res:2
     * n:3, res:5 = 2*2 + 1 = 2*dp[2] + dp[0]
     * n:4, res:11 = 2*5 + 1 = 2*dp[3] + dp[1]
     * n:5, res:24 = 2*11 + 2 = 2*dp[4] + dp[2]
     * @param n
     * @return
     */
    public int numTilings(int n) {
        if (n < 2) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int mod = (int)1e9 + 7;
        long[] dp = new long[n + 1];
        dp[0] = 1l;
        dp[1] = 1l;
        dp[2] = 2l;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 3]) % mod;
        }
        return (int)dp[n];
    }
}
