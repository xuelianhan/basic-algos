package org.ict.algorithm.leetcode.string;

/**
 * You are given a string s, where every two consecutive vertical bars '|' are grouped into a pair.
 * In other words, the 1st and 2nd '|' make a pair, the 3rd and 4th '|' make a pair, and so forth.
 *
 * Return the number of '*' in s, excluding the '*' between each pair of '|'.
 *
 * Note that each '|' will belong to exactly one pair.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "l|*e*et|c**o|*de|"
 * Output: 2
 * Explanation: The considered characters are underlined: "l|*e*et|c**o|*de|".
 * The characters between the first and second '|' are excluded from the answer.
 * Also, the characters between the third and fourth '|' are excluded from the answer.
 * There are 2 asterisks considered. Therefore, we return 2.
 * Example 2:
 *
 * Input: s = "iamprogrammer"
 * Output: 0
 * Explanation: In this example, there are no asterisks in s. Therefore, we return 0.
 * Example 3:
 *
 * Input: s = "yo|uar|e**|b|e***au|tifu|l"
 * Output: 5
 * Explanation: The considered characters are underlined: "yo|uar|e**|b|e***au|tifu|l".
 * There are 5 asterisks considered. Therefore, we return 5.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters, vertical bars '|', and asterisks '*'.
 * s contains an even number of vertical bars '|'.
 * @author sniper
 * @date 13 Aug, 2022
 * LC2315
 */
public class CountAsterisks {

    public static void main(String[] args) {
        String s = "l|*e*et|c**o|*de|";
        int result = countAsterisks(s);
        System.out.println(result);
    }


    /**
     * Solution provided by lee215
     * Parse the input, if currently met even bars, we count *.
     * @param s
     * @return
     */
    public int countAsterisksV2(String s) {
        int res = 0, bars = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '*' && bars % 2 == 0) {
                res++;
            }
            if (s.charAt(i) == '|') {
                bars++;
            }
        }
        return res;
    }

    public static int countAsterisks(String s) {
        boolean betweenVertical = false;
        int cnt = 0;
        int verticalCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*' && !betweenVertical) {
                cnt++;
            }
            if (c == '|') {
                verticalCnt++;
                if (verticalCnt % 2 != 0) {
                    betweenVertical = true;
                } else {
                    betweenVertical = false;
                }
            }
        }
        return cnt;
    }
}
