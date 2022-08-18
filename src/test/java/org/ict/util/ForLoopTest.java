package org.ict.util;

/**
 * @author sniper
 * @date 2022/8/18
 */
public class ForLoopTest {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 1) {
                    /**
                     * This break only interrupt the inner-for-loop
                     */
                    break;
                }
                System.out.println("i:" + i + ", j:" + j);
            }
        }
    }
}
