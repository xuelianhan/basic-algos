package org.ict.algorithm.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together.
 * 12 is written as XII, which is simply X + II.
 * The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII.
 * Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 3
 * Output: "III"
 * Explanation: 3 is represented as 3 ones.
 * Example 2:
 *
 * Input: num = 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * Example 3:
 *
 * Input: num = 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 *
 * Constraints:
 *
 * 1 <= num <= 3999
 * @author sniper
 * @date 20 Oct, 2022
 * LC12
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        int num = 3999;
        String res = intToRoman(num);
        System.out.println(res);
    }

    public static String intToRomanV2(int num) {
        /**
         * "", 1000, 2000, 3000
         */
        String M[] = {"", "M", "MM", "MMM"};
        /**
         * "", 100, 200, 300, 400, 500, 600, 700, 800, 900
         */
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        /**
         * "", 10, 20, 30, 40, 50, 60, 70, 80, 90
         */
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        /**
         * "", 1, 2, 3, 4, 5, 6, 7, 8, 9
         */
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

    /**
     * Understand the following solution.
     * @param num
     * @return
     */
    public String intToRomanV1(int num) {
        StringBuilder res = new StringBuilder();
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[]  values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int i = 0;
        while (num > 0) {
            while (num >= values[i]) {
                num -= values[i];
                res.append(roman[i]);
            }
            i++;
        }
        return res.toString();
    }

    public static String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        Map<Integer, String> map = romanMap();
        if (map.containsKey(num)) {
            return map.get(num);
        }
        while (num > 0) {
            if (num >= 1000) {
                num -= 1000;
                res.append("M");
            } else if (num >= 900 && num < 1000) {
                num -= 900;
                res.append("CM");
            } else if (num >= 500 && num < 900) {
                num -= 500;
                res.append("D");
            } else if (num >= 400 && num < 500) {
                num -= 400;
                res.append("CD");
            } else if (num >= 100 && num < 400) {
                num -= 100;
                res.append("C");
            } else if (num >= 90 && num < 100) {
                num -= 90;
                res.append("XC");
            } else if (num >= 50 && num < 90) {
                num -= 50;
                res.append("L");
            } else if (num >= 40 && num < 50) {
                num -= 40;
                res.append("XL");
            } else if (num >= 10 && num < 40) {
                num -= 10;
                res.append("X");
            } else if (num == 9) {
                num -= 9;
                res.append("IX");
            } else if (num >= 5 && num < 9) {
                num -= 5;
                res.append("V");
            } else if (num == 4) {
                num -= 4;
                res.append("IV");
            } else if (num >= 1 && num < 4) {
                num -= 1;
                res.append("I");
            }
        }
        return res.toString();
    }

    public static  Map<Integer, String> romanMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");
        return map;
    }
}
