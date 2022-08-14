package org.ict.algorithm.leetcode.string;

/**
 * You own a Goal Parser that can interpret a string command.
 * The command consists of an alphabet of "G", "()" and/or "(al)" in some order.
 * The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al".
 * The interpreted strings are then concatenated in the original order.
 *
 * Given the string command, return the Goal Parser's interpretation of command.
 *
 *
 *
 * Example 1:
 *
 * Input: command = "G()(al)"
 * Output: "Goal"
 * Explanation: The Goal Parser interprets the command as follows:
 * G -> G
 * () -> o
 * (al) -> al
 * The final concatenated result is "Goal".
 * Example 2:
 *
 * Input: command = "G()()()()(al)"
 * Output: "Gooooal"
 * Example 3:
 *
 * Input: command = "(al)G(al)()()G"
 * Output: "alGalooG"
 *
 *
 * Constraints:
 *
 * 1 <= command.length <= 100
 * command consists of "G", "()", and/or "(al)" in some order.
 * @author sniper
 * @date 14 Aug, 2022
 * LC1678
 */
public class GoalParserInterpretation {

    public static void main(String[] args) {
        String command = "G";
        String result = interpret(command);
        System.out.println(result);
    }


    public static String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length();) {
            char c = command.charAt(i);
            if (c == 'G') {
                sb.append(c);
                i++;
            }
            if (c == '(' && ((i + 1) < command.length())) {
               char next = command.charAt(i+1);
               if (next == 'a') {
                   sb.append("al");
                   i += 4;
               }
               if (next == ')') {
                   sb.append("o");
                   i += 2;
               }
            }
        }
        return sb.toString();
    }
}
