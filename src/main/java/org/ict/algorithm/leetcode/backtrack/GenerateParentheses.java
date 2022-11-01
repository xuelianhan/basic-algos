package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 * @author sniper
 * @date 01 Nov, 2022
 * LC22
 */
public class GenerateParentheses {

    /**
     * Backtracking/Depth First Search Solution
     *
     * Apparently, the performance of the brute force algorithm is not ideal,
     * as we don’t need to generate the invalid Parentheses in the first place.
     * We can use two counters to remember the number of opening and closed Parentheses respectively,
     * and only backtracking those valid branches.
     * The invalid branches are cut-off and abandoned.
     *
     * The total number of different Parentheses is the n-th Catalan number,
     * which is 1/(n+1)C(2n, n) and the complexity is bounded asymptotically to 4^n/(n*sqrt(n)).
     *
     * @see <a href="https://helloacm.com/how-to-generate-parentheses-using-bruteforce-or-depth-first-search-backtracking-algorithms"></a>
     *
     * Time Cost 3ms
     * @param n
     * @return
     */
    public List<String> generateParenthesisV1(int n) {
        List<String> result = new ArrayList<>();
        List<Character> track = new ArrayList<>();
        /**
         * result, n, track, open, close
         */
        dfs(result, n, "", 0, 0);
        return result;
    }

    /**
     * Understanding the following Solution.
     * We can use two counters to remember the number of opening and closed Parentheses respectively,
     * and only backtracking those valid branches.
     * The invalid branches are cut-off and abandoned.
     *
     * @param result
     * @param track
     * @param n
     * @param open
     * @param close
     */
    public void dfs(List<String> result, int n, String track,  int open, int close) {
        if (track.length() == 2 * n) {
            result.add(track);
            return;
        }
        /**
         * Because open initialized with zero, it's at most n '(' characters
         * So open is less than n here(open is start from 0 to n-1)
         */
        if (open < n) {
            dfs(result, n, track + "(", open + 1, close);
        }
        /**
         * he close Parentheses should be less than the opening Parentheses
         */
        if (close < open) {
            dfs(result,  n, track + ")", open, close + 1);
        }
    }


    /**
     * Brute-Force Solution
     * Time Complexity: O(N*2^(2*N))
     * Time Cost 3ms
     *
     * @see <a href="https://helloacm.com/how-to-generate-parentheses-using-bruteforce-or-depth-first-search-backtracking-algorithms"></a>
     * @param n
     * @return
     *
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        char[] track = new char[2*n];
        /**
         * As each character has two possibilities, and there are N*2 characters
         * e.g. n=1, s = "()"
         */
        backtrack(result, track, 0);
        return result;
    }

    /**
     * Same with backtrack method.
     * Time Complexity: O(N*2^(2*N))
     * @param result
     * @param track
     * @param pos
     */
    public void backtrackV1(List<String> result, char[] track, int pos) {
        if (pos == track.length) {
            if (isValidParenthesis(track)) {
                result.add(new String(track));
            }
            return;
        }
        track[pos] = '(';
        backtrack(result, track, pos + 1);
        track[pos] = ')';
        backtrack(result, track, pos + 1);
    }

    /**
     * Time Complexity: O(N*2^(2*N))
     * @param result
     * @param track
     * @param pos
     */
    public void backtrack(List<String> result, char[] track, int pos) {
        if (pos == track.length) {
            /**
             * Don't put isValidParenthesis along with the above condition(pos == track.length)
             * You can think why?
             * Because it will cause track[pos] out of bound.
             */
            if (isValidParenthesis(track)) {
                result.add(new String(track));
            }
        } else {
            track[pos] = '(';
            backtrack(result, track, pos + 1);
            track[pos] = ')';
            backtrack(result, track, pos + 1);
        }
    }

    /**
     * First, we can check if a given string is a valid parenthesis.
     * If we meet '(' we increment the balance, and ')' we decrement it.
     * At any time, if the balance is negative, it is invalid.
     * Finally, a valid Parenthesis should have zero balance at the end.
     *
     * Time Complexity: O(N)
     * @param a
     * @return
     */
    public boolean isValidParenthesis(char[] a) {
        int balance = 0;
        for (int i = 0; i < a.length; i++) {
            if ('(' == a[i]) {
                balance++;
            } else if (')' == a[i]) {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
    }


}
