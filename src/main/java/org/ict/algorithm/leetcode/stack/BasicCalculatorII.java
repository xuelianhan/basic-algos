package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-2^31, 2^31 - 1].
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
        //String s = "0-2147483647";
        //String s = "1*2-1";
        //String s = "1-1*2";
        //String s = "1-1*2*3";
        //String s = "1-1+1";
        //String s = " 36/2 ";
        String s = "3+2*2";

        BasicCalculatorII instance = new BasicCalculatorII();
        int result = instance.calculateV1(s);
        System.out.println(result);
    }

    /**
     * e.g. s = "1*2 + 3/2 "
     *
     * @param s
     * @return
     */
    public int calculateV4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char prevSign = '+';
        int num = 0;
        int curRes = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            }

            if (i == s.length() - 1 || !Character.isDigit(c) && c != ' ') {
                switch (prevSign) {
                    case '+':
                        res += curRes;
                        curRes = num;
                        break;
                    case '-':
                        res += curRes;
                        curRes = -num;
                        break;
                    case '*':
                        curRes *= num;
                        break;
                    case '/':
                        curRes /= num;
                        break;
                }
                prevSign = c;
                num = 0;
            }
        }
        res += curRes;
        return res;
    }

    /**
     * e.g. s = "1*2 + 3/2 "
     *
     * e.g. s = "3/2 "
     *
     * @param s
     * @return
     */
    public int calculateV3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int num = 0;
        int n = s.length();
        /**
         * Initialize previous sign with "+"
         */
        char prevSign = '+';
        int res = 0;
        int curRes = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = arr[i];
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == (n - 1)) {
                switch (prevSign) {
                    case '+':
                        curRes += num;
                        break;
                    case '-':
                        curRes -= num;
                        break;
                    case '*':
                        curRes *= num;
                        break;
                    case '/':
                        curRes /= num;
                        break;
                }
                if (c == '+' || c == '-' || i == (n - 1)) {
                    res += curRes;
                    /**
                     * A little tricky here.
                     */
                    curRes = 0;
                }
                /**
                 * Same little tricky.
                 */
                prevSign = c;
                num = 0;
            }
        }
        return res;
    }

    /**
     * Understanding the following solution.
     * Iteration Solution without Stack.
     *
     * Time Cost 5ms
     *
     * e.g. s = " 3/2 "
     * i:0, c = ' ',
     *
     * @param s
     * @return
     */
    public int calculateV2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int num = 0;
        int n = s.length();
        /**
         * Initialize previous sign with "+"
         */
        char prevSign = '+';
        int res = 0;
        int curRes = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = arr[i];
            if (c >= '0' && c <= '9') {
                num = 10 * num + c - '0';
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == (n - 1)) {
                switch (prevSign) {
                    case '+':
                        curRes += num;
                        break;
                    case '-':
                        curRes -= num;
                        break;
                    case '*':
                        curRes *= num;
                        break;
                    case '/':
                        curRes /= num;
                        break;
                }
                if (c == '+' || c == '-' || i == (n - 1)) {
                    res += curRes;
                    /**
                     * A little tricky here.
                     */
                    curRes = 0;
                }
                /**
                 * Same little tricky.
                 */
                prevSign = c;
                num = 0;
            }
        }
        return res;
    }

    /**
     * Similar with calculate, the only difference is to append "+" to the original input string.
     * e.g. s = "3+2*2 "
     * s = "3+2*2 +"
     * i:0, c:'3', num:0 --> num:3, preSign:'+', push 3 into the stack, stack:3, preSign:'3', num:0
     * i:1, c:'+', num:0, preSign:'3' --> preSign:'+', num:0
     * i:2, c:'2', num:0 --> num:2, preSign:'+', push 2 into the stack, stack:2,3, preSign:'2', num:0
     * i:3, c:'*', num:0, preSign:'2' --> preSign:'*', num:0
     * i:4, c:'2', num:0 --> num:2, preSign:'*', pop 2 from the stack, 2 * num = 4, push 4 into the stack, stack:4,3, preSign:'2', num:0
     * i:5, c:' ', num:0, skip
     * i:6, c:'+', num:0, preSign:'2' --> preSign:'+', num:0
     * for-loop-end
     * stack:4,3
     * res = 4 + 3 = 7
     * return 7
     *
     * @param s
     * @return
     */
    public int calculateV1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        /**
         * Append "+" to the original input string.
         */
        s += "+";
        /**
         * Initialize previous sign with "+"
         */
        char preSign = '+';
        int num = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
                continue;
            }
            if (c == ' ') {
                continue;
            }
            if (preSign == '+') {
                stack.push(num);
            } else if (preSign == '-') {
                stack.push(-num);
            } else if (preSign == '*') {
                stack.push(stack.pop() * num);
            } else if (preSign == '/') {
                stack.push(stack.pop() / num);
            }
            preSign = c;
            num = 0;
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    /**
     * Using One stack to store the operand,
     * and we process * and / first,
     * then we process remained number in the stack with addition "+"
     * e.g. s = " 3/2 "
     * i:0, arr[0] = ' ', num:0, prevSign:'+', arr[0] is neither digit nor sign, and not the last element, do nothing.
     * i:1, arr[1] = '3', num:0 --> num = 10 * 0 + '3' - '0' = 3, prevSign:'+'
     * i:2, arr[2] = '/', num:3, prevSign:'+', operand.push(3), operand:3, preSign = '/', num = 0
     * i:3, arr[3] = '2', num:0 --> num = 2
     * i:4, arr[4] = ' ', num:2, i == n - 1, prevSign = '/', operand.pop() = 3, num = 2, operand.push(3 / 2), operand: 1, preSign = ' ', num = 0
     *
     * operand:1
     * pop 1, res = 0 + 1 = 1
     * return res:1
     *
     * @author abner
     * @see <a href="https://leetcode.com/problems/basic-calculator-ii/solutions/63003/share-my-java-solution"></a>
     * @param s
     * @return
     */
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int num = 0;
        /**
         * Initialize previous sign with "+"
         */
        char prevSign = '+';
        int n = s.length();
        char[] arr = s.toCharArray();
        Deque<Integer> operandStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = arr[i];
            /**
             * Don't add the following skip code, Think about case: s = " 3/2 "
             * if (arr[i] == ' ') {
             *   continue;
             * }
             */
            /**
             * e.g. s = "0-2147483647"
             */
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            }
            /**
             * e.g. s = "1-1+1"
             * e.g. s = " 3/2 "
             */
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == (n - 1)) {
                if (prevSign == '+') {
                    operandStack.push(num);
                }
                if (prevSign == '-') {
                    operandStack.push(-num);
                }
                if (prevSign == '*') {
                    operandStack.push(operandStack.pop() * num);
                }
                if (prevSign == '/') {
                    operandStack.push(operandStack.pop() / num);
                }
                prevSign = c;
                num = 0;
            }
        }

        int res = 0;
        while (!operandStack.isEmpty()) {
            res += operandStack.pop();
        }
        return res;
    }
}
