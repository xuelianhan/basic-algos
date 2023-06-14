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
