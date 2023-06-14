package org.ict.algorithm.leetcode.twopointers;

/**
 * Sometimes people repeat letters to represent extra feeling. For example:
 *
 * "hello" -> "heeellooo"
 * "hi" -> "hiiii"
 * In these strings like "heeellooo",
 * we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".
 *
 * You are given a string s and an array of query strings words.
 * A query word is stretchy if it can be made to be equal to s by any number of applications of the following extension operation:
 * choose a group consisting of characters c,
 * and add some number of characters c to the group so that the size of the group is three or more.
 *
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo",
 * but we cannot get "helloo" since the group "oo" has a size less than three.
 * Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".
 * If s = "helllllooo", then the query word "hello" would be stretchy
 * because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
 * Return the number of query strings that are stretchy.
 *
 *
 *
 * Example 1:
 * Input: s = "heeellooo", words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 *
 *
 * Example 2:
 * Input: s = "zzzzzyyyyy", words = ["zzyy","zy","zyy"]
 * Output: 3
 *
 *
 * Constraints:
 * 1 <= s.length, words.length <= 100
 * 1 <= words[i].length <= 100
 * s and words[i] consist of lowercase letters.
 * @author sniper
 * @date 14 Jun 2023
 * LC809, Medium, frequency=11
 */
public class ExpressiveWords {

    /**
     * Time Cost 2ms
     * @param s
     * @param words
     * @return
     */
    public int expressiveWordsV2(String s, String[] words) {
        int res = 0;
        for (String t : words) {
            if (check(s, t)) {
                res++;
            }
        }
        return res;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) {
            return false;
        }
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            int k = i;
            while (k < m && s.charAt(k) == s.charAt(i)) {
                ++k;
            }
            int c1 = k - i;
            i = k;
            k = j;
            while (k < n && t.charAt(k) == t.charAt(j)) {
                ++k;
            }
            int c2 = k - j;
            j = k;
            if (c1 < c2 || (c1 < 3 && c1 != c2)) {
                return false;
            }
        }
        return i == m && j == n;
    }

    /**
     * Time Cost 2ms
     * @param s
     * @param words
     * @return
     */
    public int expressiveWordsV1(String s, String[] words) {
        int res = 0;
        for (final String word : words) {
            if (isStretchy(s, word)) {
                res++;
            }
        }
        return res;
    }

    private boolean isStretchy(final String s, final String word) {
        final int n = s.length();
        final int m = word.length();
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (j < m && s.charAt(i) == word.charAt(j)) {
                j++;
            } else if (i > 1 && s.charAt(i) == s.charAt(i - 1) && s.charAt(i - 1) == s.charAt(i - 2)) {
            } else if (0 < i && i + 1 < n && s.charAt(i - 1) == s.charAt(i) && s.charAt(i) == s.charAt(i + 1)) {
            } else {
                return false;
            }
        }
        return j == m;
    }

    /**
     * Time Cost 7ms
     * @param s
     * @param words
     * @return
     */
    public int expressiveWords(String s, String[] words) {
        int res = 0;
        int m = s.length();
        for (String word : words) {
            int i = 0, j = 0;
            for (; i < m; ++i) {
                if (j < word.length() && s.charAt(i) == word.charAt(j)){
                    ++j;
                } else if (i > 0 && s.charAt(i) == s.charAt(i - 1) && i + 1 < m && s.charAt(i) == s.charAt(i + 1)) {
                    ++i;
                } else if (!(i > 1 && s.charAt(i) == s.charAt(i - 1) && s.charAt(i) == s.charAt(i - 2))) {
                    break;
                }
            }
            if (i == m && j == word.length()) {
                ++res;
            }
        }
        return res;
    }
}
