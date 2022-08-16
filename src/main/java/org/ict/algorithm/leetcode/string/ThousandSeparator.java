package org.ict.algorithm.leetcode.string;

/**
 * Given an integer n, add a dot (".") as the thousands separator and return it in string format.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 987
 * Output: "987"
 * Example 2:
 *
 * Input: n = 1234
 * Output: "1.234"
 *
 *
 * Constraints:
 *
 * 0 <= n <= 2^31 - 1
 * @author sniper
 * @date 2022/8/15
 * LC1556
 */
public class ThousandSeparator {

    public static void main(String[] args) {
        //int n = 1234;//ok
        //int n = 12345;//ok
        //int n = 123456;//ok
        //int n = 1234567;//ok
        //int n = 12345678;
        //int n = Integer.MAX_VALUE;
        int n = 84849284;
        String result = thousandSeparatorV2(n);
        System.out.println(result);
    }

    public static String thousandSeparatorV2(int n){
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        for (int i = sb.length() - 3; i > 0; i -= 3) {
            sb.insert(i, '.');
        }
        return sb.toString();
    }

    public static String thousandSeparator(int n) {
        if (n >= 0 && n <= 999) {
            return String.valueOf(n);
        }
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder();
        /**
         * Compute dot count with the length of n
         */
        int dotCnt = (s.length() % 3 == 0 ? s.length() / 3 - 1 : n / 3);
        int cnt = 0;
        int i = s.length() - 1;
        for (; i >= 2; i -= 3) {
            /**
             * equal operation should appear here.
             */
            if (cnt >= dotCnt) {
                break;
            }
            sb.append(s.charAt(i));
            sb.append(s.charAt(i - 1));
            sb.append(s.charAt(i - 2));
            sb.append('.');
            cnt++;
        }
        /**
         * complement the remained digits not accessed
         */
        while (i >= 0) {
            sb.append(s.charAt(i));
            i--;
        }
        return sb.reverse().toString();
    }

}
