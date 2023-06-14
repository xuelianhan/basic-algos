package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * You are given an integer array nums.
 * You want to maximize the number of points you get by performing the following operation any number of times:
 *
 * Pick any nums[i] and delete it to earn nums[i] points.
 * Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 *
 *
 * Example 1:
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 *
 *
 * Example 2:
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 10^4
 * @author sniper
 * @date 14 Jun 2023
 * LC740, Medium, frequency=11
 * This problem reduces to {@link org.ict.algorithm.leetcode.dynamicprogramming.HouseRobber}
 */
public class DeleteAndEarn {

    public int deleteAndEarnV2(int[] nums) {
        int[] bucket = new int[10_001];
        for (int num : nums) {
            bucket[num] += num;
        }

        for (int i = 2; i < 10_001; i++) {
            bucket[i] = Math.max(bucket[i - 1], bucket[i - 2] + bucket[i]);
        }
        return bucket[10_000];
    }

    public int deleteAndEarnV1(int[] nums) {
        int[] bucket = new int[10001];
        for (int num : nums) {
            bucket[num] += num;
        }

        int takePrev = 0;
        int notTakePrev = 0;
        for (int num : bucket) {
            int dp = Math.max(takePrev, notTakePrev + num);
            notTakePrev = takePrev;
            takePrev = dp;
        }

        return takePrev;
    }

    /**
     * Time Cost 4ms
     * e.g. nums = [2,2,3,3,3,4]
     * bucket:[0, 0, 4, 9, 4,...]
     * i:2, takePrev:0, notTakePrev:0, bucket[2]:4
     *      takeCurrent = 0 + 4 = 4
     *      notTakeCurrent = max(0, 0) = 0
     *      takePrev = takeCurrent = 4, notTakePrev = notTakeCurrent = 0
     * i:3, takePrev:4, notTakePrev:0, bucket[3]:9
     *      takeCurrent = 0 + 9 = 9
     *      notTakeCurrent = max(0, 4) = 4
     *      takePrev = takeCurrent = 9, notTakePrev = notTakeCurrent = 4
     * i:4, takePrev:9, notTakePrev:4, bucket[4]:4
     *      takeCurrent = 4 + 4 = 8
     *      notTakeCurrent = max(4, 9) = 9
     *      takePrev = takeCurrent = 8, notTakePrev = notTakeCurrent = 9
     * return max(takePrev, notTakePrev) = max(8, 9) = 9
     *
     *
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int[] bucket = new int[10001];
        for (int num : nums) {
            bucket[num] += num;
        }

        /**
         * Adjacent numbers do not add up to points.
         * e.g. nums = [3,4,2]
         * if we choose 4, then the adjacent numbers(4-1, 4+1) are 3 and 5, 3 cannot be added to the total points.
         *
         * takePrev means take the previous number
         * notTakePrev means not take the previous number
         * Because we iterate by increasing order, so we don't need to consider the later element of current.
         */
        int takePrev = 0;
        int notTakePrev = 0;
        for (int i = 0; i < 10001; i++) {
            /**
             * If we take the current number bucket[i], then we can not take the previous number.
             * If we don't take the current number,
             * the previous number can be taken or not, we choose the bigger one of them.
             */
            int takeCurrent = notTakePrev + bucket[i];
            int notTakeCurrent = Math.max(notTakePrev, takePrev);
            takePrev = takeCurrent;
            notTakePrev = notTakeCurrent;
        }
        return Math.max(takePrev, notTakePrev);
    }

}
