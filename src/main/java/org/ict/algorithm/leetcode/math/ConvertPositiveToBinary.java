package org.ict.algorithm.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Convert a positive int to binary form.
 *
 *
 * @author sniper
 * @date 01 Dec, 2022
 */
public class ConvertPositiveToBinary {

    public static void main(String[] args) {
        //int x = (int)Math.pow(2, 30);
        int x = 328;
        String b = toBinary(x);
        String b1 = toBinaryString(x);
        String h = toHex(x);
        String h1 = toHexString(x);
        //x:1073741824, b:1000000000000000000000000000000
        System.out.println("x:" + x + ", b: " + b);
        System.out.println("x:" + x + ", b1:" + b1);
        //x:1073741824, h:40000000
        System.out.println("x:" + x + ", h: " + h);
        System.out.println("x:" + x + ", h1:" + h1);
    }

    public static String toBinary(int x) {
        return Integer.toBinaryString(x);
    }

    public static String toHex(int x) {
        return Integer.toHexString(x);
    }

    public static String toBinaryString(int x) {
        int dividend = x;
        StringBuilder sb = new StringBuilder();
        while (dividend > 0) {
            int mod = dividend % 2;
            dividend /= 2;
            sb.append(mod);
        }
        return sb.reverse().toString();
    }

    public static String toHexString(int x) {
        Map<Integer, Character> map = generateHexMap();
        int dividend = x;
        StringBuilder sb = new StringBuilder();
        while (dividend > 0) {
            int mod = dividend % 16;
            dividend /= 16;
            sb.append(map.get(mod));
        }
        return sb.reverse().toString();
    }

    public static Map<Integer, Character> generateHexMap() {
        Map<Integer, Character> map = new HashMap<>();
        char c = 'a';
        char d = '0';
        for (int i = 0; i < 16; i++) {
            if (i < 10) {
                map.put(i, d);
                d++;
            } else {
                map.put(i, c);
                c++;
            }
        }
        return map;
    }
}
