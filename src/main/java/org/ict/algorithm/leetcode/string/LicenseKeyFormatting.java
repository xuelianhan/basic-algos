package org.ict.algorithm.leetcode.string;

/**
 *
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
 * The string is separated into n + 1 groups by n dashes.
 * You are also given an integer k.
 *
 * We want to reformat the string s such that each group contains exactly k characters,
 * except for the first group, which could be shorter than k but still must contain at least one character.
 * Furthermore, there must be a dash inserted between two groups,
 * and you should convert all lowercase letters to uppercase.
 *
 * Return the reformatted license key.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "5F3Z-2e-9-w", k = 4
 * Output: "5F3Z-2E9W"
 * Explanation: The string s has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 * Example 2:
 *
 * Input: s = "2-5g-3-J", k = 2
 * Output: "2-5G-3J"
 * Explanation: The string s has been split into three parts,
 * each part has 2 characters except the first part as it could be shorter mentioned above.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of English letters, digits, and dashes '-'.
 * 1 <= k <= 10^4
 * @author sniper
 * @date 14 Feb, 2022
 * LC482
 */
public class LicenseKeyFormatting {

    public static void main(String[] args) {
        String s = "r";
        int k = 1;
        String result = licenseKeyFormatting(s, k);
        System.out.println(result);
    }

    public static String licenseKeyFormatting(String s, int k) {
        int i = s.length() - 1;
        int j = 0;
        char dash = '-';
        StringBuffer sb = new StringBuffer();
        while (i >= 0) {
            char c = s.charAt(i);
            if (dash == c) {
                i--;
                continue;
            }
            if (Character.isLetter(c)){
                if (Character.isLowerCase(c)) {
                    c = Character.toUpperCase(c);
                }
                sb.append(c);
                j++;
            }
            if (Character.isDigit(c)) {
                sb.append(c);
                j++;
            }
            if (j % k == 0) {
                sb.append('-');
            }
            i--;
        }
        String result = sb.reverse().toString();
        if (result.length() == 0) {
            return "";
        }
        if (dash == result.charAt(0)) {
            return result.substring(1);
        } else {
            return result;
        }
    }
}
