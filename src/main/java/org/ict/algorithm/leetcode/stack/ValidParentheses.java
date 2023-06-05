package org.ict.algorithm.leetcode.stack;



import java.util.*;

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
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 *
 * @author sniper
 * LC20, Easy, frequency=177
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
        ValidParentheses instance = new ValidParentheses();
        boolean res = instance.isValid(s);
        System.out.println(res);
    }

    /**
     * Time Cost 1ms
     * --------------------------
     * class Solution:
     *     def isValid(self, s: str) -> bool:
     *         if not s or len(s) < 2:
     *             return False
     *         stack = []
     *         for c in s:
     *             if c == '(':
     *                 stack.append(')')
     *             elif c == '[':
     *                 stack.append(']')
     *             elif c == '{':
     *                 stack.append('}')
     *             elif not stack or stack.pop() != c:
     *                 return False
     *         return not stack
     * @see <a href="https://leetcode.com/problems/valid-parentheses/solutions/9178/short-java-solution"></a>
     * @author phoenix13steve
     * @param s
     * @return
     */
    public boolean isValidV2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
                /**
                 * Notice using pop instead of peek
                 */
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        /**
         * Notice check stack at last.
         */
        return stack.isEmpty();
    }

    public boolean isValidV1(String s) {
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
                if (match != a[i]) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }


    public boolean isValid(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        Set<Character> set = new HashSet<>();
        set.add('(');
        set.add('[');
        set.add('{');
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char last = stack.pop();
                if (ch == ')' && last != '(') {
                    return false;
                }
                if (ch == ']' && last != '[') {
                    return false;
                }
                if (ch == '}' && last != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
