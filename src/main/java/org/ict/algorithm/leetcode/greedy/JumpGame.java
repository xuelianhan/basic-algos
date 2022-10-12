package org.ict.algorithm.leetcode.greedy;

/**
 * You are given an integer array nums.
 * You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what.
 * Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^5
 * @author sniper
 * @date 11 Oct, 2022
 * LC55
 */
public class JumpGame {

    public boolean canJumpV3(int[] nums) {
        int reachable = 0;
        int i = 0;
        for (; i < nums.length && i <= reachable; i++) {
            reachable = Math.max(reachable, i + nums[i]);
        }
        return (i == nums.length);
    }

    public boolean canJumpV2(int[] nums) {
        int reachable = 0;
        for (int i = 0; i <= reachable; i++) {
            reachable = Math.max(reachable, i + nums[i]);
            if (reachable >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * We are initially positioned at the array's first index.
     * So we iterate the array from begin to end.
     *
     * Greedy Solution provided by hllowrld
     *
     * The basic idea is this: at each step, we keep track of the furthest reachable index.
     * The nature of the problem (eg. maximal jumps where you can hit a range of targets instead of singular jumps where you can only hit one target)
     * is that for an index to be reachable, each of the previous indices have to be reachable.
     *
     * Hence, it suffices that we iterate over each index,
     * and If we ever encounter an index that is not reachable,
     * we abort and return false.
     * By the end, we will have iterated to the last index. If the loop finishes, then the last index is reachable.
     *
     * e.g.
     * nums=[2, 3, 1, 1, 4]
     * 0 1 2 3 4
     * 2 3 1 1 5
     * i:0, reachable=max(0, 0+2)=2, reachable>0
     * i:1, reachable=max(2, 1+3)=4, reachable>1
     * i:2, reachable=max(4, 2+1)=4, reachable>2
     * i:3, reachable=max(4, 3+1)=4, reachable>3
     * i:4, reachable=max(4, 4+4)=8, reachable>4
     *
     * e.g.
     * nums=[3,2,1,0,4]
     * 0 1 2 3 4
     * 3 2 1 0 4
     * i:0, reachable=max(0, 0+3)=3, reachable>0
     * i:1, reachable=max(3, 1+2)=3, reachable>1
     * i:2, reachable=max(3, 2+1)=3, reachable>2
     * i:3, reachable=max(3, 3+0)=3, reachable=3
     * i:4, reachable<4, return directly, for-loop-ended
     *
     * @param nums
     * @return
     */
    public boolean canJumpV1(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (reachable < i) {
                return false;
            }
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }


    /**
     * Greedy Solution
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int upper = nums.length - 1;
        int jumpIndex = 0;
        for (int i = 0; i < upper; i++) {
            jumpIndex = Math.max(jumpIndex, i + nums[i]);
            if (jumpIndex <= i) {
                return false;
            }
        }
        return jumpIndex >= upper;
    }
}
