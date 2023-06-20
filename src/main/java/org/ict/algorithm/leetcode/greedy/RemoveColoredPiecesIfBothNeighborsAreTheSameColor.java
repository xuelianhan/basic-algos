package org.ict.algorithm.leetcode.greedy;

/**
 * There are n pieces arranged in a line, and each piece is colored either by 'A' or by 'B'. You are given a string colors of length n where colors[i] is the color of the ith piece.
 *
 * Alice and Bob are playing a game where they take alternating turns removing pieces from the line. In this game, Alice moves first.
 *
 * Alice is only allowed to remove a piece colored 'A' if both its neighbors are also colored 'A'. She is not allowed to remove pieces that are colored 'B'.
 * Bob is only allowed to remove a piece colored 'B' if both its neighbors are also colored 'B'. He is not allowed to remove pieces that are colored 'A'.
 * Alice and Bob cannot remove pieces from the edge of the line.
 * If a player cannot make a move on their turn, that player loses and the other player wins.
 * Assuming Alice and Bob play optimally, return true if Alice wins, or return false if Bob wins.
 *
 *
 *
 * Example 1:
 *
 * Input: colors = "AAABABB"
 * Output: true
 * Explanation:
 * AAABABB -> AABABB
 * Alice moves first.
 * She removes the second 'A' from the left since that is the only 'A' whose neighbors are both 'A'.
 *
 * Now it's Bob's turn.
 * Bob cannot make a move on his turn since there are no 'B's whose neighbors are both 'B'.
 * Thus, Alice wins, so return true.
 * Example 2:
 *
 * Input: colors = "AA"
 * Output: false
 * Explanation:
 * Alice has her turn first.
 * There are only two 'A's and both are on the edge of the line, so she cannot move on her turn.
 * Thus, Bob wins, so return false.
 * Example 3:
 *
 * Input: colors = "ABBBBBBBAAA"
 * Output: false
 * Explanation:
 * ABBBBBBBAAA -> ABBBBBBBAA
 * Alice moves first.
 * Her only option is to remove the second to last 'A' from the right.
 *
 * ABBBBBBBAA -> ABBBBBBAA
 * Next is Bob's turn.
 * He has many options for which 'B' piece to remove. He can pick any.
 *
 * On Alice's second turn, she has no more pieces that she can remove.
 * Thus, Bob wins, so return false.
 *
 *
 * Constraints:
 *
 * 1 <= colors.length <= 10^5
 * colors consists of only the letters 'A' and 'B'
 * @author sniper
 * @date 20 Jun 2023
 * LC2038, Medium, frequency=9
 */
public class RemoveColoredPiecesIfBothNeighborsAreTheSameColor {


    /**
     * Understanding the following solution
     * Time Cost 10ms
     * -------------------------------------
     * class Solution {
     * public:
     *     bool winnerOfGame(string colors) {
     *         int countAAA = 0;
     *         int countBBB = 0;
     *         for (int i = 1; i < colors.length() - 1; i++) {
     *             if (colors[i - 1] == colors[i] && colors[i] == colors[i + 1]) {
     *                 if (colors[i] == 'A') {
     *                     countAAA++;
     *                 } else {
     *                     countBBB++;
     *                 }
     *             }
     *         }
     *         return countAAA > countBBB;
     *     }
     * };
     * -------------------------------------
     * class Solution:
     *     def winnerOfGame(self, colors: str) -> bool:
     *         countAAA, countBBB = 0, 0
     *         for a, b, c in zip(colors, colors[1:], colors[2:]):
     *             if 'A' == a == b == c:
     *                 countAAA += 1
     *             elif 'B' == a == b == c:
     *                 countBBB += 1
     *         return countAAA > countBBB
     * -------------------------------------
     * class Solution:
     *     def winnerOfGame(self, colors: str) -> bool:
     *         countAAA, countBBB = 0, 0
     *         for i in range(1, len(colors) - 1):
     *             if colors[i - 1] == colors[i] == colors[i + 1]:
     *                 if colors[i] == 'A':
     *                     countAAA += 1
     *                 elif colors[i] == 'B':
     *                     countBBB += 1
     *         return countAAA > countBBB
     *
     * @param colors
     * @return
     */
    public boolean winnerOfGame(String colors) {
        int countAAA = 0;
        int countBBB = 0;
        int n = colors.length();
        char[] arr = colors.toCharArray();
        for (int i = 1; i < n - 1; i++) {
            if (arr[i - 1] == arr[i] && arr[i] == arr[i + 1]) {
                if (arr[i] == 'A') {
                    countAAA++;
                } else {
                    countBBB++;
                }
            }
        }
        return countAAA > countBBB;
    }
}
