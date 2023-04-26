package org.ict.algorithm.leetcode.twopointers;

/**
 * Description
 * Given an array of strings wordsDict and two strings that already exist in the array word1 and word2,
 * return the shortest distance between the occurrence of these two words in the list.
 *
 * Note that word1 and word2 may be the same.
 * It is guaranteed that they represent two individual words in the list.
 *
 * Example 1:
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * Output: 1
 *
 * Example 2:
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
 * Output: 3
 *
 *
 * Constraints:
 * 1 <= wordsDict.length <= 10^5
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] consists of lowercase English letters.
 * word1 and word2 are in wordsDict.
 * @author sniper
 * @date 26 Apr, 2023
 * LC245, Medium
 */
public class ShortestWordDistanceIII {

    public static void main(String[] args) {
        String[] wordsDict = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "makes";
        String word2 = "makes";

        ShortestWordDistanceIII instance = new ShortestWordDistanceIII();
        int res = instance.shortestWordDistanceV1(wordsDict, word1, word2);
        System.out.println(res);
    }

    public int shortestWordDistanceV1(String[] wordsDict, String word1, String word2) {
        int i = -1;
        int res = wordsDict.length;
        for (int k = 0; k < wordsDict.length; k++) {
            if (wordsDict[k].equals(word1) || wordsDict[k].equals(word2)) {
                if (i != -1 && (word1.equals(word2) || !wordsDict[k].equals(wordsDict[i]))) {
                    res = Math.min(res, k - i);
                }
                i = k;
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int res = wordsDict.length;
        if (word1.equals(word2)) {
            for (int i = -1, k = 0; k < wordsDict.length; k++) {
                if (wordsDict[k].equals(word1)) {
                    if (i != -1) {
                        res = Math.min(res, k - i);
                    }
                    i = k;
                }
            }
        } else {
            for (int i = -1, j = -1, k = 0; k < wordsDict.length; k++) {
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
        }
        return res;
    }
}
