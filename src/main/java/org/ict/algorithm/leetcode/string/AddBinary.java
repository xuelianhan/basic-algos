package org.ict.algorithm.leetcode.string;


/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 *
 * @author  sniper
 * LC67
 */
public class AddBinary {

    public static void main(String[] args) {
        int s = 60;
        char i = '0';
        char j = '1';
        System.out.println((int)'0');//48
        System.out.println((int)'1');//49
        System.out.println(s + i);//108
        System.out.println(s + j);//109
        System.out.println(s - '0' + i);//60
        System.out.println(s - '0' +j);//61
        System.out.println(8 % 2);//0
        System.out.println(8 % 2 + '0');//48
        System.out.println((char)(8 % 2 + '0'));//0

        System.out.println(9 % 2);//1
        System.out.println(9 % 2 + '0');//49
        System.out.println((char)(9 % 2 + '0'));//1
    }

    public String addBinaryV2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public String addBinary(String a, String b) {
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
            result = (char)(s % 2  + '0') + result;
            //result = s % 2 + result;// this is right too.
            /**
             * Compute the carry
             */
            s /= 2;

            /**
             * Move to the next digits
             */
            i--;j--;
        }
        return result;
    }

    private int binaryToDecimal(String s) {
        double decimal = 0;
        char[] a = s.toCharArray();
        //from last or from first, both ok
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == '1') {
                decimal = decimal + Math.pow(2, a.length - 1 - i);
            }
        }
        return (int)decimal;
    }

    /**
     * The following solution may exceed the range for some cases
     * a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
     * b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"
     * @deprecated
     * @param a
     * @param b
     * @return
     */
    public String addBinaryWrong(String a, String b) {
        int da = Integer.parseInt(a, 2);
        int db = Integer.parseInt(b, 2);
        int c = da + db;
        return Integer.toBinaryString(c);
    }
}
