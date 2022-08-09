package org.ict.algorithm.leetcode.math;

/**
 * @author sniper
 * @date 2022/8/9
 */
public class GreatestCommonDivider {

    public int gcdByBruteForce(int n1, int n2) {
        int gcd = 1;
        for (int i = 0; i <= n1 && i <= n2; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    public int gcdByEuclidsAlgorithm(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcdByEuclidsAlgorithm(n2, n1 % n2);
    }
}
