package org.ict.algorithm.leetcode.prefixsum;

/**
 * You are given a 0-indexed array nums comprising n non-negative integers.
 * In one operation, you must:
 * Choose an integer i such that 1 <= i < n and nums[i] > 0.
 * Decrease nums[i] by 1.
 * Increase nums[i - 1] by 1.
 * Return the minimum possible value of the maximum integer of nums after performing any number of operations.
 *
 * Example 1:
 * Input: nums = [3,7,1,6]
 * Output: 5
 * Explanation:
 * One set of optimal operations is as follows:
 * 1. Choose i = 1, and nums becomes [4,6,1,6].
 * 2. Choose i = 3, and nums becomes [4,6,2,5].
 * 3. Choose i = 1, and nums becomes [5,5,2,5].
 * The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.
 * Therefore, we return 5.
 *
 * Example 2:
 * Input: nums = [10,1]
 * Output: 10
 * Explanation:
 * It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.
 *
 * Constraints:
 * n == nums.length
 * 2 <= n <= 10^5
 * 0 <= nums[i] <= 10^9
 * @author sniper
 * @date 05 Apr, 2023
 * LC2439, Medium
 */
public class MinimizeMaximumOfArray {

    public static void main(String[] args) {
        int[] nums = {3,7,1,6};
        MinimizeMaximumOfArray instance = new MinimizeMaximumOfArray();
        int res = instance.minimizeArrayValue(nums);
        System.out.println(res);
    }

    /**
     * The best we can do is to reduce nums[i] to the average of array nums[0, i].
     * Time Cost 9ms
     *
     * Intuition
     * In one operation:
     * decrease A[i] by 1.
     * increase A[i - 1] by 1.
     * We actually move the value of A[i] to A[i - 1] by 1,
     * the sum won't change.
     *
     * If A[i] < A[i + 1],
     * then we can repeatedly do the operations,
     * until A[i] >= A[i+1].
     * So finally the array A will become decrescendo order.
     *
     *
     * Explanation
     * We calculate the prefix sum array and their average.
     * The average is the lower bound of the result,
     * and it's reachable lower bound by the process in intuition,
     * so this average is the result.
     *
     *
     * Calculate average
     * We need to calculate the ceil integer of the average,
     * For example 7 = 3 + 2 + 2,
     * the ceil average is 3.
     *
     * The ceil average with sum of i + 1 number:
     * ceil(double(sum) / (i + 1))
     *
     * We can also do integer division:
     * (sum + i) / (i + 1)
     *
     * Complexity
     * Time O(n)
     * Space O(1)
     *
     * @author votrubac lee215
     * @see <a href="https://leetcode.com/problems/minimize-maximum-of-array/solutions/2706472/average/"></a>
     * @see <a href="https://leetcode.com/problems/minimize-maximum-of-array/solutions/2706521/java-c-python-prefix-sum-average-o-n"></a>
     * @param nums
     * @return
     */
    public int minimizeArrayValueV2(int[] nums) {
        long sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res = Math.max(res, ((sum + i) / (i + 1)));
        }
        return (int)res;
    }

    /**
     * Let say, we are making all values of array <= max.
     * we can't change a[0], so if a[0] > max, then it is not possible to make.
     *
     * Now there are two cases :
     * if a[i] > max or if a[i] < max:
     *
     * If a[i] < max :
     * then we can add some value to that element, so extra += max-a[i];
     *
     * If a[i] > max :
     * then we will be taking some value from that element, and will be adding that value to prev elements.
     * Now as ex is containing maximum value which we can add to all the previous elements of that ith element.
     * So if value to remove is greater than ex, then also it is not possible.
     * Otherwise, extra -= diff
     *
     * Time Complexity : O(NlogM)
     * Time Cost 16ms
     * @author x21svge
     * @see <a href="https://leetcode.com/problems/minimize-maximum-of-array/solutions/2706375/binary-search-with-explanation"></a>
     * @param nums
     * @return
     */
    public int minimizeArrayValueV1(int[] nums) {
        int lo = 0;
        int hi = (int)1e9;
        int res = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (feasibleV1(nums, mid)) {
                hi = mid - 1;
                res = mid;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }

    private boolean feasibleV1(int[] nums, int max) {
        long extra = 0;
        for (int num : nums) {
            if (num > max) {
                long diff = num - max;
                if (diff > extra) {
                    return false;
                }
                extra -= diff;
            } else {
                extra += max - num;
            }
        }
        return true;
    }

    /**
     * Binary Search Solution
     * Time Cost 25ms
     *
     * Choose an integer i such that 1 <= i < n and nums[i] > 0.
     * Decrease nums[i] by 1.
     * Increase nums[i - 1] by 1.
     * So the total sum of the array don't change.
     * e.g. nums=[3,7,1,6], sum = 17
     * 1. 7 is the maximum, so choose i = 1, and nums becomes [4,6,1,6], sum = 17, max = 6
     * 2. 6 is the maximum, so choose i = 3, and nums becomes [4,6,2,5], sum = 17, max = 6
     * 3. 6 is the maximum, so choose i = 1, and nums becomes [5,5,2,5], sum = 17, max = 5
     * You can image it as some fluid peaks flow into horizon.
     *
     * @author Larry
     * @param nums
     * @return
     */
    public int minimizeArrayValue(int[] nums) {
        long lo = 0;
        long hi = 1000_000_000;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2L;
            if (feasible(nums, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return (int)lo;
    }

    private boolean feasible(int[] nums, long target) {
        long carry = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            long x = nums[i];
            x += carry;
            carry = 0;
            if (x >= target) {
                carry = x - target;
            }
        }
        return carry == 0;
    }
}
