package org.ict.algorithm.leetcode.array;

import java.util.Arrays;
import org.ict.algorithm.util.RandomGenUtil;

public class AnnieTestOne {
    /**
     * A non-empty zero-indexed array A consisting of N integers is given.The amplitude of this array is defined as the largest
     * possible difference between two of its elements.i.e.:
     * amplitude(A) = max{ A[P] - A[Q]: 0 <= P, Q < N,}
     * that,given a non-empty zero-indexed array A consisting of N integers, returns its amplitude.
     * For example, given array A such that:
     * int[] A = {10, 2, 44, 15, 39, 20}
     * the function should return 42
     *
     * Assume that:
     * N is an integer within the range [1, 1,000,000],
     * each element of array A is an integer within the range [0,5,000,000]
     *
     * Complexity:
     * expected worst-case time complexity is O(N);
     * expected worst-case space complexity is O(1),beyond input storage(not counting the storage required for
     * input arguments)
     *
     * Elements of input arrays can be modified.
     *
     */
    public static int solutionA(int[] A) {
        if (A == null || A.length <= 0) {
            return 0;
        }
        Arrays.sort(A);
        int diff = A[A.length - 1] - A[0];
        return diff;
    }

    public static int[] genRandomTestArray(int N, int min, int max) {
        int[] A = new int[N];
        
        for (int i = 0; i < N; i++) {
            A[i] = RandomGenUtil.getRandomIntInRangeV1(min, max);
        }
        return A;
    }

    public static void main(String[] args) {
        int[] A = genRandomTestArray(1000000, 0, 5000000);
        int diff = solutionA(A);
        System.out.println(diff);
    }
}
