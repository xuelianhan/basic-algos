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
        //System.out.println((int)'A');//65
        //System.out.println((int)'Z');//90
        //System.out.println(convertToTitle(1));//A
        //System.out.println(convertToTitle(25));//Y
        //System.out.println(convertToTitle(26));//Z
        //System.out.println(convertToTitle(27));//AA
        //System.out.println(convertToTitle(28));//AB
        //System.out.println(convertToTitle(701));//ZY
        //System.out.println(convertToTitle(5000));//GJH
        System.out.println(convertToTitle(52));//AZ
        //System.out.println(convertToTitle(2147483647));//FXSHRXW
    }

    public static String convertToTitle(int n) {
        if(n <= 26) {
            return String.valueOf((char)(n - 1 + 'A'));
        }
        String s = "";
        while (n > 26) {
            // store remainder in r;
            int r = n % 26 ;
            n = n / 26;
            if (r > 0) {
                s = convertNumberToAlphabet(r) + s;
            } else {

            }
        }
        if (n >= 1) {
            s = convertNumberToAlphabet(n) + s;
        }
        System.out.println("s:" + s);
        return s;
    }

    public static String convertNumberToAlphabet(int n) {
        return String.valueOf((char)(n - 1 + 'A'));
    }
}
