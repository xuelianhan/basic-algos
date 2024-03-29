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
        //String s = "catsandog";
        //String[] dict = {"cats","dog","sand","and","cat"};

        String s = "leetcode";
        String[] dict = {"leet","code"};

        WordBreak instance = new  WordBreak();
        instance.wordBreakV4(s, new ArrayList<>(Arrays.asList(dict)));
    }

    /**
     * Dynamic Programming Solution
     *
     * Time Complexity
     * 1 + 2 + 3 + 4 + ... + n = n(1 + n)/2.
     * So the time complexity is O(N^2)
     *
     * dp[i] stands for whether chars in [0, i) can be segmented into words from the dictionary.
     * dp[0] means the empty string, empty no need to segmented, so it's true.
     *
     * e.g. s = "leetcode", wordDict = ["leet","code"]
     * 0 1 2 3 4 5 6 7
     * l e e t c o d e
     *
     * i:1:
     *   j:0, dp[0]:true, str(0, 1)=l, "l" not in the dict, j++
     * i:2:
     *   j:0, dp[0]:true, str(0, 2)=le, "le" not in the dict, j++
     *   j:1, dp[1]:false, j++
     * i:3:
     *   j:0, dp[0]:true, str(0, 3)=lee, "lee" not in the dict, j++
     *   j:1, dp[1]:false, j++
     *   j:2, dp[2]:false, j++
     * i:4:
     *   j:0, dp[0]:true, str(0, 4)=leet, "leet" in the dict, dp[4] = true,
     *   break-inner-for-loop
     * i:5:
     *   j:0, dp[0]:true, str(0,5)=leetc, "leetc" not in the dict, j++
     *   j:1, dp[1]:false, j++
     *   j:2, dp[2]:false, j++
     *   j:3, dp[3]:false, j++
     *   j:4, dp[4]:true, str(4,5)=c, "c" not in the dict, j++
     * i:6:
     *   j:0, dp[0]:true, str(0,6)=leetco, "leetco" not in the dict, j++
     *   j:1, dp[1]:false, j++
     *   j:2, dp[2]:false, j++
     *   j:3, dp[3]:false, j++
     *   j:4, dp[4]:true, str(4,6)=co, "co" not in the dict, j++
     *   j:5: dp[5]:false, j++
     * i:7:
     *   j:0, dp[0]:true, str(0,7)=leetcod, "leetcod" not in the dict, j++
     *   j:1, dp[1]:false, j++
     *   j:2, dp[2]:false, j++
     *   j:3, dp[3]:false, j++
     *   j:4, dp[4]:true, str(4,7)=cod, "cod" not in the dict, j++
     *   j:5, dp[5]:false, j++
     *   j:6, dp[6]:false, j++
     * i:8:
     *   j:0, dp[0]:true, str(0,8)=leetcode, "leetcode" not in the dict, j++
     *   j:1, dp[1]:false, j++
     *   j:2, dp[2]:false, j++
     *   j:3, dp[3]:false, j++
     *   j:4, dp[4]:true, str(4,8)="code", code in the dict, dp[8] = true,
     *   break-inner-for-loop
     *
     * return dp[8]:true
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreakV4(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        Set<String> dict = new HashSet<>(wordDict);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                /**
                 * use j to split range[0, i) into two parts:
                 * 1.[0, j): this is the dp[j]
                 * 2.[j, i): this is the substring(j, i)
                 * we have already got dp[j] from previous steps,
                 * so we need only to search substring(j, i) whether in the dict or not.
                 * If both dp[j] and substring(j, i) in dict, then dp[i] is true.
                 * It means range[0, i) can be segmented.
                 * we can move to the next i-loop.
                 */
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean end;
        char cur;
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
     * Trie-Tree DFS Solution
     * Time cost 5ms
     * The basic idea is building a trie tree with all the words in the dict, then we
     * use depth-first-search strategy in the trie tree for each partition.
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

    /**
     * Trie-Tree-Depth-First-Search
     *
     *
     * 0 1 2 3 4 5 6 7 8
     * c a t s a n d o g
     * e.g. s = "catsandog", dict = ["cats","dog","sand","and","cat"]
     *                 root
     *              ----------
     *             / |   |   |
     *            c  d   s   a
     *           /   |   |   |
     *          a    o   a   n
     *         /     |   |   |
     *       t(Y)   g(Y) n  d(Y)
     *       /           |
     *      s(Y)        d(Y)
     *
     * pos:0
     *   i:0, find 'c' in trie, i++
     *   i:1, find 'a' in trie, i++
     *   i:2, find 't' in trie,
     *        't' is the end, dfs invoke wordBreak(arr, memo, root, 2+1) --> 'cat' branch, dfs 3
     *         pos:3
     *         i:3, find 's' in trie, i++
     *         i:4, find 'a' in trie, i++
     *         i:5, find 'n' in trie, i++
     *         i:6, find 'd' in trie,
     *              'd' is the end, dfs invoke wordBreak(arr, memo, root, 6+1) --> 'sand' branch, dfs 7
     *              pos:7
     *              i:7, the node 'o' is null in root, for-loop-break
     *              put (7, false) in the memo
     *         put(3, false) in the memo.
     *   i++,
     *   i:3, find 's' in trie,
     *        's' is the end, dfs invoke wordBreak(arr, memo, root, 3+1) --> 'cats' branch, dfs 4
     *         pos:4
     *         i:4, find 'a' in trie, i++
     *         i:5, find 'n' in trie, i++
     *         i:6, find 'd' in trie,
     *              'd' is the end, dfs invoke wordBreak(arr, memo, root, 6+1) --> 'and' branch, dfs 7
     *               pos:7
     *               i:7, return memo(7, false)
     *         put (4, false) in memo
     * put (0, false) in memo
     *
     *
     * @param arr
     * @param memo
     * @param root
     * @param pos
     * @return
     */
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
     * Time Complexity O(2^N)
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
     * Time Complexity O(2^N)
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
     * @see <a href="https://www.mathsisfun.com/algebra/sequences-sums-geometric.html"></a>
     * Geometric Sum Formula:
     * Sum = a(1 - q^n)/(1 - q), for the array: a, a*r, a*r^2, ..., a*r^(n-1).
     * 1 + 2^1 + 2^2 + ... + 2^(n-1) = (1-2^n)/(1-2) = 2^n - 1
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
        return false;
    }
}
