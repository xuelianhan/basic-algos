package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

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
     * @see <a href="https://helloacm.com/how-to-generate-parentheses-using-bruteforce-or-depth-first-search-backtracking-algorithms"></a>
     * @param n
     * @return
     */
    public List<String> generateParenthesisV1(int n) {
        List<String> result = new ArrayList<>();
        return result;
    }



    /**
     * Brute-Force Solution
     *
     * @param n
     * @return
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
