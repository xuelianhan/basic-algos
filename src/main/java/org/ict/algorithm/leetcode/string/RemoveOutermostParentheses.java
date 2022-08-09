package org.ict.algorithm.leetcode.string;

/**
 * A valid parentheses string is either empty "", "(" + A + ")", or A + B,
 * where A and B are valid parentheses strings, and + represents string concatenation.
 *
 * For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
 * A valid parentheses string s is primitive if it is nonempty,
 * and there does not exist a way to split it into s = A + B, with A and B nonempty valid parentheses strings.
 *
 * Given a valid parentheses string s, consider its primitive decomposition:
 * s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.
 *
 * Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()())(())"
 * Output: "()()()"
 * Explanation:
 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
 * Example 2:
 *
 * Input: s = "(()())(())(()(()))"
 * Output: "()()()()(())"
 * Explanation:
 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
 * Example 3:
 *
 * Input: s = "()()"
 * Output: ""
 * Explanation:
 * The input string is "()()", with primitive decomposition "()" + "()".
 * After removing outer parentheses of each part, this is "" + "" = "".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is either '(' or ')'.
 * s is a valid parentheses string.
 * @author sniper
 * @date 2022/8/9
 * LC1021
 */
public class RemoveOutermostParentheses {

    public static void main(String[] args) {
        String s = "((()))";
        String result = removeOuterParentheses(s);
        System.out.println(result);
    }

    public static String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(Character c : s.toCharArray()) {
            if (c == '(' && count++ > 0) {
                sb.append(c);
            }
            if (c == ')' && count-- > 1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * The solution is same as removeOuterParenthesesV1
     * @param s
     * @return
     */
    public String removeOuterParenthesesV2(String s) {
        String res = "";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (count > 0) {
                    res += s.charAt(i);
                }
                count++;
            }

            if (s.charAt(i) == ')') {
                if (count > 1) {
                    res += s.charAt(i);
                }
                count--;
            }
        }
        return res;
    }

    /**
     * 1.Initialize a variable count to store the number of opening parentheses, i.e. ‘(‘.
     *
     * 2.Add every ‘(‘ to the result if count is greater than 0,
     * i.e.add all ‘(‘ after the first ‘(‘ of a primitive substring is encountered.
     *
     * 3.Add every ‘)’ to the result if count is greater than 0,
     * i.e. add all ‘)’ before the last ‘)’ of a primitive substring is encountered.
     *
     * 4.Finally, print the resultant string obtained.
     * @param s
     * @return
     */
    public static String removeOuterParenthesesV1(String s) {
        String res = "";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' && count++ > 0) {
                res += s.charAt(i);
            }
            if (s.charAt(i) == ')' && count-- > 1) {
                res += s.charAt(i);
            }
        }
        return res;
    }
}
