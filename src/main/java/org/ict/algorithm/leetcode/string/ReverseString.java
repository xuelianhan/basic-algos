package org.ict.algorithm.leetcode.string;

/**
 * Write a function that reverses a string.
 * The input string is given as an array of characters s.
 *
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is a printable ascii character.
 *
 * @author sniper
 * LC344
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] s = "Hannah".toCharArray();
        System.out.println(s);
        reverseString(s);
        System.out.println(s);
    }

    public static void reverseString(char[] s) {
        char[] temp = new char[1];
        int i = s.length - 1;
        int j = 0;
        while (i >= j) {
            temp[0] = s[i];
            s[i--] = s[j];
            s[j++] = temp[0];
        }
    }
}
