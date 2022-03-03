package org.ict.algorithm.leetcode.string;

/**
 * You are given an array of strings words and a string pref.
 *
 * Return the number of strings in words that contain pref as a prefix.
 *
 * A prefix of a string s is any leading contiguous substring of s.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["pay","attention","practice","attend"], pref = "at"
 * Output: 2
 * Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".
 * Example 2:
 *
 * Input: words = ["leetcode","win","loops","success"], pref = "code"
 * Output: 0
 * Explanation: There are no strings that contain "code" as a prefix.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length, pref.length <= 100
 * words[i] and pref consist of lowercase English letters.
 * @author sniper
 * @date 03 Mar, 2022
 *
 * LC2185
 */
public class CountingWordsWithGivenPrefix {

    public int prefixCount(String[] words, String pref) {
        if (null == words || words.length == 0 || null == pref || pref == "") {
            return 0;
        }
        int count = 0;
        for (String s : words) {
            if (s.startsWith(pref)) {
                count++;
            }
        }
        return count;
    }
}
