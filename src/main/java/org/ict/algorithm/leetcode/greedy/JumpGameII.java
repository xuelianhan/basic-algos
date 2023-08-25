package org.ict.algorithm.leetcode.greedy;

/**
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1].
 * The test cases are generated such that you can reach nums[n - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 * @author sniper
 * @date 11 Oct, 2022
 * LC45, Medium, frequency=7
 */
public class JumpGameII {

    /**
     * 7ms BFS
     * @param nums
     * @return
     */
    public int jumpV2(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int reachable = 0;
        int lastReachable = 0;
        int level = 0;
        int i = 0;
        /**
         * Nodes of current level
         */
        while (i <= reachable) {
            level++;
            for (; i <= lastReachable ; i++) {
                reachable = Math.max(reachable, i + nums[i]);
                if (reachable >= (nums.length - 1)) {
                    return level;
                }
            }
            lastReachable = reachable;
        }
        return level;
    }

    /**
     * BFS Solution provided by enriquewang.
     * 1 ms
     *
     * I try to change this problem to a BFS problem,
     * where nodes in level-i are all the nodes that can be reached in i-1th jump.
     * for example. 2 3 1 1 4 , is
     * 2||
     * 3 1||
     * 1 4 ||
     *
     * clearly, the minimum jump of 4 is 2 since 4 is in level 3.
     *
     * e.g.
     * [2,3,1,1,4] expected 2
     *
     *
     * @param nums
     * @return
     */
    public int jumpV1(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int reachable = 0;
        int lastReachable = 0;
        int level = 0;
        int i = 0;
        /**
         * Nodes of current level
         */
        while (lastReachable - i + 1 > 0) {
            level++;
            for (; i <= lastReachable ; i++) {
                reachable = Math.max(reachable, i + nums[i]);
                if (reachable >= (nums.length - 1)) {
                    return level;
                }
            }
            lastReachable = reachable;
        }
        return level;
    }

    /**
     * Understanding the following solution
     *
     * 6ms
     *
     * Greedy Solution or Implicit BFS Solution
     * Description detailed by EddieCarrillo.
     * i == lastReachable means you visited all the items on the current level.
     * Incrementing minimumJumps++ is like incrementing the level you are on.
     * While lastReachable = reachable is like getting the queue size (level size) for the next level you are traversing.
     *
     * e.g.
     *  0 1 2 3 4
     * [1,2,1,1,1] expected 3
     * i:0, reachable=max(0, 0+1)=1, lastReachable=0=i, minimumJumps=1, lastReachable=1
     * i:1, reachable=max(1, 1+2)=3, lastReachable=1=i, minimumJumps=2, lastReachable=3
     * i:2, reachable=max(3, 2+1)=3, lastReachable!=i.
     * i:3, reachable=max(3, 3+1)=4, lastReachable=i, minimumJumps=3, lastReachable=4
     * i:4, i < 5 - 1 not match, for-loop-end, return minimumJumps.
     *
     * e.g.
     * [0]
     * for-loop not satisfy the condition, return minimumJumps=0.
     *
     * e.g.
     * [2,1]
     * i:0, reachable=max(0, 0+2)=2, lastReachable=i, minimumJumps=1, lastReachable=2
     * i:1, i < 2 - 1 not match, for-loop-end, return minimumJumps.
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int reachable = 0;
        int lastReachable = 0;
        int minimumJumps = 0;
        /**
         * Because the test cases generated such that you can reach at nums[n - 1].
         * So we step at nums.length-2 is enough.
         */
        for (int i = 0; i < nums.length - 1 ; i++) {
            reachable = Math.max(reachable, i + nums[i]);
            if (i == lastReachable) {
                minimumJumps++;
                lastReachable = reachable;
            }
        }
        return minimumJumps;
    }
}
