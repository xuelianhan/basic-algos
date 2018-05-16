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
 * @see http://mziccard.me/2015/05/08/modulo-and-division-vs-bitwise-operations/
 * @see https://www.geeksforgeeks.org/program-to-find-remainder-without-using-modulo-or-operator/
 * @see https://www.java-forums.org/new-java/33930-how-calculate-remainder-java.html
 * @see https://www.dyclassroom.com/programming/how-to-find-modulus-without-using-modulus-operator
 * @see https://www.omnicalculator.com/math/remainder
 *
 */
public class NumberUtil {
    
    /**
     *  
     * When you perform division, you can typically write down this operation in the following way:
     * x/n = q + r/n, <--> x = q * n + r <--> r = x - q * n
     * where:
     * x is the initial number you want to divide, called the dividend.
     * n is the number you divide by; it is called the divisor.
     * q is the result of division rounded down to the nearest integer; it is called the quotient.
     * r is the remainder of this mathematical operation.
     * 
     * When performing division with our calculator with remainders, 
     * it is important to remember that all of these values must be integers. 
     * Otherwise the result will be correct in the terms of formulas, 
     * but will not make mathematical sense.
     * @param origin
     * @param divisor
     * @return
     */
    public static long getRemainderV2(long origin, long divisor) {
        //mod
        //q = x / n;  //finding quotient (integer part only)
        //p = q * n;  //finding product
        //r = x - p;  //finding modulus
        return (origin - divisor * (origin / divisor));
    }

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
    
    /*origin:0,mod:8, remainderOld:0, remainder:0
      origin:1,mod:8, remainderOld:1, remainder:1
      origin:2,mod:8, remainderOld:2, remainder:2
      origin:3,mod:8, remainderOld:3, remainder:3
      origin:4,mod:8, remainderOld:4, remainder:4
      origin:5,mod:8, remainderOld:5, remainder:5
      origin:6,mod:8, remainderOld:6, remainder:6
      origin:7,mod:8, remainderOld:7, remainder:7
      origin:8,mod:8, remainderOld:0, remainder:0
      origin:9,mod:8, remainderOld:1, remainder:1
    */
    public static void main(String[] args) {
        int mod = 8;// mod must be power of 2
        int loopSize = 10;
        for (int i = 0; i < loopSize; i++) {
            long remainderOld = (i % (mod));
            long remainder = getRemainder(i, mod);
            long remainderNew = getRemainderV2(i, mod);
            System.out.println("origin:" + i + ",mod:" + (mod) + ", remainderOld:" + remainderOld + ", remainder:" + remainder + ", remainderNew:" + remainderNew);
        }
    }
}
