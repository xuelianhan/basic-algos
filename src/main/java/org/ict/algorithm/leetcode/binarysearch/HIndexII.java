package org.ict.algorithm.leetcode.binarysearch;

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received
 * for their ith paper and citations is sorted in ascending order,
 * return the researcher's h-index.
 * According to the definition of h-index on Wikipedia:
 * The h-index is defined as the maximum value of h such that the given researcher
 * has published at least h papers that have each been cited at least h times.
 *
 * You must write an algorithm that runs in logarithmic time.
 *
 * Example 1:
 * Input: citations = [0,1,3,5,6]
 * Output: 3
 * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total,
 * and each of them had received 0, 1, 3, 5, 6 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each,
 * and the remaining two with no more than 3 citations each, their h-index is 3.
 *
 * Example 2:
 * Input: citations = [1,2,100]
 * Output: 2
 *
 * Constraints:
 * n == citations.length
 * 1 <= n <= 10^5
 * 0 <= citations[i] <= 1000
 * citations is sorted in ascending order.
 *
 * @author sniper
 * @date 05 Apr, 2023
 * LC275, Medium
 * @see org.ict.algorithm.leetcode.sort.HIndex
 */
public class HIndexII {

    public static void main(String[] args) {
        int[] citations = {2,2};
        HIndexII instance = new HIndexII();
        int res = instance.hIndex(citations);
        System.out.println(res);
    }

    /**
     * e.g. citations = [2,2], expected 2
     * e.g. citations = [3,3,3], expected 3
     * So hi can reach the length of citations
     * e.g.[0,1,3,5,6], expected 3
     * lo:0, hi:5, mid:2, cite[2]:3, n-mid=5-2=3, hi=mid=2
     * lo:0, hi:2, mid:1, cite[1]:1, n-mid=5-1=4, lo=mid+1=2
     * lo:2, hi:2, while-loop-ended
     * return n-lo=5-2=3
     *
     * @param citations
     * @return
     */
    public int hIndexV2(int[] citations) {
        int n = citations.length;
        int lo = 0;
        int hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (citations[mid] < n - mid) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return n - lo;
    }

    /**
     * You must write an algorithm that runs in logarithmic time.
     * @param citations
     * @return
     */
    public int hIndexV1(int[] citations) {
        int n = citations.length;
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (citations[mid] == n - mid) {
                return n - mid;
            } else if (citations[mid] < n - mid) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return n - lo;
    }

    /**
     * You must write an algorithm that runs in logarithmic time.
     * e.g.[0,1,3,5,6], expected 3
     *      0 1 2 3 4
     * lo:0, hi:4, 0 < 4, mid:2, cite[2]:3, n-mid=5-3=2, hi=mid=2
     * lo:0, hi:2, 0 < 2, mid:1, cite[1]:1, n-mid=5-1=4, lo=mid+1=2
     * lo:2, hi:2, while-loop-end
     * return n-lo=5-2=3.
     *
     * e.g. [0], expected 0
     * lo:0, hi:0
     *
     * e.g. [1], expected 1
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int lo = 0;
        int hi = n - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (citations[mid] < n - mid) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return citations[n - 1] == 0 ? 0 : n - lo;
    }
}
