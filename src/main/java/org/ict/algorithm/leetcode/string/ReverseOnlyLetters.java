package org.ict.algorithm.leetcode.string;

/**
 * Given a string s, reverse the string according to the following rules:
 *
 * All the characters that are not English letters remain in the same position.
 * All the English letters (lowercase or uppercase) should be reversed.
 * Return s after reversing it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ab-cd"
 * Output: "dc-ba"
 * Example 2:
 *
 * Input: s = "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 * Example 3:
 *
 * Input: s = "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of characters with ASCII values in the range [33, 122].
 * s does not contain '\"' or '\\'.
 * @author sniper
 * @date 2022/8/4
 * LC917
 */
public class ReverseOnlyLetters {

    public static void main(String[] args) {
        /*
        char a = 'a';
        char z = 'z';
        char A = 'A';
        char Z = 'Z';
        System.out.println((int)a);
        System.out.println((int)z);
        System.out.println((int)A);
        System.out.println((int)Z);
        System.out.println(Character.isLetter('!'));
         */
        String s = "Test1ng-Leet=code-Q!";
        System.out.println(reverseOnlyLetters(s));
    }

    public static String reverseOnlyLetters(String s) {
        char[] temp = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            char x = s.charAt(i);
            char y = s.charAt(j);
            if (Character.isLetter(x) && Character.isLetter(y)) {
                //swap
                char c = x;
                temp[i] = y;
                temp[j] = c;
                i++;
                j--;
            } else if (Character.isLetter(x) && !Character.isLetter(y)) {
                j--;
            } else if (!Character.isLetter(x) && Character.isLetter(y)) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return new String(temp);
    }
}
