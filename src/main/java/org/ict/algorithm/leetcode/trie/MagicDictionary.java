package org.ict.algorithm.leetcode.trie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Design a data structure that is initialized with a list of different words.
 * Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.
 *
 * Implement the MagicDictionary class:
 *
 * MagicDictionary() Initializes the object.
 * void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
 * bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure,
 * otherwise returns false.
 *
 *
 * Example 1:
 *
 * Input
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * Output
 * [null, null, false, true, false, false]
 *
 * Explanation
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // return False
 * magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
 * magicDictionary.search("hell"); // return False
 * magicDictionary.search("leetcoded"); // return False
 *
 *
 * Constraints:
 *
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] consists of only lower-case English letters.
 * All the strings in dictionary are distinct.
 * 1 <= searchWord.length <= 100
 * searchWord consists of only lower-case English letters.
 * buildDict will be called only once before search.
 * At most 100 calls will be made to search.
 * @author sniper
 * @date 13 Nov, 2022
 * LC676
 */
public class MagicDictionary {


    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean end;
    }

    TrieNode root;

    /**
     * An easier and better solution using HashMap.
     * Use word length as key, and save all the words with same length in a set as value to the key.
     * @param searchWord
     * @return
     */
    Map<Integer, Set<String>> map;

    /**
     * Your MagicDictionary object will be instantiated and called as such:
     * MagicDictionary obj = new MagicDictionary();
     * obj.buildDict(dictionary);
     * boolean param_2 = obj.search(searchWord);
     */
    public MagicDictionary() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            TrieNode p = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (p.children[idx] == null) {
                    p.children[idx] = new TrieNode();
                }
                p = p.children[idx];
            }
            p.end = true;
        }
    }

    public void buildDictWithMap(String[] dictionary) {
        for (String word : dictionary) {
            int len = word.length();
            if (!map.containsKey(len)) {
                map.put(len, new HashSet<>());
            }
            map.get(len).add(word);
        }
    }


    public boolean searchTrie(TrieNode root, String word) {
        TrieNode p = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (p.children[idx] == null) {
                return false;
            }
            p = p.children[idx];
        }
        return p.end;
    }


    public boolean searchV2(String word) {
        int len = word.length();
        if (!map.containsKey(len)) {
            return false;
        }
        for (String s : map.get(len)) {
            int count = 0;
            for (int i = 0; i < len; i++) {
                if (word.charAt(i) != s.charAt(i)) {
                    count++;
                }
                /**
                 * If word's length equals the string's length in the dictionary,
                 * then we check whether two strings has only one different char.
                 */
                if (count == 1) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean searchV1(String searchWord) {
        return dfsOnTrie(searchWord, root, 0, 0);
        //return dfsOnTrieV1(searchWord, root, 0, 0);
        //return dfsOnTrieV2(searchWord, root, 0, 0);
    }

    /**
     * Thoughts from GraceMeng:
     * We could build a trie using word in dicts.
     * If two words differ by one character only,
     * they will be within the branches regardless of the mismatched character.
     * @param searchWord
     * @param parent
     * @param pos
     * @param curEditCount
     * @return
     */
    public boolean dfsOnTrieV2(String searchWord, TrieNode parent, int pos, int curEditCount) {
        if (parent == null || curEditCount > 1) {
            return false;
        }
        if (pos == searchWord.length()) {
            return (parent.end && curEditCount == 1);
        }
        if (pos < searchWord.length() && curEditCount <= 1) {

            for (int i = 0; i < 26 ; i++) {
                /**
                 * skip null-child node
                 */
                if (parent.children[i] == null) {
                    continue;
                }
                int nextEditCount = (i == searchWord.charAt(pos) - 'a' ? curEditCount : curEditCount + 1);
                /**
                 * Only dfs return true can we return true.
                 * Don't return dfs directly.
                 */
                if (dfsOnTrieV2(searchWord, parent.children[i], pos + 1, nextEditCount)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfsOnTrieV1(String searchWord, TrieNode parent, int pos, int curEditCount) {
        if (parent == null) {
            return false;
        }
        if (parent.end && pos == searchWord.length() && curEditCount == 1) {
            return true;
        }
        if (pos < searchWord.length() && curEditCount <= 1) {
            boolean found = false;
            /**
             * Only found turns out true, for-loop breaks and return true.
             */
            for (int i = 0; !found && i < 26 ; i++) {
                /**
                 * skip null-child node
                 */
                if (parent.children[i] == null) {
                    continue;
                }
                int nextEditCount = (i == searchWord.charAt(pos) - 'a' ? curEditCount : curEditCount + 1);
                found = dfsOnTrieV1(searchWord, parent.children[i], pos + 1, nextEditCount);
            }
            return found;
        }
        return false;
    }

    public boolean dfsOnTrie(String searchWord, TrieNode parent, int pos, int curEditCount) {
        if (parent == null) {
            return false;
        }
        if (parent.end && pos == searchWord.length() && curEditCount == 1) {
            return true;
        }
        if (pos < searchWord.length() && curEditCount <= 1) {
            /**
             * Only found turns out true, for-loop breaks and return true.
             */
            boolean found = false;
            for (int i = 0; !found && i < 26 ; i++) {
                int nextEditCount = (i == searchWord.charAt(pos) - 'a' ? curEditCount : curEditCount + 1);
                found = dfsOnTrie(searchWord, parent.children[i], pos + 1, nextEditCount);
            }
            return found;
        }
        return false;
    }

    /**
     * Very slow but clear.
     * @param searchWord
     * @return
     */
    public boolean search(String searchWord) {
        char[] arr = searchWord.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (arr[i] == c) {
                    continue;
                }
                char temp = arr[i];
                arr[i] = c;
                if (searchTrie(root, new String(arr))) {
                    return true;
                }
                arr[i] = temp;
            }
        }
        return false;
    }




}
