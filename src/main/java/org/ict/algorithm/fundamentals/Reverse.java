package org.ict.algorithm.fundamentals;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

public class Reverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();

        StdOut.print("Input: ");
        while(!StdIn.isEmpty()) {
            Integer i = StdIn.readInt();
            stack.push(i);
            StdOut.print(i + " ");
        }
        StdOut.println("\n");

        StdOut.print("Output: ");
        for (int i : stack) {
            StdOut.print(i + " ");
        }
        StdOut.println("\n");
    
    }

}
