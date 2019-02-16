package org.ict.algorithm.leetcode.array;

public class AnnieTestTwo {

    /**
     * A non-empty zero-indexed array A consisting of N integers is given.
     * Any integer k, such that 0<= k < N - 1, splits array A into two 
     * non-empty parts:A[0],...A[K], and A[K+1],...A[N-1]
     * The difference between the two parts is the value of 
     * abs(max{A[0],...A[K]} - max{A[K+1],...A[N-1]})
     * Given a non-empty zero-indexed array A contains N integers, 
     * return the maximum difference between the two parts into which
     * it can be split.
     * For example, given the following array A:
     * int[] A = {1, 3, -3} the function should return abs(3 - (-3)) = 6 
     * int[] A = {4, 3, 2, 5, 1, 1} the function should return abs(5 - 1) = 4 at K = 3
     * Assume that:
     * N is an integer within the range[2, 100,000]
     * each element of array A is an integer within the range[-1,000,000,000, 1,000,000,000]
     * 
     * Complexity:
     * expected worst-case time complexity is O(N).
     * expected worst-case space complexity is O(N), beyond 
     * input storage (not counting the storage required for input arguments)
     *
     * Elements of input arrays can be modified.
     *
     */
    public static int solutionB(int[] A) {
        if (A == null || A.length <= 0) {
            return 0;
        }
        int[] B = new int[A.length]; 

        int kthMax = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > kthMax) {
                kthMax = A[i];
            }
            B[i] = kthMax;
        }

        int maxAbs = 0;
        int nthMin = A[A.length - 1];
        for (int j = A.length - 1; j >= 0; j--) {
            if (A[j] < nthMin) {
                nthMin = A[j];
            }
            int abs = Math.abs(B[j] - nthMin);
            if (abs > maxAbs) {
                maxAbs = abs;
            }
        }
        return maxAbs;
    }

    public static void main(String[] args) {


    }
}
