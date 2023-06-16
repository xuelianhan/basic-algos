package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of strings words and a string chars.
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 * Return the sum of lengths of all good strings in words.
 *
 *
 * Example 1:
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
 *
 * Example 2:
 * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * Output: 10
 * Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 *
 * Constraints:
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * words[i] and chars consist of lowercase English letters.
 * @author sniper
 * @date 16 Jun 2023
 * LC1160, Easy, frequency=18
 */
public class FindWordsThatCanBeFormedByCharacters {

    /**
     * Understanding the following solution
     * Time Cost 7ms
     * @param words
     * @param chars
     * @return
     */
    public int countCharactersV1(String[] words, String chars) {
        int[] cntOne = new int[26];
        for (char ch : chars.toCharArray()) {
            cntOne[ch - 'a']++;
        }

        int res = 0;
        for (String word : words) {
            int[] cntTwo = new int[26];
            boolean match = true;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                cntTwo[idx]++;
                if (cntTwo[idx] > cntOne[idx]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                res += word.length();
            }
        }
        return res;
    }


    /**
     * Time Cost 55ms
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> frequencyCnt = new HashMap<>(chars.length());
        for(char c : chars.toCharArray()) {
            frequencyCnt.put(c, frequencyCnt.getOrDefault(c, 0) + 1);
        }
        int cnt = 0;
        Map<Character, Integer> map = new HashMap<>(100);
        for(String word : words) {
            map.clear();
            if (word.length() > chars.length()) {
                continue;
            }
            for (char c : word.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            int i = 0;
            for (; i < word.length(); i++) {
                char c = word.charAt(i);
                if (frequencyCnt.getOrDefault(c, 0) < map.get(c)) {
                    break;
                }
            }
            if (i == word.length()) {
                cnt += word.length();
            }
        }
        return cnt;
    }
}
