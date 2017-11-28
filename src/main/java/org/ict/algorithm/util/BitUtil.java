package org.ict.algorithm.util;

/**
 * x ^ 0s = x   x & 0s = 0   x | 0s = x
 *
 *
 *
 */
public class BitUtil {
    
    public static boolean getBit(int num, int i) {
        return  (((1 << i) & num) != 0);
    }

    public static int setBit(int num, int i) {
        return ((1 << i) | num);
    }

    public static int cleanBit(int num, int i) {
        int mask = ~(1 << i);
        return (mask & num);
    }

    /**
     * To clear all bits from most significant bit through i (inclusive),
     * we create a mask with 1 at the i-th bit (1 << i).Then, we substract 1
     * from it, giving  us a sequence of 0s followed by i 1s.We then AND our
     * number with this mask to leave just the last i bits.
     *
     */
    public static int cleaBitsMSBthroughI(int num, int i) {
        int mask = (1 << i) - 1;
        return (mask & num);
    }

    /**
     * To clear all bits from i through 0 (inclusive), we tack a sequence of
     * all 1s (which is -1) and shift it over by 31 - i bits. By using the >>>
     * operator, we shift the initial 1 as well that indicates the sign bit.
     * This gives us a sequence of 1s followed by i 0 bits.
     *
     *
     */
    public static in clearBitsIthrough0(int num, int i) {
        int mask = ~(-1 >> (31 - i));
        return (mask & num);
    }

    /**
     * 
     *
     *
     */
    public static int updateBit(int num, int i, int v) {
        int mask = ~(1 << i); 
        return (mask & num) | (v << i);
    }

}
