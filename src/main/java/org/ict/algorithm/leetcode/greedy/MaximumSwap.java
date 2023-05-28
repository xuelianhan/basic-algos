package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

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
        int res = instance.maximumSwapV4(num);
        System.out.println(res);
    }


    /**
     * todo
     *
     * Two Pointers Solution
     * ------------------------------------
     * e.g. num = 1993, expected 9913
     * arr: ['1', '9', '9', '3']
     * i:2, pMax:3, arr[i] > arr[pMax], pMax:2, p1:-1, p2:-1
     * i:1, pMax:2, arr[i] == arr[pMax], do nothing
     * i:0, pMax:2, arr[i] < arr[pMax], p1=i=0, p2=pMax=2
     * p1:0, p1 != -1, swap(arr, p1, p2)= swap(arr, 0, 2)
     * arr: ['9', '9', '1', '3']
     *
     * @param num
     * @return
     */
    public int maximumSwapV5(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int n = arr.length;
        /**
         * Indicate the location of maximum digit at low bit.
         * Let's call it pMax for short.
         */
        int idxOfMaxAtLowBit = n - 1;
        /**
         * Indicate the location of big digit at low bit.
         * Let's call it p1.
         */
        int idxOfBigAtLowBit = -1;
        /**
         * Indicate the location of small digit at high bit.
         * Let's call it p2.
         */
        int idxOfSmallAtHighBit = -1;
        /**
         * Notice the order is from low bit to high bit.
         */
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[idxOfMaxAtLowBit]) {
                idxOfSmallAtHighBit = i;
                idxOfBigAtLowBit = idxOfMaxAtLowBit;
            } else if (arr[i] > arr[idxOfMaxAtLowBit]) {
                idxOfMaxAtLowBit = i;
            }
        }
        if (idxOfSmallAtHighBit != -1) {
            swap(arr, idxOfSmallAtHighBit, idxOfBigAtLowBit);
        }
        return Integer.valueOf(new String(arr));
    }

    /**
     * Understanding the following solution
     * Similar with maximumSwapV1 but using a back index-array instead.
     * This method move the index instead of the data.
     * e.g. num = 1993, expected 9913
     * arr:  ['1', '9', '9', '3']
     * back: [ 0,   1,   2,   3]
     * back: [ 2,   2,   2,   3]
     * @param num
     * @return
     */
    public int maximumSwapV4(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int n = arr.length;
        int[] back = new int[n];
        for (int i = 0; i < n; i++) {
            back[i] = i;
        }

        for (int i = n - 2; i >= 0; i--) {
            /**
             * Notice condition is <=, not < here.
             * back[i] store the index of greater than or equal to current element on the right side.
             * e.g. num = 1993
             * Assume that using <, then the back index array like this: [1, 1, 2, 3]
             * The final result will be 9193, not 9913.
             * Because we need to swap the index of big digit from the low bit to high bit,
             * so we need equals == too.
             */
            if (arr[i] <= arr[back[i + 1]]) {
                back[i] = back[i + 1];
            }
        }

        for (int i = 0; i < n; i++) {
            int j = back[i];
            if (arr[i] < arr[j]) {
                swap(arr, i, j);
                return Integer.valueOf(new String(arr));
            }
        }
        return num;
    }


    /**
     * Understanding the following solution
     *
     * Bucket Solution
     * Similar with maximumSwapV2
     * ---------------------------------
     * class Solution:
     *     def maximumSwap(self, num: int) -> int:
     *         # Convert the input integer to a list of digits.
     *         arr = list(str(num))
     *         # Create a bucket array, which stores the index of each digit in the original list.
     *         bucket = [None] * 10
     *         for i, digit in enumerate(arr):
     *             bucket[int(digit)] = i
     *         for i in range(len(arr)):
     *             # Iterate over the bucket array from 9 util the current digit(not include)
     *             for j in range(9, int(arr[i]), -1):
     *                 if bucket[j] and bucket[j] > i:
     *                     arr[i], arr[bucket[j]] = arr[bucket[j]], arr[i]
     *                     print(arr)
     *                     return int("".join(arr))
     *         return num
     *
     * @param num
     * @return
     */
    public int maximumSwapV3(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int[] bucket = new int[10];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i] - '0'] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 9; j > arr[i] - '0'; j--) {
                if (bucket[j] > i) {
                    swap(arr, i, bucket[j]);
                    return Integer.valueOf(new String(arr));
                }
            }
        }
        return num;
    }


    /**
     * Understanding the following solution
     *
     * Bucket Solution
     * Build ten buckets, representing the numbers 0 to 9,
     * and each bucket stores the last position where the number appears, which is the low position.
     * If the corresponding position of the number in the bucket is larger than the current position of the number,
     * which means that the lower position is larger than the current higher position,
     * then the two numbers are directly exchanged and returned.
     * ----------------------------------------------------------
     * class Solution:
     *     def maximumSwap(self, num: int) -> int:
     *         arr = list(str(num))
     *         bucket = [None] * 10
     *         for i, digit in enumerate(arr):
     *             bucket[int(digit)] = i
     *
     *         for i in range(len(arr)):
     *             for j in range(9, int(arr[i]), -1):
     *                 if not bucket[j] or bucket[j] <= i:
     *                     continue
     *                 arr[i], arr[bucket[j]] = arr[bucket[j]], arr[i]
     *                 return int("".join(arr))
     *
     *         return num
     * ----------------------------------------------------------
     * e.g. num = 98368
     * bucket:[0,0,0,2,0,0,3,0,4,0]
     * i:0, arr[0]:'9', j:9, arr[0] - '0'= 9, j == 9
     * i:1, arr[1]:'8', j:9, arr[1] - '0'= 8, j > 8, bucket[9]:0, 0 <= i, skip
     * i:2, arr[2]:'3', j:9, arr[2] - '0'= 3, j > 3, bucket[9]:0, 0 < i, skip
     *                  j:8, j > 3, bucket[8]:4, 4 > i:2, swap(arr, 2, 4), arr:['9', '8', '8', '6', '3']
     *                  return 98863
     * @param num
     * @return
     */
    public int maximumSwapV2(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int[] bucket = new int[10];
        /**
         * Bucket[i] record the lowest location for digit-i.
         */
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i] - '0'] = i;
        }
        /**
         * Notice the iterating order, arr: from high bit to low bit, bucket: find the digit bigger than current digit.
         */
        for (int i = 0; i < arr.length; i++) {
            /**
             * Notice here use j > instead of j >=. Why?
             * Our target is to find the digit bigger than current arr[i] - '0'
             * and to swap them if the digit's index is at the low bit.
             * If the digit is equals to current arr[i] - '0', we don't need to swap them.
             * (Brother, we are same at height.)
             */
            for (int j = 9; j > arr[i] - '0'; j--) {
                if (bucket[j] <= i) {
                    continue;
                }
                swap(arr, i, bucket[j]);
                return Integer.valueOf(new String(arr));
            }
        }
        return num;
    }


    /**
     * Swap the small digit on the high-bit with the large digit on the low-bit.
     * If the large digit is on high-bit already, we don't need to swap.
     * So we need to find the most large digit on the right side of each digit including
     * itself.
     * ---------------------------------
     * e.g. num = 1993, swap the second 9 with the first 1, we get the maximum 9913.
     * input: 1 9 9 3
     * back:  9 9 9 3
     * output:9 9 1 3
     *
     * After we build the back-array,
     * we traverse the original numbers from the beginning and find that 1 is smaller than 9,
     * so we look for 9 from the end to the front,
     * and when we find it, we get 9913
     *
     * @param num
     * @return
     */
    public int maximumSwapV1(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int n = arr.length;
        /**
         * Using a copy, don't use the original array directly.
         */
        char[] back = Arrays.copyOf(arr, n);
        for (int i = n - 2; i >= 0; i--) {
            /**
             * Don't forget to add '0' before converting char back.
             */
            back[i] = (char)(Math.max(back[i] - '0', back[i + 1] - '0') + '0');
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == back[i]) {
                continue;
            }
            for (int j = n - 1; j > i; j--) {
                if (arr[j]== back[i]) {
                    swap(arr, i, j);
                    return Integer.valueOf(new String(arr));
                }
            }
        }
        return num;
    }

    /**
     * Brute-Force Solution
     * Time Cost 1ms
     * Swap all the possible locations, and find the maximum.
     *
     * e.g. num = 1993
     * i:0, j:1, res=max(1993, 9193)=9193
     * i:0, j:2, res=max(9193, 9913)=9913
     * i:0, j:3, res=max(9913, 3991)=9913
     * i:1, j:2, res=max(9913, 1993)=9913
     * i:1, j:3, res=max(9913, 1399)=9913
     * i:2, j:3, res=max(9913, 1939)=9913
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
                 * This means that you have only one chance to swap two digits, or do nothing to get the maximum.
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
