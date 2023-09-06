package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed string s and a dictionary of words dictionary.
 * You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary.
 * There may be some extra characters in s which are not present in any of the substrings.
 *
 * Return the minimum number of extra characters left over if you break up s optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
 * Output: 1
 * Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8.
 * There is only 1 unused character (at index 4), so we return 1.
 *
 * Example 2:
 *
 * Input: s = "sayhelloworld", dictionary = ["hello","world"]
 * Output: 3
 * Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12.
 * The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 50
 * 1 <= dictionary.length <= 50
 * 1 <= dictionary[i].length <= 50
 * dictionary[i] and s consists of only lowercase English letters
 * dictionary contains distinct words
 * @author sniper
 * @date 04 Sep 2023
 * LC2707, Medium
 */
public class ExtraCharactersInAString {

    public int minExtraCharV3(String s, String[] dictionary) {
        return 0;
    }

    public int minExtraCharV2(String s, String[] dictionary) {
        return 0;
    }

    /**
     * Bottom-Up Solution
     * Time Cost 74ms
     *
     * @author MohakHarjani
     * @see <a href="https://leetcode.com/problems/extra-characters-in-a-string/solutions/3568666/recursion-top-down-bottom-up-explained-easy-to-understand"></a>
     * @param s
     * @param dictionary
     * @return
     */
    public int minExtraCharV1(String s, String[] dictionary) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : dictionary) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            String curStr = "";
            int minExtra = s.length();
            for (int cutIdx = i; cutIdx < n; cutIdx++) {
                /**
                 * curStr will be a string from (i to cutIdx)
                 */
                curStr += s.charAt(cutIdx);
                int currExtra = (map.containsKey(curStr)) ? 0 : curStr.length();
                int nextExtra = dp[cutIdx + 1];
                int totalExtra = currExtra + nextExtra;
                minExtra = Math.min(minExtra, totalExtra);
            }
            dp[i] = minExtra;
        }
        return dp[0];
    }

    /**
     * Top-Down Memorization Solution
     * Time Cost 68ms
     * @author MohakHarjani
     * @see <a href="https://leetcode.com/problems/extra-characters-in-a-string/solutions/3568666/recursion-top-down-bottom-up-explained-easy-to-understand"></a>
     * @param s
     * @param dictionary
     * @return
     */
    public int minExtraChar(String s, String[] dictionary) {
        Map<String, Integer> mp = new HashMap<>();
        for (String word : dictionary) {
            mp.put(word, mp.getOrDefault(word, 0) + 1);
        }
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        int ans = helper(s, mp, dp,0);
        return ans;
    }

    private int helper(String s, Map<String, Integer> map, int[] dp, int index) {
        if (index >= s.length()) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }

        String curStr = "";
        int minExtra = s.length();
        for (int cutIdx = index; cutIdx < s.length(); cutIdx++) {
            /**
             * curStr will be a string from (index to cutIdx)
             */
            curStr += s.charAt(cutIdx);
            /**
             * If string not in dictionary, we have to delete as they are extra chars
             */
            int currExtra = (map.containsKey(curStr)) ? 0 : curStr.length();
            int nextExtra = helper(s, map, dp, cutIdx + 1);
            int totalExtra = currExtra + nextExtra;

            minExtra = Math.min(minExtra, totalExtra);
        }
        dp[index] = minExtra;
        return minExtra;
    }


}
