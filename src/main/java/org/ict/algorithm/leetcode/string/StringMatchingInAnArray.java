package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of string words.
 * Return all strings in words which is substring of another word in any order.
 *
 * String words[i] is substring of words[j],
 * if can be obtained removing some characters to left and/or right side of words[j].
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["mass","as","hero","superhero"]
 * Output: ["as","hero"]
 * Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
 * ["hero","as"] is also a valid answer.
 * Example 2:
 *
 * Input: words = ["leetcode","et","code"]
 * Output: ["et","code"]
 * Explanation: "et", "code" are substring of "leetcode".
 * Example 3:
 *
 * Input: words = ["blue","green","bu"]
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 30
 * words[i] contains only lowercase English letters.
 * It's guaranteed that words[i] will be unique.
 * @author sniper
 * @date 2022/8/12
 * LC1408
 */
public class StringMatchingInAnArray {

    public static void main(String[] args) {
        String[] words = {"blue","green","bu"};
        List<String> result = stringMatching(words);
        System.out.println(result);
    }

    public static List<String> stringMatching(String[] words) {
        Set<String> list = new HashSet<>();
        for(int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                if (words[i].length() > words[j].length()) {
                    if (words[i].contains(words[j])) {
                        list.add(words[j]);
                    }
                } else {
                    if (words[j].contains(words[i])) {
                        list.add(words[i]);
                    }
                }
            }
        }
        return new ArrayList<>(list);
    }
}
