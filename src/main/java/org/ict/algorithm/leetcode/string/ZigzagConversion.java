package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 *
 * Input: s = "A", numRows = 1
 * Output: "A"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 * @author sniper
 * @date 09 May 2023
 * LC6, Medium, frequency=2
 */
public class ZigzagConversion {

    /**
     * Understanding the following solution
     * Time Cost 6ms
     * ------------------------------------
     * class Solution {
     * public:
     *     string convert(string s, int numRows) {
     *         vector<vector<char>> rows(numRows);
     *         int direction = (numRows == 1) - 1;
     *
     *         int r = 0;
     *         for (const char c : s) {
     *             rows[r].push_back(c);
     *             if (r == 0 || r == numRows - 1) {
     *                 direction *= -1;
     *             }
     *             r += direction;
     *         }
     *
     *         string res;
     *         for (const vector<char>& row : rows) {
     *             for (const char c : row) {
     *                 res += c;
     *             }
     *         }
     *         return res;
     *     }
     * };
     * @param s
     * @param numRows
     * @return
     */
    public String convertV3(String s, int numRows) {
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int r = 0;
        int direction = numRows == 1 ? 0 : -1;
        for (char ch : s.toCharArray()) {
            rows[r].append(ch);
            if (r == 0 || r == numRows - 1) {
                direction *= -1;
            }
            r += direction;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder b : rows) {
            res.append(b.toString());
        }
        return res.toString();
    }

    /**
     * Time Cost 24ms
     * @param s
     * @param numRows
     * @return
     */
    public String convertV2(String s, int numRows) {
        List<Character>[] rows = new List[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new ArrayList<>();
        }

        int r = 0;
        int direction = numRows == 1 ? 0 : -1;
        for (char ch : s.toCharArray()) {
            rows[r].add(ch);
            if (r == 0 || r == numRows - 1) {
                direction *= -1;
            }
            r += direction;
        }

        StringBuilder res = new StringBuilder();
        for (List<Character> list : rows) {
            String t = list.stream().map(String::valueOf).collect(Collectors.joining());
            res.append(t);
        }
        return res.toString();
    }

    /**
     * Understanding the following solution
     * Time Cost 7ms
     * ---------------------------------------
     * class Solution:
     *     def convert(self, s: str, numRows: int) -> str:
     *         rows = [''] * numRows
     *         r = 0
     *         direction = (numRows == 1) - 1
     *         for c in s:
     *             rows[r] += c
     *             if r == 0 or r == numRows - 1:
     *                 direction *= -1
     *             r += direction
     *         return ''.join(rows)
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convertV1(String s, int numRows) {
        List<Character>[] rows = new List[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new ArrayList<>();
        }

        int r = 0;
        int direction = numRows == 1 ? 0 : -1;
        for (char ch : s.toCharArray()) {
            rows[r].add(ch);
            if (r == 0 || r == numRows - 1) {
                direction *= -1;
            }
            r += direction;
        }

        StringBuilder res = new StringBuilder();
        for (List<Character> list : rows) {
            for (char ch : list) {
                res.append(ch);
            }
        }
        return res.toString();
    }

    /**
     * Understanding the following solution
     * Time Cost 8ms
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }

        List<Character>[] rows = new List[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new ArrayList<>();
        }
        /**
         * r is the index to track which rows a character should be added to
         */
        int r = 0;
        /**
         * -1 means go up, 1 means go down
         */
        int direction = 1;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            rows[r].add(s.charAt(i));
            if (r == numRows - 1) {
                direction = -1;
            } else if (r == 0) {
                direction = 1;
            }
            r += direction;
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            List<Character> list = rows[i];
            for (char ch : list) {
                res.append(ch);
            }
        }
        return res.toString();
    }
}
