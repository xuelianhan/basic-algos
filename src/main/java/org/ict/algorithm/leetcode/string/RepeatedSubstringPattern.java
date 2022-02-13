package org.ict.algorithm.leetcode.string;

/**
 *
 * Given a string s, check if it can be constructed by taking
 * a substring of it and appending multiple copies of the substring together.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 * Example 2:
 *
 * Input: s = "aba"
 * Output: false
 * Example 3:
 *
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 *
 * Input: s = "a"
 * Output: false
 *
 * Input: s = "ababba"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 *
 * @author sniper
 * @date 12 Feb, 2022
 * LC459
 */
public class RepeatedSubstringPattern {

    public static void main(String[] args) {
        // Expected false, but returns true, fix it.
        String s = "aba";

        boolean result = repeatedSubstringPattern(s);
        System.out.println(result);
    }

    /**
     * Ther's a literally one-line solution to the problem.
     * Repeat the given string twice and remove the first and last character of the newly created string,
     * check if a given string is a substring of the newly created string.
     *
     * "aba"
     * abaaba
     *  baab
     * @param s
     * @return
     * @see <a href="https://stackoverflow.com/questions/40670242/find-a-repeated-substring-pattern-in-a-given-string"></a>
     * answered by Manish Chauhan, edited by Julien M
     *
     * Mathematical Proof:
     * Let P be the pattern that is repeated K times in a string S.
     * S = P*K.
     *
     * Let N be the newly created string by repeating string S
     * N = S+S.
     *
     * Let F be the first character of string N and L be the last character of string N
     * N = ( F+ P*(K-1) )+ (P*(K-1) + L)
     * N = F+ P(2K-2)+ L
     *
     * If K = 1. i.e a string repeated only once
     * N = F+L. //as N != S So False
     *
     * If K ≥ 2.
     * N = F+k'+ N
     *
     * Where k'≥K. As our S=P*K. So, S must be in N.
     * We can further use KMP algorithm to check if S is a sub-string of N.
     * Which will give us time complexity of O(n)
     */
    public static boolean repeatedSubstringPattern(String s) {
        String newStr = s + s;
        String middle = newStr.substring(1, newStr.length() - 1);
        System.out.println(middle);
        if (middle.contains(s)) {
            return true;
        }
        return false;
    }

    /**
     * Wrong answer, only consider the frequency but ignore the sequence of string,
     * so the solution is not right.
     *
     * case:
     * Expected false, but returns true, fix it.
     * String s = "ababba";
     *
     * @param s
     * @return
     */
    public static boolean wrongAnswer(String s) {
        if (s.length() == 1) {
            return true;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 'a']++;
        }
        int last = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                if (last == 0) {
                    last = arr[i];
                } else {
                    if (last == arr[i]) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }
        if (last == 1) {
            return false;
        }
        return true;
    }

}
