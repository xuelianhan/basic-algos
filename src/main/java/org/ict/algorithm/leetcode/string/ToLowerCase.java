package org.ict.algorithm.leetcode.string;

/**
 *
 * Given a string s,
 * return the string after replacing every uppercase letter with the same lowercase letter.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Hello"
 * Output: "hello"
 * Example 2:
 *
 * Input: s = "here"
 * Output: "here"
 * Example 3:
 *
 * Input: s = "LOVELY"
 * Output: "lovely"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of printable ASCII characters.
 * @author sniper
 * LC709
 *
 */
public class ToLowerCase {
    public static void main(String[] args) {
        char a = 'a';
        char A = 'A';
        System.out.println((int)a);//97
        System.out.println((int)A);//65

    }

    public String toLowerCase(String s) {
        int i = 0;
        char[] a = s.toCharArray();
        while (i < a.length) {
            char c = a[i];
            if (Character.isUpperCase(c)) {
                char t =Character.toLowerCase(c);
                a[i] = t;
            }
            i++;
        }
        return new String(a);
    }

}
