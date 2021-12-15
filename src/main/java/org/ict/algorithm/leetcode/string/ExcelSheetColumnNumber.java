package org.ict.algorithm.leetcode.string;

/**
 * Given a string columnTitle that represents the column title as appear in an Excel sheet,
 * return its corresponding column number.
 *
 * For example:
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 *
 * Example 1:
 *
 * Input: columnTitle = "A"
 * Output: 1
 * Example 2:
 *
 * Input: columnTitle = "AB"
 * Output: 28
 * Example 3:
 *
 * Input: columnTitle = "ZY"
 * Output: 701
 * Example 4:
 *
 * Input: columnTitle = "FXSHRXW"
 * Output: 2147483647
 *
 *
 * Constraints:
 *
 * 1 <= columnTitle.length <= 7
 * columnTitle consists only of uppercase English letters.
 * columnTitle is in the range ["A", "FXSHRXW"].
 */
public class ExcelSheetColumnNumber {

    public static void main(String[] args) {
        System.out.println(titleToNumber("A"));
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("ZY"));
        System.out.println(titleToNumber("FXSHRXW"));
    }

    public static int titleToNumber(String s) {
        int result = 0;
        if (null == s || s.length() == 0) {
            return 0;
        }
        int i = s.length() - 1;
        while (i >= 0) {
            char c = s.charAt(i);
            int x = (c - 'A') + 1;
            result += x * Math.pow(26, s.length() - 1 - i);
            i--;
        }
        return result;
    }

}
