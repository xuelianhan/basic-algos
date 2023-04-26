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
        String[] wordsDict = {"practice", "practice", "makes", "perfect", "coding", "makes"};
        ShortestWordDistanceII.WordDistanceV4 wd = new ShortestWordDistanceII.WordDistanceV4(wordsDict);
        String word1 = "coding";
        String word2 = "practice";
        int res = wd.shortest(word1, word2);
        System.out.println(res);
    }

    static class WordDistanceV4 {

        private Map<String, List<Integer>> map = new HashMap<>();

        /**
         * Similar with WordDistanceV3, only a little different at
         * the constructor using Map.computeIfAbsent.
         * @param wordsDict
         */
        public WordDistanceV4(String[] wordsDict) {
            for (int i = 0; i < wordsDict.length; i++) {
                map.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
            }
        }

        /**
         * Time Complexity O(M + N)
         *
         * e.g.
         * wordsDict = ["practice", "practice", "makes", "perfect", "coding", "makes"]
         * word1 = "coding"
         * word2 = "practice"
         *
         * list1:[4], list2:[0, 1]
         * i:0, j:0, res = min(MAX, abs(list1[0] - list2[0])) = min(MAX, abs(4 - 0)) = 4
         *           a:4, b:0, a > b, j++, j:1
         *
         * @param word1
         * @param word2
         * @return
         */
        public int shortest(String word1, String word2) {
            int res = Integer.MAX_VALUE;
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);

            for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
                int a = list1.get(i);
                int b = list2.get(j);
                res = Math.min(res, Math.abs(a - b));
                /**
                 * At here, both <= and < is OK
                 */
                if (a < b) {
                    i++;
                } else {
                    j++;
                }
            }
            return res;
        }

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
         *
         * e.g.
         * wordsDict = ["practice", "practice", "makes", "perfect", "coding", "makes"]
         * word1 = "coding"
         * word2 = "practice"
         *
         * list1:[4], list2:[0, 1]
         * i:0, j:0, res = min(MAX, abs(list1[0] - list2[0])) = min(MAX, abs(4 - 0)) = 4
         *           a:4, b:0, a > b, j++, j:1
         * i:0, j:1, res = min(4, abs(list1[0] - list2[1])) = min(4, abs(4 - 1)) =  3
         *           a:4, b:1, a > b, j++, j:2
         * j == 2 == list2.size(), for-loop-ended
         * return res:3
         *
         * @param word1
         * @param word2
         * @return
         */
        public int shortest(String word1, String word2) {
            int res = Integer.MAX_VALUE;
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);

            for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
                int a = list1.get(i);
                int b = list2.get(j);
                res = Math.min(res, Math.abs(a - b));
                /**
                 * At here, both <= and < is OK
                 */
                if (a < b) {
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

    /**
     * class WordDistance:
     *     def __init__(self, wordsDict: List[str]):
     *         self.d = defaultdict(list)
     *         for i, w in enumerate(wordsDict):
     *             self.d[w].append(i)
     *
     *     def shortest(self, word1: str, word2: str) -> int:
     *         a, b = self.d[word1], self.d[word2]
     *         ans = inf
     *         i = j = 0
     *         while i < len(a) and j < len(b):
     *             ans = min(ans, abs(a[i] - b[j]))
     *             if a[i] <= b[j]:
     *                 i += 1
     *             else:
     *                 j += 1
     *         return ans
     */
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
         *
         * The following code can be optimized so that the complexity of the query is changed from O(MN) to O(M+N),
         * where M and N are the lengths of two words,
         * and two pointers i and j are needed to point to a position in the position array,
         * both initialized to 0 at the beginning,
         * then compare the numbers in the position array,
         * and move the pointer of the smaller one backward by one until the traversal of one of the array is completed.
         *
         * Why we can only move the smaller pointer, other than iterate both of the two arrays?
         * Because our target is to get the minimum difference, and the index array of list1, list2 are ascending order
         * for example, suppose that list1 = [1, 2, 3], list2 = [4, 5]
         * because all the items in list1 less than 4 in list2,
         * Once we complete traverse list1, there is no need to compare list1 with 5 in list2.
         * res = min(res, abs(1 - 4)) = 3
         * res = min(3, abs(2 - 4)) = 2
         * res = min(2, abs(3 - 4)) = 1
         *
         *
         * @see  WordDistanceV1 shortest-method
         * @see  WordDistanceV2 shortest-method
         * @see  WordDistanceV3 shortest-method
         *
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
