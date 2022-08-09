package org.ict.algorithm.leetcode.string;

import java.util.Stack;

/**
 * ou are given a string s consisting of lowercase English letters.
 * A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.
 * It can be proven that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal,
 * and this is the only possible move.
 * The result of this move is that the string is "aaca",
 * of which only "aa" is possible, so the final string is "ca".
 * Example 2:
 *
 * Input: s = "azxxzy"
 * Output: "ay"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * @author sniper
 * @date 2022/8/9
 * LC1047
 */
public class RemoveAllAdjacentDuplicatesInString {

    public static void main(String[] args) {
        String s = "azxxzy";
        String result = removeDuplicatesV1(s);
        System.out.println(result);
    }

    /**
     * Two pointers solution provided by lee215
     *
     * i refers to the index to set next character in the output string.
     * j refers to the index of current iteration in the input string.
     *
     * Iterate characters of S one by one by increasing j.
     *
     * If S[j] is same as the current last character S[i - 1],
     * we remove duplicates by doing i -= 2.
     *
     * If S[j] is different as the current last character S[i - 1],
     * we set S[i] = S[j] and increment i++.
     *
     * You can easily update this solution to remove more duplicates.
     * Now it's a special case where we only remove replicates k = 2.
     * @param s
     * @return
     */
    public static String removeDuplicatesV3(String s) {
        int i = 0, n = s.length();
        char[] res = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            res[i] = res[j];
            if (i > 0 && res[i - 1] == res[i]) {
                i -= 2;
            }
        }
        return new String(res, 0, i);
    }

    /**
     * String Stack Solution is provided by lee215.
     * use the StringBuilder like a stack.
     * @param s
     * @return
     */
    public static String removeDuplicatesV2(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int size = sb.length();
            if (size > 0 && sb.charAt(size - 1) == c) {
                sb.deleteCharAt(size - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Use stack to remove duplicated characters.
     *
     * Keep a res as a characters stack.
     * Iterate characters of S one by one.
     * If the next character is same as the last character in res,
     * pop the last character from res.
     * In this way, we remove a pair of adjacent duplicates characters.
     *
     * If the next character is different,
     * we append it to the end of res.
     * @param s
     * @return
     */
    public static String removeDuplicatesV1(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
           if (!stack.isEmpty() && c == stack.peek()) {
               stack.pop();
           } else {
               stack.push(c);
           }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

}
