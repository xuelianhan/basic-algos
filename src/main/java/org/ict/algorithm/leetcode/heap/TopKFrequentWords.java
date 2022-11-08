package org.ict.algorithm.leetcode.heap;

import java.util.*;

/**
 * Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 *
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 500
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * k is in the range [1, The number of unique words[i]]
 *
 *
 * Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 * @author sniper
 * @date 08 Nov, 2022
 * LC692
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        if (k == words.length) {
            result.addAll(Arrays.asList(words));
            return result;
        }

        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        /**
         * init heap 'the less frequent element first'.
         * create a MinPQ here.
         */
        Queue<String> heap = new PriorityQueue<>((w1, w2) -> (freq.get(w1) - freq.get(w2)));
        //Queue<String> heap = new PriorityQueue<>(Comparator.comparingInt(freq::get));

        /**
         *  2. keep k top frequent elements in the heap.
         *  O(N log k) < O(N log N) time.
         */
        for (String word: freq.keySet()) {
            heap.add(word);
            if (heap.size() > k) {
                /**
                 * heap will poll the top element(smallest) out.
                 */
                heap.poll();
            }
        }

        /**
         *  3. build an output array and put the kth-biggest at the end position in the array.
         *   O(k log k) time.
         */
        int i = 0;
        while (i < k) {
            result.add(heap.poll());
            i++;
        }

        return result;
    }
}
