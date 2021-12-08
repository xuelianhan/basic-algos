package org.ict.algorithm.leetcode.string;


import org.checkerframework.checker.units.qual.C;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s containing just the characters
 * '(', ')',
 * '{', '}',
 * '[' and ']',
 * determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 *
 * @author sniper
 * LC20
 */
public class ValidParentheses {

    public static void main(String[] args) {
        //String s = "()";//true
        //String s = "()[]{}";//true
        //String s = "(]";//false
        //String s = "([)]";//false
        //String s = "{[]}";//true
        //String s = "((";//false
        //String s = "){";//false
        String s = ")(){}";//false;
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return false;
        }
        char[] a = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        for (int i = 0; i < a.length; i++) {
            if (a[i] == '(' || a[i] == '{' || a[i] == '[')  {
                stack.push(a[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char cur = stack.pop();
                char match = map.get(cur);
                if (match == a[i]) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

}
