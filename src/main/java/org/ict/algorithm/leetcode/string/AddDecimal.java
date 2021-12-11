package org.ict.algorithm.leetcode.string;

/**
 * @author sniper
 * similar like LC67
 */
public class AddDecimal {

    public static void main(String[] args) {
        String a = "121";
        String b = "121";
        String c = addDecimal(a, b);
        System.out.println(c);
    }

    public static String addDecimal(String a, String b) {
        String result = "";
        // digit sum
        int s = 0;
        int i = a.length() - 1, j = b.length() - 1;
        /**
         * traverse both strings from last position
         * char '0' ascii code is 48
         * char '1' ascii code is 49
         * So sum of char and int must be subtracted with char '0'
         * s == 1 means recently sum has carry
         */
        while (i >= 0 || j >= 0 || s == 1) {
            /**
             * add the digit of A if exist
             *
             */
            s += (i >= 0) ? a.charAt(i) - '0': 0;
            /**
             * add the digit of B if exist
             */
            s += (j >= 0) ? b.charAt(j) - '0': 0;
            /**
             * if s is 1 or 3 then add 1 to result
             */
            result = (char)(s % 10  + '0') + result;
            /**
             * Compute the carry
             */
            s /= 10;

            /**
             * Move to the next digits
             */
            i--;j--;
        }
        return result;
    }
}
