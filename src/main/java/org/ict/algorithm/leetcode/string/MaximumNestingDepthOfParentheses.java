package org.ict.algorithm.leetcode.string;

/**
 * A string is a valid parentheses string (denoted VPS) if it meets one of the following:
 *
 * It is an empty string "", or a single character not equal to "(" or ")",
 * It can be written as AB (A concatenated with B), where A and B are VPS's, or
 * It can be written as (A), where A is a VPS.
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 *
 * depth("") = 0
 * depth(C) = 0, where C is a string with a single character not equal to "(" or ")".
 * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's.
 * depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 * For example,
 * "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2),
 * and ")(" and "(()" are not VPS's.
 *
 * Given a VPS represented as string s, return the nesting depth of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(1+(2*3)+((8)/4))+1"
 * Output: 3
 * Explanation: Digit 8 is inside of 3 nested parentheses in the string.
 * Example 2:
 *
 * Input: s = "(1)+((2))+(((3)))"
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
 * It is guaranteed that parentheses expression s is a VPS.
 * @author sniper
 * @date 14 Aug, 2022
 * LC1614
 */
public class MaximumNestingDepthOfParentheses {

    public static void main(String[] args) {
        //String s = "(1)+((2))+(((3)))";
        //String s = "(1+(2*3)+((8)/4))+1";
        String s = "()(()())";
        int result = maxDepth(s);
        System.out.println(result);
    }

    /**
     * The depth of any character in the VPS is the
     * ( number of left brackets before it ) - ( number of right brackets before it )
     * @param s
     * @return
     */
    public static int maxDepth(String s) {
        int res = 0;
        int leftBracketCnt = 0;
        int rightBracketCnt = 0;
        for (char ch : s.toCharArray()) {
            if ('(' == ch) {
                leftBracketCnt++;
            }
            if (')' == ch) {
                rightBracketCnt++;
            }
            res = Math.max(res, (leftBracketCnt - rightBracketCnt));
        }
        return res;
    }

    /**
     * Solution provided by lee215
     *
     * Ignore digits and signs,
     * only count the current open parentheses cur.
     *
     * The depth equals to the maximum open parentheses.
     *
     * More Parentheses Problem To Advance
     * Here is a ladder of parentheses problem, in order of problem number.
     *
     * 1541.Minimum Insertions to Balance a Parentheses String
     * 1249.Minimum Remove to Make Valid Parentheses
     * 1111.Maximum Nesting Depth of Two Valid Parentheses Strings
     * 1190.Reverse Substrings Between Each Pair of Parentheses
     * 1021.Remove Outermost Parentheses
     * 921.Minimum Add to Make Parentheses Valid
     * 856.Score of Parentheses
     *
     * @param s
     * @return
     */
    public int maxDepthV2(String s) {
        int res = 0, cur = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                res = Math.max(res, ++cur);
            }
            if (s.charAt(i) == ')') {
                cur--;
            }
        }
        return res;

    }
}
