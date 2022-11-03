package org.ict.algorithm.leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.
 *
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order.
 * Each element can be selected at most once.
 *
 * Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
 *
 * A palindrome is a string that reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["lc","cl","gg"]
 * Output: 6
 * Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
 * Note that "clgglc" is another longest palindrome that can be created.
 * Example 2:
 *
 * Input: words = ["ab","ty","yt","lc","cl","ab"]
 * Output: 8
 * Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
 * Note that "lcyttycl" is another longest palindrome that can be created.
 * Example 3:
 *
 * Input: words = ["cc","ll","xx"]
 * Output: 2
 * Explanation: One longest palindrome is "cc", of length 2.
 * Note that "ll" is another longest palindrome that can be created, and so is "xx".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 10^5
 * words[i].length == 2
 * words[i] consists of lowercase English letters.
 * @author sniper
 * @date 03 Nov, 2022
 * LC2131
 */
public class LongestPalindromeConcatenatingTwoLetterWords {

    public static void main(String[] args) {
        String[] words = {"ab","ty","yt","lc","cl","ab", "ba", "ty", "aa", "aa", "bb", "ab", "ef"};//not pass
        //String[] words = {"cc","ll","xx"};//pass
        //String[] words = {"lc","cl","gg"};//pass
        //String[] words = {"ab","ty","yt","lc","cl","ab"};//pass
        int result = wrongAnswer(words);
        System.out.println(result);
    }

    /**
     * 2 letter words can be of 2 types:
     *
     * Where both letters are same
     * Where both letters are different
     * Based on the above information:
     *
     * If we are able to find the mirror of a word, ans += 4
     * The variable unpaired is used to store the number of unpaired words with both letters same.
     * Unpaired here means a word that has not found its mirror word.
     * At the end if unpaired same letter words are > 0, we can use one of them as the center of the palindromic string.
     * @param words
     * @return
     */
    public static int longestPalindrome(String[] words) {
        int res = 0;
        Map<String, Integer> freq = new HashMap<>();
        int unpaired = 0;
        for (String word : words) {
            if (!freq.containsKey(word)) {
                freq.put(word, 0);
            }
            if (word.charAt(0) == word.charAt(1)) {
                if (freq.get(word) > 0) {
                    unpaired--;
                    freq.put(word, freq.get(word) - 1);
                    res += 4;
                } else {
                    freq.put(word, freq.get(word) + 1);
                    unpaired++;
                }
            } else {
                String mirror = new String(new char[]{word.charAt(1), word.charAt(0)});
                if (freq.containsKey(mirror) && freq.get(mirror) > 0) {
                    res += 4;
                    freq.put(mirror, freq.get(mirror) - 1);
                } else {
                    freq.put(word, freq.get(word) + 1);
                }
            }
        }
        if (unpaired > 0) {
            res += 2;
        }
        return res;
    }

    /**
     * String[] words = {"ab","ty","yt","lc","cl","ab", "ba", "ty", "aa", "aa", "bb", "ab", "ef"};
     * -------
     * ef:1
     * fe:0
     * -------1*4
     * ab:3
     * ba:1
     * -------1*4
     * ty:2
     * yt:1
     * -------1*4
     * lc:1
     * cl:1
     * -------2*2
     * aa:2
     * bb:1
     *
     * @param words
     * @return
     */
    public static int wrongAnswer(String[] words) {
        int res = 0;
        Map<String, Integer> freq = new HashMap<>();
        int uniqueMaxLen = 0;
        int validPairCnt = 0;
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
            if (word.charAt(0) == word.charAt(1)) {
                int uniqueCnt = freq.getOrDefault(word,0);
                uniqueMaxLen = Math.max(uniqueMaxLen, uniqueCnt);
            } else {
                String mirror = new String(new char[]{word.charAt(1), word.charAt(0)});
                int mirrorCnt = freq.getOrDefault(mirror, 0);
                validPairCnt += Math.min(mirrorCnt, freq.getOrDefault(word, 0));
            }
        }
        res = 4 * validPairCnt + 2 * uniqueMaxLen;
        return res;
    }


}
