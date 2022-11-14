package org.ict.algorithm.leetcode.trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor.
 * For example, when the root "an" is followed by the successor word "other", we can form a new word "another".
 *
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
 * replace all the successors in the sentence with the root forming it.
 * If a successor can be replaced by more than one root,
 * replace it with the root that has the shortest length.
 *
 * Return the sentence after the replacement.
 *
 *
 *
 * Example 1:
 *
 * Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 * Example 2:
 *
 * Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * Output: "a a b c"
 *
 *
 * Constraints:
 *
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] consists of only lower-case letters.
 * 1 <= sentence.length <= 10^6
 * sentence consists of only lower-case letters and spaces.
 * The number of words in sentence is in the range [1, 1000]
 * The length of each word in sentence is in the range [1, 1000]
 * Every two consecutive words in sentence will be separated by exactly one space.
 * sentence does not have leading or trailing spaces.
 * @author sniper
 * @date 13 Nov, 2022
 * LC648
 */
public class ReplaceWords {

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList("cat","bat","rat");
        String sentence = "the cattle was rattled by the battery";
        ReplaceWords instance = new ReplaceWords();
        String result = instance.replaceWords(dictionary, sentence);
        System.out.println(result);
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> dict = new HashSet<>(dictionary);
        TrieNode root = buildTrie(dict);
        String[] arr = sentence.split("\\s+");
        for (int i = 0; i < arr.length; i++) {
            String prefix = findPrefix(arr[i], root);
            if (!prefix.isEmpty()) {
                arr[i] = prefix;
            }
        }
        return String.join(" ", arr);
    }

    static class TrieNode {
        TrieNode [] children = new TrieNode[26];
        boolean end;
        String word;
    }

    public TrieNode buildTrie(Set<String> dict) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            TrieNode p = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (p.children[idx] == null) {
                    p.children[idx] = new TrieNode();
                }
                p = p.children[idx];
            }
            p.end = true;
            p.word = word;
        }
        return root;
    }

    public String findPrefixV1(String word, TrieNode root) {
        TrieNode p = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (p.end || p.children[idx] == null) {
                break;
            }
            p = p.children[idx];
        }
        return p.end ? p.word : "";
    }

    public String findPrefix(String word, TrieNode root) {
        TrieNode p = root;
        StringBuilder prefix = new StringBuilder();
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (p.end || p.children[idx] == null) {
                break;
            }
            prefix.append(ch);
            p = p.children[idx];
        }
        return p.end ? prefix.toString() : "";
    }
}
