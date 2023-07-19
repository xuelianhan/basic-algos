package org.ict.algorithm.leetcode.stack;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, '+', '-', '*', '/' operators,
 * and open '(' and closing parentheses ')'.
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-2^31, 2^31 - 1].
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1+1"
 * Output: 2
 * Example 2:
 *
 * Input: s = "6-4/2"
 * Output: 4
 * Example 3:
 *
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 *
 *
 * Constraints:
 *
 * 1 <= s <= 10^4
 * s consists of digits, '+', '-', '*', '/', '(', and ')'.
 * s is a valid expression.
 * @author sniper
 * @date 09 Feb, 2023
 * LC772, Hard
 */
public class BasicCalculatorIII {

    public static void main(String[] args) {
        String s = "60-4/2";
        BasicCalculatorIII instance = new BasicCalculatorIII();
        int res = instance.calculate(s);
        System.out.println(res);
    }

    public int calculate(String s) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        boolean hasPrevNum = false;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);
                hasPrevNum = true;
            } else if (c == '(') {
                ops.push('(');
                hasPrevNum = false;
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    calc(nums, ops);
                }
                ops.pop(); // Pop '('.
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (!hasPrevNum) {
                    nums.push(0);
                }
                while (!ops.isEmpty() && precedes(ops.peek(), c)) {
                    calc(nums, ops);
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            calc(nums, ops);
        }
        return nums.peek();
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        int b = nums.pop();
        int a = nums.pop();
        char op = ops.pop();
        if (op == '+') {
            nums.push(a + b);
        } else if (op == '-') {
            nums.push(a - b);
        } else if (op == '*') {
            nums.push(a * b);
        } else {
            // op == '/'
            nums.push(a / b);
        }
    }

    /**
     * Returns true if prevOp is an operator,
     * and priority(prevOp) >= priority(currOp).
     * @param prevOp
     * @param currOp
     * @return
     */
    private boolean precedes(char prevOp, char currOp) {
        if (prevOp == '(') {
            return false;
        }
        return prevOp == '*' || prevOp == '/' || currOp == '+' || currOp == '-';
    }

}
