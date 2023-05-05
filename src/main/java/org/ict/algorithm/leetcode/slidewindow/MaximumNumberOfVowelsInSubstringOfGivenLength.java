package org.ict.algorithm.leetcode.slidewindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and an integer k,
 * return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 *
 *
 * Example 1:
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 *
 * Example 2:
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 *
 * Example 3:
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 *
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * 1 <= k <= s.length
 *
 * @author sniper
 * @date 06 May 2023
 * LC1456, Medium
 */
public class MaximumNumberOfVowelsInSubstringOfGivenLength {

    public static void main(String[] args) {
        String s = "leetcode";
        int k = 3;
        MaximumNumberOfVowelsInSubstringOfGivenLength instance = new MaximumNumberOfVowelsInSubstringOfGivenLength();
        instance.maxVowelsV1(s, k);
    }

    /**
     * class Solution:
     *   def maxVowels(self, s: str, k: int) -> int:
     *     ans = 0
     *     maxi = 0
     *     vowels = {'a', 'e', 'i', 'o', 'u'}
     *
     *     for i, c in enumerate(s):
     *       if c in vowels:
     *         maxi += 1
     *       if i >= k and s[i - k] in vowels:
     *         maxi -= 1
     *       ans = max(ans, maxi)
     *
     *     return ans
     * -------------------------------------
     * e.g. s = "leetcode", k = 3
     * i:0, c:'l', max:0, max:0, res:0
     * i:1, c:'e', max:1, max:1, res:1
     * i:2, c:'e', max:2, max:2, res:2
     * i:3, c:'t', max:2, max:2, res:2
     * i:4, c:'c', max:2, max:1, res:2
     * i:5, c:'o', max:2, max:1, res:2
     * i:6, c:'d', max:1, max:1, res:2
     * i:7, c:'e', max:2, max:2, res:2
     * @param s
     * @param k
     * @return
     */
    public int maxVowelsV1(String s, int k) {
        int res = 0;
        int max = 0;
        Set<Character> vowels = new HashSet<>();
        vowels.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                max++;

            }
            if (i >= k && vowels.contains(s.charAt(i - k))) {
                max--;
            }
            res = Math.max(res, max);
        }
        return res;
    }

    /**
     * Time Limit Exceeded
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        Set<Character> vowels = new HashSet<>();
        vowels.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int left = 0;
        int right = left + k;
        int res = 0;
        while (right <= s.length()) {
            String sub = s.substring(left, right);
            int cnt = 0;
            for (int i = 0; i < k; i++) {
                if (vowels.contains(sub.charAt(i))) {
                    cnt++;
                }
            }
            res = Math.max(res, cnt);
            left++;
            right = left + k;
        }
        return res;
    }
}
