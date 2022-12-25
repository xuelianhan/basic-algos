package org.ict.algorithm.leetcode.bitvector;

/**
 * @author sniper
 * @date 25 Dec, 2022
 */
public class BitwiseCompliment {

    public static void main(String[] args) {
        BitwiseCompliment instance = new BitwiseCompliment();
        instance.testBitwiseCompliment();
    }

    /**
     * x:-1, y:0
     * x:-2, y:1
     * x:-3, y:2
     * x:-4, y:3
     * x:-5, y:4
     * This means if x < 0, ~x equals -x - 1
     */
    public void testBitwiseCompliment() {
        int x = 0;
        for (int i = 0; i < 5; i++) {
            x -= 1;
            int y = ~x;
            System.out.println("x:" + x + ", y:" + y);
        }
    }
}
