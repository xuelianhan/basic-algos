package org.ict.algorithm.leetcode.slidewindow;

import java.util.*;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 * @author sniper
 * @date 22 Oct, 2022
 * LC3
 */
public class LongestSubstringWithoutRepeatingCharacters {


    public int lengthOfLongestSubstringV6(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        int[] map = new int[128];
        Arrays.fill(map, -1);
        int start = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            /**
             * start must calculate before res.
             */
            start = Math.max(start, map[ch]);
            res = Math.max(res, i - start);
            map[ch] = i;
        }
        return res;
    }

    public int lengthOfLongestSubstringV5(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        /**
         * 128 is enough to store the characters.
         */
        int[] map = new int[128];
        Arrays.fill(map, -1);
        int start = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map[ch] > start) {
                start = map[ch];
            }
            map[ch] = i;
            res = Math.max(res, i - start);
        }
        return res;
    }

    /**
     * e.g.
     * s = "abcab"
     * i:0, ch:a, start=-1, map[97]=-1 --> map[97]=0, res=max(0, 0-(-1))=1
     * i:1, ch:b, start=-1, map[98]=-1 --> map[98]=1, res=max(1, 1-(-1))=2
     * i:2, ch:c, start=-1, map[99]=-1 --> map[99]=2, res=max(2, 2-(-1))=3
     * i:3, ch:a, map[97] > start, start=0, map[97]=0 --> map[97]=3, res=max(3, 3-0)=3
     * i:4, ch:b, map[98] > start, start=1, map[98]=4, res=max(3, 4-1)=3.
     *
     *        a   b   c  a   b
     * start  -1 -1  -1  0   1
     *
     *
     * e.g.
     * s = "abcabcbb"
     * i:0, ch:a, start=-1, map[97]=-1 --> map[97]=0, res=max(0, 0-(-1))=1
     * i:1, ch:b, start=-1, map[98]=-1 --> map[98]=1, res=max(1, 1-(-1))=2
     * i:2, ch:c, start=-1, map[99]=-1 --> map[99]=2, res=max(2, 2-(-1))=3
     * i:3, ch:a, map[97] > start, start=0, map[97]=3, res=max(3, 3-0)=3
     * i:4, ch:b, map[98] > start, start=1, map[98]=4, res=max(3, 4-1)=3
     * i:5, ch:c, map[99] > start, start=2, map[99]=5, res=max3, 5-2)=3
     * i:6, ch:b, map[98] > start, start=4, map[98]=6, res=max(3, 6-4)=3
     * i:7, ch:b, map[98] > start, start=6, map[98]=7, res=max(3, 7-6)=3
     *        0   1   2   3   4   5   6   7
     *        a   b   c   a   b   c   b   b
     * start -1  -1  -1   0   1   2   4   6
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringV4(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        int[] map = new int[256];
        Arrays.fill(map, -1);

        /**
         * start means the index of the latest repeat char.
         */
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map[ch]  > start) {
                start = map[ch];
            }
            map[ch] = i;
            res = Math.max(res, i - start);
        }
        return res;
    }

    /**
     * HashMap Solution
     * e.g.
     * s = "abcab"
     *
     * i:0, ch:a, start=-1, {a, 0}, res=max(0, 0 - (-1))=1
     * i:1, ch:b, start=-1, {b, 1}, res=max(1, 1 - (-1))=2
     * i:2, ch:c, start=-1, {c, 2}, res=max(2, 2 - (-1))=3
     * i:3, ch:a, start=0, {a, 3}, res=max(3, 3 - 0)=3
     * i:4, ch:b, start=1, {b, 4}, res=max(3, 4 - 1)=3
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringV3(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch));
            }
            map.put(ch, i);
            res = Math.max(res, i - start);
        }
        return res;
    }


    /**
     * HashMap Solution
     *
     * The basic idea is, keep a hashmap which stores the characters in string as keys, and their positions as values,
     * and keep two pointers which define the max substring. move the right pointer to scan through the string ,
     * and meanwhile update the hashmap.
     * If the character is already in the hashmap, then move the left pointer to the right of the same character last found.
     * Note that the two pointers can only move forward.
     *
     * e.g.
     * s = "abcab"
     *
     * i:0, ch:a, start=0, {a, 0}, res=max(0, 0 - 0 + 1)=1
     * i:1, ch:b, start=0, {b, 1}, res=max(1, 1 - 0 + 1)=2
     * i:2, ch:c, start=0, {c, 2}, res=max(2, 2 - 0 + 1)=3
     * i:3, ch:a, start=1, {a, 3}, res=max(3, 3 - 1 + 1)=3
     * i:4, ch:b, start=2, {b, 4}, res=max(3, 4 - 2 + 1)=3
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringV2(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch) + 1);
            }
            map.put(ch, i);
            res = Math.max(res, i - start + 1);
        }
        return res;
    }

    /**
     * Understand the following solution
     *
     * Two-Pointers + HashSet solution.
     * The idea is use a hashset to track the longest substring without repeating characters so far,
     * use a fast pointer j to see if character j is in the hashset or not, if not, add it to the hashset, move j forward and update the max length,
     * otherwise, delete from the head by using a slow pointer i until we can put character j to the hash set.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringV1(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                res = Math.max(res, set.size());
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return res;
    }

    /**
     * Slide-Window solution
     * Please compare it with lengthOfLongestSubstring.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringV0(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        int[] map = new int[128];
        int left = 0, right = 0;
        while (right < s.length()) {
            char rc = s.charAt(right);
            map[rc]++;
            right++;
            while (map[rc] > 1) {
                char lc = s.charAt(left);
                map[lc]--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }


    /**
     * Slide-Window solution
     *
     * s = "abcab"
     * left:0, right:0, map[97]=1, res=max(0, 0-0+1)=1, right:1
     * left:0, right:1, map[98]=1, res=max(1, 1-0+1)=2, right:2
     * left:0, right:2, map[99]=1, res=max(2, 2-0+1)=3, right:3
     * left:0, right:3, map[97]=2, map[97]--, map[97]=1, left:1, res=max(3, 3-1+1)=3, right:4
     * left:1, right:4, map[98]=2, map[98]--, map[98]=1, left:2, res=max(3, 4-2+1)=3, right:5
     *
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        /**
         * Array to store the occurrences of all the characters
         */
        int[] map = new int[128];
        int left = 0, right = 0;
        /**
         * Iterate over the string till the right pointer reaches the end of the string
         */
        while (right < s.length()) {
            char rc = s.charAt(right);
            map[rc]++;
            /**
             * If the occurrence become more than 1, the current char is a repeated char.
             * We need to shrink the map.
             */
            while (map[rc] > 1) {
                /**
                 * Reduce the occurrence of temp as it might be present ahead also in the string
                 */
                char lc = s.charAt(left);
                map[lc]--;
                /**
                 * Contraction of the present map till the occurrence of the 't' char becomes 1
                 */
                left++;
            }
            /**
             * After the map is shrink, we need to calculate the max-length of no repeated substring.
             * As the index starts from 0 , ans will be (right pointer-left pointer + 1)
             */
            res = Math.max(res, right - left + 1);
            /**
             * Now will increment the right pointer
             */
            right++;
        }
        return res;
    }

}
