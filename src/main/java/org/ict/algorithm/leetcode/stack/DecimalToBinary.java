package org.ict.algorithm.leetcode.stack;

import java.util.Stack;

/**
 * Convert positive integer to binary
 * @author sniper
 * @date 12 Feb, 2022
 */
public class DecimalToBinary {


    public static void main(String[] args) {
        /**
         * 1010
         */
        int num = 10;
        decimalToBinary(num);
    }


    public static void decimalToBinary(int num) {
        Stack<Integer> stack = new Stack<>();

        while (num > 0) {
            stack.push( num % 2);
            num = num / 2;
        }

        while (!stack.isEmpty()) {
            Integer x = stack.pop();
            System.out.print(x);
        }
    }
}
