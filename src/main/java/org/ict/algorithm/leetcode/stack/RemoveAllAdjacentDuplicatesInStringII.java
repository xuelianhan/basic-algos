package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given a string s and an integer k,
 * a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them,
 * causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made.
 * It is guaranteed that the answer is unique.
 *
 *
 * Example 1:
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 *
 * Example 2:
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 *
 * Example 3:
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s only contains lowercase English letters.
 * @author sniper
 * @date 07 May 2023
 * LC1209, Medium, frequency=30
 */
public class RemoveAllAdjacentDuplicatesInStringII {


    /**
     * Time Cost 18ms
     * ------------------------------------------------------
     * class Solution:
     *     def removeDuplicates(self, s: str, k: int) -> str:
     *         stack = []
     *         for ch in s:
     *             if stack and stack[-1][0] == ch:
     *                 stack[-1][1] += 1
     *             else:
     *                 stack.append([ch, 1])
     *             if stack[-1][1] == k:
     *                 stack.pop()
     *         return ''.join(ch * count for ch, count in stack)
     *
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates(String s, int k) {
        Deque<Pair> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().ch == ch) {
                stack.peek().count++;
            } else {
                stack.push(new Pair(ch, 1));
            }
            /**
             * Pop the pair if its count equals k.
             */
            if (stack.peek().count == k) {
                stack.pop();
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            /**
             * Append count times.
             */
            for (int i = 0; i < p.count; i++) {
                res.append(p.ch);
            }
        }
        /**
         * Don't forget reverse here!
         */
        return res.reverse().toString();
    }

    static class Pair {
        private char ch;

        private int count;

        public Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
}
