package org.ict.algorithm.leetcode.trie;


import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;


/**
 * You are given a 0-indexed array of unique strings words.
 *
 * A palindrome pair is a pair of integers (i, j) such that:
 *
 * 0 <= i, j < word.length,
 * i != j, and
 * words[i] + words[j] (the concatenation of the two strings) is a
 * palindrome
 * .
 * Return an array of all the palindrome pairs of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["abcddcba","dcbaabcd","slls","llssssll"]
 * Example 2:
 *
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * Example 3:
 *
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["a","a"]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 5000
 * 0 <= words[i].length <= 300
 * words[i] consists of lowercase English letters.
 * @author sniper
 * @date 16 Nov, 2022
 * LC336
 * 1.communication
 * 2.problem solving
 * 3.coding
 * 4.testing
 */
public class PalindromePairs {

    public static void main(String[] args) {
        String[] words = {"abcd","dcba","lls","s","sssll"};
        //String[] words = {"a", ""};
        PalindromePairs instance = new PalindromePairs();
        List<List<Integer>> result = instance.palindromePairsV5(words);
        System.out.println(result);
    }

    /**
     * It's OK.
     * Time Cost 152ms faster than Trie-Tree Solution.
     *
     * Descriptions provided by chamrc.
     * Using the unique length of all the words is really a nice trick to cut the useless palindrome check. Thanks.
     * BTW, is the time complexity O(nul)? n is the words count,
     * u is the count of unique length of the words,
     * l is the average length of the word.
     * Even though after Java 7 update 6, substring operation got updated as of memory leak issues,
     * and the time complexity became O(k),
     * where k is the length of the word of the substring.
     * But since the isPalindrome check only walks through the part (length - 1 - k),
     * the added up time would still be O(l).
     * This is would be the reason why the current solution is much faster than the TrieTree solution with time complexity of O(nll),
     * as there are unnecessary palindrome checks.
     *
     * @author sagnik_20
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairsV5(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) {
            return res;
        }

        TreeSet<Integer> set = new TreeSet<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
            set.add(words[i].length());
        }

        for (int i = 0; i < words.length; i++) {
            /**
             * Find the reversed string directly in the HashMap
             * Because isPalindrome has already processed the empty string.
             * we don't need to consider the case curLength==1 separated.
             * e.g.
             * words=["a", "abcd", "dcba"]
             */
            String reversed = new StringBuilder(words[i]).reverse().toString();
            if (map.containsKey(reversed) && map.get(reversed) != i) {
                res.add(Arrays.asList(i, map.get(reversed)));
            }

            /**
             * Iterate the length less than curLength, because set has sorted already.
             * This is a smart trick to make fewer iterations.
             * We split words only by length less than current word's length, other than split one by one in palindromePairsV3 and palindromePairsV4.
             * This technique avoids many invalid check.
             */
            int curLength = words[i].length();
            for (Integer k : set) {
                /**
                 * split words only by length less than current word's length
                 */
                if (k == curLength) {
                    break;
                }
                /**
                 * Check left part:
                 * 0,...,curLength-1-k, curLength-k,...,curLength-1
                 * words = ["abcd","dcba","lls","s","sssll"]
                 * sll, (0, 3-1-1), "sl" is not a palindrome.
                 */
                if (isPalindrome(reversed, 0, curLength - 1 - k)) {
                    String s1 = reversed.substring(curLength - k);
                    if (map.containsKey(s1)) {
                        res.add(Arrays.asList(i, map.get(s1)));
                    }
                }

                /**
                 * Check right part:
                 * 0,...,curLength-1-k, curLength-k,...,curLength
                 * words = ["abcd","dcba","lls","s","sssll"]
                 * sll, (1, 3-1), "ll" is a palindrome.
                 * check substring(0, 1)="s", "s" exists in the map.
                 */
                if (isPalindrome(reversed, k, curLength - 1)) {
                    String s2 = reversed.substring(0, k);
                    if (map.containsKey(s2)) {
                        res.add(Arrays.asList(map.get(s2), i));
                    }
                }
            }
        }
        return res;
    }



    /**
     * @see <a href="https://leetcode.com/problems/palindrome-pairs/solutions/79195/o-n-k-2-java-solution-with-trie-structure"></a>
     * @author fun4LeetCode
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairsV2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        TrieNodeV2 root = new TrieNodeV2();

        for (int i = 0; i < words.length; i++) {
            buildTrieV2(root, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            searchTrieV2(words, i, root, res);
        }
        return res;
    }


    /**
     * words = ["abcd","dcba","lls","s","sssll"]
     *                  root
     *               -----------
     *              /  |    |   \
     *             d   a    s    l
     *            /    |    |     \
     *           c     b   l,[2]   l
     *          /      |    |       \
     *         b       c  2,l,[2]    s,[4]
     *        /        |              \
     *     0,a,[0]   1,d,[1]          s,[4]
     *                                  \
     *                                4,s,[4]
     * @param root
     * @param word
     * @param index
     */
    public void buildTrieV2(TrieNodeV2 root, String word, int index) {
        TrieNodeV2 p = root;
        char[] arr = word.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            int idx = arr[i] - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new TrieNodeV2();
            }
            /**
             * Notice here we invoke isPalindrome firstly, then move the pointer p to the next node.
             */
            if (isPalindrome(word, 0, i)) {
                p.list.add(index);
            }
            p = p.children[idx];
        }
        /**
         * If input word is an empty string "",
         * the arr's length is zero, for-loop won't run, and root-node will
         * add the empty string's index to its list, and mark the endIndex value
         * to the empty string's index.
         * For root-node, all the palindrome words index will store in this list.
         * empty string word index is include, and it also exists in endIndex variable.
         */
        p.list.add(index);
        p.endIndex = index;
    }

    public void searchTrieV2(String[] words, int index, TrieNodeV2 root, List<List<Integer>> res) {
        TrieNodeV2 p = root;
        int n =  words[index].length();
        for (int i = 0; i < n; i++) {
            if (p.endIndex >= 0 && p.endIndex != index && isPalindrome(words[index], i, n - 1)) {
                res.add(Arrays.asList(index, p.endIndex));
            }
            int idx = words[index].charAt(i) - 'a';
            p = p.children[idx];
            if (p == null) {
                return;
            }
        }
        /**
         * For empty string input, this for-loop assure the "" can be concat with other palindrome string.
         */
        for (int j : p.list) {
            if (index == j) {
                continue;
            }
            res.add(Arrays.asList(index, j));
        }
    }

    static class TrieNodeV2 {
        TrieNodeV2[] children = new TrieNodeV2[26];
        /**
         * Equals to -1 in default.
         * If it is a word's end, it is the index of the word.
         */
        int endIndex = -1;
        /**
         *  List of word indices such that nodes can construct a palindrome
         *  e.g.
         */
        List<Integer> list = new ArrayList<>();
    }

    /**
     * 1.communication
     * Does all the words contain lowercase letters only? Yes
     * Does the words contain empty string word? Yes
     * Does the words are unique in the array? Yes.
     *
     * 2.problem solving
     * Tell your own thoughts about the question to the interviewer, and how do you going to solve the problem.
     * A whole idea and the data structure that you will to use.
     * we can separate the words into two classes:
     * i.empty-string word and self-palindrome words,
     * e.g.Trie-tree.
     *
     * 3.coding
     *  Write a template firstly, and then communicate with the interviewer to describe what are you doing while writing each line of code.
     * 4.testing
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairsV1(String[] words) {
        if (null == words || words.length == 0) {
            return new ArrayList<>();
        }
        int n = words.length;
        TrieNode root = new TrieNode();
        List<Integer> selfPalindromeWordIndices = new ArrayList<>();
        List<List<Integer>> finalResult = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            /**
             * Only preprocess non-empty strings
             */
            if (!words[i].isEmpty()) {
                /**
                 * 1.Collect self-palindrome words, e.g. "aaa", "b", "abba".
                 */
                addSelfPalindromeIndices(words[i], i, selfPalindromeWordIndices);
                /**
                 * 2.Reverse each word and add the reversed word into the Trie-Tree.
                 */
                //buildTrie(root, reverse(words[i]), i);
                buildTrieV1(root, words[i], i);
            }
        }

        for (int i = 0; i < n; i++) {
            if (words[i].isEmpty()) {
                /**
                 * 3.For empty string, we concat it directly with self-palindrome word,
                 * e.g. "" and "aaa", "" and "b", "" and "bbb";
                 */
                for (int pairId : selfPalindromeWordIndices) {
                    finalResult.add(new ArrayList<>(Arrays.asList(i, pairId)));
                    finalResult.add(new ArrayList<>(Arrays.asList(pairId, i)));
                }
            } else {
                /**
                 * 4.For non-empty string, we search the palindrome word pairs in the Trie-Tree.
                 */
                List<Integer> wordIndices = search(root, words[i], i);
                for (int pairId : wordIndices) {
                    finalResult.add(new ArrayList<>(Arrays.asList(i, pairId)));
                }
            }
        }
        return finalResult;
    }

    /**
     * The following is the thought from GraceMeng, you can see the original post by the href-link below.
     * I add some descriptions with decomposed cases to help understand her thought.
     * -----------------------------------------------------------------
     * We want to concatenate string B to string A to make AB palindrome.
     * How could AB be palindrome?
     * If B ends with x, then A must starts with x.
     * If the second character of B is y, then the second last character of A is y...
     * That is,
     *   Case 1. A must be prefix of reversed B, and the rest of reversed B should be palindrome.
     *   For example, (B:oooabc - cbaooo,    A:cba       AB:cba|oooabc), the rest of reversed B:(ooo)
     *
     *   Case 2. Or, reversed B must be prefix of A, and the rest of A should be palindrome.
     *   For example, (B:abc - cba           A:cbaooo,   AB:cbaooo|abc), the rest of A:(ooo)
     *
     * Each word in words can be B. We put all reversed words in a trie.
     *   (cba, oooabc) ---> reversed: (abc, cbaooo)
     *   (cbaooo, abc) ---> reversed: (oooabc, cba)
     *
     * Each word in words can be A, so we search A in trie, In this way,
     *   Case 1. if we found A in trie, and the branch under the end node is a palindrome, we found it!
     *   e.g. (A, B)=(cba, oooabc), we found A:cba in trie, the branch under the end node(a) is "ooo", "ooo" is a palindrome.
     *   Case 2. if we reach a leaf of trie, and the rest of A is palindrome, we found it!
     *   e.g. A:"cbaooo", when we reach the leaf node 'c' in the path "oooabc"(reversed-A), the rest of A:(ooo) is a palindrome.
     *
     *   For Case 1., we modify TrieNode data structure by adding belowPalindromeWordIds.
     *   the belowPalindromeWordIds is a list of word indices such that nodes below can construct a palindrome.
     *   For Case 2., we create a method isPalindrome(str, start, end).
     *
     * Please take care of corner cases of empty string.
     * the constraint words[i].length == 0 indicates it may be empty string.
     * Both ("", self-palindrome) and (self-palindrome, "") are still palindrome.
     * -------------------------------------------------------------------
     * Let's take some examples to understand the above thoughts of GraceMeng.
     * case forms:(A, B), the first element in case pair is A, the second one is B:
     * case 1. ("abcd", "dcba"), ("bat", "tab"), A is the prefix of reversed-B, B is the prefix of reversed-A.
     * case 2. ("s", "lls"), A is the prefix of reversed-B.
     * case 3. ("sssll", "lls"), B is the prefix of reversed-A.
     * case 4. ("a", ""), ("", "ccc"), empty string combined with palindrome string already, this is the corner case.
     *
     * case-1 can be combined into case-2 or case-3.
     * So there are three cases in total need to be considered.
     *
     * We build the trie-tree with all the words reversed from the input.
     *           -----root----
     *          / | \  \   \  \
     *         a  l  s  d   o  c
     *        /   |   \  \   \  \
     *       b    l    l  c   o  b
     *      /     |     \  \   \  \
     *     c      s      l  b   o  a
     *    /       |          \   \  \
     *   d        s           a   a  o
     *            |                \  \
     *            s                 b  o
     *                               \  \
     *                                c  o
     * @see <a href="https://leetcode.com/problems/palindrome-pairs/solutions/176205/beats-80-trie-java-with-explanations"></a>
     * @author GraceMeng
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        if (null == words || words.length == 0) {
            return new ArrayList<>();
        }

        int n = words.length;
        TrieNode root = new TrieNode();
        List<List<Integer>> finalResult = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            /**
             * Empty string can be composed with self-palindrome string.
             * e.g. "" and "ooo" --> "ooo".
             * Another reason is the Trie-Tree don't care the empty string.
             * So we must deal with empty string in separated switch.
             */
            if (words[i].isEmpty()) {
                List<Integer> selfPalindromeWordIndices = getSelfPalindrome(words);
                for (int pairId : selfPalindromeWordIndices) {
                    finalResult.add(new ArrayList<>(Arrays.asList(i, pairId)));
                    finalResult.add(new ArrayList<>(Arrays.asList(pairId, i)));
                }
            } else {
                buildTrie(root, reverse(words[i]), i);
            }
        }

        /**
         * Search in the Trie-Tree.
         */
        for (int i = 0; i < n; i++) {
            List<Integer> wordIndices = search(root, words[i], i);
            for (int pairId : wordIndices) {
                finalResult.add(new ArrayList<>(Arrays.asList(i, pairId)));
            }
        }
        return finalResult;
    }

    public List<Integer> search(TrieNode root, String word, int index) {
        List<Integer> wordIndices = new ArrayList<>();
        TrieNode p = root;
        int n = word.length();
        char[] arr = word.toCharArray();
        for (int i = 0; i < n; i++) {
            boolean flag = isPalindrome(word, i, n - 1);
            //System.out.println("word:" + word + ", i:" + i + ", n-1:" + (n-1) + ", isPalindrom:" + flag + ", p.endIndex:" + p.endIndex);
            if (p.endIndex > -1 && flag) {
                wordIndices.add(p.endIndex);
            }
            int idx = arr[i] - 'a';
            if (p.children[idx] == null) {
                return wordIndices;
            }
            p = p.children[idx];
        }
        if (p.endIndex > -1 && p.endIndex != index) {
            wordIndices.add(p.endIndex);
        }
        if (!p.belowPalindromeWordIds.isEmpty()) {
            wordIndices.addAll(p.belowPalindromeWordIds);
        }
        //System.out.println("search word:" + word + ", wordIndices:" + wordIndices);
        return wordIndices;
    }


    /**
     * abcd --> dcba
     *           cba
     *            ba
     *             a
     * @param root
     * @param word reversed form of the word in the array
     * @param index the index of the word in the array
     */
    public void buildTrieV1(TrieNode root, String word, int index) {
        TrieNode p = root;
        int n = word.length();
        char[] arr = word.toCharArray();
        /**
         * Build Trie-Tree with the reversed word.
         * We can iterate each word from the end to the beginning.
         */
        for (int i = n - 1; i >= 0; i--) {
            int idx = arr[i] - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new TrieNode();
            }
            p = p.children[idx];
            if (isPalindrome(word, 0, i - 1)) {
                p.belowPalindromeWordIds.add(index);
            }
        }
        p.endIndex = index;
        //System.out.println("buildTrie word:" + word + ", index:" + index + ", endIndex:" + p.endIndex + ", belowPalindromeWordIds:" + p.belowPalindromeWordIds);
    }

    /**
     *
     * @param root
     * @param word
     * @param index
     */
    public void buildTrie(TrieNode root, String word, int index) {
        TrieNode p = root;
        int n = word.length();
        char[] arr = word.toCharArray();
        /**
         * Build Trie-Tree with the reversed word.
         * We can iterate each word from the end to the beginning.
         * See the method of buildTrieV1.
         */
        for (int i = 0; i < n; i++) {
            int idx = arr[i] - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new TrieNode();
            }
            p = p.children[idx];
            if (isPalindrome(word, i + 1, n - 1)) {
                p.belowPalindromeWordIds.add(index);
            }
        }
        p.endIndex = index;
        //System.out.println("buildTrie word:" + word + ", index:" + index + ", endIndex:" + p.endIndex + ", belowPalindromeWordIds:" + p.belowPalindromeWordIds);
    }



    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        /**
         * Equals to -1 in default.
         * If it is a word's end, it is the index of the word.
         */
        int endIndex = -1;
        /**
         *  List of word indices such that nodes below can construct a palindrome
         *  e.g. words=["lls", "sssll"]
         *  "sssll" --> "llsss"
         *  root
         *    |
         *    l   list:[], Does "lsss" can be palindrome? NO
         *    |
         *    l   list:[1], Does "sss" can be palindrome? YES
         *    |
         *    s   list:[1], Does "ss" can be palindrome? YES
         *    |
         *    s   list:[1], Does "s" can be palindrome? YES
         *    |
         *    s   list:[], i=n-1, i+1=n, lo=n, hi=n-1, lo > hi, NO
         */
        List<Integer> belowPalindromeWordIds = new ArrayList<>();
    }

    public void addSelfPalindromeIndices(String word, int index,  List<Integer> wordIndices) {
        if (isPalindrome(word, 0, word.length() - 1)) {
            wordIndices.add(index);
        }
    }

    public List<Integer> getSelfPalindrome(String[] words) {
        List<Integer> wordIndices = new ArrayList<>();
        int i = 0;
        for (String word : words) {
            if (isPalindrome(word, 0, word.length() - 1)) {
                wordIndices.add(i);
            }
            i++;
        }
        return wordIndices;
    }

    /**
     * No need to use this method, because we can iterate the word from end to beginning.
     * @param str
     * @return
     */
    public String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Empty string "" in this method will return false;
     * because hi = -1, lo = 0, lo > hi, so return false.
     * @param s
     * @param lo
     * @param hi
     * @return
     */
    public boolean isPalindrome(String s, int lo, int hi) {
        if (lo > hi) {
            return false;
        }
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    /**
     * Empty string "" in this method will return true;
     * lo = 0, hi = -1, lo > hi, so return true.
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        while (lo <= hi) {
            if (s.charAt(lo++) != s.charAt(hi--)) {
                return false;
            }
        }
        return true;
    }



    /**
     * Time Limit Exceed.
     * 1.Store each word with its index into a HashMap.
     * 2.For each word, we split it into two parts, s1 and s2.
     * We check that s1 or s2 whether is palindrome string or not.
     * If s1 is a palindrome string, we can put s1 as the middle part, s2 as the right part,
     * then we check the reversed s2 whether in the HashMap or not.
     * If the HashMap contains the reversed s2, then we find a new palindrome pair.
     * reversed-s2 + s1 + s2
     * 3.We can do the same steps for s2 like s1 above.
     *
     * Consider test case: ["a", ""]
     *
     * it actually deals with empty strings very elegantly, consider the case {"a", ""}
     * when i = 0, j = 0, str1 = "", str2 = "a",
     * --> isPalindrome(str1) is true, but i = map.get(""), no result is added
     * --> isPalindrome(str2) is true, i != map.get(""), solution [0, 1] is added to result
     *
     * when i = 0, j = 1, str1="a", str2=""
     * --> isPalindrome(str1) is true, i!= map.get(""), solution [1,0] is added to result
     * --> str2 is empty, loop skipped
     * @param words
     * @return
     * @deprecated
     */
    public List<List<Integer>> palindromePairsV4(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) {
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            /**
             * Notice "<=" here!
             * Because substring upper excluded,
             * here j should be less than and equal to words[i].length();
             * This can handle empty string input.
             */
            for (int j = 0; j <= words[i].length(); j++) {
                String s1 = words[i].substring(0, j);
                String s2 = words[i].substring(j);

                if (isPalindrome(s1)) {
                    String reversedS2 = new StringBuilder(s2).reverse().toString();
                    /**
                     * s1.length() != 0 to process the empty string to avoid duplicated results.
                     * Consider test case: ["a", ""]
                     */
                    if (map.containsKey(reversedS2) && map.get(reversedS2) != i && s1.length() != 0) {
                        List<Integer> newPair = new ArrayList<>();
                        newPair.add(map.get(reversedS2));
                        newPair.add(i);
                        res.add(newPair);
                    }
                }

                if (isPalindrome(s2)) {
                    String reversedS1 = new StringBuilder(s1).reverse().toString();
                    if (map.containsKey(reversedS1) && map.get(reversedS1) != i) {
                        List<Integer> newPair = new ArrayList<>();
                        newPair.add(i);
                        newPair.add(map.get(reversedS1));
                        res.add(newPair);
                    }
                }
            }
        }
        return res;
    }

    /**
     * Time Limit Exceed.
     * 1.Store each word with its index into a HashMap.
     * 2.For each word, we split it into two parts, s1 and s2.
     * We check that s1 or s2 whether is palindrome string or not.
     * If s1 is a palindrome string, we can put s1 as the middle part, s2 as the right part,
     * then we check the reversed s2 whether in the HashMap or not.
     * If the HashMap contains the reversed s2, then we find a new palindrome pair.
     * reversed-s2 + s1 + s2
     * 3.We can do the same steps for s2 like s1 above.
     *
     * Consider test case: ["a", ""]
     * it actually deals with empty strings very elegantly, consider the case {"a", ""}
     * when i = 0, j = 0, str1 = "", str2 = "a",
     * --> isPalindrome(str1) is true, but i = map.get(""), no result is added
     * --> isPalindrome(str2) is true, i != map.get(""), solution [0, 1] is added to result
     *
     * when i = 0, j = 1, str1="a", str2=""
     * --> isPalindrome(str1) is true, i!= map.get(""), solution [1,0] is added to result
     * --> str2 is empty, loop skipped
     * @deprecated
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairsV3(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) {
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            /**
             * Notice "<=" here!
             * Because substring upper excluded,
             * here j should be less than and equal to words[i].length();
             * This can handle empty string input.
             */
            for (int j = 0; j <= words[i].length(); j++) {
                String s1 = words[i].substring(0, j);
                String s2 = words[i].substring(j);

                if (isPalindrome(s1)) {
                    String reversedS2 = new StringBuilder(s2).reverse().toString();
                    if (map.containsKey(reversedS2) && map.get(reversedS2) != i) {
                        List<Integer> newPair = new ArrayList<>();
                        newPair.add(map.get(reversedS2));
                        newPair.add(i);
                        res.add(newPair);
                    }
                }

                if (isPalindrome(s2)) {
                    String reversedS1 = new StringBuilder(s1).reverse().toString();
                    /**
                     * s2.length() != 0 to process the empty string to avoid duplicated results.
                     * Consider test case: ["a", ""]
                     */
                    if (map.containsKey(reversedS1) && map.get(reversedS1) != i && s2.length() != 0) {
                        List<Integer> newPair = new ArrayList<>();
                        newPair.add(i);
                        newPair.add(map.get(reversedS1));
                        res.add(newPair);
                    }
                }
            }
        }
        return res;
    }




}
