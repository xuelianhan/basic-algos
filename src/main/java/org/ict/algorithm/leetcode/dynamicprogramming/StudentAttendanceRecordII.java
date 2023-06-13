package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * An attendance record for a student can be represented as a string
 * where each character signifies whether the student was absent, late, or present on that day.
 * The record only contains the following three characters:
 *
 * 'A': Absent.
 * 'L': Late.
 * 'P': Present.
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 *
 * The student was absent ('A') for strictly fewer than 2 days total.
 * The student was never late ('L') for 3 or more consecutive days.
 * Given an integer n, return the number of possible attendance records of length n
 * that make a student eligible for an attendance award.
 * The answer may be very large, so return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 8
 * Explanation: There are 8 records with length 2 that are eligible for an award:
 * "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
 * Example 2:
 *
 * Input: n = 1
 * Output: 3
 * Example 3:
 *
 * Input: n = 10101
 * Output: 183236316
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * @author sniper
 * @date 13 Jun 2023
 * LC552, Hard, frequency=14
 */
public class StudentAttendanceRecordII {

    /**
     * todo
     * Understanding the following solution
     * ---------------------------------------
     * P(n) is the total number of all possible attendance records ended with 'P' with length n.
     * L(n) is the total number of all possible attendance records ended with 'L' with length n.
     * A(n) is the total number of all possible attendance records ended with 'A' with length n.
     * @see <a href="https://leetcode.com/problems/student-attendance-record-ii/solutions/101643/share-my-o-n-c-dp-solution-with-thinking-process-and-explanation/"></a>
     * @author KJer
     * @param n
     * @return
     */
    public int checkRecordV2(int n) {
        int m = 1000000007;
        int[] A = new int [n];
        int[] P = new int [n];
        int[] L = new int [n];

        P[0] = 1;//P
        L[0] = 1;//L
        A[0] = 1;//A

        if (n > 1) {
            L[1] = 3;//AL, PL, LL
            A[1] = 2;//LA, PA
        }
        if (n > 2) {
            A[2] = 4;//LLA, LPA, PLA, PPA
        }
        for(int i = 1; i < n; i++){
            A[i - 1] %= m;
            P[i - 1] %= m;
            L[i - 1] %= m;
            int temp = (A[i - 1] + P[i - 1]) % m;
            P[i] = (temp + L[i - 1]) % m;
            if(i > 1) {
                L[i] = (temp + (A[i - 2] + P[i - 2]) % m) % m;
            }
            if(i > 2) {
                A[i] = ((A[i - 1] + A[i - 2]) % m + A[i - 3]) % m;
            }
        }

        return ((A[n - 1] % m + P[n - 1] % m) % m + L[n - 1] % m) % m;
    }

    /**
     * @see <a href="https://leetcode.com/problems/student-attendance-record-ii/solutions/101633/improving-the-runtime-from-o-n-to-o-log-n/"></a>
     * @author lixx2100
     * @param n
     * @return
     */
    public int checkRecordV1(int n) {
        final int MOD = 1000000007;
        int[][][] f = new int[n + 1][2][3];

        f[0] = new int[][]{{1, 1, 1}, {1, 1, 1}};
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    int val = f[i - 1][j][2]; // ...P
                    if (j > 0) val = (val + f[i - 1][j - 1][2]) % MOD; // ...A
                    if (k > 0) val = (val + f[i - 1][j][k - 1]) % MOD; // ...L
                    f[i][j][k] = val;
                }
            }
        }
        return f[n][1][2];
    }

    /**
     *
     * @see <a href="https://leetcode.com/problems/student-attendance-record-ii/solutions/101638/simple-java-o-n-solution/"></a>
     * @author dettier
     * @param n
     * @return
     */
    public int checkRecord(int n) {
        long mod = 1_000_000_007L;
        /**
         * PorL[i] indicates the number of permutations of the first i numbers in the array ending with P or L, no A
         */
        long[] PorL = new long[n + 1];
        /**
         * P[i] indicates the number of permutations of the first i numbers in the array1 ending with P, no A
         */
        long[] P = new long[n + 1];
        /**
         * index-0 may be P or L, there is only one permutation: P or L,
         * if you choose P, then index-0 cannot be L, otherwise the same case
         */
        PorL[0] = 1;
        /**
         * index-0 is P, there is only one permutation: P
         */
        P[0] = 1;
        /**
         * index-0 is P, index-1 is L, otherwise:
         * PL, LP
         */
        PorL[1] = 2;
        /**
         * PP
         */
        P[1] = 1;

        for (int i = 2; i <= n; i++) {
            P[i] = PorL[i - 1];
            PorL[i] = (P[i] + P[i - 1] + P[i - 2]) % mod;
        }

        long res = PorL[n];
        for (int i = 0; i < n; i++) {
            long s = (PorL[i] * PorL[n - i - 1]) % mod;
            res = (res + s) % mod;
        }

        return (int)res;
    }
}
