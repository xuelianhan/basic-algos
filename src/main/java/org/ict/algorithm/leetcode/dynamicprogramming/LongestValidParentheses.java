package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.ArrayDeque;
import java.util.Arrays;
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
     * Understanding the following solution
     * Two-Pointer Solution
     *
     * @param s
     * @return
     */
    public int longestValidParenthesesV4(String s) {
        int res = 0;
        int l = 0;
        int r = 0;
        int n = s.length();
        char[] arr = s.toCharArray();
        /**
         * 1st-round scanning
         * e.g. s = "())"
         */
        for (int i = 0; i < n; i++) {
            if (arr[i] == '(') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                res = Math.max(res, 2 * r);
            } else if (r > l) {
                l = 0;
                r = 0;
            }
        }

        /**
         * 2nd-round scanning
         * e.g. s = "(()"
         */
        l = 0;
        r = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == '(') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                res = Math.max(res, 2 * l);
            } else if (l > r) {
                l = 0;
                r = 0;
            }
        }
        return res;
    }

    /**
     * Dynamic Programming Solution
     * @param s
     * @return
     */
    public int longestValidParenthesesV3(String s) {
        int res = 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int j = i - 2 - dp[i - 1];
            if (s.charAt(i - 1) == '(' || j < 0 || s.charAt(j) == ')') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i - 1] + 2 + dp[j];
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    /**
     * Dynamic Programming Solution
     * @author jerryrcwong
     * @see <a href="https://leetcode.com/problems/longest-valid-parentheses/solutions/14133/my-dp-o-n-solution-without-using-stack"></a>
     * @param s
     * @return

     */
    public int longestValidParenthesesV2(String s) {
        String s2 = ")" + s;
        /**
         * dp[i] := Length of the longest valid parentheses substring of s2[1..i]
         */
        int[] dp = new int[s2.length()];
        for (int i = 1; i < s2.length(); i++) {
            if (s2.charAt(i) == ')' && s2.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * Understanding the following solution
     * Stack Solution
     * We can actually compute the longest length on the fly.
     * ------------------------------------------------------
     * class Solution {
     * public:
     *     int longestValidParentheses(string s) {
     *         int res = 0;
     *         int n = s.size();
     *         stack<int> stk;
     *         stk.push(-1);
     *         for (int i = 0; i < n; ++i) {
     *             if (s[i] == '(') {
     *                 stk.push(i);
     *             } else {
     *                 stk.pop();
     *                 if (stk.empty()) {
     *                     stk.push(i);
     *                 } else {
     *                     res = max(res, i - stk.top());
     *                 }
     *             }
     *         }
     *         return res;
     *     }
     * };
     * ------------------------------------------------------
     * class Solution:
     *     def longestValidParentheses(self, s: str) -> int:
     *         stack = deque()
     *         stack.append(-1)
     *         res = 0
     *         for i in range(0, len(s)):
     *             if s[i] == '(':
     *                 stack.append(i)
     *             else:
     *                 stack.pop()
     *                 if not stack:
     *                     stack.append(i)
     *                 else:
     *                     res = max(res, i - stack[-1])
     *         return res
     * ------------------------------------------------------
     * class Solution:
     *     def longestValidParentheses(self, s: str) -> int:
     *         stack, res = [-1], 0
     *         for i in range(0, len(s)):
     *             if s[i] == '(':
     *                 stack.append(i)
     *             else:
     *                 stack.pop()
     *                 if not stack:
     *                     stack.append(i)
     *                 else:
     *                     res = max(res, i - stack[-1])
     *         return res
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
     * class Solution {
     * public:
     *     int longestValidParentheses(string s) {
     *         int res = 0;
     *         int start = 0;
     *         int n = s.size();
     *         stack<int> stk;
     *         for (int i = 0; i < n; ++i) {
     *             if (s[i] == '(') {
     *                 stk.push(i);
     *             } else if (s[i] == ')') {
     *                 if (stk.empty()) {
     *                     start = i + 1;
     *                 } else {
     *                     stk.pop();
     *                     res = stk.empty() ? max(res, i - start + 1) : max(res, i - stk.top());
     *                 }
     *             }
     *         }
     *         return res;
     *     }
     * };
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
     * ----------------------------------------------------------
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
