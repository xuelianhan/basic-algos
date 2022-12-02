package org.ict.algorithm.leetcode.math;

/**
 * They are using two's complement notation to represent negative numbers.
 * To get it, you start with your value, express it in binary,
 * change all the 0's to 1's and vice versa, then add 1.
 * You can do it directly in hex,
 * subtracting each digit from 15,
 * then adding 1.
 * It would be clearer what is going on if you convert the numbers on the left (which are in decimal) to hex,
 * so 20010=𝐶8𝐻 and 𝐶8𝐻+38𝐻=0𝐻.
 * It has the advantage that you can add, subtract, and multiply without worrying about the signs.
 * There are other notations.
 *
 * Change the number into 8 bit binary number then take 2's complement;
 *  you will get the hexadecimal of negative number.
 *
 * e.g., for −3 change into binary: 00000011 take 2's complement: 11111101=𝐹𝐷 (hex)
 * @see <a href="https://www.instructables.com/Convert-Negative-Numbers-to-Binary/"></a>
 * @author sniper
 * @date 01 Dec, 2022
 *
 */
public class ConvertNegativeToBinary {

    /**
     * -328:
     * 328:0000 0001 0100 1000
     * flip each bit:1111 1110 1011 0111
     * add 1 at the tail:
     *
     * @param args
     */
    public static void main(String[] args) {
        int x = - (int)Math.pow(2, 30);
        String rs1 = toBinary(x);
    }

    /**
     * For Java there is no unsigned int data type.
     * So in case of java, convert number to long and make the 32 higher bits as all zeroes.
     * Idea remains the same as above.
     * @param x
     * @return
     */
    public static String toBinary(int x) {
        return null;
    }

    /**
     * For Java there is no unsigned int data type.
     * So in case of java, convert number to long and make the 32 higher bits as all zeroes.
     * Idea remains the same as above.
     * @param x
     * @return
     */
    public static String toHex(int x) {
        return null;
    }


}
