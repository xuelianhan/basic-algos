package org.ict.algorithm.leetcode.slidewindow;

/**
 * Given a string s and an integer k,
 * return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^5
 * @author sniper
 * @date 24 Oct, 2022
 * LC395
 */
public class LongestSubstringAtLeastKRepeatingCharacters {


    public int longestSubstringV1(String s, int k) {
        int res = 0;
        int[] map = new int[26];
        for (char ch : s.toCharArray()) {
            map[ch]++;
        }

        int left = 0, right = 0;
        while (right < s.length()) {
            char rc = s.charAt(right);
            map[rc]++;
            right++;

        }
        return res;
    }

    /**
     * Divide And Conquer solution
     *
     * If a character is less than K in the string,
     * it must not be in the longest substring,
     * thus, we can use this character to divide the string into two halves and recursively conquers/solves it.
     *
     * The time complexity is O(N^2) as the splitting could take N times and for each split,
     * we need to count the frequencies of characters.
     * Although in most/practical cases, the algorithm performs better than O(N^2).
     * The space complexity is O(N).
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] map = new char[26];
        for (char ch : s.toCharArray()) {
            map[ch-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            int fre = map[i];
            /**
             * when fre == 0, i + 'a' not exist in s
             */
            if (fre > 0 && fre < k) {
                int j = 0;
                while (j < s.length() && s.charAt(j) != 'a' + i) {
                    j++;
                }
                return Math.max(longestSubstring(s.substring(0, j), k), longestSubstring(s.substring(j + 1), k));
            }
        }
        return s.length();
    }
}
