package org.ict.algorithm.leetcode.string;

import java.util.*;

/**
 * A substring is a contiguous (non-empty) sequence of characters within a string.
 *
 * A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u')
 * and has all five vowels present in it.
 *
 * Given a string word, return the number of vowel substrings in word.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aeiouu"
 * Output: 2
 * Explanation: The vowel substrings of word are as follows (underlined):
 * - "aeiouu"
 * - "aeiouu"
 * Example 2:
 *
 * Input: word = "unicornarihan"
 * Output: 0
 * Explanation: Not all 5 vowels are present, so there are no vowel substrings.
 * Example 3:
 *
 * Input: word = "cuaieuouac"
 * Output: 7
 * Explanation: The vowel substrings of word are as follows (underlined):
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 100
 * word consists of lowercase English letters only.
 * @author sniper
 * @date 2022/8/17
 * LC2062
 */
public class CountVowelSubstringsOfString {

    public static void main(String[] args) {
        //String word = "aeiouu";
        /**
         * Expected 7
         */
        String word = "cuaieuouac";
        int result = countVowelSubstrings(word);
        System.out.println(result);
    }

    public int countVowelSubStringsV3(String word) {
        return 0;
    }

    public int countVowelSubstringsV2(String word) {
        int count = 0;
        Map<Character, Integer> lastSeen = new HashMap<>(5);
        lastSeen.put('a', -1);
        lastSeen.put('e', -1);
        lastSeen.put('i', -1);
        lastSeen.put('o', -1);
        lastSeen.put('u', -1);
        for (int i = 0, lastInvalidPos = -1; i < word.length(); ++i) {
            if (lastSeen.containsKey(word.charAt(i))) {
                lastSeen.put(word.charAt(i), i);
                count += Math.max(Collections.min(lastSeen.values()) - lastInvalidPos, 0);
            } else {
                lastInvalidPos = i;
            }
        }
        return count;
    }

    public static int countVowelSubstrings(String word) {
        if (word.length() < 5) {
            return 0;
        }
        int count = 0;
        Set<Character> vowels = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            for (int j = i; j < word.length(); j++) {
                char ch = word.charAt(j);
                if (!isVowel(ch)) {
                    /**
                     * Here is break, not continue
                     * because our vowels require every char in it is vowel.
                     * So we don't need continue if we meet a consonant char.
                     */
                    break;
                }
                vowels.add(ch);
                if (vowels.size() == 5) {
                    count++;
                }
            }
            /**
             * When i-loop ends, the counts should be restart.
             * So we need clear vowels set.
             */
            vowels.clear();
        }
        return count;
    }

    public static boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }


}
