package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal,
 * otherwise, return false.
 *
 * Swapping letters is defined as taking two indices i and j (0-indexed)
 * such that i != j and swapping the characters at s[i] and s[j].
 *
 * For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 *
 *
 * Example 1:
 *
 * Input: s = "ab", goal = "ba"
 * Output: true
 * Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.
 * Example 2:
 *
 * Input: s = "ab", goal = "ab"
 * Output: false
 * Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.
 * Example 3:
 *
 * Input: s = "aa", goal = "aa"
 * Output: true
 * Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.
 *
 *
 * Constraints:
 *
 * 1 <= s.length, goal.length <= 2 * 10^4
 * s and goal consist of lowercase letters.
 *
 * @author sniper
 * @date 2022/8/3
 * lc859
 */
public class BuddyStrings {

    public static void main(String[] args) {
        String s = "abcd";
        String goal = "abcd";
        System.out.println(s.hashCode());
        System.out.println(goal.hashCode());
    }

    public static boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.length() == 1) {
            return false;
        }
        /**
         * If s equals goal:
         * consider case such as:
         * s = abcc, goal = abcc, expect true
         * s = aa,  goal = aa,  expect true
         * s = ab,  goal = ab, expect false
         */
        if (s.hashCode() == goal.hashCode()) {
            Set<Character> set = new HashSet<>();
            for(char c : s.toCharArray()) {
                set.add(c);
            }
            return (set.size() < s.length());
        }
        /**
         * consider cases s not equals goal,
         * s and goal should have only 2 diffs
         *
         * e.g.
         * s = abcd, goal = dbca
         * diff = [0, 3]
         */
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                diff.add(i);
            }
        }
        /**
         * diff = [0, 3]
         * s = abcd
         * g = dbca
         *
         */
        return (diff.size() == 2
                && s.charAt(diff.get(0)) == goal.charAt(diff.get(1))
                && s.charAt(diff.get(1)) == goal.charAt(diff.get(0))
        );
    }
}
