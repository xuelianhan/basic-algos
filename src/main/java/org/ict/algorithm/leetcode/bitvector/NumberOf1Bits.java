package org.ict.algorithm.leetcode.bitvector;


/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 *
 * Note:
 *
 * Note that in some languages, such as Java, there is no unsigned integer type.
 * In this case, the input will be given as a signed integer type.
 * It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation.
 * Therefore, in Example 3, the input represents the signed integer. -3.
 *
 *
 * Example 1:
 *
 * Input: n = 00000000000000000000000000001011
 * Output: 3
 * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 * Example 2:
 *
 * Input: n = 00000000000000000000000010000000
 * Output: 1
 * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
 * Example 3:
 *
 * Input: n = 11111111111111111111111111111101
 * Output: 31
 * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 *
 *
 * Constraints:
 *
 * The input must be a binary string of length 32.
 *
 *
 * Follow up: If this function is called many times, how would you optimize it?
 * @author sniper
 * @date 23 Sep, 2022
 * LC191
 */
public class NumberOf1Bits {

    /**
     * Why doesn't Java support unsigned ints?
     * @see <a href="https://codefordev.com/discuss/5171515356/why-doesnt-java-support-unsigned-ints"></a>
     *
     * Answer #1
     * This is from an interview with Gosling and others, about simplicity:
     *
     * Gosling: For me as a language designer,
     * which I don't really count myself as these days,
     * what "simple" really ended up meaning was could I expect J. Random Developer to hold the spec in his head.
     * That definition says that, for instance,
     * Java isn't -- and in fact a lot of these languages end up with a lot of corner cases,
     * things that nobody really understands. Quiz any C developer about unsigned,
     * and pretty soon you discover that almost no C developers actually understand what goes on with unsigned,
     * what unsigned arithmetic is.
     * Things like that made C complex.
     * The language part of Java is, I think, pretty simple.
     * The libraries you have to look up.
     * -------------------------------------
     * Answer #2
     * With JDK8 there are new APIs for Long and Integer which provide helper methods when treating long and int values as unsigned values.
     *
     * compareUnsigned
     * divideUnsigned
     * parseUnsignedInt
     * parseUnsignedLong
     * remainderUnsigned
     * toUnsignedLong
     * toUnsignedString
     *--------------------------------------
     * Answer #3
     * Reading between the lines, I think the logic was something like this:
     *
     * 1.generally, the Java designers wanted to simplify the repertoire of data types available
     *
     * 2.for everyday purposes, they felt that the most common need was for signed data types
     *
     * 3.for implementing certain algorithms, unsigned arithmetic is sometimes needed,
     * but the kind of programmers that would be implementing such algorithms
     * would also have the knowledge to "work round" doing unsigned arithmetic with signed data types
     * Mostly, I'd say it was a reasonable decision.
     * Possibly, I would have:
     *
     * made byte unsigned, or at least have provided a signed/unsigned alternatives,
     * possibly with different names,
     * for this one data type (making it signed is good for consistency, but when do you ever need a signed byte?)
     * done away with 'short' (when did you last use 16-bit signed arithmetic?)
     * Still, with a bit of kludging, operations on unsigned values up to 32 bits aren't tooo bad,
     * and most people don't need unsigned 64-bit division or comparison.
     * -------------------------------------
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = -3;
        int res = hammingWeightV1(n);
        System.out.println(res);
    }

    /**
     * Brian Kernighan Algorithm
     *
     * Understand the following method.
     * @param n
     * @return
     */
    public int hammingWeightV4(int n) {
        int res = 0;
        while (n != 0) {
            /**
             * if n = 1100, n-1=1011
             * n & (n - 1) = 1000
             * It set the right most 1 in to 0.
             * final effect: 1100 --> 1000
             */
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    /**
     * Understand the following method.
     *
     * The following description of interpreter provided by fabrizio3.
     *
     * An Integer in Java has 32 bits, e.g. 00101000011110010100001000011010.
     * To count the 1s in the Integer representation we put the input int
     * n in bit AND with 1 (that is represented as 00000000000000000000000000000001, and if this operation result is 1,
     * that means that the last bit of the input integer is 1. Thus we add it to the 1s count.
     * ones = ones + (n & 1);
     *
     * Then we shift the input Integer by one on the right, to check for the
     * next bit.
     * n = n>>>1;
     *
     * We need to use bit shifting unsigned operation >>> (while >> depends on sign extension)
     *
     * We keep doing this until the input Integer is 0.
     * In Java we need to put attention on the fact that the maximum integer is 2147483647.
     * Integer type in Java is a signed int and there is no unsigned int.
     * So the input 2147483648 is represented in Java as -2147483648 (in java int type has a cyclic representation,
     * that means Integer.MAX_VALUE+1==Integer.MIN_VALUE).
     *
     * This force us to use n!=0 in the while condition and
     * we cannot use n>0 because the input 2147483648 would correspond to -2147483648 in java and
     * the code would not enter the while if the condition is n>0 for n=2147483648.
     * @param n
     * @return
     */
    public static int hammingWeightV3(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            /**
             * must use >>>(unsigned right shift, only Java has this operation) instead of >>(signed right shift)
             *
             * n >>> 1 will discard one bit at the right most, and complement one bit 0 at the left most.
             */
            n = n >>> 1;
        }
        return res;
    }

    /**
     *
     * @see <a href="https://www.johncanessa.com/2021/12/10/leetcode-191-number-of-1-bits-in-java/"></a>
     * @param n
     * @return
     */
    public static int hammingWeightV2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += n & 1;
            /**
             * using >>> (unsigned right shift)
             */
            n = n >>> 1;
        }
        return res;
    }

    public static int hammingWeightV1(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += n & 1;
            /**
             * using >> (signed right shift )is ok here, but for the safe,
             * you'd better to use >>> instead of >>
             */
            n = n >> 1;
        }
        return res;
    }


    /**
     * you need to treat n as an unsigned value
     *
     *
     * 11111111111111111111111111111101
     * as -3 expected 31, not -2
     * So the following is not right in Java, but right in other languages.
     * @see <a href="https://www.tutorialcup.com/interview/algorithm/number-of-1-bits.htm"></a>
     * @param n
     * @return
     */
    public static int hammingWeightWrongAnswerInJava(int n) {
        return 0;
        /**
         * int res = 0;
         * while (n != 0) {
         *     res += n % 2;
         *     n = n / 2;
         * }
         * return res;
         */

        /**
         *
         * int ans=0;
         * for (int i = 0; i < 32; i++) {
         *     ans = ans + (n & 1);
         *     n = n >> 1;
         * }
         * return ans;
         */
    }
}
