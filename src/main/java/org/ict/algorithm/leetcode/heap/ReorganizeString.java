package org.ict.algorithm.leetcode.heap;

import javassist.compiler.ast.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a string s,
 * rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: s = "aaab"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 * @author sniper
 * @date 23 Aug 2023
 * LC767, Medium
 */
public class ReorganizeString {

    /**
     * Understanding the following solution
     * Time Cost 1ms
     *
     * No Sorting O(N):
     *
     * count letter appearance and store in hash[i]
     * find the letter with the largest occurrence.
     * put the letter into even index number (0, 2, 4 ...) char array
     * put the rest into the array
     *
     * e.g. s = "vvvlo"
     * bucket[v]=3
     * bucket[l]=1
     * bucket[o]=1
     * -------------
     * res: 0,1,2,3,4
     * res:[v, ,v, ,v]
     * res:[v,l,v, ,v]
     * res:[v,l,v,o,v]
     * return "vlvov"
     *
     * @author fangbiyi
     * @see <a href="https://leetcode.com/problems/reorganize-string/solutions/232469/java-no-sort-o-n-0ms-beat-100"></a>
     * @param s
     * @return
     */
    public String reorganizeStringV1(String s) {
        int[] bucket = new int[26];
        int n = s.length();
        int maxFreq = 0;
        int letter = 0;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            bucket[c]++;
            if (bucket[c] > maxFreq) {
                maxFreq = bucket[c];
                letter = c;
            }
        }

        if (maxFreq > (n + 1) / 2) {
            return "";
        }

        /**
         * Put the most frequent letter into even index:
         * 0, 2, 4, 6,...
         */
        char[] res = new char[n];
        int idx = 0;
        while (bucket[letter] > 0) {
            res[idx] = (char)(letter + 'a');
            idx += 2;
            bucket[letter]--;
        }

        /**
         * Put the rest letters into the odd index.
         */
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                if (idx >= n) {
                    idx = 1;
                }
                res[idx] = (char)(i + 'a');
                idx += 2;
                bucket[i]--;
            }
        }

        return String.valueOf(res);
    }

    /**
     * Understanding the following solution
     * Time Cost 5ms
     * e.g. s = "vvvlo"
     * @param s
     * @return
     */
    public String reorganizeString(String s) {
        int maxFreq = 0;
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(c));
        }

        /**
         * If a letter occurs more often than half of the total length,
         * then two neighboring letters must occur.
         * e.g. s = "aaab"
         * maxFreq=3, s.length=4, (s.length() + 1) / 2 = 2
         * 3 > 2, so return "";
         */
        if (maxFreq > (s.length() + 1) / 2) {
            return "";
        }
        /**
         * Sort by Pair's Left field( frequency count here)
         */
        Queue<Pair<Integer, Character>> maxHeap = new PriorityQueue<>((a, b) -> b.left- a.left);
        for (char c : freq.keySet()) {
            maxHeap.offer(new Pair<>(freq.get(c), c));
        }

        /**
         * e.g. s = "vvvlo"
         * maxHeap:{3, v},{1, l},{1, o}
         * res:v, maxHeap:{1, l},{1, o}, prevChar:v, prevFreq:2
         * res:vl, prevFreq > 0, maxHeap:{2, v},{1, o}, prevChar:l, prevFreq:0
         * res:vlv, prevFreq==0, prevChar:v, prevFreq:1, maxHeap:{1, o}
         * res:vlvo, prevFreq > 0, maxHeap:{v, 1}, prevChar:o, prevFreq:0
         * res:vlvov, prevFreq==0, prevChar:v, prevFreq:0
         * maxHeap is empty
         * return "vlvov"
         */
        char prevChar = '#';
        int prevFreq = 0;
        StringBuilder res = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Pair<Integer, Character> p = maxHeap.poll();
            res.append(p.right);
            if (prevFreq > 0) {
                maxHeap.offer(new Pair<>(prevFreq, prevChar));
            }
            prevChar = p.right;
            prevFreq = p.left - 1;
        }
        return res.toString();
    }

    static class Pair<L, R> {
        private L left;
        private R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }
    }
}
