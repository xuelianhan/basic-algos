package org.ict.algorithm.leetcode.string;

/**
 * The power of the string is the maximum length of a non-empty substring that contains only one unique character.
 *
 * Given a string s, return the power of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 2
 * Explanation: The substring "ee" is of length 2 with the character 'e' only.
 * Example 2:
 *
 * Input: s = "abbcccddddeeeeedcba"
 * Output: 5
 * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters.
 * @author sniper
 * @date 2022/8/12
 * LC1446
 */
public class ConsecutiveCharacters {

    public static void main(String[] args) {
        String s = "ccbccbb";
        //String s = "leetcode";
        //String s = "abbcccddddeeeeedcba";
        //String s = "ccc";
        //String s = "bacacccbba";
        int result = maxPower(s);
        System.out.println(result);
    }

    /**
     * Slide window
     * @param s
     * @return
     */
    public int maxPowerV2(String s) {
        int res = 0;
        for(int b = 0, e = 0; e < s.length(); e++) {
            if(s.charAt(b)!= s.charAt(e)) {
                b = e;
            }
            res = Math.max(res, e - b + 1);
        }
        return res;
    }

    /**
     * Two pointers
     * @param s
     * @return
     */
    public static int maxPower(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int max = 1;
        for (int i = 0, j = 1; i < s.length() && j < s.length();) {
            if (s.charAt(i) != s.charAt(j)) {
                max = Math.max((j-i), max);
                i = j;
            } else {
                max = Math.max(j - i + 1, max);
            }
            j++;
        }
        return max;
    }
}
