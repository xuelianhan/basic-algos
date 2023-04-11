package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, partition the string into one or more substrings such that the characters in each substring are unique.
 * That is, no letter appears in a single substring more than once.
 *
 * Return the minimum number of substrings in such a partition.
 *
 * Note that each character should belong to exactly one substring in a partition.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abacaba"
 * Output: 4
 * Explanation:
 * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
 * It can be shown that 4 is the minimum number of substrings needed.
 * Example 2:
 *
 * Input: s = "ssssss"
 * Output: 6
 * Explanation:
 * The only valid partition is ("s","s","s","s","s","s").
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of only English lowercase letters.
 * @author sniper
 * @date 04 Apr, 2023
 * LC2405, Medium
 */
public class OptimalPartitionOfString {

    public int partitionStringV5(String s) {
        Set<Character> set = new HashSet<>();
        //todo
        return 0;
    }

    public int partitionStringV4(String s) {
        Set<Character> set = new HashSet<>();
        //todo
        return 0;
    }

    public int partitionStringV3(String s) {
        Set<Character> set = new HashSet<>();
        //todo
        return 0;
    }

    public int partitionStringV2(String s) {
        Set<Character> set = new HashSet<>();
        //todo
        return 0;
    }

    /**
     * Greedy Solution:
     * Extend string as long as possible if there is no duplicate characters.
     * @param s
     * @return
     */
    public int partitionStringV1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        /**
         * There is at least one string
         * e.g. s = "123", the result is "123" itself, so the res is initialized as one.
         * e.g. s = "111"
         */
        int res = 1;
        for (int i = 0; i < n; i++) {
            if (set.contains(s.charAt(i))) {
                res++;
                set.clear();
            }
            set.add(s.charAt(i));
        }
        return res;
    }

    /**
     * todo
     * Analysis here
     * todo
     * @author Larry
     * @param s
     * @return
     */
    public int partitionString(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j >= 0 && !set.contains(s.charAt(j))) {
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
                set.add(s.charAt(j));
                j -= 1;
            }
            set.clear();
        }
        return dp[n];
    }


}
