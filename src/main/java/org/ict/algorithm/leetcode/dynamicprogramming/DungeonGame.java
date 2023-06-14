package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.Arrays;

/**
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of m x n rooms laid out in a 2D grid.
 * Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 * Some rooms are guarded by demons (represented by negative integers),
 * so the knight loses health upon entering these rooms;
 * other rooms are either empty (represented as 0) or contain magic orbs that
 * increase the knight's health (represented by positive integers).
 * To reach the princess as quickly as possible,
 * the knight decides to move only rightward or downward in each step.
 *
 * Return the knight's minimum initial health so that he can rescue the princess.
 *
 * Note that any room can contain threats or power-ups,
 * even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * Output: 7
 * Explanation: The initial health of the knight must be at least 7
 * if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
 * Example 2:
 *
 * Input: dungeon = [[0]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == dungeon.length
 * n == dungeon[i].length
 * 1 <= m, n <= 200
 * -1000 <= dungeon[i][j] <= 1000
 * @author sniper
 * @date 14 Jun 2023
 * LC174, Hard, frequency=12
 */
public class DungeonGame {

    public static void main(String[] args) {
        int[][] dungeon = {{-2,-3,3}, {-5,-10,1}, {10,30,-5}};
        DungeonGame instance = new DungeonGame();
        int res = instance.calculateMinimumHP(dungeon);
        System.out.println(res);
    }

    /**
     * 1-Dimension DP
     * @param dungeon
     * @return
     */
    public int calculateMinimumHPV1(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[j] = Math.max(1, Math.min(dp[j], dp[j + 1]) - dungeon[i][j]);
            }
        }

        return dp[0];
    }

    /**
     * Understanding the following solution
     * 2-Dimension DP
     * Time Cost 2ms
     * -----------------
     * dp[i][j] is used to represent the starting blood volume from the current location (i, j),
     * the first to deal with the starting life value of the room where the princess is located,
     * and then slowly spread to the first room, and constantly get the optimal life value of each location.
     * If we start traversing from the starting position,
     * we do not know the initial amount of blood that should be initialized,
     * but when we reach the princess room, we know that the amount of blood can not be less than 1.
     * If the princess room also needs to drop blood,
     * then after dropping blood left 1 to ensure that the starting position of the minimum amount of blood.
     * Then the following to derive the state transfer equation,
     * first consider what determines the amount of blood in each position,
     * the knight will hang mainly because when going to the next room,
     * the amount of blood dropped is greater than its own blood value,
     * and can go to the room only the right and the lower side,
     * so the current position of the blood is determined by the right and the lower room survivable blood,
     * furthermore, it should be determined by the smaller survivable blood,
     * because the smaller We need the starting blood to be as small as possible because we are pushing backwards,
     * and the amount of blood left after the PK when the knight enters the room in the reverse direction
     * is the starting blood before the pk when the knight enters the room in the forward direction.
     * So use the right side of the current room
     * and the next room in the smaller amount of blood of the knight minus the number of the current room,
     * if it is a negative number or 0, that the current room is positive,
     * so the knight into the current room after the value of life is 1 on the line,
     * because there will be no blood loss.
     * And if the difference is positive, the current room's blood may be positive or negative,
     * but the knight's life value after entering the current room must be the difference.
     * So our state transfer equation is dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]).
     * In order to better deal with the boundary case,
     * our two-dimensional dp array than the original array of the number of rows and columns are more than 1,
     * first all initialized to integer number maximum INT_MAX, as we know that after reaching the princess room,
     * the amount of blood after the knight firefight is at least 1,
     * then at this time the right side of the princess room and the next room in the number we set to 1,
     * so that the amount of survival blood to reach the princess room is 1 minus The number of the princess room compared to 1,
     * take the larger value.
     * -----------------------------
     * e.g. dungeon = [[-2,-3,3],
     *                [-5,-10,1],
     *                [10,30,-5]]
     *
     * dp:
     * [
     *   [7,   5,  2, MAX],
     *   [6,   11, 5, MAX],
     *   [1,   1,  6,   1],
     *   [MAX,MAX, 1, MAX]
     * ]
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, Integer.MAX_VALUE));
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = Math.max(dp[i][j], 1);
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }
}
