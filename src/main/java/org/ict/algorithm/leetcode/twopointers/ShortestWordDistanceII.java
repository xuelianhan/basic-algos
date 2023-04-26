package org.ict.algorithm.leetcode.twopointers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 * Design a data structure that will be initialized with a string array,
 * and then it should answer queries of the shortest distance between two different strings from the array.
 * Implement the WordDistance class:
 * WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
 * int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.
 *
 *
 * Example 1:
 * Input
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * Output
 * [null, 3, 1]
 *
 * Explanation
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // return 3
 * wordDistance.shortest("makes", "coding");    // return 1
 *
 *
 * Constraints:
 * 1 <= wordsDict.length <= 3 * 10^4
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] consists of lowercase English letters.
 * word1 and word2 are in wordsDict.
 * word1 != word2
 * At most 5000 calls will be made to shortest.
 *
 * @see ShortestWordDistance
 * The difference is that this problem needs to invoke shortest-function many times
 * other than only one time in ShortestWordDistance
 *
 * @author sniper
 * @date 26 Apr, 2023
 * LC244, Medium, frequency=71
 */
public class ShortestWordDistanceII {

    public static void main(String[] args) {
        String[] wordsDict = {"practice", "makes", "perfect", "coding", "makes"};
        ShortestWordDistanceII.WordDistanceV3 wd = new ShortestWordDistanceII.WordDistanceV3(wordsDict);
        String word1 = "coding";
        String word2 = "practice";
        int res = wd.shortest(word1, word2);
        System.out.println(res);
    }

    static class WordDistanceV3 {

        private Map<String, List<Integer>> map = new HashMap<>();

        public WordDistanceV3(String[] wordsDict) {
            for (int i = 0; i < wordsDict.length; i++) {
                map.putIfAbsent(wordsDict[i], new ArrayList<>());
                map.get(wordsDict[i]).add(i);
            }
        }

        /**
         * Time Complexity O(M + N)
         * @param word1
         * @param word2
         * @return
         */
        public int shortest(String word1, String word2) {
            int res = Integer.MAX_VALUE;
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);

            for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
                res = Math.min(res, Math.abs(list1.get(i) - list2.get(j)));
                /**
                 * At here, both <= and < is OK
                 */
                if (list1.get(i) < list2.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }
            return res;
        }

    }

    static class WordDistanceV2 {

        private Map<String, List<Integer>> map = new HashMap<>();

        public WordDistanceV2(String[] wordsDict) {
            for (int i = 0; i < wordsDict.length; i++) {
                map.putIfAbsent(wordsDict[i], new ArrayList<>());
                map.get(wordsDict[i]).add(i);
            }
        }

        /**
         * Time Complexity O(M + N)
         * @param word1
         * @param word2
         * @return
         */
        public int shortest(String word1, String word2) {
            int i = 0;
            int j = 0;
            int res = Integer.MAX_VALUE;
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);

            while (i < list1.size() && j < list2.size()) {
                res = Math.min(res, Math.abs(list1.get(i) - list2.get(j)));
                /**
                 * At here, both <= and < is OK
                 */
                if (list1.get(i) < list2.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }

            return res;
        }

    }

    static class WordDistanceV1 {

        private Map<String, List<Integer>> map = new HashMap<>();

        public WordDistanceV1(String[] wordsDict) {
            for (int i = 0; i < wordsDict.length; i++) {
                map.putIfAbsent(wordsDict[i], new ArrayList<>());
                map.get(wordsDict[i]).add(i);
            }
        }

        /**
         * Time Complexity O(M + N)
         * @param word1
         * @param word2
         * @return
         */
        public int shortest(String word1, String word2) {
            int i = 0;
            int j = 0;
            int res = Integer.MAX_VALUE;
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);

            while (i < list1.size() && j < list2.size()) {
                res = Math.min(res, Math.abs(list1.get(i) - list2.get(j)));
                /**
                 * At here, both <= and < is OK
                 */
                if (list1.get(i) <= list2.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }

            return res;
        }

    }

    static class WordDistance {

        /**
         * Create a mapping of each word and all its occurrences index in the string array.
         */
        private Map<String, List<Integer>> map = new HashMap<>();

        public WordDistance(String[] wordsDict) {
            for (int i = 0; i < wordsDict.length; i++) {
                map.putIfAbsent(wordsDict[i], new ArrayList<>());
                map.get(wordsDict[i]).add(i);
            }
        }

        /**
         * Time Complexity O(M*N)
         * @param word1
         * @param word2
         * @return
         */
        public int shortest(String word1, String word2) {
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < map.get(word1).size(); i++) {
                for (int j = 0; j < map.get(word2).size(); j++) {
                    List<Integer> list1 = map.get(word1);
                    List<Integer> list2 = map.get(word2);
                    res = Math.min(res, Math.abs(list1.get(i) - list2.get(j)));
                }
            }
            return res;
        }

    }
}
