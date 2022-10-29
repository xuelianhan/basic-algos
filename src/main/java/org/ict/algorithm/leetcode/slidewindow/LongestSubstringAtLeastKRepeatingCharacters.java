package org.ict.algorithm.leetcode.slidewindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    public static void main(String[] args) {
        String s = "ababbc";
        int k = 2;
        longestSubstringV2(s, k);
    }

    /**
     * Description provided by ioctl.
     *
     * This problem prompts us to use two pointers method,
     * however it's quite difficult to decide the condition to expand (end++) and shrink (start++).
     * That's where the following posted solution rocks.
     *
     * Here are some explanations to understand the code:
     * how do we explore all possible solutions (substrings that satisfy given constraints) ?
     *
     * this post's solution explores this way
     * -- find all sub-strings which have "curUnique=1" unique character(s) and each character in the substring repeats at least "k" times
     * -- find all sub-strings which have "curUnique=2" unique character(s) and each character in the substring repeats at least "k" times
     * -- ....
     * -- find all sub-strings which have "curUnique=26" unique character(s) and each character in the substring repeats at least "k" times
     * -- and we're done! at curUnique = 26, we're done.
     * Take the max of all the above valid substrings (by tracking with res variable) -- that'll be our answer.
     * Details: (for a fixed curUnique)
     * Basically, we want to find a window (start to end) which has "curUnique" unique chars and if all curUnique occur at least K times,
     * we have a candidate solution
     * [Rules for window Expansion] so expand (end++) as long as the num of unique characters between 'start' to 'end' are curUnique or less (uniqueCnt <= curUnique)
     * during expansion, also track chars that are "countK" (which occur at least k)
     * end of the loop update res (max len of valid window which have curUnique unique chars and all h have at least k occurrences)
     * once we see (uniqueCnt == curUnique + 1), we just expanded our window with (curUnique+1)th unique char, so we should start to shrink now.
     * [Rules to window Shrink window] shrink as long as we have unique chars > curUnique (update countK, uniqueCnt, start along the way)
     * @param s
     * @param k
     * @return
     */
    public int longestSubstringV4(String s, int k) {
        int res = 0;
        for (int curUnique = 1; curUnique <= 26; curUnique++) {
            res = Math.max(res, slideWindow(s, k, curUnique));
        }
        return res;
    }

    public int longestSubstringV3(String s, int k) {
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            set.add(ch);
        }
        int res = 0;
        for (int curUnique = 1; curUnique <= set.size(); curUnique++) {
            res = Math.max(res, slideWindow(s, k, curUnique));
        }
        return res;
    }

    private int slideWindowV2(String s, int k, int curUnique) {
        int res = 0;
        int[] freq = new int[26];
        int start = 0, end = 0;
        int uniqueCnt = 0, countK = 0;
        while (end < s.length()) {
            if (uniqueCnt <= curUnique) {
                int idx = s.charAt(end) - 'a';
                freq[idx]++;
                // New unique character
                if (freq[idx] == 1) uniqueCnt++;
                // New character which occurs at least k times
                if (freq[idx] == k) countK++;
                // Expand window by incrementing end by 1
                end++;
            } else {
                int idx = s.charAt(start) - 'a';
                // Check if this character is present at least k times
                if (freq[idx] == k) countK--;
                // Check if this character is unique
                if (freq[idx] == 1) uniqueCnt--;
                freq[idx]--;
                // Shrink the window by incrementing start by 1
                start++;
            }

            if (uniqueCnt == curUnique && countK == curUnique) {
                res = Math.max(res, end - start);
            }
        }
        return res;
    }


    private int slideWindowV1(String s, int k, int curUnique) {
        int res = 0;
        int[] freq = new int[26];
        int start = 0, end = 0;
        int uniqueCnt = 0, countK = 0;
        while (end < s.length()) {
            if (uniqueCnt <= curUnique) {
                int idx = s.charAt(end) - 'a';
                freq[idx]++;
                // New unique character
                if (freq[idx] == 1) uniqueCnt++;
                // New character which occurs at least k times
                if (freq[idx] == k) countK++;
                // Expand window by incrementing end by 1
                end++;
            } else {
                int idx = s.charAt(start) - 'a';
                freq[idx]--;
                // Check if this character is present at least k times
                if (freq[idx] == k-1) countK--;
                // Check if this character is unique
                if (freq[idx] == 0) uniqueCnt--;
                // Shrink the window by incrementing start by 1
                start++;
            }

            if (uniqueCnt == curUnique && countK == curUnique) {
                res = Math.max(res, end - start);
            }
        }
        return res;
    }


    private int slideWindow(String s, int k, int curUnique) {
        int res = 0;
        int[] freq = new int[26];
        int start = 0, end = 0;
        int uniqueCnt = 0, countK = 0;
        while (end < s.length()) {
            if (uniqueCnt <= curUnique) {
                int idx = s.charAt(end) - 'a';
                // New unique character
                if (freq[idx] == 0) uniqueCnt++;
                freq[idx]++;
                // New character which occurs at least k times
                if (freq[idx] == k) countK++;
                // Expand window by incrementing end by 1
                end++;
            } else {
                int idx = s.charAt(start) - 'a';
                // Check if this character is present at least k times
                if (freq[idx] == k) countK--;
                freq[idx]--;
                // Check if this character is unique
                if (freq[idx] == 0) uniqueCnt--;
                // Shrink the window by incrementing start by 1
                start++;
            }

            if (uniqueCnt == curUnique && countK == curUnique) {
                res = Math.max(res, end - start);
            }
        }
        return res;
    }

    /**
     * Slide Window Solution
     *
     * find all sub-strings which have unique characters count(from 1 to unique) of character(s) and each character in the substring repeats at least "k" times.
     * return the max of the longest one.
     * @see <a href="https://www.geeksforgeeks.org/longest-substring-where-all-the-characters-appear-at-least-k-times-set-3/"></a>
     *
     * e.g. the whole process like this:
     * --------------------------------
     * [---] [---]  [---]  [---]  [---] max1
     * [---------]  [---------] max2
     * [----------------------] maxN
     * res = max(max1, max2, ..., maxN)
     *
     * e.g.s = "ababbc", k = 2.
     * unique = 3
     * 0 1 2 3 4 5
     * a b a b b c
     * curUnique:1, uniqueCnt:0, end:0, freq[0]:0 --> uniqueCnt:1, freq[0]:1, countK:0, end:1, window:a
     *              uniqueCnt:1, end:1, freq[1]:0 --> uniqueCnt:2, freq[1]:1, countK:0, end:2, window:ab
     *              uniqueCnt:2, start:0, freq[0]:1 --> freq[0]:0, uniqueCnt:2 --> uniqueCnt:1, countK:0, start:1, window:b
     *              uniqueCnt:1, end:2, freq[0]:0, uniqueCnt:1 --> uniqueCnt:2, freq[0]:1, countK:0, end:3, window:ba
     *              uniqueCnt:2, start:1, freq[1]:1 --> freq[1]:0, uniqueCnt:2 --> uniqueCnt:1, countK:0, start:2, window:a
     *              uniqueCnt:1, end:3, freq[1]:0 --> uniqueCnt:2, freq[1]:1, countK:0, end:4, window:ab
     *              uniqueCnt:2, start:2, freq[0]:1 --> freq[0]:0, uniqueCnt:2 --> uniqueCnt:1, countK:0, start:3, window:b
     *              uniqueCnt:1, end:4, freq[1]:1 --> freq[1]:2, countK:1, end:5, window:bb, res=max(0, 5-3)=2
     *              uniqueCnt:1, end:5, freq[2]:0 --> uniqueCnt:2, freq[2]:1, countK:1, end:6, window:bbc.
     *              end-while-loop of curUnique:1
     * curUnique:2,
     * curUnique:3
     * ------------------------------------------
     * uniqueCnt:1, end:1, freq[0]:1, countK:0, window:a
     * uniqueCnt:2, end:2, freq[1]:1, countK:0, window:ab
     * uniqueCnt:1, start:1, freq[0]:0, countK:0, window:b
     * uniqueCnt:2, end:3, freq[0]:1, countK:0, window:ba
     * uniqueCnt:1, start:2, freq[1]:0, countK:0, window:a
     * uniqueCnt:2, end:4, freq[1]:1, countK:0, window:ab
     * uniqueCnt:1, start:3, freq[0]:0, countK:0, window:b
     * uniqueCnt:1, end:5, freq[1]:2, countK:1, window:bb
     * uniqueCnt:1, start:3, countK:1, res:bb
     * uniqueCnt:2, end:6, freq[2]:1, countK:1, window:bbc
     * uniqueCnt:1, end:1, freq[0]:1, countK:0, window:a
     * uniqueCnt:2, end:2, freq[1]:1, countK:0, window:ab
     * uniqueCnt:2, end:3, freq[0]:2, countK:1, window:aba
     * uniqueCnt:2, end:4, freq[1]:2, countK:2, window:abab
     * uniqueCnt:2, start:0, countK:2, res:abab
     * uniqueCnt:2, end:5, freq[1]:3, countK:2, window:ababb
     * uniqueCnt:2, start:0, countK:2, res:ababb
     * uniqueCnt:3, end:6, freq[2]:1, countK:2, window:ababbc
     * uniqueCnt:1, end:1, freq[0]:1, countK:0, window:a
     * uniqueCnt:2, end:2, freq[1]:1, countK:0, window:ab
     * uniqueCnt:2, end:3, freq[0]:2, countK:1, window:aba
     * uniqueCnt:2, end:4, freq[1]:2, countK:2, window:abab
     * uniqueCnt:2, end:5, freq[1]:3, countK:2, window:ababb
     * uniqueCnt:3, end:6, freq[2]:1, countK:2, window:ababbc
     *
     * @param s
     * @param k
     * @return
     */
    public static int longestSubstringV2(String s, int k) {
        /**
         * Find the number of unique characters in String
         */
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            set.add(ch);
        }
        int res = 0;
        int[] freq = new int[26];
        /**
         * Iterate in range [1, unique]
         */
        for (int curUnique = 1; curUnique <= set.size(); curUnique++) {
            Arrays.fill(freq, 0);
            /**
             * Stores the start, and the end of the window
             * Stores the current number of unique characters and characters occurring at least K times
             */
            int start = 0, end = 0;
            int uniqueCnt = 0, countK = 0;
            while (end < s.length()) {
                if (uniqueCnt <= curUnique) {
                    int idx = s.charAt(end) - 'a';
                    // New unique character
                    if (freq[idx] == 0) uniqueCnt++;
                    freq[idx]++;
                    // New character which occurs at least k times
                    if (freq[idx] == k) countK++;
                    // Expand window by incrementing end by 1
                    end++;
                    System.out.println("uniqueCnt:" + uniqueCnt + ", end:" + end + ", freq[" + idx + "]:" + freq[idx] + ", countK:" + countK + ", window:" + s.substring(start, end));
                } else {
                    int idx = s.charAt(start) - 'a';
                    // Check if this character is present at least k times
                    if (freq[idx] == k) countK--;
                    freq[idx]--;
                    // Check if this character is unique
                    if (freq[idx] == 0) uniqueCnt--;
                    // Shrink the window by incrementing start by 1
                    start++;
                    System.out.println("uniqueCnt:" + uniqueCnt + ", start:" + start + ", freq[" + idx + "]:" + freq[idx] + ", countK:" + countK + ", window:" + s.substring(start, end));
                }
                /**
                 * If there are curUnique characters and each character is at least k times,
                 * update the overall maximum length
                 */
                if (uniqueCnt == curUnique && countK == curUnique) {
                    res = Math.max(res, end - start);
                    System.out.println("uniqueCnt:" + uniqueCnt + ", start:" + start + ", countK:" + countK + ", res:" + s.substring(start, end));
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
