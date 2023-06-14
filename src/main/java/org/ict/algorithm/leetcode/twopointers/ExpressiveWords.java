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
     *
     * Time Cost 2ms
     *
     * @param s
     * @param words
     * @return
     */
    public int expressiveWordsV2(String s, String[] words) {
        int res = 0;
        for (String w : words) {
            if (check(s, w)) {
                res++;
            }
        }
        return res;
    }

    /**
     * e.g. s = "heeellooo", w = "hello"
     * n:9, m:5
     * i:0, j:0, i2:0, j2:0, i < 9 and j < 5
     *           s[0] == w[0] == 'h'
     *           i2 < 9 and s[i2] == s[i], i2++, i2:1
     *           j2 < 5 and w[j2] == w[j], j2++, j2:1
     *           i2 - i = 1, j2 - j = 1,
     *           i = i2, j = j2
     * i:1, j:1, i2:1, j2:1, i < 9 and j < 5
     *           s[1] == w[1] == 'e'
     *           i2 < 9 and s[i2] == s[i], i2++, i2:2
     *           i2 < 9 and s[i2] == s[i], i2++, i2:3
     *           i2 < 9 and s[i2] == s[i], i2++, i2:4
     *           j2 < 5 and w[j2] == w[j], j2++, j2:2
     *           i2 - i = 4 - 1= 3, j2 - j = 2 - 1 = 1
     *           i = i2, j = j2
     * i:4, j:2, i2:4, j2:2, i < 9 and j < 5
     *           s[4] == w[2] == 'l'
     *           i2 < 9 and s[i2] == s[i], i2++, i2:5
     *           i2 < 9 and s[i2] == s[i], i2++, i2:6
     *           j2 < 5 and w[j2] == w[j], j2++, j2:3
     *           j2 < 5 and w[j2] == w[j], j2++, j2:4
     *           i2 - i = 6 - 4 = 2, j2 - j = 4 - 2 = 2
     *           i = i2, j = j2
     * i:6, j:4, i2:6, j2:4, i < 9 and j < 5
     *           s[6] == w[4] == 'o'
     *           i2 < 9 and s[i2] == s[i], i2++, i2:7
     *           i2 < 9 and s[i2] == s[i], i2++, i2:8
     *           i2 < 9 and s[i2] == s[i], i2++, i2:9
     *           j2 < 5 and w[j2] == w[j], j2++, j2:5
     *           i2 - i = 9 - 6 = 3, j2 - j = 5 - 4 = 1
     *           i = i2, j = j2
     * i:9, j:5, end-for-loop
     * return i == 9 and j == 5
     *
     * @param s
     * @param w
     * @return
     */
    private boolean check(String s, String w) {
        int n = s.length();
        int m = w.length();
        int i = 0, j = 0;
        for (int i2 = 0, j2 = 0; i < n && j < m; i = i2, j = j2) {
            if (s.charAt(i) != w.charAt(j)) {
                return false;
            }
            while (i2 < n && s.charAt(i2) == s.charAt(i)) {
                i2++;
            }
            while (j2 < m && w.charAt(j2) == w.charAt(j)) {
                j2++;
            }
            if (i2 - i != j2 - j && i2 - i < Math.max(3, j2 - j)) {
                return false;
            }
        }
        return i == n && j == m;
    }

    /**
     * Understanding the following solution
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
        for (int i = 0; i < n; i++) {
            if (j < m && s.charAt(i) == word.charAt(j)) {
                j++;
            } else if (i > 1 && s.charAt(i) == s.charAt(i - 1) && s.charAt(i - 1) == s.charAt(i - 2)) {
            } else if (0 < i && i < (n - 1) && s.charAt(i - 1) == s.charAt(i) && s.charAt(i) == s.charAt(i + 1)) {
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
