package org.ict.algorithm.leetcode.string;

import java.util.Stack;

/**
 * Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.
 *
 * For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
 * Return the resulting string.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "abcdefd", ch = "d"
 * Output: "dcbaefd"
 * Explanation: The first occurrence of "d" is at index 3.
 * Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
 * Example 2:
 *
 * Input: word = "xyxzxe", ch = "z"
 * Output: "zxyxxe"
 * Explanation: The first and only occurrence of "z" is at index 3.
 * Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".
 * Example 3:
 *
 * Input: word = "abcd", ch = "z"
 * Output: "abcd"
 * Explanation: "z" does not exist in word.
 * You should not do any reverse operation, the resulting string is "abcd".
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 250
 * word consists of lowercase English letters.
 * ch is a lowercase English letter.
 * @author sniper
 * @date 04 Mar, 2022
 * LC2000
 */
public class ReversePrefixOfWord {

    public String reversePrefixV2(String word, char ch) {
        char[] c = word.toCharArray();
        int locate = 0;
        for (int i = 0; i < word.length(); i++) {
            if (ch == c[i]) {
                /**
                 * first occurrence of ch
                 */
                locate = i;
                break;
            }
        }
        char[] res = new char[word.length()];
        for (int i = 0; i <= locate; i++) {
            res[i] = c[locate - i];
        }
        for (int i = locate + 1; i < word.length(); i++) {
            res[i] = c[i];
        }
        return String.valueOf(res);
    }

    public String reversePrefix(String word, char ch) {
        if (word.length() == 1) {
            return word;
        }
        StringBuffer sb = new StringBuffer();
        int idx = word.indexOf(Character.toString(ch));
        if (idx < 0) {
            return word;
        }
        sb.append(word.substring(0, idx + 1));
        String first = sb.reverse().toString();
        return first + word.substring(idx + 1);
    }
}
