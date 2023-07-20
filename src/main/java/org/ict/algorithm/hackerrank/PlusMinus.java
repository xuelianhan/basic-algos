package org.ict.algorithm.hackerrank;

import java.util.List;

/**
 * @see <a href="https://www.hackerrank.com/challenges/plus-minus/problem?isFullScreen=true"></a>
 * @author sniper
 * @date 20 Jul 2023
 */
public class PlusMinus {

    public static void plusMinus(List<Integer> arr) {
        int pCnt = 0;
        int nCnt = 0;
        int zCnt = 0;
        for (int x : arr) {
            if (x > 0) {
                pCnt++;
            } else if (x < 0) {
                nCnt++;
            } else {
                zCnt++;
            }
        }
        int n = arr.size();

        System.out.println(Math.round(pCnt*1.0 / n * 1000000.0) / 1000000.0);
        System.out.println(Math.round(nCnt*1.0 / n * 1000000.0) / 1000000.0);
        System.out.println(Math.round(zCnt*1.0 / n * 1000000.0) / 1000000.0);
    }
}
