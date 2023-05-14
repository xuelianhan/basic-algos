package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * You are given a 0-indexed 2D integer array questions where questions[i] = [points-i, brainpower-i].
 * The array describes the questions of an exam,
 * where you have to process the questions in order (i.e., starting from question 0)
 * and make a decision whether to solve or skip each question.
 * Solving question-i will earn you points-i points, but you will be unable to solve each of the next brainpower-i questions.
 * If you skip question-i, you get to make the decision on the next question.
 *
 * For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
 * If question 0 is solved, you will earn 3 points, but you will be unable to solve questions 1 and 2.
 * If instead, question 0 is skipped and question 1 is solved, you will earn 4 points,
 * but you will be unable to solve questions 2 and 3.
 * Return the maximum points you can earn for the exam.
 *
 * Example 1:
 * Input: questions = [[3,2],[4,3],[4,4],[2,5]]
 * Output: 5
 * Explanation: The maximum points can be earned by solving questions 0 and 3.
 * - Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
 * - Unable to solve questions 1 and 2
 * - Solve question 3: Earn 2 points
 * Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.
 *
 * Example 2:
 * Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
 * Output: 7
 * Explanation: The maximum points can be earned by solving questions 1 and 4.
 * - Skip question 0
 * - Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
 * - Unable to solve questions 2 and 3
 * - Solve question 4: Earn 5 points
 * Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.
 *
 * Constraints:
 * 1 <= questions.length <= 10^5
 * questions[i].length == 2
 * 1 <= points-i, brainpower-i <= 10^5
 * @author sniper
 * @date 13 May 2023
 * LC2140, Medium,
 */
public class SolvingQuestionsWithBrainpower {

    /**
     * Understanding the following solution.
     * Time Cost 5ms
     * Time complexity O(n),
     * Space complexity O(n)
     * _____________________
     * def mostPoints(self, questions: List[List[int]]) -> int:
     *         n = len(questions)
     *         f = [0] * (n + 1)
     *         for i in range(n - 1, -1, -1):
     *             p, b = questions[i]
     *             j = i + b + 1
     *             f[i] = max(f[i + 1], p + (0 if j > n else f[j]))
     *         return f[0]
     * -------------------------
     * class Solution {
     * public:
     *     long long mostPoints(vector<vector<int>>& questions) {
     *         const int n = questions.size();
     *         // dp[i] := max points starting from questions[i]
     *         vector<long long> dp(n + 1);
     *
     *         for (int i = n - 1; i >= 0; --i) {
     *         const int points = questions[i][0];
     *         const int brainpower = questions[i][1];
     *         const int nextIndex = i + brainpower + 1;
     *         const long long nextPoints = nextIndex < n ? dp[nextIndex] : 0;
     *         dp[i] = max(points + nextPoints, dp[i + 1]);
     *         }
     *
     *         return dp[0];
     *     }
     * };
     * -------------------------------------------
     * object Solution {
     *     def mostPoints(questions: Array[Array[Int]]): Long = {
     *         val n = questions.length
     *         val dp = Array.fill(n + 1)(0L)
     *         for (i <- n - 1 to 0 by -1) {
     *             val p = questions(i)(0)
     *             val b = questions(i)(1)
     *             val j = i + b + 1
     *             dp(i) = Math.max(dp(i + 1), p + (if (j <= n) dp(j) else 0))
     *         }
     *         dp(0)
     *     }
     * }
     * -----------------------------------------------
     * In recursive solution, we iterate from index-0 to index-(n-1),
     * while in dynamic solution, we do the reverse order,
     * from n-1 to 0
     * e.g. questions = [[3,2],[4,3],[4,4],[2,5]]
     * i:3, p:2, b:5, j=i+b+1=9, dp[3]=max(dp[4], p+0)=max(0, 3)=3
     *
     * @param questions
     * @return
     */
    public long mostPointsV1(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int p = questions[i][0];
            int b = questions[i][1];
            int j = i + b + 1;
            /**
             * Skip i-th question, you go to the (i+1)-th question.
             * Or you take the i-th question, go the i + question[i][1] + 1 question if
             * it's in bound of question.
             */
            dp[i] = Math.max(dp[i + 1], p + (j > n ? 0 : dp[j]));
        }
        return dp[0];
    }

    /**
     * Understanding the following solution
     * Recursive + Memorization Solution
     * Time Cost 20ms
     * -----------------------------
     * class Solution:
     *     def mostPoints(self, questions: List[List[int]]) -> int:
     *         @cache
     *         def dfs(i: int) -> int:
     *             if i >= len(questions):
     *                 return 0
     *             p, b = questions[i]
     *             return max(p + dfs(i + b + 1), dfs(i + 1))
     *
     *         return dfs(0)
     * @param questions
     * @return
     */
    public long mostPoints(int[][] questions) {
        Long[] memo = new Long[questions.length];
        return helper(0, questions, memo);
    }

    private long helper(int idx, int[][] questions, Long[] memo) {
        if (idx >= questions.length) {
            return 0;
        }
        if (memo[idx] != null) {
            return memo[idx];
        }
        /**
         * Only two choices, take current-idx question and solve it,
         * or leave current-dx question and go to the next.
         */
        long take = questions[idx][0] + helper(idx + questions[idx][1] + 1, questions, memo);
        long leave = helper(idx + 1, questions, memo);
        memo[idx] = Math.max(take, leave);
        return memo[idx];
    }

}
