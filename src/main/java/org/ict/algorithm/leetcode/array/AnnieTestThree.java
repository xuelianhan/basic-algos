package org.ict.algorithm.leetcode.array;

import java.util.Map;
import java.util.HashMap;
import java.math.BigInteger;

public class AnnieTestThree {

    /**
     * A non-empty zero-indexed array A consisting of N integers is given.
     * The array is sorted in ascending order and it does not contain
     * duplicate values. The array describes a number K as follows:
     *   K = power2(A[0]) + power2(A[1]) + ... + power2(A[N - 1])
     * write a function that, given a non-empty zero-indexed array A
     * consisting of N non-negative integers, returns the number of bits
     * set to 1 in the binary representation of the number 3*K, where K 
     * is the number described by array A.
     * 
     * Assume that:
     *   N is an integer within the range[1, 10,000]
     *   each element of array A is an integer within the range
     *   [0,...1,000,000,000]
     *   array A is sorted in non-decreasing order.
     *   array A does not contain duplicates.
     *
     * Complexity:
     *   expected worst-case time complexity is O(N);
     *   expected worst-case space complexity is O(1),beyond input storage
     *   (not counting the storage required for input arguments).
     *
     * Elements of input arrays can be modified.
     *
     */
    public int solutionC(int[] A) {
      if (A == null || A.length <= 0) {
        return 0;
      }
      BigInteger sum = BigInteger.ZERO;
      for (int i : A) {
        BigInteger b =  BigInteger.valueOf(new Double(Math.pow(2, i)).intValue());
        sum = sum.add(b);
      }
      BigInteger three = new BigInteger("3");
      BigInteger result = sum.multiply(three);
      int bitOneCnt = result.bitCount();

      return bitOneCnt;
    }

    /**
     * The above solution may exceed the limit of Integer.
     * Take note of conditions of the problem, 3 * K can break down as
     * (2 + 1) * K = 2 * K + K,
     * K = power2(A[0]) + power2(A[1]) + ... + power2(A[N - 1]) 
     * e.g. K = power(1) + power(4) + power(5)
     * We can calculate the 1's count: 3, because 1 ,4 ,5 is not duplicated
     * numbers, So the solution is obvious: Use HashMap to storage the power number
     * power2(1) + power2(4) + power2(5)
     * + 2 * power2(1) + 2 * power2(4) + 2 * power2(5)
     * = power2(1) + power2(4) + power2(5) + power2(2) + power(5) + power(6);
     * 1, 4, 5, 2, 5, 6 --> 1, 2, 4, 5, 5, 6
     * 1,2,4 -->the 1's count in its binary representaion format is 3
     * 5,5 --> 6 
     * 6, 6 --> 7, so the final sequence of power is 1, 2, 4, 7
     * So the final result is 4, because the sequence has 4 not duplicated power numbers.
     */
    public static int solutionCright(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], A[i]);
        }
        int bitOneCount = A.length; 
        for (int i = 0; i < A.length; i++) {
            int temp = A[i] + 1; 
            Integer loc = map.get(temp); 
            if (loc == null) {
                bitOneCount++;
            } else {
                temp = temp + 1;
            }
            map.put(temp, temp);
        }
        return bitOneCount;
    }

    public static void main(String[] args) {
        int x = Integer.bitCount(150);
        System.out.println(x);
        int[] A = {1, 4, 5};
        int y = solutionCright(A);
        System.out.println(y);
    }
}
