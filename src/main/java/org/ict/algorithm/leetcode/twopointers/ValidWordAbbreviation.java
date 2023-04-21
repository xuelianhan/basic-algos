package org.ict.algorithm.leetcode.twopointers;

/**
 * Description
 * A string can be abbreviated by replacing any number of non-adjacent,
 * non-empty substrings with their lengths.
 * The lengths should not have leading zeros.
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 * Example 1:
 * Input: word = "internationalization", abbr = "i12iz4n"
 * Output: true
 * Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
 *
 * Example 2:
 * Input: word = "apple", abbr = "a2e"
 * Output: false
 * Explanation: The word "apple" cannot be abbreviated as "a2e".
 *
 *
 * Constraints:
 * 1 <= word.length <= 20
 * word consists of only lowercase English letters.
 * 1 <= abbr.length <= 10
 * abbr consists of lowercase English letters and digits.
 * All the integers in abbr will fit in a 32-bit integer.
 *
 * @author sniper
 * @date 19 Apr, 2023
 * LC408, Medium, frequency=114
 */
public class ValidWordAbbreviation {

    public boolean validWordAbbreviationV3(String word, String abbr) {
        int m = word.length();
        int n = abbr.length();
        int p = 0;
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            if (abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                if (cnt == 0 && abbr.charAt(j) == '0') {
                    return false;
                }
                cnt = cnt * 10 + abbr.charAt(j) - '0';
            } else {
                p += cnt;
                if (p >= m || word.charAt(p) != abbr.charAt(j)) {
                    return false;
                }
                p++;
                cnt = 0;
            }
        }
        return p + cnt == m;
    }

    public boolean validWordAbbreviationV2(String word, String abbr) {
        int m = word.length();
        int n = abbr.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                if (abbr.charAt(j) == '0') {
                    return false;
                }
                int num = 0;
                while (j < n && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                    num = 10 * num + abbr.charAt(j) - '0';
                    j++;
                }
                i += num;
            } else {
                if (word.charAt(i) != abbr.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        return i == m && j == n;
    }

    /**
     * Understanding the following solution
     * @param word
     * @param abbr
     * @return
     */
    public boolean validWordAbbreviationV1(String word, String abbr) {
        int m = word.length();
        int n = abbr.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }
            /**
             * e.g. word="substitution", abbr="s010n"
             */
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                return false;
            }
            int num = 0;
            while (j < n && Character.isDigit(abbr.charAt(j))) {
                num = num * 10 + abbr.charAt(j) - '0';
                j++;
            }
            i += num;
        }
        return i == m && j == n;
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        int m = word.length();
        int n = abbr.length();
        int i = 0;
        int j = 0;
        while (i < m) {
            if (j == n) {
                return false;
            }
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }
            int k = j;
            while (k < n && Character.isDigit(abbr.charAt(k))) {
                k++;
            }
            String t = abbr.substring(j, k);
            if (j == k || t.charAt(0) == '0' || Integer.parseInt(t) == 0) {
                return false;
            }
            i += Integer.parseInt(t);
            j = k;
        }
        return i == m && j == n;
    }
}
