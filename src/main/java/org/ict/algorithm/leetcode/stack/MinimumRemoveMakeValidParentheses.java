package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ),
 * so that the resulting parentheses string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Example 2:
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 *
 * Example 3:
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is either'(' , ')', or lowercase English letter.
 *
 * @author sniper
 * @date 11 Apr, 2023
 * LC1249, Medium, frequency=282.
 */
public class MinimumRemoveMakeValidParentheses {

    /**
     * Understanding the following solution
     * Two-Pointers Solution
     * Time Cost 12ms
     *
     * Using two variables, left and right, to indicate the number of left and right brackets respectively,
     * At first, iterating through the given string s, and count all the number of right brackets.
     * Secondly, if left and right are equal to each other, it means that there are no more right brackets,
     * and the left bracket is illegal at this time, so let left increase by 1.
     * If right bracket is encountered, right decreases by 1 first,
     * because right indicates the number of right brackets that follow,
     * and if left is equal to 0,
     * it means that there is no corresponding left bracket in front of it, so it is skipped,
     * otherwise left is self-subtracting by 1.
     * For all cases where there is no continue, it is added to the result res,
     * which means that the corresponding letter or left and right brackets are legal
     *
     * e.g. s = "a)b(c)d"
     * left:0, right:2
     * i:0, ch:a, res:a
     * i:1, ch:), right:2->1, left:0, skip this ch:), res:a
     * i:2, ch:b,
     * @param s
     * @return
     */
    public String minRemoveToMakeValidV2(String s) {
        int left = 0;
        int right = 0;
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                right++;
            }
        }

        StringBuilder res = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (left == right) {
                    /**
                     * It means current ch '(' is illegal, because left-bracket counts have matched out.
                     * We need to skip it.
                     */
                    continue;
                }
                left++;
            } else if (ch == ')') {
                right--;
                if (left == 0) {
                    /**
                     * It means current ch ')' is illegal, because there is no left-bracket to match with it.
                     * We need to skip it.
                     */
                    continue;
                }
                left--;
            }
            res.append(ch);
        }
        return res.toString();
    }

    /**
     * Understanding the following solution
     * Time Cost 13ms
     * @param s
     * @return
     */
    public String minRemoveToMakeValidV1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        /**
         * Record the position of the left parentheses '('.
         * e.g. s = "lee(t(c)o)de)"
         * and mark the illegal '(' or ')' to '*' in the char array.
         */
        Deque<Integer> stack = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(i);
            } else if (arr[i] == ')') {
                if (stack.isEmpty()) {
                    /**
                     * The current char arr[i] is illegal, because there is no '(' to match ')'
                     */
                    arr[i] = '*';
                } else {
                    stack.pop();
                }
            }
        }
        /**
         * If stack is not empty, it means these positions are illegal, marking them with '*'.
         */
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            arr[idx] = '*';
        }
        /**
         * Collect the characters except '*', return collected result.
         */
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '*') {
                res.append(arr[i]);
            }
        }
        return res.toString();
    }

    /**
     * Time Cost 27ms
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                /**
                 * The current char is ')', Now we check the top element in stack.
                 * If stack is empty, current position is not illegal, push it into the stack.
                 * If the char of string with position of top item in stack is not '(', push it into the stack.
                 */
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        /**
         * Skip all the illegal positions in the stack.
         */
        StringBuilder res = new StringBuilder();
        Set<Integer> set = new HashSet<>(stack);
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(i)) {
                continue;
            }
            res.append(s.charAt(i));
        }
        return res.toString();
    }


}
