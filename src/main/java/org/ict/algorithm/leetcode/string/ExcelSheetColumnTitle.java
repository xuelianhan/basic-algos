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
        //System.out.println(convertToTitle(52));//AZ
        System.out.println(convertToTitle(28*26));//AAZ
        //System.out.println(convertToT     itle(2147483647));//FXSHRXW
    }

    public static String convertToTitleV2(int n) {
        return n == 0 ? "" : convertToTitleV2((n - 1) / 26) + (char) ((n - 1) % 26 + 'A');
    }

    public static String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while(n > 0){
            //very important, this convert n to n - 1.
            // n = n - 1;
            n--;
            //insert at the head
            result.insert(0, (char)('A' + n % 26));
            n /= 26;
        }
        return result.toString();
    }
}
