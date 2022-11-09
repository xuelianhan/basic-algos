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

    public static void main(String[] args) {
        //expected: ["i","love"]
        //String[] words = {"i","love","leetcode","i","love","coding"};
        //int k = 2;

        //expected: ["the", "is", "sunny", "day"]
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        int k = 4;

        //expected: ["a","aa","aaa"]
        //String[] words = {"aaa","aa","a"};
        //int k = 3;

        List<String> result = topKFrequent(words, k);
        System.out.println(result);
    }

    public static List<String> topKFrequentV1(String[] words, int k) {
        List<String> result = new ArrayList<>();
        //todo
        return result;
    }


    /**
     * Min-Heap Solution
     * @param words
     * @param k
     * @return
     */
    public static List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        /**
         * Notice here using hashmap may lead case failed. Why?
         * Because there are some constraints:
         * 1.Return the answer sorted by the frequency from highest to lowest.
         * 2.Sort the words with the same frequency by their lexicographical order
         */
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        Set<Map.Entry<String, Integer>> set = freq.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(set);
        Collections.sort(list, (b1, b2) -> {
            /**
             * sort list by frequency from lowest to highest
             */
            if (b1.getValue() < b2.getValue()) {
                return -1;
            } else if(b1.getValue() > b2.getValue()) {
                return 1;
            } else {
                /**
                 * sort by the reverse dictionary order
                 */
                return b2.getKey().compareTo(b1.getKey());
            }
        });

        /**
         * init minHeap 'the less frequent element first'.
         * create a MinPQ here.
         * some constraints:
         * 1.Return the answer sorted by the frequency from highest to lowest.
         * 2.Sort the words with the same frequency by their lexicographical order
         */
        Queue<String> minHeap = new PriorityQueue<>((w1, w2) -> {
            /**
             * sort list by frequency from lowest to highest
             */
            if (freq.get(w1) < freq.get(w2)) {
                return -1;
            } else if (freq.get(w1) > freq.get(w2)) {
                return 1;
            } else {
                /**
                 * sort by the reverse dictionary order
                 */
                return w2.compareTo(w1);
            }
        });

        /**
         *  2. keep k top frequent elements in the minHeap.
         *  O(N log k) < O(N log N) time.
         */
        for (Map.Entry<String, Integer> entry : list) {
            minHeap.offer(entry.getKey());
            if (minHeap.size() > k) {
                /**
                 * minHeap will poll the top element(smallest) out.
                 */
                minHeap.poll();
            }
        }

        /**
         *  3. build an output array and put the kth-biggest at the end position in the array.
         *   O(k log k) time.
         */
        int i = 0;
        while (i < k) {
            result.add(0, minHeap.poll());
            i++;
        }

        return result;
    }
}
