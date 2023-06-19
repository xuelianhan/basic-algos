package org.ict.algorithm.leetcode.array;

/**
 * Given a 0-indexed integer array nums of size n,
 * find the maximum difference between nums[i] and nums[j] (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].
 *
 * Return the maximum difference.
 * If no such i and j exists, return -1.
 *
 *
 * Example 1:
 * Input: nums = [7,1,5,4]
 * Output: 4
 * Explanation:
 * The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
 * Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i > j, so it is not valid.
 *
 * Example 2:
 * Input: nums = [9,4,3,2]
 * Output: -1
 * Explanation:
 * There is no i and j such that i < j and nums[i] < nums[j].
 *
 * Example 3:
 * Input: nums = [1,5,2,10]
 * Output: 9
 * Explanation:
 * The maximum difference occurs with i = 0 and j = 3, nums[j] - nums[i] = 10 - 1 = 9.
 *
 *
 * Constraints:
 * n == nums.length
 * 2 <= n <= 1000
 * 1 <= nums[i] <= 10^9
 * @author sniper
 * @date 19 Jun 2023
 * LC2016, Easy, frequency=9
 */
public class MaximumDifferenceBetweenIncreasingElements {

    /**
     * Understanding the following solution
     *
     * The only difference from LC121.Best Time to Buy and Sell Stock
     * {@link BestTimeToBuyAndSellStock}
     * is that we need to return -1 if no profit can be made.
     * ---------------------------------
     * class Solution {
     * public:
     *     int maximumDifference(vector<int>& nums) {
     *         int res = -1;
     *         int minV = nums[0];
     *         for (int i = 1; i < nums.size(); i++) {
     *             if (nums[i] > minV) {
     *                 res = max(res, nums[i] - minV);
     *             }
     *             minV = std::min(minV, nums[i]);
     *         }
     *         return res;
     *     }
     * };
     * -----------------------------
     * class Solution:
     *     def maximumDifference(self, nums: List[int]) -> int:
     *         res = -1
     *         minV = nums[0]
     *         for i in range(1, len(nums), 1):
     *             if nums[i] > minV:
     *                 res = max(res, nums[i] - minV)
     *             minV = min(minV, nums[i])
     *         return res
     *
     * @param nums
     * @return
     */
    public int maximumDifference(int[] nums) {
        int res = -1;
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > min) {
                res = Math.max(res, nums[i] - min);
            }
            min = Math.min(min, nums[i]);
        }
        return res;
    }
}
