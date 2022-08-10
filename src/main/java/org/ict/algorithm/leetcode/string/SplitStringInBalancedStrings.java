package org.ict.algorithm.leetcode.string;

import java.util.Arrays;

/**
 * Balanced strings are those that have an equal quantity of 'L' and 'R' characters.
 *
 * Given a balanced string s, split it into some number of substrings such that:
 *
 * Each substring is balanced.
 * Return the maximum number of balanced strings you can obtain.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "RLRRLLRLRL"
 * Output: 4
 * Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
 * Example 2:
 *
 * Input: s = "RLRRRLLRLL"
 * Output: 2
 * Explanation: s can be split into "RL", "RRRLLRLL", each substring contains same number of 'L' and 'R'.
 * Note that s cannot be split into "RL", "RR", "RL", "LR", "LL", because the 2nd and 5th substrings are not balanced.
 * Example 3:
 *
 * Input: s = "LLLLRRRR"
 * Output: 1
 * Explanation: s can be split into "LLLLRRRR".
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 1000
 * s[i] is either 'L' or 'R'.
 * s is a balanced string.
 * @author sniper
 * @date 2022/8/10
 * LC1221
 */
public class SplitStringInBalancedStrings {

    public static void main(String[] args) {
        String s = "LLLLRRRR";
        int result = balancedStringSplit(s);
        System.out.println(result);
    }

    public static int balancedStringSplit(String s) {
        int cnt = 0;
        int[] chars = new int[2];
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                chars[0]++;
            }
            if (s.charAt(i) == 'L') {
                chars[1]++;
            }
            if (chars[0] == chars[1] && chars[0] != 0) {
                Arrays.fill(chars, 0);
                cnt++;
            }
        }
        return Math.max(1, cnt);
    }
}
