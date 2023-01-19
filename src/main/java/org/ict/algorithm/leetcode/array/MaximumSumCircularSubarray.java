package org.ict.algorithm.leetcode.array;

/**
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning of the array.
 * Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most once.
 * Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3.
 * Example 2:
 *
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
 * Example 3:
 *
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * @author sniper
 * @date 19 Jan, 2023
 * LC918, Medium
 */
public class MaximumSumCircularSubarray {

    /**
     * One Pass Solution provided by lee215.
     * Intuition
     * I guess you know how to solve max subarray sum (without circular).
     * If not, you can have a reference here: 53. Maximum Subarray
     *
     *
     * Explanation
     * So there are two case.
     * Case 1. The first is that the subarray take only a middle part, and we know how to find the max subarray sum.
     * Case2. The second is that the subarray take a part of head array and a part of tail array.
     * We can transfer this case to the first one.
     * The maximum result equals to the total sum minus the minimum subarray sum.
     *
     *
     * Here is a diagram by @motorix:
     *
     * Case1: max SubArray is not circular
     * 0------Max subArray------N-1
     *
     * Case2: max SubArray is circular
     * 0-------------------Max subArray-------------------2*N-1
     *
     * equals:
     * 0---Max---Min SubArray---SubArray--N-1
     *
     *
     * So the max subarray circular sum equals to
     * max(the max subarray sum, the total sum - the min subarray sum)
     *
     *
     * Prove of the second case
     * max(prefix+suffix)
     * = max(total sum - subarray)
     * = total sum + max(-subarray)
     * = total sum - min(subarray)
     *
     *
     * Corner case
     * Just one to pay attention:
     * If all numbers are negative, maxSum = max(A) and minSum = sum(A).
     * In this case, max(maxSum, total - minSum) = 0, which means the sum of an empty subarray.
     * According to the deacription, We need to return the max(A), instead of sum of am empty subarray.
     * So we return the maxSum to handle this corner case.
     *
     *
     * Complexity
     * One pass, time O(N)
     * No extra space, space O(1)
     * @see <a href="https://leetcode.com/problems/maximum-sum-circular-subarray/solutions/178422/one-pass"></a>
     * @author lee215
     * @param nums
     * @return
     */
    public int maxSubarraySumCircularV1(int[] nums) {
        int total = 0, maxSum = nums[0], curMax = 0, minSum = nums[0], curMin = 0;
        for (int a : nums) {
            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + a, a);
            minSum = Math.min(minSum, curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    /**
     * Kadane Algorithm
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {

        return 0;
    }
}
