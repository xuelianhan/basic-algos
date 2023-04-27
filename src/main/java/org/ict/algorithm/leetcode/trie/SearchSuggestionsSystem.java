package org.ict.algorithm.leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given an array of strings products and a string searchWord.
 * Design a system that suggests at most three product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with searchWord.
 * If there are more than three products with a common prefix return the three lexicographically minimums products.
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 *
 * Example 1:
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
 *
 * Example 2:
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Explanation: The only word "havana" will always be suggested while typing the search word.
 *
 *
 * Constraints:
 * 1 <= products.length <= 1000
 * 1 <= products[i].length <= 3000
 * 1 <= sum(products[i].length) <= 2 * 10^4
 * All the strings of products are unique.
 * products[i] consists of lowercase English letters.
 * 1 <= searchWord.length <= 1000
 * searchWord consists of lowercase English letters.
 *
 * @author sniper
 * @date 26 Apr, 2023
 * LC1268, Medium, frequency=68
 */
public class SearchSuggestionsSystem {


    public static void main(String[] args) {
        //String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        //String searchWord = "mouse";
        String[] products = {"havana"};
        String searchWord = "tatiana";

        SearchSuggestionsSystem instance = new SearchSuggestionsSystem();
        List<List<String>> res = instance.suggestedProducts(products, searchWord);
        System.out.println(res);
        //res.forEach(System.out::println);
    }


    /**
     * Understanding the following solution
     * 
     * Time Cost 18ms
     * Design a system that suggests at most three product names from products
     * after each character of searchWord is typed
     *
     * @param products
     * @param searchWord
     * @return
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        /**
         * This sorting assure the lexicographical order.
         */
        Arrays.sort(products);

        /**
         * Build Trie-Tree
         */
        TrieNode root = new TrieNode();
        for (int i = 0; i < products.length; i++) {
            root.insert(products[i], i);
        }

        /**
         * To search and collect result.
         */
        List<List<String>> res = new ArrayList<>();
        for (List<Integer> list : root.search(searchWord)) {
            List<String> temp = new ArrayList<>();
            for (int i : list) {
                temp.add(products[i]);
            }
            res.add(temp);
        }
        return res;
    }


    static class TrieNode {
        TrieNode[] children = new TrieNode[26];

        /**
         * The index of sorted products.
         */
        List<Integer> idxList = new ArrayList<>();

        /**
         * Store word and its index-i into the Trie-Tree
         * @param word
         * @param i
         */
        public void insert(String word, int i) {
            TrieNode node = this;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
                /**
                 * If there are more than three products with a common prefix,
                 * return the three lexicographically minimums products.
                 */
                if (node.idxList.size() < 3) {
                    node.idxList.add(i);
                }
            }
        }

        public List<Integer>[] search(String word) {
            int n = word.length();
            List<Integer>[] res = new List[n];
            Arrays.setAll(res, k -> new ArrayList<>());

            TrieNode node = this;
            for (int i = 0; i < n; i++) {
                int idx = word.charAt(i) - 'a';
                if (node.children[idx] == null) {
                    break;
                }
                node = node.children[idx];
                /**
                 * Collect the words-index with same character.
                 */
                res[i] = node.idxList;
            }
            return res;
        }

    }



}
