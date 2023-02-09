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

    public int calculateV3(String s) {
        return 0;
    }

    /**
     *
     *
     * Time Cost 5ms
     *
     * e.g s = "-(12 + (2+1))"
     * i:0, c:'-', res=res+sign*num=0, sign:-1, num:0, sign:-1
     * i:1, c:'(', stack:-1,0, res:0, sign:1
     * i:2, c:'1', num:1
     * i:3, c:'2', num:12
     * i:4, c:' '
     * i:5, c:'+', res=res+sign*num=0+1*12=12, num:0, sign:1
     * i:6, c:' '
     * i:7, c:'(', stack:1,12,-1,0, res:0, sign:1
     * i:8, c:'2', num:2
     * i:9, c:'+', res=res+sign*num=0+1*2=2, num:0, sign:1
     * i:10,c:'1', num:1
     * i:11,c:')', res=res+sign*num=2+1=3, num:0, stack:1,12,-1,0, res=res*pop=3*1=3, stack:12,-1,0, res=res+pop=3+12=15, stack:-1,0
     * i:12,c:')', res=res+sign*num=15+1*0=15, num:0, stack:-1,0, res=res*pop=15*(-1)=-15, res=res+pop=-15+0=-15, stack:empty
     * end-for-loop
     * res=res+sign*num=-15+1*0=-15
     * return res:-15
     * -----------------------------------------------------
     * e.g. s = "1 + 1"
     * i:0, c:'1', num:1
     * i:1, c:' '
     * i:2, c:'+', res=res+sign*num=0+1*1=1, num:0, sign:1
     * i:3, c:' '
     * i:4, c:'1', num:1
     * for-loop-end
     * num==1, res=res+sign*num=1+1*1=2
     * return res:2
     *
     * @param s
     * @return
     */
    public int calculateV2(String s) {
        int res = 0;
        int num = 0;
        int sign = 1;
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = 10 * num + c - '0';
            } else if (c == '+' || c == '-') {
                res += sign * num;
                num = 0;
                sign = (c == '+' ? 1 : -1);
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res += sign * num;
                num = 0;
                res *= stack.pop();
                res += stack.pop();
            }
        }
        if (num != 0) {
            /**
             * e.g. s = "1 + 1"
             */
            res += sign * num;
        }
        return res;
    }

    /**
     * Understanding the following solution.
     *
     *
     * @param s
     * @return
     * @author HelloWorld123456
     * @see <a href="https://leetcode.com/problems/basic-calculator/solutions/62362/java-easy-version-to-understand/"></a>
     */
    public int calculateV1(String s) {
        int n = s.length();
        int sign = 1;
        int res = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                /**
                 * A little trick here, so we don't need backward pointer-i,
                 * please compare it in method calculate.
                 */
                while ((i + 1) < n && Character.isDigit(s.charAt(i + 1))) {
                    num = 10 * num + s.charAt(i + 1) - '0';
                    i++;
                }
                res += sign * num;
            } else if ('+' == s.charAt(i)) {
                sign = 1;
            } else if ('-' == s.charAt(i)) {
                sign = -1;
            } else if ('(' == s.charAt(i)) {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (')' == s.charAt(i)) {
                res = res* stack.pop() + stack.pop();
            }
        }

        return res;
    }


    /**
     * Understanding the following solution.
     *
     * Time Cost 6ms
     *
     * e.g. s = "-(12 + (2+1))"
     * i:0, c:'-', sign:-1
     * i:1, c:'(', res:0, sign:-1, stack:-1,0, res:0, sign:1
     * i:2, c:'1', res:0, num:1, i:2 --> i:3, num:12, i:3 --> i:4, res=res+sign*num=0+1*12=12, i-- --> i:3, i++ --> i:4
     * i:4, c:' '
     * i:5, c:'+', sign:1
     * i:6, c:' '
     * i:7, c:'(', res:12, sign:1, stack:1,12,-1,0, res:0, sign:1
     * i:8, c:'2', num:2, i:9, res=res+sign*num=0+2=2, i-- --> i:8, i++ --> i:9
     * i:9, c:'+', sign:1
     * i:10,c:'1', num:1, i:11, res=res+sign*num=2+1=3, i-- -->i:10, i++ --> i:11
     * i:11,c:')', stack:1,12,-1,0, res=res*pop=3*1=3, res=res+pop=3+12=15, stack:-1,0
     * i:12,c:')', stack:-1,0, res=res*pop=15*(-1)=-15, res=res+pop=-15+0=-15
     * return res:-15
     *
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
                /**
                 * Be sure to backward one step of pointer-i
                 */
                i--;
            } else if (c == '(') {
                /**
                 * Store current result first, then save significant.
                 */
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                /**
                 * Multiply significant first, then add number to result.
                 */
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
