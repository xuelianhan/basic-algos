package org.ict.algorithm.util;

/**
 * A remainder is the amount left after a number is taken out of another number a set amount of times. This is best explained by an example:
 * Java Code:  
 * Remainder of 26 / 6:
 * 26 - 6 = 20
 *        20 - 6 = 14
 *                 14 - 6 = 8
 *                          8 - 6 = 2
 *        Since 2 is less than 6, it is the remainder.
 *
 * Therefore, the REMAINDER of 26/6 = 2.
 * Modulus (A%B) calculates the remainder of A/B. 
 * 
 * // Multiplication
 * i * 8; // normal
 * i << 3; // bitwise [8 = 2^3, so use 3]
 *  
 * // Division
 * i / 16; // normal
 * i >> 4; // bitwise [16 = 2^4, so use 4]
 *  
 * // Modulus
 * i % 4; // normal
 * i & 3; // bitwise [4 = 1 << 2, apply ((1 << 2) - 1), so use 3]
 *
 */
public class NumberUtil {

    /**
     * Mod must be the power of 2, e.g. 2, 4, 8, 16...,
     * Only in this case, the result is same with % modular operator
     * Return remainders between 0(inclusive) and (mod-1)(inclusive)
     * @param origin
     * @param mod
     * @return
     */
    public static long getRemainder(long origin, long mod) {
        long remainder = origin & (mod - 1);
        return remainder;
    }
    
    public static void main(String[] args) {
        int mod = 8;// mod must be power of 2
        int loopSize = 10;
        for (int i = 0; i < loopSize; i++) {
            long remainderOld = (i % (mod));
            long remainder = getRemainder(i, mod);
            System.out.println("origin:" + i + ",mod:" + (mod) + ", remainderOld:" + remainderOld + ", remainder:" + remainder);
        }
    }
}
