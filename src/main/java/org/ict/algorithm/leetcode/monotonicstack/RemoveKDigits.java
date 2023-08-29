package org.ict.algorithm.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 *
 *
 *
 * Example 1:
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 * Example 2:
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200.
 * Note that the output must not contain leading zeroes.
 *
 * Example 3:
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 *
 *
 * Constraints:
 * 1 <= k <= num.length <= 10^5
 * num consists of only digits.
 * num does not have any leading zeros except for the zero itself.
 * @author sniper
 * @date 28 Aug 2023
 * LC402, Medium
 */
public class RemoveKDigits {

    public static void main(String[] args) {
        //String num = "1432219";
        //int k = 3;

        String num = "10200";
        int k = 1;

        RemoveKDigits instance = new RemoveKDigits();
        String res = instance.removeKdigits(num, k);
        System.out.println(res);
    }


    /**
     * Understanding the following solution
     * @param num
     * @param k
     * @return
     */
    public String removeKdigitsV2(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }

        LinkedList<Character> stack = new LinkedList<>();
        for (char ch : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peekLast() > ch) {
                stack.pollLast();
                k--;
            }
            stack.addLast(ch);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }

        StringBuilder res = new StringBuilder();
        for (char ch : stack) {
            if (ch == '0' && res.length() == 0) {
                continue;
            }
            res.append(ch);
        }

        return res.length() == 0 ? "0" : res.toString();
    }

    /**
     * Understanding the following solution
     * @param num
     * @param k
     * @return
     */
    public String removeKdigitsV1(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }

        LinkedList<Character> stack = new LinkedList<>();
        for (char ch : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peekLast() > ch) {
                stack.pollLast();
                k--;
            }
            stack.addLast(ch);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            char ch = stack.removeFirst();
            if (ch == '0' && res.length() == 0) {
                continue;
            }
            res.append(ch);
        }

        return res.length() == 0 ? "0" : res.toString();
    }

    /**
     * Understanding the following solution
     * Monotonic Stack Solution
     * Time Cost 9ms
     *
     * Make the number on the high digit as small as possible in order to make the whole remaining number as small as possible
     * e.g. num = "1432219", k = 3
     * i:0, k:3, stack:1
     * i:1, k:3, stack:1,4
     * i:2, k:2, stack:1,3
     * i:3, k:1, stack:1,2
     * i:4, k:1, stack:1,2,2
     * i:5, k:0, stack:1,2,1
     * i:6, k:0, stack:1,2,1,9
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        /**
         * e.g. num = "10", k = 2
         */
        if (num.length() == k) {
            return "0";
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > ch) {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }

        /**
         * e.g. num = "1234", k = 2
         * so we pop 4 and 3 out of the stack, we get "12" at last.
         */
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            /**
             * Notice using removeLast here, not remove() or removeFirst()
             * e.g. stack:"0200", the left side is last, the right side is first,
             * viewing "0200" from the right side.
             */
            char ch = stack.removeLast();
            /**
             * Skip the leading '0', e.g.num = "10200", k = 1
             * stack: "0200"
             */
            if (ch == '0' && res.length() == 0) {
                continue;
            }
            res.append(ch);
        }

        return res.length() == 0 ? "0" : res.toString();
    }
}
