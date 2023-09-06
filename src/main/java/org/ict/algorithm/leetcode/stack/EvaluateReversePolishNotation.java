package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * It is guaranteed that the given RPN expression is always valid.
 * That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 *
 *
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 *
 * Constraints:
 *
 * 1 <= tokens.length <= 104
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 * @author sniper
 * @date 17 Dec, 2022
 * LC150, Medium, frequency=75
 */
public class EvaluateReversePolishNotation {


    /**
     * Time Cost 6ms
     * -----------------------------------------------
     * class Solution:
     *  def evalRPN(self, tokens: List[str]) -> int:
     *         if len(tokens) == 1:
     *             return int(tokens[0])
     *
     *         stack = []
     *
     *         for s in tokens:
     *             if s == "+":
     *                 number_one = stack.pop()
     *                 number_two = stack.pop()
     *                 stack.append(number_two + number_one)
     *             elif s == "-":
     *                 number_one = stack.pop()
     *                 number_two = stack.pop()
     *                 stack.append(number_two - number_one)
     *             elif s == "*":
     *                 number_one = stack.pop()
     *                 number_two = stack.pop()
     *                 stack.append(number_two * number_one)
     *             elif s == "/":
     *                 number_one = stack.pop()
     *                 number_two = stack.pop()
     *                 stack.append(int(number_two / number_one))
     *             else:
     *                 stack.append(int(s))
     *
     *         return stack.pop()
     * -----------------------------------------------
     * impl Solution {
     *     pub fn eval_rpn(tokens: Vec<String>) -> i32 {
     *         if tokens.len() == 1 {
     *             return tokens[0].parse().unwrap();
     *         }
     *
     *         let mut stack: Vec<i32> = Vec::new();
     *
     *         for s in tokens {
     *             match &s[..] {
     *                 "+" => {
     *                     let number_one = stack.pop().unwrap();
     *                     let number_two = stack.pop().unwrap();
     *                     stack.push(number_two + number_one);
     *                 }
     *                 "-" => {
     *                     let number_one = stack.pop().unwrap();
     *                     let number_two = stack.pop().unwrap();
     *                     stack.push(number_two - number_one);
     *                 }
     *                 "*" => {
     *                     let number_one = stack.pop().unwrap();
     *                     let number_two = stack.pop().unwrap();
     *                     stack.push(number_two * number_one);
     *                 }
     *                 "/" => {
     *                     let number_one = stack.pop().unwrap();
     *                     let number_two = stack.pop().unwrap();
     *                     stack.push(number_two / number_one);
     *                 }
     *                 _ => stack.push(s.parse().unwrap()),
     *             }
     *         }
     *
     *         stack.pop().unwrap()
     *     }
     * }
     * -----------------------------------------------
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            if ("+".equals(s)) {
                int numberOne = stack.pop();
                int numberTwo = stack.pop();
                stack.push(numberTwo + numberOne);
            } else if ("-".equals(s)) {
                int numberOne = stack.pop();
                int numberTwo = stack.pop();
                stack.push(numberTwo - numberOne);
            } else if ("*".equals(s)) {
                int numberOne = stack.pop();
                int numberTwo = stack.pop();
                stack.push(numberTwo * numberOne);
            } else if ("/".equals(s)) {
                int numberOne = stack.pop();
                int numberTwo = stack.pop();
                stack.push(numberTwo / numberOne);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }
}
