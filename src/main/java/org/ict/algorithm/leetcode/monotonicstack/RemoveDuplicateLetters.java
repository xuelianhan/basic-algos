package org.ict.algorithm.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string s,
 * remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order
 * among all possible results.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 *
 *
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 * @author sniper
 * @date 23 Aug 2023
 * LC316, Medium
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        RemoveDuplicateLetters instance = new RemoveDuplicateLetters();
        String s = "cbadcbcd";
        String res = instance.removeDuplicateLetters(s);
        System.out.println(res);
    }

    public String removeDuplicateLettersV1(String s) {
        //todo
        return null;
    }


    /**
     * e.g. s = "cbacdcbc"
     * expected: "acdb"
     *
     * e.g. s = "cbadcbcd"
     * expected: "abcd"
     *
     * @author hi-malik
     * @see <a href="https://leetcode.com/problems/remove-duplicate-letters/solutions/1859410/java-c-detailed-visually-explained/"></a>
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        /**
         * Track the lastIndex of character presence
         */
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] seen = new boolean[26];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - 'a';
            if (seen[cur]) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > cur && i < lastIndex[stack.peek()]) {
                seen[stack.pop()] = false;
            }
            stack.push(cur);
            seen[cur] = true;
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append((char)(stack.pop() + 'a'));
        }
        return res.reverse().toString();
    }
}
