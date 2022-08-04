package org.ict.algorithm.leetcode.string;

import java.util.*;

/**
 * A sentence is a string of single-space separated words where each word consists only of lowercase letters.
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Given two sentences s1 and s2,
 * return a list of all the uncommon words.
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "this apple is sweet", s2 = "this apple is sour"
 * Output: ["sweet","sour"]
 * Example 2:
 *
 * Input: s1 = "apple apple", s2 = "banana"
 * Output: ["banana"]
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 200
 * s1 and s2 consist of lowercase English letters and spaces.
 * s1 and s2 do not have leading or trailing spaces.
 * All the words in s1 and s2 are separated by a single space.
 * @author sniper
 * @date 2022/8/3
 * lc884
 */
public class UncommonWordsfromTwoSentences {

    public static void main(String[] args) {
        /**
         * output:   ["s","ejt"]
         * expected: ["ejt"]
         */
        String s1 = "s z z z s";
        String s2 = "s z ejt";
        //String s1 = "apple apple";
        //String s2 = "banana";
        //String s1 = "this apple is sweet";
        //String s2 = "this apple is sour";
        String[] result = uncommonFromSentences(s1, s2);
        System.out.println(Arrays.deepToString(result));
    }

    /**
     * Elegant Solution provided by Lee215
     * @param A
     * @param B
     * @return
     */
    public static String[] uncommonFromSentences(String A, String B) {
        String space = " ";
        Map<String, Integer> count = new HashMap<>(8);
        for (String w : (A + space + B).split(space)) {
            count.put(w, count.getOrDefault(w, 0) + 1);
        }
        ArrayList<String> res = new ArrayList<>();
        for (String w : count.keySet()) {
            if (count.get(w) == 1) {
                res.add(w);
            }
        }
        return res.toArray(new String[0]);
    }

    public static String[] uncommonFromSentencesV1(String s1, String s2) {
        String spaceRegex = "\\s";
        List<String> list = new ArrayList<>();
        Map<String, Integer> map1 = new HashMap<>(8);
        Map<String, Integer> map2 = new HashMap<>(8);
        for(String item : s1.split(spaceRegex)) {
           if (map1.get(item) == null) {
               map1.put(item, 1);
           } else {
               map1.put(item, map1.get(item) + 1);
           }
        }
        for(String item : s2.split(spaceRegex)) {
            if (map2.get(item) == null) {
                map2.put(item, 1);
            } else {
                map2.put(item, map2.get(item) + 1);
            }
        }

        for(String item : map1.keySet()) {
            if (map1.get(item) > 1) {
                continue;
            }
            if (!map2.keySet().contains(item)) {
                list.add(item);
            }
        }
        for(String item : map2.keySet()) {
            if (map2.get(item) > 1) {
                continue;
            }
            if (!map1.keySet().contains(item)) {
                list.add(item);
            }
        }
        String[] result = new String[list.size()];
        return list.toArray(result);
    }
}
