package org.ict.algorithm.leetcode.slidewindow;

/**
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous sub-array whose length is equal to k that has the maximum average value and return this value.
 * Any answer with a calculation error less than 10^-5 will be accepted.
 *
 *
 *
 * Example 1:
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Example 2:
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 *
 * Constraints:
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * @author sniper
 * @date 23 Jul 2023
 * LC643, Easy
 */
public class MaximumAverageSubArrayI {

    public static void main(String[] args) {
        int[] nums = {-1};
        int k = 1;
        MaximumAverageSubArrayI instance = new MaximumAverageSubArrayI();
        double res = instance.findMaxAverage(nums, k);
        System.out.println(res);
    }

    /**
     * Improved Sliding-Window Solution
     * Time Cost 6ms
     * -------------------
     * class Solution {
     * public:
     *     double findMaxAverage(vector<int>& nums, int k) {
     *         double sum = accumulate(nums.begin(), nums.begin() + k, 0), res = sum;
     *         for (int i = k; i < nums.size(); i++) {
     *             sum += nums[i] - nums[i - k];
     *             res = max(res, sum);
     *         }
     *         return res / k;
     *     }
     * };
     * -------------------
     * class Solution:
     *     def findMaxAverage(self, nums: List[int], k: int) -> float:
     *         sumk = sum(nums[:k])
     *         ans = sumk
     *         for i in range(k, len(nums)):
     *             sumk += nums[i] - nums[i - k]
     *             ans = max(ans, sumk)
     *         return ans / k
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverageV2(int[] nums, int k) {
        int n = nums.length;
        double sum = 0.0;
        /**
         *  1 <= k <= n <= 10^5
         */
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double res = sum;
        for (int i = k; i < n; i++) {
            sum += nums[i] - nums[i - k];
            res = Math.max(res, sum);
        }
        return res / k;
    }

    /**
     * Improved Sliding-Window Solution
     * Time Cost 6ms
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverageV1(int[] nums, int k) {
        int n = nums.length;
        double sum = 0.0;
        for (int i = 0; i < Math.min(n, k); i++) {
            sum += nums[i];
        }
        double res = sum;
        for (int i = k; i < n; i++) {
            sum += nums[i] - nums[i - k];
            res = Math.max(res, sum);
        }
        return res / k;
    }

    /**
     * Time Limit Exceeded.
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        /**
         * Notice here, in java, Double.MIN_VALUE is actually a positive number
         */
        double res = Double.NEGATIVE_INFINITY;
        int n = nums.length;
        for (int i = 0; i < n - k + 1; i++) {
            double sum = 0;
            int l = i;
            int r = i + k - 1;
            while (l <= r) {
                sum += nums[l++];
            }
            res = Math.max(res, sum);
        }
        return res / k;
    }

}
