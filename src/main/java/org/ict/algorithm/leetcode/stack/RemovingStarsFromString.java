package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given a string s, which contains stars *.
 *
 * In one operation, you can:
 *
 * Choose a star in s.
 * Remove the closest non-star character to its left, as well as remove the star itself.
 * Return the string after all stars have been removed.
 *
 * Note:
 *
 * The input will be generated such that the operation is always possible.
 * It can be shown that the resulting string will always be unique.
 *
 *
 * Example 1:
 * Input: s = "leet**cod*e"
 * Output: "lecoe"
 * Explanation: Performing the removals from left to right:
 * - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
 * - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
 * - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
 * There are no more stars, so we return "lecoe".
 *
 * Example 2:
 * Input: s = "erase*****"
 * Output: ""
 * Explanation: The entire string is removed, so we return an empty string.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters and stars *.
 * The operation above can be performed on s.
 * @author sniper
 * @date 11 Apr, 2023
 * LC2390, Medium
 */
public class RemovingStarsFromString {

    /**
     * Intuition
     * "*" will only affect the characters before it.
     * The input s works like tapping and "*" works like the deletion action.
     *
     * Explanation
     * Use a stack to simulate the process.
     * For each character c in string s,
     * if c == '*', pop the last element from the stack,
     * to simulate the deletion.
     * if c != '*', push the element to the stack.
     *
     * Time Complexity O(N)
     * Space Complexity O(N)
     *
     *
     * def removeStars(self, s: str) -> str:
     *         stack = []
     *         for c in s:
     *             if c != '*':
     *                 stack.append(c)
     *             else:
     *                 stack.pop()
     *         return "".join(stack)
     * Time Cost 44ms
     * @author lee215
     * @param s
     * @return
     */
    public String removeStarsV1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                res.setLength(res.length() - 1);
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }


    /**
     * Time Complexity O(N)
     * Space Complexity O(N)
     * Time Cost 56ms
     * Similar with
     * @see org.ict.algorithm.leetcode.stack.ValidParentheses
     * @param s
     * @return
     */
    public String removeStars(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return "";
                }
                stack.pop();
            }
        }
        if (stack.isEmpty()) {
            return "";
        }
        char[] arr = new char[stack.size()];
        int i = arr.length - 1;
        while (!stack.isEmpty()) {
            arr[i--] = stack.pop();
        }
        return new String(arr);
    }
}
