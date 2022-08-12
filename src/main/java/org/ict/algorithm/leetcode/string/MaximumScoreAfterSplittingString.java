package org.ict.algorithm.leetcode.string;

/**
 * Given a string s of zeros and ones,
 * return the maximum score after splitting the string into two non-empty substrings
 * (i.e. left substring and right substring).
 *
 * The score after splitting a string is the number of zeros
 * in the left substring plus the number of ones in the right substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "011101"
 * Output: 5
 * Explanation:
 * All possible ways of splitting s into two non-empty substrings are:
 * left = "0" and right = "11101", score = 1 + 4 = 5
 * left = "01" and right = "1101", score = 1 + 3 = 4
 * left = "011" and right = "101", score = 1 + 2 = 3
 * left = "0111" and right = "01", score = 1 + 1 = 2
 * left = "01110" and right = "1", score = 2 + 1 = 3
 * Example 2:
 *
 * Input: s = "00111"
 * Output: 5
 * Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
 * Example 3:
 *
 * Input: s = "1111"
 * Output: 3
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 500
 * The string s consists of characters '0' and '1' only.
 * @author sniper
 * @date 2022/8/12
 * LC1422
 */
public class MaximumScoreAfterSplittingString {

    public static void main(String[] args) {
        String s = "1111";
        int result = maxScore(s);
        System.out.println(result);
    }

    /**
     * Solution provided by vikrant chavan
     * This can be done in single pass as below.
     * With this approach we do not know the number of ones on right while iterating,
     * so I have added TotalOne's to the result before returning.
     *
     * Logic behind this -
     * Result = Max of (ZerosOnLeft + OnesOnRight)
     * = Max of (ZerosOnLeft + (TotalOnes - OnesOnLeft))
     * = Max of (ZerosOnLeft - OnesOnLeft) + TotalOnes (as TotalOnes is constant)
     * @param s
     * @return
     */
    public int maxScoreV2(String s) {
        int zeros = 0, ones = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            } else {
                ones++;
            }
            if (i != s.length()-1) {
                max = Math.max(zeros - ones, max);
            }
        }
        return max + ones;
    }

    public static int maxScore(String s) {
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);
            int leftCnt = countNumber(left, true);
            int rightCnt = countNumber(right, false);
            if ((leftCnt + rightCnt) > max) {
                max = leftCnt + rightCnt;
            }
        }
        return max;
    }

    public static int countNumber(String input, boolean left) {
        int count = 0;
        for (char c : input.toCharArray()) {
            if (left) {
                if (c == '0') {
                    count++;
                }
            } else {
                if (c == '1') {
                    count++;
                }
            }
        }
        return count;
    }
}
