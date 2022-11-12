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
        //String s = "abc";
        //String[] dict = {"a", "b", "c"};
        List<String> wordDict = new ArrayList<>(Arrays.asList(dict));
        WordBreakII instance = new WordBreakII();
        List<String>  result = instance.wordBreak(s, wordDict);
        System.out.println(result);
    }

    public List<String> wordBreakV1(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();

        return result;
    }

    public void backtrackV1(List<String> result, LinkedList<String> track, Set<String> dict, String s, int end) {

    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        LinkedList<String> track = new LinkedList<>();
        backtrack(result, track, dict, s, 0);
        return result;
    }

    /**
     * Input: s = "catsanddog", dict = ["cat","cats","and","sand","dog"]
     *                   catsanddog
     *                   ----------
     *                 /     |
     *            cat /      | cats
     *               /       |
     *          sanddog    anddog
     *             /         |
     *        sand/          |and
     *           /           |
     *        dog           dog
     * 0 1 2 3 4 5 6 7 8 9
     * c a t s a n d d o g
     * If not adding return:
     *
     * start:2:
     *   substring(0, 3), sub:cat, track:cat, backtrack(3)
     *   substring(3, 7), sub:sand, track:cat,sand, backtrack(7)
     *   substring(7, 10), sub:dog, track:cat,sand,dog, end==s.length, add the track into result;
     *   remove dog
     *   remove sand
     *   remove cat
     * start:3:
     *   substring(0, 4), sub:cats, track:cats, backtrack(4)
     *   substring(4, 7), sub:and, track:cats,and, backtrack(7)
     *   substring(7, 10), sub:dog, track:cats,and,dog, end==s.length, add the track into result;
     *   remove dog
     *   remove and
     *   remove cats
     *
     * track:[cat]
     *     track:[cat, sand]
     *         track:[cat, sand, dog]
     *         result:[cat sand dog]
     *         start to remove last for range:(7, 10), track:[cat, sand, dog]
     *         end to remove last for range:(7, 10), track:[cat, sand]
     *     start to remove last for range:(3, 7), track:[cat, sand]
     *     end to remove last for range:(3, 7), track:[cat]
     * start to remove last for range:(0, 3), track:[cat]
     * end to remove last for range:(0, 3), track:[]
     * track:[cats]
     *     track:[cats, and]
     *         track:[cats, and, dog]
     *         result:[cat sand dog, cats and dog]
     *         start to remove last for range:(7, 10), track:[cats, and, dog]
     *         end to remove last for range:(7, 10), track:[cats, and]
     *     start to remove last for range:(4, 7), track:[cats, and]
     *     end to remove last for range:(4, 7), track:[cats]
     * start to remove last for range:(0, 4), track:[cats]
     * end to remove last for range:(0, 4), track:[]
     * [cat sand dog, cats and dog]
     * ==================Add return, you will get the wrong answer================
     * track:[cat]
     *     track:[cat, sand]
     *         track:[cat, sand, dog]
     *         result:[cat sand dog]
     *         start to remove last for range:(3, 7), track:[cat, sand, dog]
     *         end to remove last for range:(3, 7), track:[cat, sand]
     *     start to remove last for range:(0, 3), track:[cat, sand]
     *     end to remove last for range:(0, 3), track:[cat]
     * track:[cat, cats]
     *     track:[cat, cats, and]
     *         track:[cat, cats, and, dog]
     *         result:[cat sand dog, cat cats and dog]
     *         start to remove last for range:(4, 7), track:[cat, cats, and, dog]
     *         end to remove last for range:(4, 7), track:[cat, cats, and]
     *     start to remove last for range:(0, 4), track:[cat, cats, and]
     *     end to remove last for range:(0, 4), track:[cat, cats]
     * [cat sand dog, cat cats and dog]
     *
     * @param result
     * @param track
     * @param dict
     * @param s
     * @param start
     */
    public void backtrack(List<String> result, LinkedList<String> track, Set<String> dict, String s, int start) {
        for (int end = start + 1; end <= s.length(); end++) {
            String sub = s.substring(start, end);
            if (!dict.contains(sub)) {
                continue;
            }
            track.add(sub);
            if (end == s.length()) {
                String[] arr = new String[track.size()];
                track.toArray(arr);
                result.add(String.join(" ", arr));
                //Notice! Don't add return here.
                //return;
                /**
                 * Input: s = "catsanddog", dict = ["cat","cats","and","sand","dog"], expected: ["cats and dog","cat sand dog"]
                 * Don't return here! Otherwise, it will generate unexpected results like the following:
                 * e.g.
                 * output: ["cat sand dog","cat cats and dog"], if you add return statement,
                 * then backtrack return directly when end==s.length, it will not execute removeLast operation,
                 * you can seem recursive invoke as push/pop in stack.
                 *
                 */
            }
            backtrack(result, track, dict, s, end);
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
