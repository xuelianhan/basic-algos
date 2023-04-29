package org.ict.algorithm.util;

/**
 * [hanxuelian@ict java]$ java org/ict/algorithm/util/Evaluate
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) 
 *
 * Enter and use Ctrl-D to end your input:
 * @see http://superuser.com/questions/140196/indicating-end-of-standard-input
 * @see http://unix.stackexchange.com/questions/16333/how-to-signal-the-end-of-stdin-input-in-bash
 * 101.0
 *
 * @see http://algs4.cs.princeton.edu/13stacks/Evaluate.java.html
 */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) {

            } else if (s.equals("+")) {
                ops.push(s); 
            } else if (s.equals("-")) {
                ops.push(s); 
            } else if (s.equals("*")) {
                ops.push(s); 
            } else if (s.equals("/")) {
                ops.push(s); 
            } else if (s.equals("%")) {
                ops.push(s); 
            } else if (s.equals("sqrt")) {
                ops.push(s); 
            } else if (s.equals(")")) {
                String op = ops.pop(); 
                Double val = vals.pop();
                if (op.equals("+")) {
                    val = vals.pop() + val; 
                } else if (op.equals("-")) {
                    val = vals.pop() - val;
                } else if (op.equals("*")) {
                    val = vals.pop() * val;
                } else if (op.equals("/")) {
                    val = vals.pop() / val;
                } else if (op.equals("%")) {
                    val = vals.pop() % val;
                } else if (op.equals("sqrt")) {
                    val = Math.sqrt(val);
                }
                vals.push(val);
            } else {
                vals.push(Double.parseDouble(s)); 
            }
        }//end while-loop
        StdOut.println("" + vals.pop());
    }

}
