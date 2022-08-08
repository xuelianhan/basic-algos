package org.ict.algorithm.leetcode.string;

import java.awt.image.WritableRaster;
import java.util.Arrays;

/**
 * A permutation perm of n + 1 integers of all the integers in the range [0, n] can be represented as a string s of length n where:
 *
 * s[i] == 'I' if perm[i] < perm[i + 1], and
 * s[i] == 'D' if perm[i] > perm[i + 1].
 * Given a string s, reconstruct the permutation perm and return it. If there are multiple valid permutations perm, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "IDID"
 * Output: [0,4,1,3,2]
 * Example 2:
 *
 * Input: s = "III"
 * Output: [0,1,2,3]
 * Example 3:
 *
 * Input: s = "DDI"
 * Output: [3,2,0,1]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is either 'I' or 'D'.
 * @author sniper
 * @date 06 Aug, 2022
 * LC942
 */
public class DIStringMatch {

    public static void main(String[] args) {
        String s = "IDIDID";
        int[] result = diStringMatch(s);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Two-pointers solution provided by lee215
     * @param s
     * @return
     */
    public static int[] diStringMatch(String s) {
        int n = s.length();
        int left = 0;
        int right = n;
        int [] result = new int[n + 1];
        for(int i = 0; i < n; i++) {
            result[i] = (s.charAt(i) == 'I'? left++ : right--);
        }
        /**
         * result[n] = left is ok too
         */
        result[n] = right;

        return result;
    }
}
