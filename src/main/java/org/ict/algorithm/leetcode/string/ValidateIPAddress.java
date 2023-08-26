package org.ict.algorithm.leetcode.string;

import java.util.Arrays;

/**
 * Given a string queryIP,
 * return "IPv4" if IP is a valid IPv4 address,
 * "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros.
 * For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses,
 * while "192.168.01.1", "192.168.1.00", and "192.168@1.1" are invalid IPv4 addresses.
 *
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits,
 * lowercase English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334"
 * and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses,
 * while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 *
 *
 * Example 1:
 * Input: queryIP = "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 *
 * Example 2:
 * Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 *
 * Example 3:
 * Input: queryIP = "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 *
 *
 * Constraints:
 * queryIP consists only of English letters, digits and the characters '.' and ':'.
 * @author sniper
 * @date 17 Aug 2023
 * LC468, Medium, frequency=8
 */
public class ValidateIPAddress {

    public static void main(String[] args) {
        String queryIP = "1.0.1.";
        /**
         * limit == 0:
         * If the limit is zero then the pattern will be applied as many times as possible,
         * the array can have any length, and trailing empty strings will be discarded.
         * ------------------------
         * limit < 0:
         * If the limit is negative then the pattern will be applied as many times as possible,
         * and the array can have any length.
         */
        String[] arr1 = queryIP.split("\\.");
        String[] arr2 = queryIP.split("\\.", -1);
        /**
         * (Command + delete) shortcut will delete the whole line
         * [1, 0, 1]
         */
        System.out.println(Arrays.toString(arr1));
        /**
         * [1, 0, 1, ]
         */
        System.out.println(Arrays.toString(arr2));
    }

    private static final String VALID_IPV6_CHARS = "0123456789abcdefABCDEF";

    /**
     * e.g. queryIp = "1.0.1.", expected "Neither"
     * If you use split("\\."), the default limit is 0,
     * you will get an array like this: [1, 0, 1],
     * then "1.0.1." will be treated as normal IPv4 address.
     * But it's not IPv4 address in fact.
     * If you use split("\\.", -1), the limit is -1,
     * you will get an array like this: [1, 0, 1, ],
     * the last space will be checked, and it is not IPv4 digit,
     * Finally you will get the right answer "Neither".
     * @param queryIP
     * @return
     */
    public String validIPAddress(String queryIP) {
        if (queryIP.chars().filter(c -> c == '.').count() == 3) {
            for (String digit : queryIP.split("\\.", -1)) {
                if (!isIPv4(digit)) {
                    return "Neither";
                }
            }
            return "IPv4";
        }
        if (queryIP.chars().filter(c -> c == ':').count() == 7) {
            for (String digit : queryIP.split("\\:", -1)) {
                if (!isIPv6(digit)) {
                    return "Neither";
                }
            }
            return "IPv6";
        }
        return "Neither";
    }

    private boolean isIPv4(String digit) {
        if (digit.isEmpty() || digit.length() > 3) {
            return false;
        }
        if (digit.length() > 1 && digit.charAt(0) == '0') {
            return false;
        }
        for (char c : digit.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        int num = Integer.parseInt(digit);
        return 0 <= num && num <= 255;
    }

    private boolean isIPv6(String digit) {
        if (digit.isEmpty() || digit.length() > 4) {
            return false;
        }
        for (char c : digit.toCharArray()) {
            if (!VALID_IPV6_CHARS.contains(c + "")) {
                return false;
            }
        }
        return true;
    }
}
