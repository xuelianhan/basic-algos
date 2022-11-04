package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', and 'u',
 * and they can appear in both cases.
 *
 * Example 1:
 *
 * Input: s = "hello"
 * Output: "holle"
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: "leotcede"
 *
 * Input: s = "aA"
 * Output: "Aa"
 *
 * Input: s = "A man, a plan, a canal: Panama"
 * //"a mAn, a plan, a canal: Panama"
 * Output: "a man, a plan, a canal: PanamA"
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 10^5
 * s consist of printable ASCII characters.
 *
 * @author sniper
 * @date 2021/12/22 12:46 PM
 * LC345
 */
public class ReverseVowelsOfString {

    public static void main(String[] args) {
        //String s = "a.";
        //String s = "A man, a plan, a canal: Panama";
        //String s = "hello";
        //String s = "leetcode";
        String s = "race car";
        String result = reverseVowels(s);
        System.out.println(result);
    }

    public static String reverseVowelsV1(String s) {
        Set<Character> vowelSet = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] a = s.toCharArray();
        int len = a.length;
        int start = 0;
        int end = len - 1;
        while (start < end) {
            while (start < end && !vowelSet.contains(a[start])) {
                start++;
            }
            while (start < end && !vowelSet.contains(a[end])) {
                end--;
            }
            char temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            start++;
            end--;
        }
        return new String(a);
    }

    /**
     * Two Pointer Solution
     * In the inner while loop,
     * Don't forget the condition "start less than end"
     * while incrementing start and decrementing end.
     * Google phone interview question.
     * @param s
     * @return
     */
    public static String reverseVowels(String s) {
        Set<Character> vowelSet = new HashSet<>();
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');
        vowelSet.add('A');
        vowelSet.add('E');
        vowelSet.add('I');
        vowelSet.add('O');
        vowelSet.add('U');
        char[] a = s.toCharArray();
        int len = a.length;
        int start = 0;
        int end = len - 1;
        while (start < end) {
            while (start < end && !vowelSet.contains(a[start])) {
                start++;
            }
            while (start < end && !vowelSet.contains(a[end])) {
                end--;
            }
            char temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            start++;
            end--;
        }
        return new String(a);
    }

}
