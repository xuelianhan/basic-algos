package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * @author sniper
 * @date 2022/8/18
 */
public class LongestPalindromicSubstring {

    /**
     * Solution provided by GraceMeng(Xinrong Meng)
     * Intuitively,
     * we list all the substrings, pick those palindromic, and get the longest one.
     * This approach takes O(n^3) time complexity, where n is the length of s.
     *
     * Dynamic Programming
     * The problem can be broken down into subproblems which are reused several times.
     * Overlapping subproblems lead us to Dynamic Programming.
     *
     * We decompose the problem as follows:
     *
     * State variable
     * The start index and end index of a substring can identify a state, which influences our decision.
     * So the state variable is state(start, end) indicates whether s[start, end] inclusive is palindromic
     *
     * Goal state
     * max(end - start + 1) for all state(start, end) = true
     *
     * State transition
     *
     * for start = end (e.g. 'a'), state(start, end) is True
     * for start + 1 = end (e.g. 'aa'), state(start, end) is True if s[start] = s[end]
     * for start + 2 = end (e.g. 'aba'),  state(start, end) is True if s[start] = s[end] and state(start + 1, end - 1)
     * for start + 3 = end (e.g. 'abba'),  state(start, end) is True if s[start] = s[end] and state(start + 1, end - 1)
     * ...
     * This approach takes O(n^2) time complexity, O(n^2) space complexity, where n is the length of s.
     * @param s
     * @return
     */
    public String longestPalindromeWithDP(String s) {

        return "";
    }
}
