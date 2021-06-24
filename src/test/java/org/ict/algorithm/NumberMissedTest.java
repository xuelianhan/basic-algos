package org.ict.algorithm;

/**
 * @author sniper
 * @date 2021/6/24 7:18 PM
 */
public class NumberMissedTest {

    public static void main(String[] args) {
        //3 is not in array
        int[] a = new int[]{1,2,4,5};
        int rs = a[0]^a[1]^a[2]^a[3]^1^2^3^4^5;
        //output 3 which is missed.
        System.out.println(rs);
    }
}
