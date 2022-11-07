package org.ict.algorithm.leetcode.math;

/**
 * @author sniper
 * @date 06 Nov, 2022
 *
 */
public class CalculatePi {

    public static void main(String[] args) {

        double res  = calculatePIV2(10000);
        System.out.println(res);
    }

    /**
     * limit:5
     * π = 4 *(1 - 1/3 + 1/5 -1/7 + 1/9 - 1/11)
     *
     * limit:6
     * π = 4 *(1 - 1/3 + 1/5 -1/7 + 1/9 - 1/11 + 1/13 - 1/15)
     *
     * @return
     */
    public static double calculatePIV1(int limit) {
        double res = 1;
        double sign = -1;
        double n = 2;
        for (int i = 0; i < limit; i++) {
            res += sign * 1/(2*n -1);
            sign = sign * (-1);
            n += 2;
        }
        return 4*res;
    }

    public static double calculatePIV2(int limit) {
        double res = 0;
        double k = 1;
        for (int i = 0; i < limit; i++) {
            if (i % 2 == 0) {
                res += 4 / k;
            } else {
                res -= 4 / k;
            }
            k += 2;
        }
        return res;
    }

    /**
     * Python version
     *
     * <code>
     * k = 1
     * s = 0
     * for i in range(6):
     *   if i % 2 == 0:
     *     s += 4/k
     *   else:
     *     s -= 4/k
     *   k += 2
     * print (s)
     *
     * </code>
     */



    /**
     * Calculate Pi using Nilkantha’s series
     *
     * π = 3 + 4 / (2*3*4) – 4 / (4*5*6) + 4 / (6*7*8) – . .
     *
     * @param PI
     * @param n
     * @param sign
     * @param limit
     * @return
     */
    public static double calculatePI(double PI, double n, double sign, int limit) {
        // Add for 1000000 terms
        double res = 0;
        for (int i = 0; i <= limit; i++) {
            res = PI + (sign * (4 / ((n) * (n + 1) * (n + 2))));
            sign = sign * (-1);

            n += 2;
        }

        return res;
    }
}
