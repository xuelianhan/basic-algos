package org.ict.algorithm.leetcode.string;

/**
 * Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2,
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
 * @author sniper
 * @date 14 Jun 2023
 * LC243, Easy, frequency=11
 */
public class ShortestWordDistance {


    /**
     *
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistanceV1(String[] wordsDict, String word1, String word2) {
        int idx = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < wordsDict.length; ++i) {
            if (wordsDict[i] == word1 || wordsDict[i] == word2) {
                if (idx != -1 && wordsDict[idx] != wordsDict[i]) {
                    res = Math.min(res, i - idx);
                }
                idx = i;
            }
        }
        return res;
    }

    /**
     * Understanding the following Solution
     * Two-Pointer Solution
     * ------------------------------------
     * class Solution:
     *     def shortestDistance(self, wordsDict: List[str], word1: str, word2: str) -> int:
     *         i = j = -1
     *         res = inf
     *         for k, w in enumerate(wordsDict):
     *             if w == word1:
     *                 i = k
     *             if w == word2:
     *                 j = k
     *             if i != -1 and j != -1:
     *                 res = min(res, abs(i - j))
     *         return res
     * --------------------------------------------
     * class Solution {
     * public:
     *     int shortestDistance(vector<string>& words, string word1, string word2) {
     *         int p1 = -1, p2 = -1, res = INT_MAX;
     *         for (int i = 0; i < words.size(); ++i) {
     *             if (words[i] == word1) p1 = i;
     *             else if (words[i] == word2) p2 = i;
     *             if (p1 != -1 && p2 != -1) res = min(res, abs(p1 - p2));
     *         }
     *         return res;
     *     }
     * };
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int res = Integer.MAX_VALUE;
        for (int k = 0, i = -1, j = -1; k < wordsDict.length; ++k) {
            if (wordsDict[k].equals(word1)) {
                i = k;
            }
            if (wordsDict[k].equals(word2)) {
                j = k;
            }
            if (i != -1 && j != -1) {
                res = Math.min(res, Math.abs(i - j));
            }
        }
        return res;
    }
}
