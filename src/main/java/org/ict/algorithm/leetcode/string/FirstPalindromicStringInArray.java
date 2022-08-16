package org.ict.algorithm.leetcode.string;

/**
 * Given an array of strings words,
 * return the first palindromic string in the array.
 * If there is no such string, return an empty string "".
 *
 * A string is palindromic if it reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abc","car","ada","racecar","cool"]
 * Output: "ada"
 * Explanation: The first string that is palindromic is "ada".
 * Note that "racecar" is also palindromic, but it is not the first.
 * Example 2:
 *
 * Input: words = ["notapalindrome","racecar"]
 * Output: "racecar"
 * Explanation: The first and only string that is palindromic is "racecar".
 * Example 3:
 *
 * Input: words = ["def","ghi"]
 * Output: ""
 * Explanation: There are no palindromic strings, so the empty string is returned.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists only of lowercase English letters.
 * @author sniper
 * @date 2022/8/16
 * LC2108
 */
public class FirstPalindromicStringInArray {

    public String firstPalindromeV2(String[] words) {
        String res = "";
        for (String word : words) {
            if (isPalindrome(word, 0, word.length() - 1)) {
                res = word;
                break;
            }
        }
        return res;
    }

    private boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;
            } else {
                lo++;
                hi--;
            }
        }
        return true;
    }

    public String firstPalindrome(String[] words) {
        String res = "";
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            String reversed = sb.append(word).reverse().toString();
            if (reversed.equals(word)) {
                res = word;
                break;
            }
            sb.setLength(0);
        }
        return res;
    }
}
