package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given two string arrays words1 and words2,
 * return the number of strings that appear exactly once in each of the two arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
 * Output: 2
 * Explanation:
 * - "leetcode" appears exactly once in each of the two arrays. We count this string.
 * - "amazing" appears exactly once in each of the two arrays. We count this string.
 * - "is" appears in each of the two arrays,but there are 2 occurrences of it in words1. We do not count this string.
 * - "as" appears once in words1, but does not appear in words2. We do not count this string.
 * Thus, there are 2 strings that appear exactly once in each of the two arrays.
 * Example 2:
 *
 * Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
 * Output: 0
 * Explanation: There are no strings that appear in each of the two arrays.
 * Example 3:
 *
 * Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
 * Output: 1
 * Explanation: The only string that appears exactly once in each of the two arrays is "ab".
 *
 *
 * Constraints:
 *
 * 1 <= words1.length, words2.length <= 1000
 * 1 <= words1[i].length, words2[j].length <= 30
 * words1[i] and words2[j] consists only of lowercase English letters.
 * @author sniper
 * @date 2022/8/17
 * LC2085
 */
public class CountCommonWordsWithOneOccurrence {

    public static void main(String[] args) {
        String[] words1 = {"a","ab"};
        String[] words2 = {"a","a","a","ab"};
        int result = countWords(words1, words2);
        System.out.println(result);
    }

    public int countWordsV2(String[] words1, String[] words2) {
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        for(String word : words1) {
            map1.put(word, map1.getOrDefault(word,0)+1);
        }
        for(String word : words2) {
            map2.put(word, map2.getOrDefault(word,0)+1);
        }

        int count = 0;
        for(String word : words1) {
            if(map1.get(word) == 1 && map2.getOrDefault(word,0) == 1) {
                count++;
            }
        }
        return count;
    }

    public static int countWords(String[] words1, String[] words2) {
        Map<String, Integer> freq1 = countFrequency(words1);
        Map<String, Integer> freq2 = countFrequency(words2);
        Set<String> set1 = new HashSet<>();
        freq1.forEach((k, v) -> {
            if (v == 1) {
                set1.add(k);
            }
        });
        Set<String> set2 = new HashSet<>();
        freq2.forEach((k, v) -> {
            if (v == 1) {
                set2.add(k);
            }
        });
        set1.retainAll(set2);
        return set1.size();
    }

    public static Map<String, Integer> countFrequency(String[] words) {
        Map<String, Integer> freq = new HashMap<>(words.length);
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        return freq;
    }

}
