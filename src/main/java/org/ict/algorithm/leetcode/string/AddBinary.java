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

    public String addBinary(String a, String b) {
        String result = "";
        // digit sum
        int s = 0;
        int i = a.length() - 1, j = b.length() - 1;
        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();

        /**
         * traverse both strings from last position
         */
        while (i > 0 || j > 0) {

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
