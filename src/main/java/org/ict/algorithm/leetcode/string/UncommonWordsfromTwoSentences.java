package org.ict.algorithm.leetcode.string;

import java.util.*;

/**
 * A sentence is a string of single-space separated words where each word consists only of lowercase letters.
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Given two sentences s1 and s2,
 * return a list of all the uncommon words.
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "this apple is sweet", s2 = "this apple is sour"
 * Output: ["sweet","sour"]
 * Example 2:
 *
 * Input: s1 = "apple apple", s2 = "banana"
 * Output: ["banana"]
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 200
 * s1 and s2 consist of lowercase English letters and spaces.
 * s1 and s2 do not have leading or trailing spaces.
 * All the words in s1 and s2 are separated by a single space.
 * @author sniper
 * @date 2022/8/3
 * lc884
 */
public class UncommonWordsfromTwoSentences {

    public static void main(String[] args) {
        String s1 = "this apple is sweet", s2 = "this apple is sour";
        String[] result = uncommonFromSentences(s1, s2);
        System.out.println(Arrays.deepToString(result));
    }

    public static String[] uncommonFromSentences(String s1, String s2) {
        String spaceRegex = "\\s";
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(String item : s1.split(spaceRegex)) {
            set.add(item);
        }
        for(String item : s2.split(spaceRegex)) {
            if (!set.contains(item)) {
                list.add(item);
                set.add(item);
            }
        }
        String[] result = new String[list.size()];
        return list.toArray(result);
    }
}
