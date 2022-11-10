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
        wordBreakV2(s, new ArrayList<>(Arrays.asList(dict)));
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean end;
    }

    public TrieNode buildTrie(Set<String> dict) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            TrieNode p = root;
            for (char ch : word.toCharArray()) {
                int i = ch - 'a';
                if (p.children[i] == null) {
                    p.children[i] = new TrieNode();
                }
                p = p.children[i];
            }
            p.end = true;
        }
        return root;
    }

    /**
     *
     * Trie-DFS Solution
     * Time cost 5ms
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreakV3(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, Boolean> memo = new HashMap<>();
        TrieNode root = buildTrie(dict);

        return wordBreakV3(s.toCharArray(), memo, root, 0);
    }

    public boolean wordBreakV3(char[] arr, Map<Integer, Boolean> memo, TrieNode root, int pos) {
        if (pos == arr.length) {
            return true;
        }
        if (memo.containsKey(pos)) {
            return memo.get(pos);
        }

        TrieNode p = root;
        boolean found = false;
        for (int i = pos; i < arr.length; i++) {
            p = p.children[arr[i] - 'a'];
            if (null == p) {
                break;
            }
            if (p.end) {
                found = wordBreakV3(arr, memo, root, i + 1);
            }

            if (found) {
                memo.put(pos, true);
                return true;
            }
        }
        memo.put(pos, false);
        return false;
    }


    /**
     * Time cost 10ms
     * Recursive solution with Memorization.
     * You can subscribe Huahua's channel on YT.
     * @author Huahua
     */
    public static boolean wordBreakV2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<String, Boolean> memo = new HashMap<>();
        return wordBreakV2(s, memo, dict);
    }


    /**
     * Time cost 15ms
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreakV1(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<String, Boolean> memo = new HashMap<>();
        return wordBreakV1(s, memo, dict);
    }

    /**
     * Time Complexity O(2^N)
     * Space Complexity O(2^N)
     *
     * The most confused thing is that it allows multiple splits and words in the dict can be used repeatedly.
     * How can we split? from left to right? If we split at the first place, then how to deal with the rest part?
     * When we encounter this situation that we don't know how to go on, one workable option is to using recursion in for-loop.
     * One little tricky technique using here is to check the longer right part in the dict firstly, then we recursively invoke
     * the break function. If we check the shorter left in the dict firstly, then we invoke the break function, in this situation,
     * we need deeper recursive invoke. But it's still working.
     *
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
     *                  put ("catsan", false) into the memo;
     *                  return wordBreak("catsan"); --> false
     * inDict("og") && wordBreak("catsand"); --> false, because "og" is not in the dict
     * inDict("g") && wordBreak("catsando"); --> false, because "g" is not in the dict
     * put ("catsandog", false) into the memo;
     * return wordBreak("catsandog");
     *
     * The time complexity depends on how many nodes the recursion tree has.
     * In the worst case, let's take an example to see the complexity.
     * e.g. s = "abc"
     *              abc
     *             / |
     *            bc c
     *           /
     *          c
     * if dict contains a, then only bc left for choice, if dict contains b, then only c left.
     * if dict contains ab, then only c left for choice
     * Given an array of length N , there are N - 1 ways/intervals to split it into two parts.
     * Each interval has two choices--split or not.
     * In the worst case, we need to check all the possibilities, which become the 2^(N-1)
     * e.g. s = "abc", there are 4 nodes in total. 4 = 2^(N-1) = 2^(3-1)
     * So time complexity is O(2^N), N is the length of the string.
     *
     * @param s
     * @param memo
     * @param dict
     * @return
     */
    public static boolean wordBreakV2(String s, Map<String, Boolean> memo, Set<String> dict) {
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
            /**
             * both checking right or left firstly are ok.
             * If we check the longer one(the right part) firstly,
             * so the recursive wordBreak will need less times to run,
             * because the right part at first is shorter than left part.
             */
            if (dict.contains(right) && wordBreakV2(left, memo, dict)) {
            //if (dict.contains(left) && wordBreakV2(right, memo, dict)) {
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        //System.out.println("memo:" + memo);
        return false;
    }

    public static boolean wordBreakV1(String s, Map<String, Boolean> memo, Set<String> dict) {
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
            /**
             * both checking right or left firstly are ok.
             * If we check the longer one(the right part) firstly,
             * so the recursive wordBreak will need less times to run,
             * because the right part at first is shorter than left part.
             */
            //if (dict.contains(right) && wordBreakV1(left, memo, dict)) {
            if (dict.contains(left) && wordBreakV1(right, memo, dict)) {
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        //System.out.println("memo:" + memo);
        return false;
    }
}
