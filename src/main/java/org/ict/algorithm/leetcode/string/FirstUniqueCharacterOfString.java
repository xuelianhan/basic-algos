package org.ict.algorithm.leetcode.string;


/**
 * Given a string s,
 * find the first non-repeating character in it and return its index.
 * If it does not exist, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 *
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 *
 * Input: s = "aabb"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of only lowercase English letters.
 * @author sniper
 * @date 2022/2/9
 * LC387, Easy, frequency=28
 */
public class FirstUniqueCharacterOfString {
    
    public static void main(String[] args) {
        String s = "loveleetcode";
        int result = firstUniqueChar(s);
        System.out.println(result);
    }

    /**
     * Understanding the following solution
     * Time Cost 8ms
     * -------------------------------------
     * class Solution:
     *     def firstUniqChar(self, s: str) -> int:
     *         count = collections.Counter(s)
     *         for i, c in enumerate(s):
     *             if count[c] == 1:
     *                 return i
     *         return -1
     * ----------------------------------------
     * class Solution {
     * public:
     *     int firstUniqChar(string s) {
     *         vector<int>  count(128);
     *         for (const char c : s) {
     *             count[c]++;
     *         }
     *
     *         for (int i = 0; i < s.length(); i++) {
     *             if (count[s[i]] == 1) {
     *                 return i;
     *             }
     *         }
     *         return -1;
     *     }
     * };
     * @param s
     * @return
     */
    public static int firstUniqueChar(String s) {
        /**
         * record the frequency of each character of s
         */
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            arr[idx]++;
        }
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (arr[idx] == 1) {
                return i;
            }
        }
        return -1;
    }
    
}
