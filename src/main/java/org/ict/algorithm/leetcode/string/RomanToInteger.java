package org.ict.algorithm.leetcode.string;

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
 * For example,
 * 2 is written as II in Roman numeral, just two one's added together.
 * 12 is written as XII, which is simply X + II.
 * The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII.
 * Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine,
 * which is written as IX.
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 * Constraints:
 *
 * 1 <= s.length <= 15
 * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 *
 * LC13, Easy, frequency=38
 * @author sniper
 */
public class RomanToInteger {

    public static void main(String[] args) {
        //String s = "III";//3,OK
        //String s = "IV";//4,OK
        //String s = "IX";//9,OK
        //String s = "LVIII";//58,Ok
        String s = "MCMXCIV";
        int result = romanToInt(s);
        System.out.println(result);
    }

    /**
     * count every Symbol and add its value to the sum, and minus the extra part of special cases.
     * @param s
     * @return
     */
    public int romanToIntV2(String s) {
        int sum = 0;
        if(s.indexOf("IV") != -1){sum -= 2;}
        if(s.indexOf("IX") != -1){sum -= 2;}
        if(s.indexOf("XL") != -1){sum -= 20;}
        if(s.indexOf("XC") != -1){sum -= 20;}
        if(s.indexOf("CD") != -1){sum -= 200;}
        if(s.indexOf("CM") != -1){sum -= 200;}

        char c[] = s.toCharArray();
        int count = 0;
        for(; count <= s.length()-1; count++){
            if (c[count] == 'M') {
                sum += 1000;
            }
            if (c[count] == 'D') {
                sum += 500;
            }
            if (c[count] == 'C') {
                sum += 100;
            }
            if (c[count] == 'L') {
                sum += 50;
            }
            if (c[count] == 'X') {
                sum += 10;
            }
            if (c[count] == 'V') {
                sum += 5;
            }
            if (c[count] == 'I') {
                sum += 1;
            }
        }
        return sum;
    }

    public static int romanToInt(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = romanMap();
        if (s.length() == 1) {
            map.get(s.charAt(0));
        }
        int total = 0;
        char[] a = s.toCharArray();
        int i = 0, j = 1;
        for (; i < a.length && j < a.length;) {
            if (a[i] == 'I' && a[j] == 'V') {
                total += 4;
                i+=2;
                j+=2;
            } else if (a[i] == 'I' && a[j] == 'X') {
                total += 9;
                i+=2;
                j+=2;
            } else if (a[i] == 'X' && a[j] == 'L') {
                total += 40;
                i+=2;
                j+=2;
            } else if (a[i] == 'X' && a[j] == 'C') {
                total += 90;
                i+=2;
                j+=2;
            } else if (a[i] == 'C' && a[j] == 'D') {
                total += 400;
                i+=2;
                j+=2;
            } else if (a[i] == 'C' && a[j] == 'M') {
                total += 900;
                i+=2;
                j+=2;
            } else {
                total += map.get(a[i]);
                i++;
                j++;
            }
        }
        if (i < a.length) {
            total += map.get(a[i]);
        }
        return total;
    }

    public static Map<Character, Integer> romanMap() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        return map;
    }
}
