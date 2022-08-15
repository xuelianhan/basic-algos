package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a string s containing only lowercase English letters and the '?' character,
 * convert all the '?' characters into lowercase letters
 * such that the final string does not contain any consecutive repeating characters.
 * You cannot modify the non '?' characters.
 *
 * It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.
 *
 * Return the final string after all the conversions (possibly zero) have been made.
 * If there is more than one solution, return any of them.
 * It can be shown that an answer is always possible with the given constraints.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "?zs"
 * Output: "azs"
 * Explanation: There are 25 solutions for this problem.
 * From "azs" to "yzs", all are valid.
 * Only "z" is an invalid modification as the string will consist of consecutive repeating characters in "zzs".
 * Example 2:
 *
 * Input: s = "ubv?w"
 * Output: "ubvaw"
 * Explanation: There are 24 solutions for this problem.
 * Only "v" and "w" are invalid modifications as the strings will consist of consecutive repeating characters in "ubvvw" and "ubvww".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consist of lowercase English letters and '?'.
 * @author sniper
 * @date 2022/8/15
 * LC1576
 */
public class ReplaceAllQuestionMarkAvoidConsecutiveRepeatingCharacters {

    public static void main(String[] args) {
        String s = "ubv??w";
        //String s = "????????ubv????w??";
        //String s = "?zs";
        String result = modifyString(s);
        System.out.println(result);
    }

    public String modifyStringV3(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == '?') {
                // Replace the current '?' with 'a', 'b', or 'c'.
                for (char curr = 'a'; curr <= 'c'; curr++) {
                    boolean prevUnequal = (i - 1 == - 1 || chars[i - 1] != curr);
                    boolean nextUnequal = (i + 1 == chars.length || curr != chars[i + 1]);
                    if (prevUnequal && nextUnequal) {
                        chars[i] = curr;
                    }
                }
            }
        }
        return new String(chars);
    }

    public String modifyStringV2(String s) {
        Set<Character> abcSet = new HashSet<>(Arrays.asList('a','b','c'));
        Set<Character> exceptSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        char prev = '0';
        char next = '0';
        for (int i = 0; i < s.length(); i++) {
            exceptSet.clear();
            char c = s.charAt(i);
            if (c == '?') {
                if (i >= 0 && i < (s.length() - 1)) {
                    next = s.charAt(i+1);
                }
                if (prev != '0') {
                    exceptSet.add(prev);
                }
                if (next != '0' && next != '?') {
                    exceptSet.add(next);
                }
                /**
                 * select anyone character except the ones in exceptSet.
                 */
                Set<Character> diff = new HashSet<>(abcSet);
                diff.removeIf(exceptSet::contains);
                char replaced = diff.iterator().next();
                sb.append(replaced);
                prev = replaced;
            } else {
                sb.append(c);
                prev = c;
            }
        }
        return sb.toString();
    }

    public static String modifyString(String s) {
        char[] arr = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Set<Character> exceptSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        char prev = '0';
        char next = '0';
        for (int i = 0; i < s.length(); i++) {
            exceptSet.clear();
            char c = s.charAt(i);
            if (c == '?') {
                if (i >= 0 && i < (s.length() - 1)) {
                    next = s.charAt(i+1);
                }
                if (prev != '0') {
                    exceptSet.add(prev);
                }
                if (next != '0' && next != '?') {
                    exceptSet.add(next);
                }
                /**
                 * select anyone character except the ones in exceptSet.
                 */
                char replaced = selectOne(arr, exceptSet);
                sb.append(replaced);
                prev = replaced;
            } else {
                sb.append(c);
                prev = c;
            }
        }
        return sb.toString();
    }

    public static char selectOne(char[] arr, Set<Character> exceptSet) {
        char res = '0';
        for (char ch : arr) {
            if (!exceptSet.contains(ch)) {
                return ch;
            }
        }
        return res;
    }
}
