package org.ict.algorithm.leetcode.string;

/**
 * Given an integer columnNumber,
 * return its corresponding column title as it appears in an Excel sheet.
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
 * Input: columnNumber = 1
 * Output: "A"
 * Example 2:
 *
 * Input: columnNumber = 28
 * Output: "AB"
 * Example 3:
 *
 * Input: columnNumber = 701
 * Output: "ZY"
 * Example 4:
 *
 * Input: columnNumber = 2147483647
 * Output: "FXSHRXW"
 *
 *
 * Constraints:
 *
 * 1 <= columnNumber <= 231 - 1
 * LC168
 */
public class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        System.out.println(convertToTitle(1));//A
        System.out.println(convertToTitle(28));//
        System.out.println(convertToTitle(701));//
    }

    public static String convertToTitle(int n) {
        char[] a = charArray();
        String s = "";
        if(n < 26) {
            s = a[n-1] + "";
        } else {
            s = a[n % 26] + "";
            
        }
        return s;
    }

    public static char[] charArray() {
        char[] a = new char[26];
        for (int i = 0; i < 26; i++) {
            a[i] = (char)(65 + i);//a's ascii code is 65
        }
        return a;
    }
}
