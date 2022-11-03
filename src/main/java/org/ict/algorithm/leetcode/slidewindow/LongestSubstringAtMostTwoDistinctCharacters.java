package org.ict.algorithm.leetcode.slidewindow;

/**
 * Given a string s , find the length of the longest substring t that contains at most 2 distinct characters.
 * Example 1:
 *
 * Input: "eceba"
 * Output: 3
 * Explanation: "ece" which its length is 3.
 *
 * Example 2:
 *
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: "aabbb" which its length is 5.
 *
 * @author sniper
 * @date 03 Nov, 2022
 * LC159
 */
public class LongestSubstringAtMostTwoDistinctCharacters {

    public static void main(String[] args) {
        String s = "ccaabbb";
        int result = lengthOfLongestSubstringTwoDistinctV2(s);
        System.out.println(result);
    }


    /**
     * Slide Window with For-While-Loop
     * @see <a href="https://leetcode.ca/2016-05-07-159-Longest-Substring-with-At-Most-Two-Distinct-Characters/"></a>
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringTwoDistinctV2(String s) {
        int ans = 0;
        int distinct = 0;
        int[] count = new int[128];
        for (int left = 0, right = 0; right < s.length(); right++) {
            count[s.charAt(right)]++;
            if (count[s.charAt(right)] == 1) {
                distinct++;
            }
            /**
             * shrink the window size
             * both distinct > 2 and distinct == 3 are ok here.
             */
            while (distinct > 2) {
                count[s.charAt(left)]--;
                if (count[s.charAt(left)] == 0) {
                    distinct--;
                }
                left++;
            }
            /**
             * update the window size
             */
            ans = Math.max(ans, right - left + 1);
            /**
             * expand the window in for-loop
             */
        }
        return ans;
    }

    public static int lengthOfLongestSubstringTwoDistinctV1(String s) {
        int ans = 0;
        int distinct = 0;
        int[] count = new int[128];
        int left = 0, right = 0;

        while (right < s.length()) {
            count[s.charAt(right)]++;
            if (count[s.charAt(right)] == 1) {
                distinct++;
            }
            /**
             * shrink the window size
             *  both distinct == 3 and distinct > 2 are ok here.
             */
            while (distinct == 3) {
                count[s.charAt(left)]--;
                if (count[s.charAt(left)] == 0) {
                    distinct--;
                }
                left++;
            }
            /**
             * expand the window
             */
            right++;
            /**
             * update the window size
             */
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    /**
     * Slide Window with While-While-Loop
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int ans = 0;
        int distinct = 0;
        int[] count = new int[128];
        int left = 0, right = 0;

        while (right < s.length()) {
            count[s.charAt(right)]++;
            if (count[s.charAt(right)] == 1) {
                distinct++;
            }
            /**
             * shrink the window size
             *  both distinct == 3 and distinct > 2 are ok here.
             */
            while (distinct == 3) {
                count[s.charAt(left)]--;
                if (count[s.charAt(left)] == 0) {
                    distinct--;
                }
                left++;
            }
            /**
             * update the window size
             */
            ans = Math.max(ans, right - left + 1);
            /**
             * expand the window
             */
            right++;
        }

        return ans;
    }
}
