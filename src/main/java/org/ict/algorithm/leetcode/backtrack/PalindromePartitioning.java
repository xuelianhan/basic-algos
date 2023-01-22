package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 * @author sniper
 * @date 01 Nov, 2022
 * LC131, Medium
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new LinkedList<>(), s, 0);
        return result;
    }

    public void backtrackV1(List<List<String>> result, LinkedList<String> track, String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(track));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i)) {
                continue;
            }
            track.add(s.substring(start, i + 1));
            backtrackV1(result, track, s, i + 1);
            track.removeLast();
        }
    }

    public void backtrack(List<List<String>> result, LinkedList<String> track, String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(track));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                track.add(s.substring(start, i + 1));
                backtrack(result, track, s, i + 1);
                track.removeLast();
            }
        }
    }

    public boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }
}
