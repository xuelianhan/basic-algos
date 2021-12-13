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
        //System.out.println((int)'A');
        //System.out.println(convertToTitle(1));//A
        //System.out.println(convertToTitle(27));//AA
        //System.out.println(convertToTitle(28));//AB
        System.out.println(convertToTitle(701));//ZY
    }

    public static String convertToTitle(int n) {
        String s = "";
        if(n < 26) {
            s = String.valueOf((char)(n - 1 + 'A'));
        } else {
           while (n > 0) {
               // store remainder in r;
               int r = n % 26;
               System.out.println("n:" + n + ", r:" + r + ", char:" +convertNumberToAlphabet(r));
               n = n / 26;
               s = convertNumberToAlphabet(r) + s;
           }
        }
        return s;
    }

    public static String convertNumberToAlphabet(int n) {
        return String.valueOf((char)(n - 1 + 'A'));
    }
}
