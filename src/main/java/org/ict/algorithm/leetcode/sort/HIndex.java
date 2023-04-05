package org.ict.algorithm.leetcode.sort;

import java.util.Arrays;

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper,
 * return the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia:
 * The h-index is defined as the maximum value of h such that
 * the given researcher has published at least h papers
 * that have each been cited at least h times.
 *
 *
 *
 * Example 1:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each,
 * their h-index is 3.
 * Example 2:
 *
 * Input: citations = [1,3,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 * @author sniper
 * @date 04 Apr, 2023
 * LC274, Medium
 * @see org.ict.algorithm.leetcode.binarysearch.HIndexII
 */
public class HIndex {

    public static void main(String[] args) {
        int[] citations = {0, 0, 0};
        HIndex instance = new HIndex();
        int res = instance.hIndexV2(citations);
        System.out.println(res);
    }

    public int hIndexV2(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = 0; i < citations.length; i++) {
            /**
             * Assume citation is the top level.
             * if citations cannot reach the top level, downgrade it until citation item can reach this level
             * return the level.
             */
            if (n > citations[i]) {
                n--;
            } else {
                return n;
            }
        }
        return n;
    }


    /**
     * e.g. citations = [3,0,6,1,5], expected 3
     * [0,1,3,5,6]
     *  5 4 3
     * e.g. citations = [0,0], expected 0
     * i:0, cite[0]=0, n:2, n > 0, n--:n:1, i++:i:1
     * i:1, cite[1]=0, n:1, n > 0, n--:n:0, i++:i:2
     * @param citations
     * @return
     */
    public int hIndexV1(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = 0; i < citations.length; i++) {
            if (n <= citations[i]) {
                return n;
            } else {
                n--;
            }
        }
        return n;
    }


    /**
     * A scientist has index h if h of his/her N papers have at least h citations each,
     * and the other (N − h) papers have no more than h citations each
     * e.g. citations = [3,0,6,1,5], expected 3
     * [0,1,3,5,6]
     *  4 3 2 1 0
     * i:4, j:0, cite[4]=6, 6 > j, i--, j++
     * i:3, j:1, cite[3]=5, 5 > j, i--, j++
     * i:2, j:2, cite[2]=3, 3 > j, i--, j++
     * i:1, j:3, cite[1]=1, 1 < j, return j:3
     * ======================================
     * e.g. citations = [0,1], expected 1
     * [0,1]
     *  1 0
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = n - 1, j = 0; i >= 0; i--, j++) {
            if (j >= citations[i]) {
                return j;
            }
        }
        return n;
    }
}
