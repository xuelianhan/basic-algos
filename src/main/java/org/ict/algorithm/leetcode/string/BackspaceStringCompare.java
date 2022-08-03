package org.ict.algorithm.leetcode.string;

import java.util.Stack;

/**
 * Given two strings s and t,
 * return true if they are equal when both are typed into empty text editors.
 * '#' means a backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 *
 * Example 1:
 *
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * Example 2:
 *
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 * Example 3:
 *
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 200
 * s and t only contain lowercase letters and '#' characters.
 *
 * Follow up: Can you solve it in O(n) time and O(1) space?
 *
 * @author sniper
 * @date 2022/8/2
 * lc844
 */
public class BackspaceStringCompare {

    public static void main(String[] args) {
        String s = "y#fo##f", t = "y#f#o##f";
        boolean result = backspaceCompareV1(s, t);
        System.out.println(result);
    }

    public static boolean backspaceCompareV2(String s, String t) {
        return false;
    }

    public static boolean backspaceCompareV1(String s, String t) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        for (char c : s.toCharArray()) {
            if ('#' == c && !stackS.isEmpty()) {
                stackS.pop();
            } else {
                if ('#' != c) {
                    stackS.push(c);
                }
            }
        }
        for (char c : t.toCharArray()) {
            if ('#' == c && !stackT.isEmpty()) {
                stackT.pop();
            } else {
                if ('#' != c) {
                    stackT.push(c);
                }
            }
        }
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();
        while (!stackS.isEmpty()) {
            s1.append(stackS.pop());
        }
        while (!stackT.isEmpty()) {
            s2.append(stackT.pop());
        }
        //System.out.println(s1);
        //System.out.println(s2);
        if (s1.length() != s2.length()) {
            return false;
        }
        return s1.toString().equals(s2.toString());
    }
}
