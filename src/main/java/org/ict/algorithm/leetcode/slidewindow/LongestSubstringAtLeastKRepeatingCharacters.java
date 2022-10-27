package org.ict.algorithm.leetcode.slidewindow;

import java.util.Arrays;

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

    public int longestSubstringV3(String s, int k) {

    }


    /**
     * Slide Window Solution
     * 1.Store the number of unique characters in the string str in a variable, say unique.
     * 2.Store the frequency of each character in frequency array.
     * 3.
     *
     * @see <a href="https://www.geeksforgeeks.org/longest-substring-where-all-the-characters-appear-at-least-k-times-set-3/"></a>
     * @param s
     * @param k
     * @return
     */
    public int longestSubstringV2(String s, int k) {
        int res = 0;
        // Create a frequency map of the characters of the String
        int freq[] = new int[26];
        // Traverse the String, s
        for (int i = 0; i < s.length(); i++) {
            // Increment the frequency of the current character by 1
            freq[s.charAt(i) - 'a']++;
        }
        // Stores count of unique characters
        int unique = 0;
        // Find the number of unique characters in String
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                unique++;
            }
        }
        // Iterate in range [1, unique]
        for (int currUnique = 1; currUnique <= unique; currUnique++) {

            Arrays.fill(freq, 0);
            // Stores the start and the
            // end of the window
            int start = 0, end = 0;
            // Stores the current number of
            // unique characters and characters
            // occurring at least K times
            int cnt = 0, countK = 0;
            while (end < s.length()) {
                if (cnt <= currUnique)
                {
                    int ind = s.charAt(end) - 'a';

                    // New unique character
                    if (freq[ind] == 0)
                        cnt++;
                    freq[ind]++;

                    // New character which
                    // occurs atleast k times
                    if (freq[ind] == k)
                        countK++;

                    // Expand window by
                    // incrementing end by 1
                    end++;
                } else {
                    int ind = s.charAt(start) - 'a';
                    // Check if this character
                    // is present atleast k times
                    if (freq[ind] == k)
                        countK--;
                    freq[ind]--;

                    // Check if this character
                    // is unique
                    if (freq[ind] == 0)
                        cnt--;

                    // Shrink the window by
                    // incrementing start by 1
                    start++;
                }

                // If there are currUnique
                // characters and each character
                // is atleast k times
                if (cnt == currUnique && countK == currUnique) {
                    // Update the overall
                    // maximum length
                    res = Math.max(res, end - start);
                }
            }
        }

        return res;
    }


    /**
     * Divide And Conquer solution
     * Cost 63ms
     * @param s
     * @param k
     * @return
     */
    public int longestSubstringV1(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (k == 1) {
            return s.length();
        }
        char[] arr = s.toCharArray();
        return helper(arr, 0, arr.length, k);
    }

    private int helper(char[] arr, int start, int end, int k) {
        /**
         * substring is shorter than k
         */
        if (end - start < k) {
            return 0;
        }
        int[] freq = new int[26];
        for (int i = start; i < end; i++) {
            freq[arr[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            int fre = freq[i];
            if (fre > 0 && fre < k) {
                for (int j = start; j < end; j++) {
                    if (arr[j] == 'a' + i) {
                        return Math.max(helper(arr, start, j,  k), helper(arr, j + 1, end, k));
                    }
                }
            }
        }
        return end - start;
    }

    /**
     * Divide And Conquer solution
     * Cost 150ms
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
        if (k == 1) {
            return s.length();
        }
        char[] freq = new char[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            int fre = freq[i];
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
