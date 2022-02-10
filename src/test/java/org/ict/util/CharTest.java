package org.ict.util;

/**
 * @author sniper
 * @date 11 Feb, 2022
 */
public class CharTest {

    public static void main(String[] args) {
        char a = 'a';
        char A = 'A';
        System.out.println(a - '0');//49
        System.out.println(A - '0');//17
        int a1 = (int)a;
        int a2 = (int)A;
        System.out.println(a1);//97
        System.out.println(a2);//65
    }
}
