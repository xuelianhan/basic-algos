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
     * Understanding the following solution
     * Stack Solution
     * We can actually compute the longest length on the fly.
     * ------------------------------------------------------
     * e.g. s = ")()())"
     * 0 1 2 3 4 5
     * ) ( ) ( ) )
     *
     *
     * @author jianchao-li
     * @param s
     * @return
     */
    public int longestValidParenthesesV1(String s) {
        int res = 0;
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        /**
         * Notice -1 here.
         * Consider 2 different cases:
         * e.g. s = "" , or s = ")()())"
         * 1. Why do we need to push a number into the stack before for-loop?
         * If we don't push a number into the stack before for-loop, the pop operation will throw an exception.
         * e.g. s = "()"
         * 2. Why this number is -1? Does other number are OK too?
         * We need to utilize the i - stack.peek(). Considering the above case, if we push 0 into the stack,
         * i:1 , i - stack.peek() = 1 - 0 =1. Actually the expected answer should be 2, that's why the -1 works.
         */
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Stack Solution
     * Time Cost 3ms
     * ----------------------------------------------------------
     * e.g. s = ")()())"
     * 0 1 2 3 4 5
     * ) ( ) ( ) )
     * res:0, start:0, stack:[]
     * i:0, s[0] = ')', stack:[ ], stack is empty, start = i + 1 =1
     * i:1, s[1] = '(', stack:[1]
     * i:2, s[2] = ')', stack:[1], pop 1, stack:[], res = max(res, i-start+1)=max(0, 2-1+1)=2
     * i:3, s[3] = '(', stack:[3]
     * i:4, s[4] = ')', stack:[3], pop 3, stack:[], res = max(res, i-start+1)=max(2, 4-1+1)=4
     * i:5, s[5] = ')', stack:[], start = i + 1 = 6, i++
     * i:6, for-loop-end, return res:4
     *
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
                        //e.g. s = "(()"
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        return res;
    }

}
