package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string],
 * where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid;
 * there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there will not be input like 3a or 2[4].
 * The test cases are generated so that the length of the output will never exceed 105.
 *
 * Example 1:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 *
 * Example 2:
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 *
 * Example 3:
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 *
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 *
 * @author sniper
 * @date 24 Apr, 2023
 * LC394, Medium, frequency=82,
 * Coupang
 */
public class DecodeString {


    /**
     * Understanding the following solution
     * Double-Stack Method
     * class Solution:
     *     def decodeString(self, s: str) -> str:
     *         stack1, stack2 = [], []
     *         num, res = 0, ''
     *         for c in s:
     *             if c.isdigit():
     *                 num = num * 10 + int(c)
     *             elif c == '[':
     *                 stack1.append(num)
     *                 stack2.append(res)
     *                 num, res = 0, ''
     *             elif c == ']':
     *                 res = stack2.pop() + res * stack1.pop()
     *             else:
     *                 res += c
     *         return res
     * -------------------------------------------
     * e.g. s = "3[a2[c]]"
     *
     * @param s
     * @return
     */
    public String decodeStringV1(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        int num = 0;
        String res = "";
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<String> stack2 = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            } else if (c == '[') {
                stack1.push(num);
                stack2.push(res);
                num = 0;
                res = "";
            } else if (c == ']') {
                StringBuilder builder = new StringBuilder();
                for (int i = 0, n = stack1.pop(); i < n; i++) {
                    builder.append(res);
                }
                res = stack2.pop() + builder.toString();
            } else {
                res += String.valueOf(c);
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Double-Stack Method
     * e.g. s = "3[a]2[bc]"
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int num = 0;
        String res = "";
        Stack<Integer> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            } else if (c == '[') {
                stack1.push(num);
                stack2.push(res);
                num = 0;
                res = "";
            } else if (c == ']') {
                StringBuilder builder = new StringBuilder();
                for (int i = 0, n = stack1.pop(); i < n; i++) {
                    builder.append(res);
                }
                res = stack2.pop() + builder.toString();
            } else {
                res += String.valueOf(c);
            }
        }
        return res;
    }
}
