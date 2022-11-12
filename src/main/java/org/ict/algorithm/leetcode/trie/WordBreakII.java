package org.ict.algorithm.leetcode.trie;

import java.util.*;

/**
 * Given a string s and a dictionary of strings wordDict,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * Example 2:
 *
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * @author sniper
 * @date 11 Nov, 2022
 * LC140
 */
public class WordBreakII {

    public static void main(String[] args) {
        String s = "catsanddog";
        String[] dict = {"cat","cats","and","sand","dog"};
        List<String> wordDict = new ArrayList<>(Arrays.asList(dict));
        WordBreakII instance = new WordBreakII();
        List<String>  result = instance.wordBreak(s, wordDict);
        System.out.println(result);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        LinkedList<String> track = new LinkedList<>();
        backtrack(result, track, dict, s, 0);
        return result;
    }

    public void backtrack(List<String> result, LinkedList<String> track, Set<String> dict, String s, int start) {
        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (!dict.contains(sub)) {
                continue;
            }
            track.add(sub);
            if (i == s.length()) {
                String[] arr = new String[track.size()];
                track.toArray(arr);
                result.add(String.join(" ", arr));
            }
            backtrack(result, track, dict, s, i);
            track.removeLast();
        }
    }

    /**
     * Not right
     * todo
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreakV3(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, Boolean> memo = new HashMap<>();
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(dict);

        boolean exist = wordBreakV3(s.toCharArray(), memo, result, root, 0);
        return (exist ? result : new ArrayList<>());
    }

    public boolean wordBreakV3(char[] arr, Map<Integer, Boolean> memo, List<String> result, TrieNode root, int pos) {
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
                result.add(p.word);
                found = wordBreakV3(arr, memo, result, root, i + 1);
            }
            if (found) {
                memo.put(pos, true);
                return true;
            }
        }
        memo.put(pos, false);
        return false;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean end;
        String word;
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
            p.word = word;
        }
        return root;
    }
}
