package org.ict.algorithm.leetcode.string;

/**
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 *
 * A defanged IP address replaces every period "." with "[.]".
 *
 *
 *
 * Example 1:
 *
 * Input: address = "1.1.1.1"
 * Output: "1[.]1[.]1[.]1"
 * Example 2:
 *
 * Input: address = "255.100.50.0"
 * Output: "255[.]100[.]50[.]0"
 *
 *
 * Constraints:
 *
 * The given address is a valid IPv4 address.
 * @author sniper
 * @date 2022/8/10
 * LC1108
 */
public class DefangingAnIPaddress {

    public static void main(String[] args) {
        String address = "255.100.50.0";
        String result = defangIPaddr(address);
        System.out.println(result);
    }

    public static String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (char c : address.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
            if (c == '.') {
                sb.append("[.]");
            }
        }
        return sb.toString();
    }
}
