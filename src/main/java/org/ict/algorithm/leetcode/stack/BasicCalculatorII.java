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
        //String s = "0-2147483647";
        //String s = "1*2-1";
        //String s = "1-1*2";
        //String s = "1-1*2*3";
        //String s = "1-1+1";
        String s = " 36/2 ";

        BasicCalculatorII instance = new BasicCalculatorII();
        int result = instance.calculate(s);
        System.out.println(result);
    }

    public int calculateV1(String s) {
        return 0;
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
        int num = 0;
        int n = s.length();
        char prevSign = '+';
        Deque<Integer> operand = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            /**
             * Don't add the following skip code, Think about case: s = " 3/2 "
             * if (arr[i] == ' ') {
             *   continue;
             * }
             */
            /**
             * e.g. s = "0-2147483647"
             */
            if (Character.isDigit(arr[i])) {
                num = 10 * num + arr[i] - '0';
            }
            /**
             * e.g. s = "1-1+1"
             * e.g. s = " 3/2 "
             */
            if (arr[i] == '+' || arr[i] == '-' || arr[i] == '*' || arr[i] == '/' || i == (n - 1)) {
                if (prevSign == '+') {
                    operand.push(num);
                }
                if (prevSign == '-') {
                    operand.push(-num);
                }
                if (prevSign == '*') {
                    operand.push(operand.pop() * num);
                }
                if (prevSign == '/') {
                    operand.push(operand.pop() / num);
                }
                prevSign = arr[i];
                num = 0;
            }
        }

        int res = 0;
        while (!operand.isEmpty()) {
            res += operand.pop();
        }
        return res;
    }
}
