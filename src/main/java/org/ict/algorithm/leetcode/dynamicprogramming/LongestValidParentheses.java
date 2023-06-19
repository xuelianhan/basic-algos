package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string containing just the characters '(' and ')',
 * return the length of the longest valid (well-formed) parentheses
 * substring
 * .
 *
 * Example 1:
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 *
 *
 * Example 2:
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 *
 *
 * Example 3:
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 * 0 <= s.length <= 3 * 10^4
 * s[i] is '(', or ')'.
 * @author sniper
 * @date 19 Jun 2023
 * LC32, Hard, frequency=9
 */
public class LongestValidParentheses {

    /**
     * Dynamic Programming Solution
     * @param s
     * @return
     */
    public int longestValidParenthesesV2(String s) {
        int res = 0;
        //todo
        return res;
    }

    /**
     * Stack Solution
     * @param s
     * @return
     */
    public int longestValidParenthesesV1(String s) {
        int res = 0;
        //todo
        return res;
    }

    /**
     * Stack Solution
     * Time Cost 3ms
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int res = 0;
        int start = 0;
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    start = i + 1;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        res = Math.max(res, i - start + 1);
                    } else {
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        return res;
    }

}
