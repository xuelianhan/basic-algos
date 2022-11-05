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
        String[] words = {"dd", "dd", "aa"};//pass, expected 6
        //String[] words = {"ab","ty","yt","lc","cl","ab", "ba", "ty", "aa", "aa", "bb", "ab", "ef"};//pass, expected 18
        //String[] words = {"cc","ll","xx"};//pass, expected 2
        //String[] words = {"lc","cl","gg"};//pass, expected 6
        //String[] words = {"ab","ty","yt","lc","cl","ab"};//pass, expected 8

        //String[] words = {"dd","aa","bb","dd","aa","dd","bb","dd","aa","cc","bb","cc","dd","cc"};//pass, expected 22
        //String[] words = {"bb", "bb"};//pass, expected 4
        int result = longestPalindrome(words);
        System.out.println(result);
    }

    public int longestPalindromeV2(String[] words) {
        int[][] counting = new int[26][26];
        int max = 0;
        for (String word : words) {
            int a = word.charAt(0) - 'a';
            int b = word.charAt(1) - 'a';
            if (counting[b][a] > 0) {
                max += 4;
                counting[b][a]--;
            } else {
                counting[a][b]++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (counting[i][i] > 0) {
                max += 2;
                break;
            }
        }
        return max;
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
    public int longestPalindromeV1(String[] words) {
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
     * -------2*2 + 1*2
     * aa:2
     * bb:1
     *
     * String[] words = {"dd","aa","bb","dd","aa","dd","bb","dd","aa","cc","bb","cc","dd","cc"}
     *
     * Please notice that we can only concat two words other than insert one word into another
     * e.g.
     * dd dd dd dd dd --> dd dd aa aa aa dd dd --> dd dd aa bb bb bb aa dd dd --> dd dd aa bb cc cc cc bb aa dd dd
     * 5 -> 5 + 3 - 1 --> 7 + 3 - 1 --> 9 + 3, the last one not need to subtract 1
     * wrong:
     * dd dd dd dd dd --> dd dd d aa aa aa d dd dd, this sequence doesn't satisfy the concat words, because it split
     * original word, such as dd --> daad.
     *
     *
     * -------
     * dd:5
     * -------
     * aa:3
     * -------
     * bb:3
     * -------
     * cc:3
     * -------
     *
     *
     * e.g.
     * dd dd aa
     * 1  0  1
     * @param words
     * @return
     */
    public static int longestPalindrome(String[] words) {
        Map<String, Integer> freq = new HashMap<>();
        /**
         * Count pair numbers such as aa,aa,bb
         * e.g. words = ["aa", "aa", "bb"]
         * maxUniqueCnt = 2, unPairedCnt = 1
         * because "aa" appears two times, "bb" appears only one.
         */
        int maxUniqueCnt = 0;
        int unPairedCnt = 0;

        /**
         * Count the valid pair numbers such as ef,fe
         * e.g. words = ["ef", "fe"]
         * validPairCnt = 1, because "ef" and "fe" compose a valid pair.
         */
        int validPairCnt = 0;
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
            if (word.charAt(0) == word.charAt(1)) {
                if (freq.get(word) > 1) {
                    /**
                     * e.g. words = ["bb", "bb"]
                     * when we meet the first "bb",unPairedCnt++,
                     * when we meet the second "bb", they compose a valid pair of uniqueCnt,
                     * So an addition of two to maxUniqueCnt, at the same time
                     * the first "bb" is no longer unpaired, we need to subtract one at unPairedCnt.
                     */
                    maxUniqueCnt += 2;
                    freq.put(word, freq.get(word) - 2);
                    unPairedCnt--;
                } else if (freq.get(word) == 1) {
                    /**
                     * when we meet word at first time, unPairedCnt need to increment one.
                     */
                    unPairedCnt++;
                }
                /**
                 * Never has the situation of freq.get(word) < 1, because we have added one in the above:
                 * freq.put(word, freq.getOrDefault(word, 0) + 1);
                 */
            } else {
                String mirror = new String(new char[]{word.charAt(1), word.charAt(0)});
                int mirrorCnt = freq.getOrDefault(mirror, 0);
                /**
                 * e.g.
                 * words = ["ef", "fe"]
                 * when iterated at "fe", its mirror "ef" has appeared once already.
                 * "ef" and "fe" compose a valid pair.
                 * To prevent duplicated counting, we also need to subtract one from their frequency count.
                 */
                if (mirrorCnt > 0) {
                    validPairCnt++;
                    freq.put(mirror, freq.get(mirror) - 1);
                    freq.put(word, freq.get(word) - 1);
                }
            }
        }
        //System.out.println("validPairCnt:" + validPairCnt + ", maxUniqCnt:" + maxUniqueCnt + ", unPairedCnt:" + unPairedCnt);
        return (4 * validPairCnt + 2 * maxUniqueCnt) + (unPairedCnt > 0 ? 2 : 0);
    }


}
