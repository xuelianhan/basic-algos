package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none),
 * followed by some number of 1's (also possibly none).
 *
 * You are given a binary string s.
 * You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 *
 * Return the minimum number of flips to make s monotone increasing.
 *
 *
 * Example 1:
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 *
 * Example 2:
 * Input: s = "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 *
 * Example 3:
 * Input: s = "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 * @author sniper
 * @date 17 Jan, 2023
 * LC926, Medium
 */
public class FlipStringToMonotoneIncreasing {

    /**
     * Time Cost 9ms
     * e.g.s = "10"
     * i:0, cnt1:0 --> cnt1:1, cnt0:0, cnt0 = min(0, 1) = 0
     * i:1, cnt0:0 --> cnt0:1, cnt1:1, cnt0 = min(1, 1) = 1
     * return cnt0:1
     *
     * e.g.s = "01"
     * i:0, cnt0:0 --> cnt0:1, cnt1:0, cnt0 = min(1, 0) = 0
     * i:1, cnt1:0 --> cnt1:1, cnt0:0, cnt0 = min(0, 1) = 0
     * return cnt0:0
     *
     * e.g.s = "010110"
     * i:0, cnt0:0 --> cnt0:1, cnt1:0, cnt0 = min(1, 0) = 0
     * i:1, cnt1:0 --> cnt1:1, cnt0:0, cnt0 = min(0, 1) = 0
     * i:2, cnt0:0 --> cnt0:1, cnt1:1, cnt0 = min(1, 1) = 1
     * i:3, cnt1:1 --> cnt1:2, cnt0:1, cnt0 = min(1, 2) = 1
     * i:4, cnt1:2 --> cnt1:3, cnt0:1, cnt0 = min(1, 3) = 1
     * i:5, cnt0:1 --> cnt0:2, cnt1:3, cnt0 = min(2, 3) = 2
     * return cnt0:2
     *
     * e.g.s = "00110"
     * i:0, cnt0:0 --> cnt0:1, cnt1:0, cnt0 = min(1, 0) = 0
     * i:1, cnt0:0 --> cnt0:1, cnt1:0, cnt0 = min(0, 0) = 0
     * i:2, cnt1:0 --> cnt1:1, cnt0:0, cnt0 = min(0, 1) = 0
     * i:3, cnt1:1 --> cnt1:2, cnt0:0, cnt0 = min(0, 2) = 0
     * i:4, cnt0:0 --> cnt0:1, cnt1:2, cnt0 = min(1, 2) = 1
     * return cnt0:1
     *
     * This is a typical case of DP.
     * Let's see the sub-question of DP first.
     * Suppose that you have a string s,
     * and the solution to the mono increase question has been already solved.
     * That is, for string s, cnt0 flips are required for the string,
     * and there were cnt1 as '1's in the original string s.
     *
     * Let's see the next step of DP.
     * Within the string s, a new incoming character,
     * say ch, is appended to the original string.
     * The question is that, how should cnt0 be updated, based on the sub-question? We should discuss it case by case.
     *
     * When '1' comes, no more flip should be applied, since '1' is appended to the tail of the original string.
     * When '0' comes, things become a little complicated.
     * There are two options for us: flip the newly appended '0' to '1',
     * after cnt0 flips for the original string; or flip cnt1 '1' in the original string to '0'.
     * Hence, the result of the next step of DP, in the '0' case, is that min(cnt0 + 1, cnt1);
     * @author LiamHuang
     * @see <a href="https://leetcode.com/problems/flip-string-to-monotone-increasing/solutions/189751/c-one-pass-dp-solution-0ms-o-n-o-1-one-line-with-explaination"></a>
     * @param s
     * @return
     */
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int cnt0 = 0;
        int cnt1 = 0;
        /**
         * Notice we should scan from 0 to n - 1, not the reverse order: n - 1 to 0
         */
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                cnt0++;
            } else {
                cnt1++;
            }
            /**
             * Only change cn0 at each round.
             */
            cnt0 = Math.min(cnt0, cnt1);
        }
        return cnt0;
    }
}
