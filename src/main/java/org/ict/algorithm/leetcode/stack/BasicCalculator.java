package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 10^5
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 *
 * @author sniper
 * @date 07 Feb, 2023
 * LC224, Hard
 */
public class BasicCalculator {

    public static void main(String[] args) {
        BasicCalculator instance = new BasicCalculator();

        String s = "1 + 1";
        int res = instance.calculate(s);
        System.out.println(res);
    }

    public int calculateV2(String s) {
        return 0;
    }

    public int calculateV1(String s) {
        return 0;
    }


    /**
     * Time Cost 6ms
     * e.g.s = "1 + 1"
     * @param s
     * @return
     */
    public int calculate(String s) {
        int res = 0;
        int sign = 1;
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            /**
             * If current character is digit, then we count the number
             */
            if (c >= '0' && c <= '9') {
                int num = 0;
                while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = 10 * num + (s.charAt(i) - '0');
                    i++;
                }
                /**
                 * store current number addition into the result, then backward one step.
                 */
                res += sign * num;
                i--;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res *= stack.pop();
                res += stack.pop();
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            }
        }
        return res;
    }
}
