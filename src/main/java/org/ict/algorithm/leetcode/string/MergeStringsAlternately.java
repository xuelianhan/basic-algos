package org.ict.algorithm.leetcode.string;

import java.util.Arrays;

/**
 * You are given two strings word1 and word2.
 * Merge the strings by adding letters in alternating order,
 * starting with word1.
 * If a string is longer than the other,
 * append the additional letters onto the end of the merged string.
 *
 * Return the merged string.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 * Explanation: The merged string will be merged as so:
 * word1:  a   b   c
 * word2:    p   q   r
 * merged: a p b q c r
 * Example 2:
 *
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 * Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 * word1:  a   b
 * word2:    p   q   r   s
 * merged: a p b q   r   s
 * Example 3:
 *
 * Input: word1 = "abcd", word2 = "pq"
 * Output: "apbqcd"
 * Explanation: Notice that as word1 is longer, "cd" is appended to the end.
 * word1:  a   b   c   d
 * word2:    p   q
 * merged: a p b q c   d
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 100
 * word1 and word2 consist of lowercase English letters.
 * @author sniper
 * @date 2022/8/16
 */
public class MergeStringsAlternately {

    public static void main(String[] args) {
        //String word1 = "abc", word2 = "pqr";
        //String word1 = "ab", word2 = "pqrs";
        String word1 = "abcd", word2 = "pq";
        String result = mergeAlternately(word1, word2);
        System.out.println(result);
    }

    public static String mergeAlternately(String word1, String word2) {
        char[] aux = new char[word1.length() + word2.length()];
        Arrays.fill(aux, '0');
        boolean flag = true;
        /**
         * use separated k for aux array counting
         * Don't mix with the count pointers i, j which are for word counting.
         *
         * the i++, j++ should be placed at the if-else condition,
         * not at the for-loop condition.
         */
        int i = 0, j = 0, k = 0;
        for (; i < word1.length() && j < word2.length();) {
            if (flag) {
                aux[k++] = word1.charAt(i);
                flag = false;
                i++;
            } else {
                aux[k++] = word2.charAt(j);
                flag = true;
                j++;
            }
        }
        while (i < word1.length()) {
            aux[k++] = word1.charAt(i);
            i++;
        }
        while (j < word2.length()) {
            aux[k++] = word2.charAt(j);
            j++;
        }
        return new String(aux);
    }
}
