package org.ict.algorithm.leetcode.trie;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 *
 * words.length == indices.length
 * The reference string s ends with the '#' character.
 * For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
 * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
 * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
 * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
 * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
 * Example 2:
 *
 * Input: words = ["t"]
 * Output: 2
 * Explanation: A valid encoding would be s = "t#" and indices = [0].
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * words[i] consists of only lowercase letters.
 * @author sniper
 * @date 14 Nov, 2022
 * LC820
 */
public class ShortEncodingOfWords {

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        //String[] words = {"feipyxx","e"};
        //String[] words = {"me","time"};
        //String[] words = {"p","grah","qwosp"};//expected: qwosp#grah#
        //String[] words = {"ctxdic","c"};
        ShortEncodingOfWords instance = new ShortEncodingOfWords();
        int result = instance.minimumLengthEncoding(words);
        System.out.println(result);
    }

    public int minimumLengthEncodingV1(String[] words) {
        if (words.length == 1) {
            return words[0].length() + 1;
        }
        /**
         * Sort words in descend order by their length.
         */
        Arrays.sort(words, (s1, s2) -> {
            if (s1.length() < s2.length()) {
                return 1;
            } else if (s1.length() > s2.length()) {
                return -1;
            } else {
                return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            /**
             * Notice here use lastIndexOf instead other than indexOf
             * For each word, we search it in the result string and check
             * current word exists in it or not.
             * If we don't find the word, we can append it directly.
             * If we have found the word, we check whether it has string "#" step after it.
             * If there is no # step after the word, we also append this word.
             * plus 1 means the #
             */
            int found = result.lastIndexOf(word);
            if (found < 0 || !"#".equals(result.substring((found + word.length()), (found + word.length() + 1)))) {
                result.append(word);
                result.append("#");
            }
        }
        return result.length();
    }

    /**
     * e.g.words = {"ctxdic", "dictionary", "d", "c"}
     * 1.we sort words by length with descendant order
     *  words = {"dictionary", "ctxdic", "d" "c",};
     * String result = "";
     * i:0, "dictionary" is not in result, so we append "dictionary#" in the result, result:dictionary#
     * i:1, "ctxdic" is not in the result, so we append "ctxdic" in the result, result:dictionary#ctxdic#
     * i:2, "d" is not in the result, so we append "d" in the result, result: dictionary#ctxdic#d#
     * i:3, "c" is in right side of the result:"c#", and it steps after with "#", so we skip it.
     * The final result:dictionary#ctxdic#d#
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        if (words.length == 1) {
            return words[0].length() + 1;
        }
        /**
         * Sort words in descend order by their length.
         */
        Arrays.sort(words, (s1, s2) -> {
            if (s1.length() < s2.length()) {
                return 1;
            } else if (s1.length() > s2.length()) {
                return -1;
            } else {
                return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            /**
             * Notice here use lastIndexOf instead other than indexOf
             * For each word, we search it in the result string and check
             * current word exists in it or not.
             * If we don't find the word, we can append it directly.
             * If we have found the word, we check whether it has string "#" step after it.
             * If there is no # step after the word, we also append this word.
             * plus 1 means the #
             */
            int found = result.lastIndexOf(word);
            if (found < 0 || !"#".equals(result.substring((found + word.length()), (found + word.length() + 1)))) {
                result.append(word);
                result.append("#");
            }
        }
        return result.length();
    }
}
