package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.*;

/**
 * You are given an integer array coins representing coins of different denominations,
 * and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 104
 * @author sniper
 * @date 23 Nov, 2022
 * LC322, Medium
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        CoinChange instance = new CoinChange();
        int result = instance.coinChangeBFS(coins, amount);
        System.out.println(result);
    }

    public int coinChangeBFSV1(int[] coins, int amount) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(amount);
        visited.add(amount);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();;
                if (cur == 0) {
                    return level;
                }
                /**
                 * No need to do this check, because target >= assure that queue never has negative elements.
                 */
                /*if (cur < 0) {
                    continue;
                }*/
                for (int coin : coins) {
                    int target = cur - coin;
                    if (target >= 0 && !visited.contains(target)) {
                        queue.offer(target);
                        visited.add(target);
                    }
                }//end-for-loop
            }//end-size-while-loop
            /**
             * Notice level++ put after the level-order-traversal in here.
             */
            level++;
        }//end-queue-while-loop
        return -1;
    }

    /**
     * Breadth-First-Search Solution
     * e.g.1: coins = [1,2,5], amount = 11
     * queue:11, visited:11, level:0, size:1, cur: 11,
     *   for-loop:
     *     coin:1, target=11-1=10, 10 > 0 and 10 not visited, queue:10, visited:10
     *     coin:2, target:11-2=9, 9 > 0 and 9 not visited, queue:10,9, visited:10,9
     *     coin:5, target:11-5=6, 6 > 0 and 6 not visited, queue:10,9,6, visited:10,9,6
     *   end-for-loop
     *   size--, size:0,
     *   level++, level:1
     * queue:10,9,6, visited:10,9,6, level:1
     *   size:3, pull 10 from the queue, cur:10
     *   for-loop:
     *     coin:1, target=10-1=9, 9 > 0 and the 9 has been visited, queue:9,6, visited:10,9,6
     *     coin:2, target=10-2=8, 8 > 0 and 8 not visited, queue:9,6,8, visited:10,9,6,8
     *     coin:5, target=10-5=5, 5 > 0 and 5 not visited, queue:9,6,8,5, visited:10,9,6,8,5
     *   end-for-loop
     *   size--,size:2, poll 9 from the queue, cur:9
     *   for-loop:
     *     coin:1, target=9-1=8, 8 > 0 and the 8 has been visited, queue:6,8,5, visited:10,9,6,8,5
     *     coin:2, target=9-2=7, 7 > 0 and 7 not visited, queue:6,8,5,7, visited:10,9,6,8,5,7
     *     coin:5, target=9-5=4, 4 > 0 and 4 not visited, queue:6,8,5,7,4, visited:10,9,6,8,5,7,4
     *   end-for-loop
     *   size--, size:1, pull 6 from the queue, cur:6
     *   for-loop:
     *     coin:1, target=6-1=5, 5 > 0 and the 5 has been visited, queue:6,8,5,7,4, visited:10,9,6,8,5,7,4
     *     coin:2, target=6-2=4, 4 > 0 and the 4 has been visited, queue:6,8,5,7,4, visited:10,9,6,8,5,7,4
     *     coin:5, target=6-5=1, 1 > 0 and 1 not visited, queue:8,5,7,4,1, visited:10,9,6,8,5,7,4,1
     *   end-for-loop
     *   size--, size:0
     *   level++, level:2
     * queue:8,5,7,4,1, visited:10,9,6,8,5,7,4,1
     *   size:5, pull 8 from the queue, cur:8
     *   for-loop:
     *     coin:1, target=8-1=7, 7 > 0 and the 7 has been visited, queue:5,7,4,1, visited:10,9,6,8,5,7,4,1
     *     coin:2, target=8-2=6, 6 > 0 and the 6 has been visited, queue:5,7,4,1, visited:10,9,6,8,5,7,4,1
     *     coin:5, target=8-5=3, 3 > 0 and 3 not visited, queue:5,7,4,1,3 visited:10,9,6,8,5,7,4,1,3
     *   end-for-loop
     *   size--, size:4, pull 5 from the queue, cur:5
     *   for-loop:
     *     coin:1, target=5-1=4, 4 > 0 and the 4 has been visited, queue:7,4,1,3, visited:10,9,6,8,5,7,4,1,3
     *     coin:2, target=5-2=3, 3 > 0 and the 3 has been visited, queue:7,4,1,3, visited:10,9,6,8,5,7,4,1,3
     *     coin:5, target=5-5=0, 0 == 0 and 0 not visited, queue:7,4,1,3,0, visited:10,9,6,8,5,7,4,1,3,0
     *   end-for-loop
     *   size--, size:3, pull 7 from the queue, cur:7
     *   for-loop:
     *     coin:1, target=7-1=6, 6 > 0 and the 6 has been visited, queue:4,1,3,0, visited:10,9,6,8,5,7,4,1,3,0
     *     coin:2, target=7-2=5, 5 > 0 and the 5 has been visited, queue:4,1,3,0, visited:10,9,6,8,5,7,4,1,3,0
     *     coin:5, target=7-5=2, 2 > 0 and 2 not visited, queue:4,1,3,0,2 visited:10,9,6,8,5,7,4,1,3,0,2
     *   end-for-loop
     *   size--, size:2, pull 4 from the queue, cur:4
     *   for-loop:
     *     coin:1, target=4-1=3, 3 > 0 and the 3 has been visited, queue:1,3,0,2 visited:10,9,6,8,5,7,4,1,3,0,2
     *     coin:2, target=4-2=2, 2 > 0 and the 2 has been visited, queue:1,3,0,2 visited:10,9,6,8,5,7,4,1,3,0,2
     *     coin:5, target=4-5=-1, -1 < 0
     *   end-for-loop
     *   size--, size:1, pull 1 from the queue, cur:1
     *   for-loop:
     *     coin:1, target=1-1=0, 0==0, and the 0 has been visited, queue:3,0,2 visited:10,9,6,8,5,7,4,1,3,0,2
     *     coin:2, target=1-2=-1, -1 < 0
     *     coin:5, target=1-5=-4, -4 < 0
     *   end-for-loop
     *   size--, size:0
     *   level++, level:3
     * queue:3,0,2 visited:10,9,6,8,5,7,4,1,3,0,2
     *   size:3, pull 3 from the queue, cur:3
     *   for-loop:
     *     coin:1, target=3-1=2, 2 > 0 and the 2 has been visited, queue:0,2 visited:10,9,6,8,5,7,4,1,3,0,2
     *     coin:2, target=3-2=1, 1 > 0 and the 1 has been visited, queue:0,2 visited:10,9,6,8,5,7,4,1,3,0,2
     *     coin:5, target=3-5=-1, -2 < 0
     *   end-for-loop
     *   size--, size:2, pull 0 from the queue, cur:0
     *   for-loop:
     *     cur:0, return level=3
     *
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeBFS(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        if (amount < coins[0]) {
            return -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(amount);
        visited.add(amount);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();;
                if (cur == 0) {
                    return level;
                }
                /**
                 * Ignore the cur when diff goes below zero.
                 */
                if (cur < 0) {
                    continue;
                }
                for (int coin : coins) {
                    int target = cur - coin;
                    if (target >= 0 && !visited.contains(target)) {
                        queue.offer(target);
                        visited.add(target);
                    }
                }//end-for-loop
            }//end-size-while-loop
            /**
             * Notice level++ put after the level-order-traversal in here.
             */
            level++;
        }//end-queue-while-loop
        return -1;
    }
}
