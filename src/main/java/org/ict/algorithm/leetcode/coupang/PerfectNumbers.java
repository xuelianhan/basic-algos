package org.ict.algorithm.leetcode.coupang;

/**
 * Perfect Numbers:
 * If a number can be written as: a^m + b^n, where a,b >= 1, m,n >= 2, we
 * call it a perfect number.
 * e.g., 1 is not a perfect number as there is no way to write 1 in this form.
 * 2=1^2+1^2. Thus, 2 is a perfect number.
 * 3 is not either.
 * 4 is not.
 * 5=1^2 + 2^2. Thus, 5 is a perfect number.
 * Find out how many numbers between 1 - N are perfect numbers.
 * Example:
 * If N=1, return 0.
 * If N=2, return 1 (2 is a perfect number)
 * If N=3, return 1 (only 2 is a perfect number).
 * If N=5, return 2 (both 2 and 5 are perfect numbers).
 * N can be as large as 1,000,000.
 *
 * @see <a href="https://www.sanfoundry.com/java-program-check-given-number-perfect-number"></a>
 * @author sniper
 * @date 20 Jul 2023
 */
public class PerfectNumbers {

    public static void main(String[] args) {
        int N = 2;
        PerfectNumbers instance = new PerfectNumbers();
        int res = instance.countPerfectNumbers(N);
        System.out.println(res);
    }

    public int countPerfectNumbers(int N) {
        int res = 0;
        for (int i = 1; i <= N; i++) {
            if (isPerfectNumber(i)) {
                res++;
            }
        }
        return res;
    }

    public boolean isPerfectNumber(int number) {
        int sum = 0;
        for(int i = 1; i <= number / 2; i++) {
            if(number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }

}
