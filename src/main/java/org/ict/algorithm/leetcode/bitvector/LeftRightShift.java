package org.ict.algorithm.leetcode.bitvector;

/**
 * @author sniper
 * @date 07 Dec, 2022
 */
public class LeftRightShift {

    public static void main(String[] args) {
        testSignedRightShift();
        //testUnsignedRightShift();
        //testLeftShiftV2();
        //testLeftShiftV1();
        //testLeftShift();
        //testRightShift();
    }

    /**
     * signed right shift res:1
     */
    public static void testSignedRightShift() {
        int res = 0;
        int a = -2147483648;
        int b = 1;
        for (int x = 31; x >= 0; x--) {
            /**
             * Use unsigned right shift, try from high bit to low bit.
             * we can't use signed right shift >> in here. Why?
             * Consider a case:
             * dividend: -2147483648
             * divisor:1
             * expected: -2147483648, if you use >>, you will get -1 at last.
             */
            if ((a >> x) - b >= 0) {
                System.out.println("(a >> " + x + "):" + (a >> x));
                res += 1 << x;
                a -= b <<x;
            }
        }
        System.out.println("signed right shift res:" + res);
    }

    /**
     * unsigned right shift res:-2147483648
     */
    public static void testUnsignedRightShift() {
        int res = 0;
        int a = -2147483648;
        int b = 1;
        for (int x = 31; x >= 0; x--) {
            /**
             * Use unsigned right shift, try from high bit to low bit.
             * we can't use signed right shift >> in here. Why?
             * Consider a case:
             * dividend: -2147483648
             * divisor:1
             * expected: -2147483648, if you use >>, you will get -1 at last.
             */
            if ((a >>> x) - b >= 0) {
                System.out.println("(a >>> " + x + "):" + (a >>> x));
                res += 1 << x;
                a -= b <<x;
            }
        }
        System.out.println("unsigned right shift res:" + res);
    }

    /**
     * x: 0, (b << (x + 1)):2, x: 1
     * x: 1, (b << (x + 1)):4, x: 2
     * x: 2, (b << (x + 1)):8, x: 3
     * x: 3, (b << (x + 1)):16, x: 4
     * x: 4, (b << (x + 1)):32, x: 5
     * x: 5, (b << (x + 1)):64, x: 6
     * x: 6, (b << (x + 1)):128, x: 7
     * x: 7, (b << (x + 1)):256, x: 8
     * x: 8, (b << (x + 1)):512, x: 9
     * x: 9, (b << (x + 1)):1024, x: 10
     * x: 10, (b << (x + 1)):2048, x: 11
     * x: 11, (b << (x + 1)):4096, x: 12
     * x: 12, (b << (x + 1)):8192, x: 13
     * x: 13, (b << (x + 1)):16384, x: 14
     * x: 14, (b << (x + 1)):32768, x: 15
     * x: 15, (b << (x + 1)):65536, x: 16
     * x: 16, (b << (x + 1)):131072, x: 17
     * x: 17, (b << (x + 1)):262144, x: 18
     * x: 18, (b << (x + 1)):524288, x: 19
     * x: 19, (b << (x + 1)):1048576, x: 20
     * x: 20, (b << (x + 1)):2097152, x: 21
     * x: 21, (b << (x + 1)):4194304, x: 22
     * x: 22, (b << (x + 1)):8388608, x: 23
     * x: 23, (b << (x + 1)):16777216, x: 24
     * x: 24, (b << (x + 1)):33554432, x: 25
     * x: 25, (b << (x + 1)):67108864, x: 26
     * x: 26, (b << (x + 1)):134217728, x: 27
     * x: 27, (b << (x + 1)):268435456, x: 28
     * x: 28, (b << (x + 1)):536870912, x: 29
     * x: 29, (b << (x + 1)):1073741824, x: 30
     */
    public static void testLeftShiftV2() {
        int a = Integer.MAX_VALUE;
        int b = 1;
        int x = 0;
        while (a - (b << (x + 1)) >= 0) {
            System.out.print("x: " + x + ", (b << (x + 1)):" + (b << (x + 1)) + ", ");
            x++;
            System.out.println("x: " + x);
        }
    }


    /**
     * (b << x << 1) equals b << (x + 1).
     *
     * x: 0, (b << x << 1):2, x: 1
     * x: 1, (b << x << 1):4, x: 2
     * x: 2, (b << x << 1):8, x: 3
     * x: 3, (b << x << 1):16, x: 4
     * x: 4, (b << x << 1):32, x: 5
     * x: 5, (b << x << 1):64, x: 6
     * x: 6, (b << x << 1):128, x: 7
     * x: 7, (b << x << 1):256, x: 8
     * x: 8, (b << x << 1):512, x: 9
     * x: 9, (b << x << 1):1024, x: 10
     * x: 10, (b << x << 1):2048, x: 11
     * x: 11, (b << x << 1):4096, x: 12
     * x: 12, (b << x << 1):8192, x: 13
     * x: 13, (b << x << 1):16384, x: 14
     * x: 14, (b << x << 1):32768, x: 15
     * x: 15, (b << x << 1):65536, x: 16
     * x: 16, (b << x << 1):131072, x: 17
     * x: 17, (b << x << 1):262144, x: 18
     * x: 18, (b << x << 1):524288, x: 19
     * x: 19, (b << x << 1):1048576, x: 20
     * x: 20, (b << x << 1):2097152, x: 21
     * x: 21, (b << x << 1):4194304, x: 22
     * x: 22, (b << x << 1):8388608, x: 23
     * x: 23, (b << x << 1):16777216, x: 24
     * x: 24, (b << x << 1):33554432, x: 25
     * x: 25, (b << x << 1):67108864, x: 26
     * x: 26, (b << x << 1):134217728, x: 27
     * x: 27, (b << x << 1):268435456, x: 28
     * x: 28, (b << x << 1):536870912, x: 29
     * x: 29, (b << x << 1):1073741824, x: 30
     */
    public static void testLeftShiftV1() {
        int a = Integer.MAX_VALUE;
        int b = 1;
        int x = 0;
        while (a - (b << x << 1) >= 0) {
            System.out.print("x: " + x + ", (b << x << 1):" + (b << x << 1) + ", ");
            x++;
            System.out.println("x: " + x);
        }
    }

    /**
     * (b << x << 1) equals b << (x + 1).
     *
     * signed right shift equals divide 2^N, N belongs to closed range [1, 31]
     * 2147483647 >> 1: 1073741823
     * 2147483647 >> 2: 536870911
     * 2147483647 >> 3: 268435455
     * 2147483647 >> 4: 134217727
     * 2147483647 >> 5: 67108863
     * 2147483647 >> 6: 33554431
     * 2147483647 >> 7: 16777215
     * 2147483647 >> 8: 8388607
     * 2147483647 >> 9: 4194303
     * 2147483647 >> 10: 2097151
     * 2147483647 >> 11: 1048575
     * 2147483647 >> 12: 524287
     * 2147483647 >> 13: 262143
     * 2147483647 >> 14: 131071
     * 2147483647 >> 15: 65535
     * 2147483647 >> 16: 32767
     * 2147483647 >> 17: 16383
     * 2147483647 >> 18: 8191
     * 2147483647 >> 19: 4095
     * 2147483647 >> 20: 2047
     * 2147483647 >> 21: 1023
     * 2147483647 >> 22: 511
     * 2147483647 >> 23: 255
     * 2147483647 >> 24: 127
     * 2147483647 >> 25: 63
     * 2147483647 >> 26: 31
     * 2147483647 >> 27: 15
     * 2147483647 >> 28: 7
     * 2147483647 >> 29: 3
     * 2147483647 >> 30: 1
     * 2147483647 >> 31: 0
     */
    public static void testRightShift() {
        int x = 0;
        for (int i = 0; i < 31; i++) {
            x = Integer.MAX_VALUE >> (i + 1);
            System.out.println(Integer.MAX_VALUE + " >> " + (i + 1) + ": " + x);
        }
    }

    /**
     * Left shift equals multiply 2^N, N belongs to closed range [1, 31]
     * 1 << 1: 2
     * 1 << 2: 4
     * 1 << 3: 8
     * 1 << 4: 16
     * 1 << 5: 32
     * 1 << 6: 64
     * 1 << 7: 128
     * 1 << 8: 256
     * 1 << 9: 512
     * 1 << 10: 1024
     * 1 << 11: 2048
     * 1 << 12: 4096
     * 1 << 13: 8192
     * 1 << 14: 16384
     * 1 << 15: 32768
     * 1 << 16: 65536
     * 1 << 17: 131072
     * 1 << 18: 262144
     * 1 << 19: 524288
     * 1 << 20: 1048576
     * 1 << 21: 2097152
     * 1 << 22: 4194304
     * 1 << 23: 8388608
     * 1 << 24: 16777216
     * 1 << 25: 33554432
     * 1 << 26: 67108864
     * 1 << 27: 134217728
     * 1 << 28: 268435456
     * 1 << 29: 536870912
     * 1 << 30: 1073741824
     * 1 << 31: -2147483648
     */
    public static void testLeftShift() {
        int x = 0;
        for (int i = 0; i < 31; i++) {
            x = 1 << (i + 1);
            System.out.println("1 << " + (i + 1) + ": " + x);
        }
    }
}
