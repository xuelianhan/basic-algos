package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Let the function f(s) be the frequency of the lexicographically smallest character in a non-empty string s.
 * For example, if s = "dcce" then f(s) = 2 because the lexicographically smallest character is 'c', which has a frequency of 2.
 *
 * You are given an array of strings words and another array of query strings queries.
 * For each query queries[i], count the number of words in words such that f(queries[i]) < f(W) for each W in words.
 *
 * Return an integer array answer, where each answer[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 * Input: queries = ["cbd"], words = ["zaaaz"]
 * Output: [1]
 * Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
 * Example 2:
 *
 * Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * Output: [1,2]
 * Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 *
 *
 * Constraints:
 *
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j], words[i][j] consist of lowercase English letters.
 * @author sniper
 * @date 21 Mar, 2023
 * LC1170, Medium
 */
public class CompareStringsFrequencyOfSmallestCharacter {

    public static void main(String[] args) {
        CompareStringsFrequencyOfSmallestCharacter instance = new CompareStringsFrequencyOfSmallestCharacter();
        //String s = "zaaaz";
        //int res = instance.countFreq(s);

        String[] queries = {"bbb","cc"};
        String[] words = {"a","aa","aaa","aaaa"};
        int[] res = instance.numSmallerByFrequency(queries, words);
        System.out.println(Arrays.toString(res));

    }

    /**
     * This version is similar with numSmallerByFrequencyV1, but it maintains an array with length of 12 other than 11,
     * in order to use freqCnt[cnt + 1] directly without index out of bound error.
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequencyV2(String[] queries, String[] words) {
        /**
         * 1 <= queries[i].length, words[i].length <= 10
         * The freqCnt-index represents frequency, it starts with 1, ends with 10, index-0 is a dummy offset,
         * and its value represents how many Strings in "words" array have same or more frequency.
         */
        int[] freqCnt = new int[12];
        for (String word : words) {
            int cnt = countFreq(word);
            freqCnt[cnt]++;
        }
        /**
         * words = ["a","aa","aaa","aaaa"]
         * freqCnt = [0,1,1,1,1,0,0,0,0,0,0]
         * freqCnt = [4,4,3,2,1,0,0,0,0,0,0]
         * i:0, freqCnt[0]=4, there are 4 words which frequency >= 0, they are "a","aa","aaa","aaaa"
         * i:1, freqCnt[1]=4, there are 4 words which frequency >= 1, they are "a","aa","aaa","aaaa"
         * i:2, freqCnt[2]=3, there are 3 words which frequency >= 2, they are "aa","aaa","aaaa"
         * i:3, freqCnt[3]=2, there are 2 words which frequency >= 3, they are "aaa","aaaa"
         * i:4, freqCnt[4]=1, there are 1 words which frequency >= 4, they are "aaaa"
         */
        for (int i = 9; i >= 0; i--){
            freqCnt[i] = freqCnt[i + 1] + freqCnt[i];
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int cnt = countFreq(queries[i]);
            res[i] = freqCnt[cnt + 1];
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Time Cost 2ms
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequencyV1(String[] queries, String[] words) {
        /**
         * 1 <= queries[i].length, words[i].length <= 10
         * The freqCnt-index represents frequency, it starts with 1, ends with 10, index-0 is a dummy offset,
         * and its value represents how many Strings in "words" array have same or more frequency.
         */
        int[] freqCnt = new int[11];
        for (String word : words) {
            int cnt = countFreq(word);
            freqCnt[cnt]++;
        }
        /**
         * words = ["a","aa","aaa","aaaa"]
         * freqCnt = [0,1,1,1,1,0,0,0,0,0,0]
         * freqCnt = [4,4,3,2,1,0,0,0,0,0,0]
         * i:0, freqCnt[0]=4, there are 4 words which frequency >= 0, they are "a","aa","aaa","aaaa"
         * i:1, freqCnt[1]=4, there are 4 words which frequency >= 1, they are "a","aa","aaa","aaaa"
         * i:2, freqCnt[2]=3, there are 3 words which frequency >= 2, they are "aa","aaa","aaaa"
         * i:3, freqCnt[3]=2, there are 2 words which frequency >= 3, they are "aaa","aaaa"
         * i:4, freqCnt[4]=1, there are 1 words which frequency >= 4, they are "aaaa"
         */
        for (int i = 9; i >= 0; i--){
            freqCnt[i] = freqCnt[i + 1] + freqCnt[i];
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int cnt = countFreq(queries[i]);
            if (cnt == freqCnt.length - 1) {
                res[i] = 0;
            } else {
                res[i] = freqCnt[cnt + 1];
            }
        }
        return res;
    }

    /**
     * Brute-Force Solution
     * Time Complexity: O(M)+O(N)+O(M*N)
     * Space Complexity: O(M)+O(N)
     * Time Cost 473ms
     *
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int m = queries.length;
        int n = words.length;
        int[] res = new int[m];
        Map<String, Integer> mapQ = new HashMap<>();
        Map<String, Integer> mapW = new HashMap<>();
        for (int i = 0; i < m; i++) {
            mapQ.put(queries[i], countFreq(queries[i]));
        }

        for (int j = 0; j < n; j++) {
            mapW.put(words[j], countFreq(words[j]));
        }

        for (int i = 0; i < m; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (mapQ.get(queries[i]) < mapW.get(words[j])) {
                    cnt++;
                }
            }
            res[i] = cnt;
        }
        return res;
    }

    private int countFreq(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            freq[idx]++;
        }
        int res = 0;
        for (int cnt : freq) {
            if (cnt > 0) {
                res = cnt;
                break;
            }
        }
        return res;
    }
}
