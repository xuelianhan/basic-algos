package org.ict.algorithm.leetcode.math;

/**
 * @author sniper
 * @date 2022/8/9
 */
public class GreatestCommonDivider {

    public static void main(String[] args) {
        int a = 320000000, b = 1600000;
        long start = System.currentTimeMillis();
        int res1 = gcdByEuclidsIterative(a, b);
        System.out.println(res1 + ", cost:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        int res2 = gcdByEuclidsRecursive(a, b);
        System.out.println(res2 + ", cost:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        int res3 = gcdByBruteForce(a, b);
        System.out.println(res3 + ", cost:" + (System.currentTimeMillis() - start));
    }

    public static int gcdByBruteForce(int a, int b) {
        int gcd = 1;
        for (int i = 1; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    public static int gcdByEuclidsRecursive(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdByEuclidsRecursive(a, a % b);
    }

    public static int gcdByEuclidsIterative(int a, int b) {
        int temp = 0;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
