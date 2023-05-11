package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.Collections;

/**
 * Given an input string s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters.
 * The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 *
 * Example 3:
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Constraints:
 * 1 <= s.length <= 10^4
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 *
 *
 * Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 * @author sniper
 * @date 2022/8/17
 * LC151, Medium, frequency=36
 */
public class ReverseWordsInString {

    /**
     * Time Cost 9ms
     * Follow-up:
     * If the string data type is mutable in your language,
     * can you solve it in-place with O(1) extra space?
     * ------------------------------
     * class Solution:
     *     def reverseWords(self, s: str) -> str:
     *         return ' '.join(reversed(s.split()))
     *
     * @param s
     * @return
     */
    public String reverseWordsV2(String s) {
       String[] words = s.trim().split("\\s+");
       Collections.reverse(Arrays.asList(words));
       return String.join(" ", words);
    }

    public String reverseWords(String s) {
        String[] arr = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
