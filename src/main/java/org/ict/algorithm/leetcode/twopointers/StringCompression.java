package org.ict.algorithm.leetcode.twopointers;

/**
 * Given an array of characters chars, compress it using the following algorithm:
 * Begin with an empty string s.
 * For each group of consecutive repeating characters in chars:
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately,
 * but instead, be stored in the input character array chars.
 * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * After you are done modifying the input array, return the new length of the array.
 * You must write an algorithm that uses only constant extra space.
 *
 * Example 1:
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 *
 * Example 2:
 * Input: chars = ["a"]
 * Output: Return 1, and the first character of the input array should be: ["a"]
 * Explanation: The only group is "a", which remains uncompressed since it's a single character.
 *
 * Example 3:
 * Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
 *
 * Constraints:
 * 1 <= chars.length <= 2000
 * chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 * @author sniper
 * @date 12 May 2023
 * LC443, Medium, frequency=24
 */
public class StringCompression {

    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'b','c','c','c'};
        StringCompression instance = new StringCompression();
        int res = instance.compress(chars);
        System.out.println(res);
    }

    /**
     * Understanding the following solution.
     * Time Cost 1ms
     * --------------------------------------
     * class Solution:
     *     def compress(self, chars: List[str]) -> int:
     *         res, i = 0, 0
     *         n = len(chars)
     *         while i in range(0, n):
     *             letter = chars[i]
     *             count = 0
     *             while i < n and chars[i] == letter:
     *                 i += 1
     *                 count += 1
     *             chars[res] = letter
     *             res += 1
     *             if count > 1:
     *                 for c in str(count):
     *                     chars[res] = c
     *                     res += 1
     *         return res
     *
     * @param chars
     * @return
     */
    public int compressV3(char[] chars) {
        int res = 0;
        int n = chars.length;
        for (int i = 0; i < n;) {
            char letter = chars[i];
            int count = 0;
            while (i < n && chars[i] == letter) {
                count++;
                i++;
            }
            chars[res++] = letter;
            if (count > 1) {
                for (char ch : String.valueOf(count).toCharArray()) {
                    chars[res++] = ch;
                }
            }
        }
        return res;
    }

    /**
     * Understanding the following solution.
     * Two Pointers Solution with Constant extra space.
     *
     * You must write an algorithm that uses only constant extra space.
     * ---------------------------------------------------
     * class Solution {
     * public:
     *     int compress(vector<char>& chars) {
     *         int n = chars.size(), res = 0;
     *         for (int i = 0, j = 1; i < n; i = j) {
     *             while (j < n && chars[j] == chars[i]){
     *                 j++;
     *             }
     *             chars[res++] = chars[i];
     *             if (j - i == 1) {
     *                 continue;
     *             }
     *             for (char c : to_string(j - i)) {
     *                 chars[res++] = c;
     *             }
     *         }
     *         return res;
     *     }
     * };
     * ----------------------------------------------------
     *
     *
     * @param chars
     * @return
     */
    public int compressV2(char[] chars) {
        int n = chars.length;
        int res = 0;
        for (int i = 0, j = 0; i < n; i = j) {
            while (j < n && chars[i] == chars[j]) {
                j++;
            }
            /**
             * Add chars
             */
            chars[res++] = chars[i];
            if (j - i == 1) {
                // Only one char doesn't need to append digit-1
                continue;
            }
            /**
             * Add digits
             */
            for (char c : String.valueOf(j - i).toCharArray()) {
                chars[res++] = c;
            }
        }
        return res;
    }

    /**
     * Understanding the following solution.
     * Two Pointer Solution
     * Time Cost 2ms
     * @param chars
     * @return
     */
    public int compressV1(char[] chars) {
        int n = chars.length;
        if (chars.length <= 1) {
            return n;
        }

        int i = 0;
        int j = i + 1;
        StringBuilder res = new StringBuilder();
        for (; j < n && i < n; j++) {
            if (chars[i] != chars[j]) {
                append(chars,i, j, res);
                i = j;
            }
        }
        if (i != j) {
            append(chars,i, j, res);
        }

        for (int k = 0; k < res.length(); k++) {
            chars[k] = res.charAt(k);
        }
        return res.length();
    }

    private void append(char[] chars, int i, int j, StringBuilder res) {
        if (j - i > 1) {
            res.append(chars[i]);
            res.append(j - i);
        } else {
            res.append(chars[i]);
        }
    }

    /**
     * Time Cost 1ms
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int n = chars.length;
        if (chars.length <= 1) {
            return n;
        }

        StringBuilder res = new StringBuilder();
        int i = 0;
        int j = i + 1;
        for (; j < n && i < n; j++) {
            if (chars[i] != chars[j]) {
                if (j - i > 1) {
                    res.append(chars[i]);
                    res.append(j - i);
                } else {
                    res.append(chars[i]);
                }
                i = j;
            }
        }
        if (i != j) {
            if (j - i > 1) {
                res.append(chars[i]);
                res.append(j - i);
            } else {
                res.append(chars[i]);
            }
        }

        for (int k = 0; k < res.length(); k++) {
            chars[k] = res.charAt(k);
        }
        return res.length();
    }
}
