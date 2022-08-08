package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * In an alien language, surprisingly,
 * they also use English lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language,
 * and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographically in this alien language.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match,
 * and the second string is shorter (in size.)
 * According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 * @author sniper
 * @date 2022/8/8
 * LC953
 */
public class VerifyingAlienDictionary {

    public static void main(String[] args) {
        //String[] words = {"hello","leetcode"};
        //String order = "hlabcdefgijkmnopqrstuvwxyz";

        //String[] words = {"word","world","row"};
        //String order = "worldabcefghijkmnpqstuvxyz";

        //String[] words = {"apple","app"};
        //String order = "abcdefghijklmnopqrstuvwxyz";

        //String[] words = {"apap","app"};
        //String order = "abcdefghijklmnopqrstuvwxyz";

        String[] words = {"fxasxpc","dfbdrifhp","nwzgs","cmwqriv","ebulyfyve","miracx","sxckdwzv","dtijzluhts","wwbmnge","qmjwymmyox"};
        String order = "zkgwaverfimqxbnctdplsjyohu";
        boolean result = isAlienSortedV1(words, order);
        System.out.println(result);
    }



    /**
     * Using hashmap to store the order of 26 characters.
     * @param words
     * @param order
     * @return
     */
    public static boolean isAlienSortedV1(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>(26);
        for(int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for(int j = 0; j < words.length - 1; j++) {
            if (!compare(words[j], words[j + 1], map)) {
                return false;
            }
        }
        return true;
    }

    public static boolean compare(String s1, String s2, Map<Character, Integer> map) {
        int l1 = s1.length();
        int l2 = s2.length();
        for (int i = 0, j = 0; i < l1 && j < l2; i++, j++) {
            /**
             * critical condition: Only if not equal can to be compared.
             */
            if (s1.charAt(i) != s2.charAt(j)) {
                if (map.get(s1.charAt(i)) > map.get(s2.charAt(j))) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        if (l1 > l2) {
            return false;
        }
        return true;
    }
}
