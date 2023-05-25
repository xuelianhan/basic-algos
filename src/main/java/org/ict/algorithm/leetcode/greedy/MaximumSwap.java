package org.ict.algorithm.leetcode.greedy;

/**
 * You are given an integer num.
 * You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 *
 * Example 1:
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 *
 * Constraints:
 * 0 <= num <= 10^8
 * @author sniper
 * @date 24 May 2023
 *
 * LC670, Medium, frequency=8
 * Tag: TikTok
 */
public class MaximumSwap {

    public static void main(String[] args) {
        int num = 1993;
        MaximumSwap instance = new MaximumSwap();
        int res = instance.maximumSwap(num);
        System.out.println(res);
    }

    /**
     *
     * @param num
     * @return
     */
    public int maximumSwapV4(int num) {
        int res = num;
        return res;
    }


    /**
     *
     * @param num
     * @return
     */
    public int maximumSwapV3(int num) {
        int res = num;
        return res;
    }


    /**
     *
     * @param num
     * @return
     */
    public int maximumSwapV2(int num) {
        int res = num;
        return res;
    }


    /**
     * Swap the small digit on the high-bit with the large digit on the low-bit
     * e.g. num = 1993
     *
     * @param num
     * @return
     */
    public int maximumSwapV1(int num) {
        int res = num;

        return res;
    }

    /**
     * Brute-Force Solution
     * Time Cost 1ms
     * Swap all the possible locations, and find the maximum.
     *
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        int res = num;
        char[] arr = Integer.toString(num).toCharArray();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(arr, i, j);
                res = Math.max(res, Integer.valueOf(new String(arr)));
                /**
                 * You can swap two digits at most once to get the maximum valued number.
                 * So we need to restore the number to the original order for the next round swap.
                 */
                swap(arr, j, i);
            }
        }
        return res;
    }

    private void swap(char[] arr, int i, int j) {
        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }

}
