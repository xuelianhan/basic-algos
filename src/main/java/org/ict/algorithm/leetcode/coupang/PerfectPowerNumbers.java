package org.ict.algorithm.leetcode.coupang;

import java.util.ArrayList;
import java.util.List;

/**
 * Perfect Power Numbers:
 * If a number can be written as: a^m + b^n, where a,b >= 1, m,n >= 2, we
 * call it a perfect number.
 * e.g.,
 * 1 is not a perfect number as there is no way to write 1 in this form.
 * 2 = 1^2 + 1^2. Thus, 2 is a perfect number.
 * 3 is not either.
 * 4 is not.
 * 5 = 1^2 + 2^2. Thus, 5 is a perfect number.
 * 6 is not.
 * 7 is not.
 * 8 = 2^2 + 2^2. Thus, 8 is a perfect number.
 * 9 = 2^3 + 1^2. Thus, 9 is a perfect number.
 * 10 = 3^2 + 1^2. Thus, 10 is a perfect number.
 * Find out how many numbers between 1 - N are perfect power numbers.
 * Example:
 * If N=1, return 0.
 * If N=2, return 1 (2 is a perfect power number)
 * If N=3, return 1 (only 2 is a perfect power number).
 * If N=4, return 1 (only 2 is a perfect power number).
 * If N=5, return 2 (both 2 and 5 are perfect power numbers).
 * If N=6, return 2 (both 2 and 5 are perfect power numbers).
 * If N=7, return 2 (both 2 and 5 are perfect power numbers).
 * If N=8, return 3 (2,5,8 are perfect power numbers).
 * If N=9, return 4 (2,5,8,9 are perfect power numbers).
 * If N=10, return 5 (2,5,8,9,10 are perfect power numbers).
 *
 * N can be as large as 1,000,000.
 *
 *
 * @see <a href="https://www.quora.com/What-is-the-algorithmic-approach-required-to-find-if-a-number-can-be-expressed-as-sum-of-two-perfect-powers-That-is-given-x-find-if-there-exists-non-negative-integers-a-b-m-n-such-that-a-m-+-b-n-x"></a>
 * @see <a href="https://www.sanfoundry.com/java-program-check-given-number-perfect-number"></a>
 * @see <a href="https://www.geeksforgeeks.org/check-if-a-number-can-be-expressed-as-sum-of-two-perfect-powers/"></a>
 * @see <a href="https://www.geeksforgeeks.org/count-of-numbers-in-range-l-r-which-can-be-represented-as-sum-of-two-perfect-powers/"></a>
 * @author sniper
 * @date 20 Jul 2023
 */
public class PerfectPowerNumbers {

    public static void main(String[] args) {
        int N = 12;
        PerfectPowerNumbers instance = new PerfectPowerNumbers();
        int res = instance.countPerfectPowerNumbers(N);
        System.out.println(res);
    }

    public int countPerfectPowerNumbers(int N) {
        return countPerfectPowerNumbers(1, N);
    }

    public int countPerfectPowerNumbers(int L, int R) {
        /**
         * Iterate over all the exponents from 2 to 20
         * 2^10=1024
         * 2^20=1024*1024
         * Generate all possible power of all the numbers that are less than R from 2 and store those numbers in
         * array powers.
         */
        List<Integer> powers = new ArrayList<>();
        powers.add(1);
        
        for (int p = 2; p <= 20; p++) {
            int num = 2;
            while ((int)(Math.pow(num, p) + 0.5) < R) {
                powers.add((int)(Math.pow(num, p) + 0.5));
                num++;
            }
        }

        /**
         * Stores if the i can be expressed as the sum of perfect power or not
         */
        int[] ok = new int[R + 2];
        for (int i = 0; i < powers.size(); i++) {
            for (int j = 0; j < powers.size(); j++) {
                int sum = powers.get(i) + powers.get(j);
                if (sum >= L && sum <= R) {
                    ok[sum] = 1;
                }
            }
        }

        /**
         * Find the prefix sum of the ok array.
         */
        for (int i = 1; i <= R; i++) {
            ok[i] += ok[i - 1];
        }

        return ok[R] - ok[L - 1];
    }



}
