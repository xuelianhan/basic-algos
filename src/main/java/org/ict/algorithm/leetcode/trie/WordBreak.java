package org.ict.algorithm.leetcode.trie;

import java.util.*;

/**
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 *
 * @author sniper
 * @date 08 Nov, 2022
 * LC139
 */
public class WordBreak {

    public static void main(String[] args) {
        String s = "catsandog";
        String[] dict = {"cats","dog","sand","and","cat"};
        wordBreak(s, new ArrayList<>(Arrays.asList(dict)));
    }

    /**
     * Recursive solution with Memorization.
     * You can subscribe Huahua's channel on YT.
     * @author Huahua
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<String, Boolean> memo = new HashMap<>();
        return wordBreak(s, memo, dict);
    }

    /**
     * Time Complexity O(2^N)
     * Space Complexity O(2^N)
     *
     * The most confused thing is that it allows multiple splits and words in the dict can be used repeatedly.
     * How can we split? from left to right? If we split at the first place, then how to deal with the rest part?
     *
     * e.g. s = "catsandog", dict = ["cats","dog","sand","and","cat"]
     *
     * inDict("atsandog") && wordBreak("c"); --> false, because "atsandog" is not in the dict
     * inDict("tsandog") && wordBreak("ca"); --> false, because "tsandog" is not in the dict
     * inDict("sandog") && wordBreak("cat"); --> false, because "sandog" is not in the dict
     * inDict("andog") && wordBreak("cats"); --> false, because "andog" is not in the dict
     * inDict("ndog") && wordBreak("catsa"); --> false, because "ndog" is not in the dict
     * inDict("dog") && wordBreak("catsan"); --> "dog" in the dict, recursive to break "catsan"
     *                  inDict("atsan") && wordBreak("c"); --> false, because "astan" is not in the dict
     *                  inDict("tsan") && wordBreak("ca"); --> false, because "tsan" is not in the dict
     *                  inDict("san") && wordBreak("cat"); --> false, because "san" is not in the dict
     *                  inDict("an") && wordBreak("cats"); --> false, because "an" is not in the dict
     *                  inDict("n") && wordBreak("catsa"); --> false, because "n" is not in the dict
     *                  return wordBreak("catsan"); --> false
     * inDict("og") && wordBreak("catsand"); --> false, because "og" is not in the dict
     * inDict("g") && wordBreak("catsando"); --> false, because "g" is not in the dict
     * return wordBreak("catsandog");
     *
     * @param s
     * @param memo
     * @param dict
     * @return
     */
    public static boolean wordBreak(String s, Map<String, Boolean> memo, Set<String> dict) {
        /**
         * Return directly if s has existed in the memo.
         */
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        /**
         * If dict contains s, we can set s as found in the memo, then return true.
         */
        if (dict.contains(s)) {
            memo.put(s, true);
            return true;
        }
        /**
         * Split string from left to right.
         */
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);
            System.out.println("inDict(" + right + ") && wordBreak(" + left + ")");
            if (dict.contains(right) && wordBreak(left, memo, dict)) {
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        return false;
    }
}
