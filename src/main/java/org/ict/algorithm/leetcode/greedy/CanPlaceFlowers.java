package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * You have a long flowerbed in which some of the plots are planted, and some are not.
 * However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's,
 * where 0 means empty and 1 means not empty, and an integer n,
 * return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 *
 *
 *
 * Example 1:
 *
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: true
 * Example 2:
 *
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: false
 * [0, 0, 0, 0]
 *
 * Constraints:
 *
 * 1 <= flowerbed.length <= 2 * 10^4
 * flowerbed[i] is 0 or 1.
 * There are no two adjacent flowers in flowerbed.
 * 0 <= n <= flowerbed.length
 * @author sniper
 * @date 07 Jan, 2023
 * LC605, Easy
 */
public class CanPlaceFlowers {

    public static void main(String[] args) {
        CanPlaceFlowers instance = new CanPlaceFlowers();
        int[] flowerbed = {1,0,0,0,0,0,0,0,1,0,0};
        //[0,1,0,0,0,0,0,0,0,1,0,0,0]
        int n = 4;
        boolean flag = instance.canPlaceFlowers(flowerbed, n);
        System.out.println("flag:" + flag);
    }

    public boolean canPlaceFlowersV5(int[] flowerbed, int n) {
        return false;
    }

    /**
     * We can find out the extra maximum number of flowers, count,
     * that can be planted for the given flowerbed arrangement.
     * To do so, we can traverse over all the elements of the flowerbed and find out those elements which are 0(implying an empty position).
     * For every such element, we check if its both adjacent positions are also empty.
     * If so, we can plant a flower at the current position without violating the no-adjacent-flowers-rule.
     * For the first and last elements, we need not check the previous and the next adjacent positions respectively.
     * If the count obtained is greater than or equal to n,
     * the required number of flowers to be planted,
     * we can plant nnn flowers in the empty spaces, otherwise not.
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowersV4(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            /**
             * if current position is empty, check whether its left and right are both empty or not.
             */
            if (flowerbed[i] == 0) {
                boolean emptyL = (i == 0 || flowerbed[i - 1] == 0);
                boolean emptyR = (i == flowerbed.length - 1 || flowerbed[i + 1] == 0);
                if (emptyL && emptyR) {
                    flowerbed[i] = 1;
                    count++;
                    if (count >= n) {
                        return true;
                    }
                }
            }
        }
        return count >= n;
    }

    /**
     * Similar as canPlaceFlowersV2, but combine i += 2 and i++ in if-else-switch.
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowersV3(int[] flowerbed, int n) {
        int[] copy = new int[flowerbed.length + 2];
        /**
         * Add zero at the start, and the end place of copied array
         */
        copy[0] = 0;
        copy[copy.length - 1] = 0;
        for (int i = 1; i < copy.length - 1; i++) {
            copy[i] = flowerbed[i - 1];
        }
        int res = 0;
        for (int i = 1; i + 1 < copy.length; i++) {
            if (copy[i - 1] == 0 && copy[i] == 0 && copy[i + 1] == 0) {
                res++;
                i++;
            }
        }
        return res >= n;
    }

    /**
     * Understanding the following Solution.
     * Time Cost 1ms
     * Time Complexity O(N)
     * Space Complexity O(N)
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowersV2(int[] flowerbed, int n) {
        int[] copy = new int[flowerbed.length + 2];
        /**
         * Add zero at the start, and the end place of copied array
         */
        copy[0] = 0;
        copy[copy.length - 1] = 0;
        for (int i = 1; i < copy.length - 1; i++) {
            copy[i] = flowerbed[i - 1];
        }
        int res = 0;
        for (int i = 1; i + 1 < copy.length;) {
            if (copy[i - 1] == 0 && copy[i] == 0 && copy[i + 1] == 0) {
                res++;
                i += 2;
            } else {
                i++;
            }
        }
        return res >= n;
    }

    /**
     * Understanding the following Solution.
     * Time Cost 1ms
     * Similar with canPlaceFlowers but delete the block of checking case of flowerbed.length == 1.
     * Time Complexity O(N)
     * Space Complexity O(N)
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowersV1(int[] flowerbed, int n) {
        int[] copy = new int[flowerbed.length + 2];
        /**
         * Add zero at the start, and the end place of copied array
         */
        copy[0] = 0;
        copy[copy.length - 1] = 0;
        for (int i = 1; i < copy.length - 1; i++) {
            copy[i] = flowerbed[i - 1];
        }
        int res = 0;
        for (int i = 0, j = i + 1, k = i + 2; k < copy.length;) {
            if (copy[i] == 0 && copy[j] == 0 && copy[k] == 0) {
                res++;
                i = k;
            } else {
                i++;
            }
            j = i + 1;
            k = j + 1;
        }
        return res >= n;
    }

    /**
     * Time Cost 1ms
     * Copy the original array, and add zero at first and last places of the copy array.
     * e.g. copy = [0, original flowerbed elements here ,0], then check the copy array at three continuous places.
     * Time Complexity O(N)
     * Space Complexity O(N)
     *
     * e.g.flowerbed = [1,0,0,0,0,0,1], n = 2, expected:true
     * e.g.flowerbed = [1,0,0,0,0,0,0,0,1,0,0], n = 4, expected: false
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1) {
            /**
             * e.g.flowerbed = [1], n = 0, expected true
             * e.g.flowerbed = [1], n > 0, expected false
             */
            if (flowerbed[0] == 1 && n > 0) {
                return false;
            } else {
                return n <= 1;
            }
        }
        int[] copy = new int[flowerbed.length + 2];
        copy[0] = 0;
        copy[copy.length - 1] = 0;
        for (int i = 1; i < copy.length - 1; i++) {
            copy[i] = flowerbed[i - 1];
        }
        int res = 0;
        for (int i = 0, j = i + 1, k = i + 2; k < copy.length;) {
           if (copy[i] == 0 && copy[j] == 0 && copy[k] == 0) {
               res++;
               i = k;
           } else {
               i++;
           }
           j = i + 1;
           k = j + 1;
        }
        return res >= n;
    }
}
