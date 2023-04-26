package org.ict.algorithm.leetcode.twopointers;

/**
 * Description
 * Given an array of strings wordsDict and two different strings
 * that already exist in the array word1 and word2,
 * return the shortest distance between these two words in the list.
 *
 * Example 1:
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
 * Output: 3
 *
 * Example 2:
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * Output: 1
 *
 * Constraints:
 * 2 <= wordsDict.length <= 3 * 10^4
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] consists of lowercase English letters.
 * word1 and word2 are in wordsDict.
 * word1 != word2
 *
 * @author sniper
 * @date 26 Apr, 2023
 * LC243, Easy
 */
public class ShortestWordDistance {

    public static void main(String[] args) {
        String[] wordsDict = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "practice";

        ShortestWordDistance instance = new  ShortestWordDistance();
        int res = instance.shortestDistanceV2(wordsDict, word1, word2);
        System.out.println(res);
    }

    /**
     * e.g. wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
     * i:0, p:-1, wordsDict[0]="practice", wordsDict[0].equals(word2), p = i, p:0
     * i:1, p:0, wordsDict[1]="makes"
     * i:2, p:0, wordsDict[2]="perfect"
     * i:3, p:0, wordsDict[3]="coding", wordsDict[3].equals(word1), p != -1, wordsDict[3] not equals wordsDict[0]
     *           res = Math.min(MAX, i - p) = 3
     * return res:3
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistanceV2(String[] wordsDict, String word1, String word2) {
        int res = Integer.MAX_VALUE;
        int p = -1;
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1) || wordsDict[i].equals(word2)) {
                if (p != -1 && !wordsDict[p].equals(wordsDict[i])) {
                    res = Math.min(res, i - p);
                }
                p = i;
            }
        }
        return res;
    }

    /**
     * Initialize to -1 with two variables p1,p2,
     * then iterate through the array,
     * and if word 1 is encountered, its position will be in p1,
     * and if word 2 is encountered, its position will be in p2,
     * if at this time p1, p2 are not -1, update the result.
     * -------------------------------------------------------
     * class Solution:
     *     def shortestDistance(self, wordsDict: List[str], word1: str, word2: str) -> int:
     *         i = j = -1
     *         ans = inf
     *         for k, w in enumerate(wordsDict):
     *             if w == word1:
     *                 i = k
     *             if w == word2:
     *                 j = k
     *             if i != -1 and j != -1:
     *                 ans = min(ans, abs(i - j))
     *         return ans
     * -------------------------------------------------------
     * class Solution {
     * public:
     *     int shortestDistance(vector<string>& wordsDict, string word1, string word2) {
     *         int ans = INT_MAX;
     *         for (int k = 0, i = -1, j = -1; k < wordsDict.size(); ++k) {
     *             if (wordsDict[k] == word1) {
     *                 i = k;
     *             }
     *             if (wordsDict[k] == word2) {
     *                 j = k;
     *             }
     *             if (i != -1 && j != -1) {
     *                 ans = min(ans, abs(i - j));
     *             }
     *         }
     *         return ans;
     *     }
     * };
     *
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistanceV1(String[] wordsDict, String word1, String word2) {
        int res = wordsDict.length;
        int p1 = -1;
        int p2 = -1;
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) {
                p1 = i;
            } else if (wordsDict[i].equals(word2)) {
                p2 = i;
            }

            if (p1 != -1 && p2 != -1) {
                res = Math.min(res, Math.abs(p1 - p2));
            }
        }
        return res;
    }

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int res = wordsDict.length;
        int p1 = -1;
        int p2 = -1;
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) {
                p1 = i;
                if (p2 != -1) {
                    res = Math.min(res, Math.abs(p1 - p2));
                }
            }
            if (wordsDict[i].equals(word2)) {
                p2 = i;
                if (p1 != -1) {
                    res = Math.min(res, Math.abs(p1 - p2));
                }
            }
        }
        return res;
    }


}
