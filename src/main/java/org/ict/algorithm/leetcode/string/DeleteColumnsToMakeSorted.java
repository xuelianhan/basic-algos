package org.ict.algorithm.leetcode.string;

/**
 * You are given an array of n strings strs, all of the same length.
 *
 * The strings can be arranged such that there is one on each line, making a grid.
 * For example, strs = ["abc", "bce", "cae"] can be arranged as:
 *
 * abc
 * bce
 * cae
 * You want to delete the columns that are not sorted lexicographically.
 * In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted while column 1 ('b', 'c', 'a') is not,
 * so you would delete column 1.
 *
 * Return the number of columns that you will delete.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["cba","daf","ghi"]
 * Output: 1
 * Explanation: The grid looks as follows:
 *   cba
 *   daf
 *   ghi
 * Columns 0 and 2 are sorted, but column 1 is not, so you only need to delete 1 column.
 * Example 2:
 *
 * Input: strs = ["a","b"]
 * Output: 0
 * Explanation: The grid looks as follows:
 *   a
 *   b
 * Column 0 is the only column and is sorted, so you will not delete any columns.
 * Example 3:
 *
 * Input: strs = ["zyx","wvu","tsr"]
 * Output: 3
 * Explanation: The grid looks as follows:
 *   zyx
 *   wvu
 *   tsr
 * All 3 columns are not sorted, so you will delete all 3.
 *
 *
 * Constraints:
 *
 * n == strs.length
 * 1 <= n <= 100
 * 1 <= strs[i].length <= 1000
 * strs[i] consists of lowercase English letters.
 * @author sniper
 * @date 07 Aug, 2022
 */
public class DeleteColumnsToMakeSorted {


    public static void main(String[] args) {
        //String[] strs = {"a","b"};
        //String[] strs = {"cba","daf","ghi"};
        String[] strs = {"zyx","wvu","tsr"};
        int result = minDeletionSize(strs);
        System.out.println(result);
    }

    public int minDeletionSizeV1(String[] strs) {
        int deleteCnt = 0;
        int m = strs.length;;
        int n = strs[0].length();
        for (int c = 0; c < n; c++) {
            char prev = 0;
            for (int r = 0; r < m; r++) {
                char cur = strs[r].charAt(c);
                if (cur < prev) {
                    deleteCnt++;
                    break;
                }
                prev = cur;
            }
        }
        return deleteCnt;
    }

    public static int minDeletionSize(String[] strs) {
        int cnt = 0;
        for(int col = 0; col < strs[0].length(); col++) {
            char prev = 0;
            for (int row = 0; row < strs.length; row++) {
                char current = strs[row].charAt(col);
                if (current < prev) {
                    cnt++;
                    break;
                }
                prev = current;
            }
        }
        return cnt;
    }
}
