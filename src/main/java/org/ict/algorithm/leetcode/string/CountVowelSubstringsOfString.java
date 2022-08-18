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

    /**
     * Sliding Window Solution provided by Vlad(votrubac)
     *
     * I spent more time on this problem than on medium/hard problems in the contest.
     * I realize that brute-force would do for 100 characters,
     * otherwise, this problem would be medium.
     *
     * j mark the start of an "all-vowel" substring, and i is the current position.
     * The window between k - 1 and i is the smallest window with all 5 vowels.
     *
     * [j, i] is a string with all vowels.
     * [k, i] is the shortest substring containing all 5 vowels.
     * Therefore, for position i, we have k - j substrings with 5 vowels.
     *
     * The picture below demonstrate it for "xxaiioueiiaxx" test case:
     *   jk   i         substrings
     * xxaiioueiiaxx         1
     *
     *   jk    i
     * xxaiioueiiaxx         1
     *
     *   jk     i
     * xxaiioueiiaxx         1
     *
     *   jk      i
     * xxaiioueiiaxx         4
     *
     * @param word
     * @return
     */
    public int countVowelSubStringsV3(String word) {
        int j = 0, k = 0, vow = 0, cnt = 0;
        Map<Character, Integer> map = new HashMap<>(5);
        map.put('a', 0);
        map.put('e', 0);
        map.put('i', 0);
        map.put('o', 0);
        map.put('u', 0);
        for (int i = 0; i < word.length(); i++) {
            char ich = word.charAt(i);
            if (map.containsKey(ich)) {
                map.put(ich, map.get(ich) + 1);
                if (map.get(ich) == 1) {
                    vow++;
                }
                while (vow == 5) {
                    char kch = word.charAt(k);
                    map.put(kch, map.get(kch) - 1);
                    if (map.get(kch) == 0) {
                        vow--;
                    }
                    k++;
                }
                cnt += (k - j);
            } else {
                map.forEach((key, val) -> {
                    map.put(key, 0);
                });
                vow = 0;
                j = i + 1;
                k = i + 1;
            }
        }
        return cnt;
    }

    /**
     * Solution provided by Kevin(kcsquared)
     * @see <a href="https://leetcode.com/kcsquared/"></a>
     *
     * Intuition:
     * 1.Keep track of 6 values: the last index we saw a consonant,
     * and the last index where we saw each of the 5 vowels.
     *
     * 2.At each index: the number of valid substrings ending here is the distance backwards
     * from the earliest last seen vowel to the last seen consonant.
     * @param word
     * @return
     */
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

    /**
     * Brute-Force solution using HashSet
     *
     * @param word
     * @return
     */
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
