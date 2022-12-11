package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * You are given a positive integer num consisting of exactly four digits.
 * Split num into two new integers new1 and new2 by using the digits found in num.
 * Leading zeros are allowed in new1 and new2, and all the digits found in num must be used.
 *
 * For example, given num = 2932, you have the following digits: two 2's, one 9 and one 3.
 * Some of the possible pairs [new1, new2] are [22, 93], [23, 92], [223, 9] and [2, 329].
 * Return the minimum possible sum of new1 and new2.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 2932
 * Output: 52
 * Explanation: Some possible pairs [new1, new2] are [29, 23], [223, 9], etc.
 * The minimum sum can be obtained by the pair [29, 23]: 29 + 23 = 52.
 * Example 2:
 *
 * Input: num = 4009
 * Output: 13
 * Explanation: Some possible pairs [new1, new2] are [0, 49], [490, 0], etc.
 * The minimum sum can be obtained by the pair [4, 9]: 4 + 9 = 13.
 *
 *
 * Constraints:
 *
 * 1000 <= num <= 9999
 * @author sniper
 * @date 11 Dec, 2022
 * LC2160
 */
public class MinimumSumFourDigitNumberAfterSplittingDigits {

    /**
     * Time Cost 0ms
     * @param num
     * @return
     */
    public int minimumSumV3(int num) {
        int[] arr = {num % 10, (num / 10) % 10, (num / 100) % 10, (num / 1000) % 10};
        Arrays.sort(arr);
        return (arr[0] + arr[1]) * 10 + arr[2] + arr[3];
    }

    /**
     * Time Cost 1ms
     * @param num
     * @return
     */
    public int minimumSumV2(int num) {
        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = num % 10;
            num /= 10;
        }
        Arrays.sort(arr);
        return (arr[0] + arr[1]) * 10 + arr[2] + arr[3];
    }

    /**
     * Time Cost 1ms
     * @param num
     * @return
     */
    public int minimumSumV1(int num) {
        int[] arr = new int[4];
        int i = 0;
        while (num > 0) {
            arr[i] = num % 10;
            num /= 10;
            i++;
        }
        Arrays.sort(arr);
        return (arr[0] + arr[1]) * 10 + arr[2] + arr[3];
    }


    /**
     * Time Cost 14ms
     * We can sort those 4 digits in an increasing order.
     * Let's say input "abcd" where a <= b <= c <= d.
     * We put those two smallest digits to be decimal's place,
     * and those two largest ones in one's place.
     * The answer is simply ac + bd.
     * @param num
     * @return
     */
    public int minimumSum(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        Arrays.sort(arr);
        int m = Integer.parseInt("" + arr[0] + arr[2]);
        int n = Integer.parseInt("" + arr[1] + arr[3]);
        return (m + n);
    }
}
