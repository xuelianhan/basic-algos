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

    public static void main(String[] args) {
        String s = "aab";
        PalindromePartitioning instance = new PalindromePartitioning();
        instance.partitionV1(s);
    }

    public List<List<String>> partitionV1(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrackV1(result, new LinkedList<>(), s, 0);
        return result;
    }

    /**
     * e.g. s = "aab"
     * backtrack(0)
     *   i:0, i < 3, start:0, s[0] = "a" is a palindrome, track add "a", track:["a"]
     *     backtrack(1):
     *       i:1, i < 3, start:1, s[1] = "a" is a palindrome, track add "a", track:["a", "a"]
     *         backtrack(2):
     *           i:2, i < 3, start:2, s[2] = "b" is a palindrome, track add "b", track:["a", "a", "b"]
     *             backtrack(3):
     *               start:3, start == s.length
     *               result:["a", "a", "b"]
     *             backtrack(3) return
     *             track.removeLast, track:["a", "a"]
     *           i:3, not match for-loop condition
     *         backtrack(2) return
     *         track.removeLast, track:["a"]
     *       i:2, i < 3, start:1, s[1]~s[2] = "ab" is not palindrome, continue
     *       i:3, not match for-loop condition
     *     backtrack(1) return
     *     track.removeLast, track:[]
     *   i:1, i < 3, start:0, s[0]~s[1] = "aa" is a palindrome, track add "aa", track:["aa"], i:1 --> i:2
     *     backtrack(2):
     *       i:2, i < 3, start:2, s[2] = "b" is a palindrome, track add "b", track:["aa", "b"]
     *         backtrack(3):
     *           start:3, start == s.length
     *           result:[["a", "a", "b"], ["aa", b]]
     *         backtrack(3) return
     *         track.removeLast, track:["aa"]
     *     backtrack(2) return
     *     track.removeLast, track:[]
     *   i:2, i < 3, start:0, s[0]~s[2] = "aab" is not palindrome, continue
     *   i:3, not match for-loop condition
     * backtrack(0) return
     * result: [["a", "a", "b"], ["aa", b]]
     * 
     * @param result
     * @param track
     * @param s
     * @param start
     */
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

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new LinkedList<>(), s, 0);
        return result;
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
