package org.ict.algorithm.leetcode.string;


import java.util.Stack;

/**
 * Given a string s and an integer k,
 * reverse the first k characters for every 2k characters counting from the start of the string.
 *
 * If there are fewer than k characters left,
 * reverse all of them.
 *
 * If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and leave the other as original.
 *
 *
 *
 * Example 1:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 *
 * Example 2:
 * Input: s = "abcd", k = 2
 * Output: "bacd"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^4
 * @author sniper
 * @date 2022/3/10
 */
public class ReverseStringII {

    public String reverseStr(String s, int k) {
        if (s.length() == 1 || k == 1) {
            return s;
        }
        if (s.length() <= k) {
            return reverseString(s);
        }
        int i = 0;
        int j = i + 2 * k;
        StringBuffer sb = new StringBuffer();
        for(i = 0; i < s.length(); ) {
            if (i < i + k) {
                String sub = s.substring(i, i + k);
                sb.append(reverseString(sub));
            } else {
                sb.append(s.charAt(i));
            }
            i  = i + k;
            j = i + 2 * k;
        }
        return sb.toString();
    }

    public static String reverseString(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            stack.push(s.charAt(i));
            i++;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
