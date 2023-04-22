package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * A parentheses string is valid if and only if:
 *
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 *
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
 * Return the minimum number of moves required to make s valid.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "())"
 * Output: 1
 * Example 2:
 *
 * Input: s = "((("
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] is either '(' or ')'.
 * @author sniper
 * @date 22 Apr, 2023
 * LC921, Medium, frequency=100
 */
public class MinimumAddToMakeParenthesesValid {

    /**
     * Understanding the following Solution
     *
     * Time Complexity O(N)
     * Space Complexity O(1)
     *
     * It's similar with minAddToMakeValidV4 but minAddToMakeValidV4 is much easier to understand.
     * -----------------------------
     * def minAddToMakeValid(self, s: str) -> int:
     *         l = 0
     *         r = 0
     *         for c in s:
     *             if c == '(':
     *                 l += 1
     *             elif l > 0:
     *                 l -= 1
     *             else:
     *                 r += 1
     *         return l + r
     *
     * @param s
     * @return
     */
    public int minAddToMakeValidV5(String s) {
        int l = 0;
        int r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else if (l > 0) {
                l--;
            } else {
                r++;
            }
        }
        return l + r;
    }

    /**
     * Understanding the following Solution
     *
     * Time Complexity O(N)
     * Space Complexity O(1)
     * --------------------------------------
     *  def minAddToMakeValid(self, s: str) -> int:
     *         l = 0
     *         r = 0
     *         for c in s:
     *             if c == '(':
     *                 l += 1
     *             else:
     *                 if l == 0:
     *                     r += 1
     *                 else:
     *                     l -= 1
     *         return l + r
     *
     *
     * @param s
     * @return
     */
    public int minAddToMakeValidV4(String s) {
        int l = 0;
        int r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else {
                if (l == 0) {
                    r++;
                } else {
                    l--;
                }
            }
        }
        return l + r;
    }


    /**
     * Understanding the following Solution
     *
     * class Solution:
     *     def minAddToMakeValid(self, s: str) -> int:
     *         stack = []
     *         for c in s:
     *             if c == ')' and stack and stack[-1] == '(':
     *                 stack.pop()
     *             else:
     *                 stack.append(c)
     *         return len(stack)
     * ----------------------------------------------------
     * class Solution {
     * public:
     *     int minAddToMakeValid(string s) {
     *         string stack;
     *         for (char c : s) {
     *             if (c == ')' && stack.size() && stack.back() == '(') {
     *                stack.pop_back();
     *             } else {
     *                stack.push_back(c);
     *             }
     *         }
     *         return stack.size();
     *     }
     * };
     * @param s
     * @return
     */
    public int minAddToMakeValidV3(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.size();
    }




    /**
     * Understanding the following Solution
     * @param s
     * @return
     */
    public int minAddToMakeValidV2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.size();
    }


    /**
     *
     * @param s
     * @return
     */
    public int minAddToMakeValidV1(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int res = 0;
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    res++;
                }
            }
        }
        return res == 0 ? stack.size() : res + stack.size();
    }


    /**
     *
     *
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int res = 0;
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    res++;
                }
            }
        }
        /**
         * e.g. s = "()))(("
         * e.g. s = "())"
         */
        return res == 0 ? stack.size() : res + stack.size();
    }

}
