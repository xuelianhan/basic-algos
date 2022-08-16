package org.ict.algorithm.leetcode.string;

/**
 * Given a string s consisting of only the characters 'a' and 'b',
 * return true if every 'a' appears before every 'b' in the string. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabbb"
 * Output: true
 * Explanation:
 * The 'a's are at indices 0, 1, and 2, while the 'b's are at indices 3, 4, and 5.
 * Hence, every 'a' appears before every 'b' and we return true.
 * Example 2:
 *
 * Input: s = "abab"
 * Output: false
 * Explanation:
 * There is an 'a' at index 2 and a 'b' at index 1.
 * Hence, not every 'a' appears before every 'b' and we return false.
 * Example 3:
 *
 * Input: s = "bbb"
 * Output: true
 * Explanation:
 * There are no 'a's, hence, every 'a' appears before every 'b' and we return true.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s[i] is either 'a' or 'b'.
 * @author sniper
 * @date 2022/8/16
 * LC2124
 */
public class CheckIfAllAAppearsBeforeAllB {

    public boolean checkStringV3(String s) {
        return !s.contains("ba");
    }

    public boolean checkString(String s) {
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkStringV2(String s) {
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i - 1) == 'b' && s.charAt(i) == 'a') {
                return false;
            }
        }
        return true;
    }
}
