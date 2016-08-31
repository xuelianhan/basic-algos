package org.ict.algorithm.util;

public class Parentheses {

    private static final char LEFT_PARENTHSIS = '(';

    private static final char RIGHT_PARENTHSIS = '(';

    private static final char LEFT_BRACE = '{';

    private static final char RIGHT_BRACE = '}';

    private static final char LEFT_BRACKET = '[';

    private static final char RIGHT_BRACKET = ']';

    public static boolean isBanlenced(String s) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == LEFT_PARENTHSIS) {
                stack.push(LEFT_PARENTHSIS);
            }
            if (s.charAt(i) == LEFT_BRACE) {
                stack.push(LEFT_BRACE);
            }
            if (s.charAt(i) == LEFT_BRACKET) {
                stack.push(LEFT_BRACKET);
            }

            if (s.charAt(i) == RIGHT_PARENTHSIS) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != LEFT_PARENTHSIS) {
                    return false;
                }
            }

            if (s.charAt(i) == RIGHT_BRACE) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != LEFT_BRACE) {
                    return false;
                }
            }

            if (s.charAt(i) == RIGHT_BRACKET) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != LEFT_BRACKET) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        In in = new In();
        String s = in.readAll().trim();
        StdOut.println(isBanlenced(s));
    }

}
