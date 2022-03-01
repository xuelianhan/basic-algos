package org.ict.algorithm.leetcode.string;

/**
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Given a string word, return true if the usage of capitals in it is right.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "USA"
 * Output: true
 * Example 2:
 *
 * Input: word = "FlaG"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 100
 * word consists of lowercase and uppercase English letters.
 * @author sniper
 * @date 2022/2/22
 * LC520
 */
public class DetectCapital {

    /**
     *
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        char[] arr = word.toCharArray();
        int upperCaseCnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isUpperCase(arr[i])) {
                upperCaseCnt++;
            }
        }
        /**
         * ffffffffffffffffffffF
         */
        if (upperCaseCnt == arr.length || upperCaseCnt == 0 || (upperCaseCnt == 1 && Character.isUpperCase(arr[0]))) {
            return true;
        }
        return false;
    }
}
