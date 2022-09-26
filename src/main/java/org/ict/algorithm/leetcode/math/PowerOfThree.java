package org.ict.algorithm.leetcode.math;

/**
 *
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 *
 * An integer n is a power of three, if there exists an integer x such that n == 3^x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 27
 * Output: true
 * Explanation: 27 = 3^3
 * Example 2:
 *
 * Input: n = 0
 * Output: false
 * Explanation: There is no x where 3^x = 0.
 * Example 3:
 *
 * Input: n = -1
 * Output: false
 * Explanation: There is no x where 3^x = (-1).
 *
 *
 * Constraints:
 *
 * -2^31 <= n <= 2^31 - 1
 *
 *
 * Follow up: Could you solve it without loops/recursion?
 *
 * @author sniper
 * @date 26 Sep, 2022
 * LC326
 */
public class PowerOfThree {

    /**
     * General Solution to check if any number N is a power of P:
     *
     *
     * log3(81) = 4
     * log3(84) = 4.0222
     *
     * if any number B is some power of A, then logB to the base A
     * will always give an integer as illustrated above.
     *
     * Log has a property where Log(a(b)) = Log(b)/Log(a)
     * Therefore, power can be calculated as power(P) = Log(b)/Log(a)
     * e.g.
     * log3(81) = Log(81)/Log(3) = 4
     * log3(84) = Log(84)/Log(3) = 4.0222
     * if P is not an integer then B is not power of A.
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThreeV4(int n) {
        int a = 3;
        int b = n;
        double p = Math.log10(b)/Math.log10(a);
        return (p - (int)p) == 0;
    }

    public boolean isPowerOfThreeV3(int n) {
        return ((n > 0) && (1162261467 % n == 0));
    }


    /**
     * A solution to the problem is by checking for the value that is power of 3.
     * We will check if the given number N divides 1162261467 (3^19).
     * If it is a power of 3, the remainder with be 0 i.e. N will divide it.
     * If it does not, the number is not the power of 3.
     * @param n
     * @return
     */
    public boolean isPowerOfThreeV2(int n) {
        return ((n > 0) && (Math.pow(3, 19) % n == 0));
    }

    /**
     * Note that for an integer N that is a power of 3 the following is true:
     *
     * For any M <= N that is a power of 3, M divides N.
     * For any M <= N that is not a power 3, M does not divide N.
     * The biggest power of 3 that fits into 32 bits is 3486784401 (3^20). This gives the following code:
     *
     * bool isPower3(std::uint32_t value) {
     *     return value != 0 && 3486784401u % value == 0;
     * }
     * Similarly for signed 32 bits it is 1162261467 (3^19):
     *
     * bool isPower3(std::int32_t value) {
     *     return value > 0 && 1162261467 % value == 0;
     * }
     * In general the magic number is:
     *
     * 3^floor(log_3 MAX) == pow(3, floor(log(MAX) / log(3)))
     *
     * Careful with floating point rounding errors, use a math calculator like Wolfram Alpha to calculate the constant.
     * For example for 2^63-1 (signed int64) both C++ and Java give 4052555153018976256,
     * but the correct value is 4052555153018976267.
     *
     * The positive divisors of 319 are exactly the powers of 3 from 30 to 319.
     * That's all powers of 3 in the possible range here (signed 32-bit integer).
     * So just check whether the number is positive and whether it divides 319.
     * @param n
     * @return
     */
    public boolean isPowerOfThreeV1(int n) {
        if (n <= 0) {
            return false;
        }
        int magic = (int)Math.pow(3, 19);
        return (magic % n == 0);
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

}
