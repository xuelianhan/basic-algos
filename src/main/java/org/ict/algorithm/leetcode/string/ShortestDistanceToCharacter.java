package org.ict.algorithm.leetcode.string;

import java.util.Arrays;

/**
 * Given a string s and a character c that occurs in s,
 * return an array of integers answer where answer.length == s.length
 * and answer[i] is the distance from index i to the closest occurrence of character c in s.
 *
 * The distance between two indices i and j is abs(i - j),
 * where abs is the absolute value function.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "loveleetcode", c = "e"
 * Output: [3,2,1,0,1,0,0,1,2,2,1,0]
 * Explanation: The character 'e' appears at indices 3, 5, 6, and 11 (0-indexed).
 * The closest occurrence of 'e' for index 0 is at index 3, so the distance is abs(0 - 3) = 3.
 * The closest occurrence of 'e' for index 1 is at index 3, so the distance is abs(1 - 3) = 2.
 * For index 4, there is a tie between the 'e' at index 3 and the 'e' at index 5,
 * but the distance is still the same: abs(4 - 3) == abs(4 - 5) = 1.
 * The closest occurrence of 'e' for index 8 is at index 6,
 * so the distance is abs(8 - 6) = 2.
 *
 *
 * Example 2:
 *
 * Input: s = "aaab", c = "b"
 * Output: [3,2,1,0]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s[i] and c are lowercase English letters.
 * It is guaranteed that c occurs at least once in s.
 *
 * @date 27 June, 2022
 * LC821
 */
public class ShortestDistanceToCharacter {

    public static void main(String[] args) {
        String s = "loveleetcode";
        char c = 'e';
        shortestToChar(s, c);
    }

    /**
     * The following solution is provided by Lee215
     * Before the first C value is reached,
     * there is no index behind it to compute a difference.
     * So letting pos = -n assures that the computed difference on the first pass
     * before the first C value is reached is higher than what would be computed on the way back.
     *
     * Say the example was aacaacacaca, and C = 'c'.
     * The first c is at index 2.
     * On the first pass, the two 'a' values will be given a difference
     * of 0 - (-12) = 12, and 1 - (-12) = 13,
     * respectively, since pos = -n = -12.
     * When the first 'c' is reached,
     * the 'a' values in front of it will be compared to it's index,
     * until the next 'c' is reached,
     * and so on so forth.
     *
     * On the way back, when that leftmost 'c' is finally reached,
     * the actual difference for the leftmost 'a' values will be computed as 2 - 1 = 1,
     * and 2 - 0 = 2,
     * since pos will now equal the index of the first 'c', which is 2.
     *
     * So basically, on the first pass,
     * the values given to everything behind the first C from the left is "incorrect".
     * On the backwards pass, this is fixed.
     *
     * @param s
     * @param c
     * @return
     * @author lee215
     */
    public static int[] shortestToChar(String s, char c) {
        /**
         * Why pos is initiated with -n? not Integer.MIN_VALUE or Integer.MAX_VALUE?
         * 
         */
        int n = s.length(), pos = -n;
        int[] res = new int[n];
        /**
         * First pass to find the shortest distance to character c from left to right.
         * Adjust pos index dynamically according to the occurrence of c.
         * the i is greater than pos when scanning from left to right.
         */
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                pos = i;
            }
            res[i] = i - pos;
        }
        /**
         * [12, 13, 14, 0, 1, 0, 0, 1, 2, 3, 4, 0]
         */
        System.out.println(Arrays.toString(res));
        /**
         * Second pass to find the shortest distance to character c from right to left
         * And fix the first pass distance.
         *
         * Due to it is guaranteed that c occurs at least once in s,
         * pos is the biggest index of c from left to right(only -n at first, then pos is positive number).
         * If we do the right-to-left search at the second pass, we need to
         * start at pos - 1(start at pos is ok but waste one-loop).
         * right-to-left
         *
         * Adjust pos index dynamically according to the occurrence of c.
         * the i pos is greater than i when scanning from right to left.
         * Use Math.min to amend the absolute distance.
         */
        for (int i = pos - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                pos = i;
            }
            res[i] = Math.min(res[i], pos - i);
        }
        /**
         * [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
         */
        System.out.println(Arrays.toString(res));
        return res;
    }
}
