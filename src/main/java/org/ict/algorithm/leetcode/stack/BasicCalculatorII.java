package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 10^5
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 * @author sniper
 * @date 18 Jan, 2023
 * LC227, Medium
 */
public class BasicCalculatorII {

    public static void main(String[] args) {
        String s = "0-2147483647";
        BasicCalculatorII instance = new BasicCalculatorII();
        int result = instance.calculate(s);
        System.out.println(result);
    }

    public int calculateV1(String s) {
        return 0;
    }

    public int calculate(String s) {
        boolean allDigit = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
                continue;
            } else if (ch == ' ') {
                continue;
            } else {
                allDigit = false;
                break;
            }
        }
        if (allDigit) {
            return Integer.valueOf(sb.toString());
        }
        Deque<Integer> operand = new ArrayDeque<>();
        Deque<Character> operator = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                operand.push(c - '0');
            } else {
                operator.push(c);
            }
        }
        //todo
        while (!operator.isEmpty()) {
            char op = operator.pop();
            if (op == '*') {
                int x1 = operand.pop();
                int x2 = operand.pop();
                operand.push(x1 * x2);
            } else if (op == '/') {
                int x1 = operand.pop();
                int x2 = operand.pop();
                operand.push(x2 / x1);
            } else if (op == '+') {
                int x1 = operand.pop();
                int x2 = operand.pop();
                operand.push(x1 + x2);
            } else if (op == '-') {
                int x1 = operand.pop();
                int x2 = operand.pop();
                operand.push(x2 - x1);
            }
        }
        return operand.pop();
    }
}
