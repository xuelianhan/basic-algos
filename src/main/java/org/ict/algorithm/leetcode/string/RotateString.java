package org.ict.algorithm.leetcode.string;

/**
 * Given two strings s and goal,
 * return true if and only if s can become goal after some number of shifts on s.
 * A shift on s consists of moving the leftmost character of s to the rightmost position.
 *
 * For example, if s = "abcde", then it will be "bcdea" after one shift.
 * Example 1:
 *
 * Input: s = "abcde", goal = "cdeab"
 * Output: true
 * Example 2:
 *
 * Input: s = "abcde", goal = "abced"
 * Output: false
 *
 * Constraints:
 * 1 <= s.length, goal.length <= 100
 * s and goal consist of lowercase English letters.
 * @author sniper
 * @date 2022/3/10
 * LC796
 */
public class RotateString {

    public static void main(String[] args) {
        String s = "abcde";
        String goal = "abced";
        boolean result = rotateString(s, goal);
        System.out.println(result);
    }

    public static boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.equals(goal)) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            String a = s.substring(0, (i + 1));
            String b = s.substring((i + 1), s.length());
            String c = b + a;
            if (c.equals(goal)) {
                return true;
            }
        }
        return false;
    }
}
