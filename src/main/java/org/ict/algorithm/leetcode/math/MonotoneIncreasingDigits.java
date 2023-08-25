package org.ict.algorithm.leetcode.math;

/**
 * An integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.
 *
 * Given an integer n,
 * return the largest number that is less than or equal to n with monotone increasing digits.
 *
 * Example 1:
 * Input: n = 10
 * Output: 9
 *
 *
 * Example 2:
 * Input: n = 1234
 * Output: 1234
 *
 *
 * Example 3:
 * Input: n = 332
 * Output: 299
 *
 *
 * Constraints:
 * 0 <= n <= 10^9
 * @author sniper
 * @date 25 Aug 2023
 * LC738, Medium, frequency=9
 */
public class MonotoneIncreasingDigits {

    /**
     * Time Cost 1ms
     * e.g. n = 332
     * 332 --> 322 --> 222 --> 299
     * The largest monotonically increasing number can be obtained by
     * finding the last value traversed from back to front that is increasing,
     * letting the previous bit be reduced by 1,
     * and making the current bit, as well as all bits after it to 9
     * @param n
     * @return
     */
    public int monotoneIncreasingDigitsV1(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        int m = arr.length;
        int k = m;
        
        for (int i = m - 1; i > 0; i--) {
            if (arr[i] < arr[i - 1]) {
                arr[i - 1]--;
                k = i;
            }
        }

        for (int i = k; i < m; i++) {
            arr[i] = '9';
        }

        return Integer.parseInt(new String(arr));
    }


    /**
     * Time Cost 1ms
     * e.g. n = 332
     * 332 --> 322 --> 222 --> 299
     * The largest monotonically increasing number can be obtained by
     * finding the last value traversed from back to front that is increasing,
     * letting the previous bit be reduced by 1,
     * and making the current bit, as well as all bits after it to 9
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        int m = arr.length;
        int k = m;

        for (int i = m - 1; i > 0; i--) {
            if (arr[i] >= arr[i - 1]) {
                continue;
            }
            arr[i - 1]--;
            k = i;
        }

        for (int i = k; i < m; i++) {
            arr[i] = '9';
        }
        return Integer.parseInt(new String(arr));
    }


}
