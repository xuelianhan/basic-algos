package org.ict.algorithm.leetcode.math;

/**
 * Given two non-negative integers, num1 and num2 represented as string,
 * return the sum of num1 and num2 as a string.
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 *
 * Example 1:
 * Input: num1 = "11", num2 = "123"
 * Output: "134"
 *
 * Example 2:
 * Input: num1 = "456", num2 = "77"
 * Output: "533"
 *
 * Example 3:
 * Input: num1 = "0", num2 = "0"
 * Output: "0"
 *
 * Constraints:
 * 1 <= num1.length, num2.length <= 10^4
 * num1 and num2 consist of only digits.
 * num1 and num2 don't have any leading zeros except for the zero itself.
 * @author sniper
 * similar like LC67
 * LC415, Easy, frequency=24
 */
public class AddStrings {

    public static void main(String[] args) {
        String a = "121";
        String b = "121";
        String c = addStrings(a, b);
        System.out.println(c);
    }

    public String addStringsV2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static String addStrings(String a, String b) {
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
