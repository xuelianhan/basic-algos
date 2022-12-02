package org.ict.algorithm.leetcode.bitvector;

import java.util.Arrays;

/**
 * There is a hidden integer array arr that consists of n non-negative integers.
 *
 * It was encoded into another integer array encoded of length n - 1, such that encoded[i] = arr[i] XOR arr[i + 1].
 * For example, if arr = [1,0,2,1], then encoded = [1,2,3].
 *
 * You are given the encoded array.
 * You are also given an integer first, that is the first element of arr, i.e. arr[0].
 *
 * Return the original array arr.
 * It can be proved that the answer exists and is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: encoded = [1,2,3], first = 1
 * Output: [1,0,2,1]
 * Explanation: If arr = [1,0,2,1], then first = 1 and encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 * Example 2:
 *
 * Input: encoded = [6,2,7,3], first = 4
 * Output: [4,2,0,7,4]
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^4
 * encoded.length == n - 1
 * 0 <= encoded[i] <= 10^5
 * 0 <= first <= 10^5
 * @author sniper
 * @date 02 Dec, 2022
 * LC1720
 */
public class DecodeXORedArray {

    public static void main(String[] args) {
        int[] encoded = {1,2,3};
        int first = 1;
        DecodeXORedArray instance = new DecodeXORedArray();
        int[] res = instance.decode(encoded, first);
        System.out.println(Arrays.toString(res));
    }

    public int[] decodeV4(int[] encoded, int first) {
        int n = encoded.length;
        int[] res = new int[n + 1];
        res[0] = first;
        for (int i = 1; i <= n; i++) {
            res[i] = encoded[i - 1] ^ res[i - 1];
        }
        return res;
    }


    public int[] decodeV3(int[] encoded, int first) {
        int n = encoded.length;
        int[] res = new int[n + 1];
        res[0] = first;
        int i = 1;
        for (int num : encoded) {
            res[i] = num ^ res[i - 1];
            i++;
        }
        return res;
    }


    public int[] decodeV2(int[] encoded, int first) {
        int n = encoded.length;
        int[] res = new int[n + 1];
        int last = 0;
        res[0] = first;
        int i = 1;
        for (int num : encoded) {
            last = num ^ res[i - 1];
            res[i++] = last;
        }
        return res;
    }

    public int[] decodeV1(int[] encoded, int first) {
        int n = encoded.length;
        int[] res = new int[n + 1];
        int last = 0;
        res[0] = first;
        int i = 0;
        for (int num : encoded) {
            last = num ^ res[i++];
            res[i] = last;
        }
        return res;
    }

    /**
     * arr[i] ^ arr[i + 1]= encoding[i]
     * arr[0] = first
     * So,
     * arr[1] = encoding[0] ^ arr[0]
     * arr[2] = encoding[1] ^ arr[1]
     * arr[3] = encoding[2] ^ arr[2]
     * ...
     * arr[n] = encoding[n-1] ^ arr[n-1]
     *
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] res = new int[n + 1];
        int last = 0;
        res[0] = first;
        int i = 0;
        for (int num : encoded) {
            last = num ^ res[i];
            i++;
            res[i] = last;
        }
        return res;
    }
}
