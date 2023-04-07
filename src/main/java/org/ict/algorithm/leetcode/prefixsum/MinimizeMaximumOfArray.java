package org.ict.algorithm.leetcode.prefixsum;

import java.util.Arrays;

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
        //int[] nums = {3,7,1,6};
        int[] nums = {10, 1};
        MinimizeMaximumOfArray instance = new MinimizeMaximumOfArray();
        int res = instance.minimizeArrayValue(nums);
        System.out.println(res);
    }

    /**
     * Understanding the following Solution
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
    public int minimizeArrayValueV3(int[] nums) {
        long sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res = Math.max(res, ((sum + i) / (i + 1)));
        }
        return (int)res;
    }

    /**
     * Understanding the following Solution
     * @param nums
     * @return
     */
    public int minimizeArrayValueV2(int[] nums) {
        /**
         * 0 <= nums[i] <= 10^9
         */
        int lo = 0;
        int hi = (int)1e9;
        /**
         * Alternative:  int hi = Arrays.stream(nums).max().getAsInt();
         */
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (feasibleV2(nums, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
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
     * Time Complexity: O(NlogM), where M = max(nums) <= 1e9
     * Time Cost 16ms
     *
     * Assume that the hi is initialized to the maximum of the nums array at first.
     * e.g. [3,7,1,6], maximum = 7
     * lo:0, hi:7, mid = 3, avg=mid=3
     *   3 == 3, extra=0+3-3=0
     *   7 > 3, diff=7-3=4, extra:0, diff > extra, return false, lo=mid+1:4
     * lo:4, hi:7, mid = 5, avg=mid=5
     *   3 < 5, extra=0+5-3=2
     *   7 > 5, diff=7-5=2, extra:2, diff==extra, extra=2-diff=0
     *   1 < 5, extra=0+5-1=4
     *   6 > 5, diff=6-5=1, extra:4, diff < extra, extra=4-1=3, return true, hi=mid-1=5-1=4, res=mid=5
     * lo:4, hi:4, mid = 4, avg=mid=4
     *   3 < 4, extra=0+4-3=1
     *   7 > 4, diff=7-4=3, extra:1, diff > extra, return false, lo=mid+1=4+1=5
     * lo:5, hi:4, while-loop-ended, return res:5
     *
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
            if (feasibleV2(nums, mid)) {
                hi = mid - 1;
                res = mid;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }

    /**
     * Time Cost 16ms
     * If avg is greater than nums[i], this means it can contribute some parts of it(avg - nums[i]) to extra for later consumption.
     * If avg is equal to nums[i], this means it can neither contribute to extra nor to weaken extra.
     * If avg is less than nums[i], this means it cannot cover the num[i], and need extra to provide some help to
     * make up the difference(num - avg). Moreover, if extra cannot over the difference, it means this avg is not feasible.
     * So return false immediately.
     * While all the elements has been accessed, no false returned at any middle process, it means this avg is feasible.
     *
     * @param nums
     * @param avg
     * @return
     */
    private boolean feasibleV2(int[] nums, int avg) {
        long extra = 0;
        for (int num : nums) {
            if (num > avg) {
                long diff = num - avg;
                if (diff > extra) {
                    return false;
                }
                extra -= diff;
            } else {
                extra += avg - num;
            }
        }
        return true;
    }


    /**
     * Improved version of feasible
     * Time Cost 13ms
     * @param nums
     * @param avg
     * @return
     */
    private boolean feasibleV1(int[] nums, int avg) {
        if (nums[0] > avg) {
            return false;
        }
        long prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            long diff = avg - prev;
            prev = nums[i] - diff;
            if (prev > avg) {
                return false;
            }
        }
        return true;
    }

    public int minimizeArrayValueV0(int[] nums) {
        int lo = 0;
        /**
         * Alternative: int hi = 1000_000_000;
         */
        int hi = Arrays.stream(nums).max().getAsInt();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (feasible(nums, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * Improved version of feasible
     * This method indicates the principle of this problem is average.
     * e.g. average = (a1 + a2 + a3 +...+ aN) / N
     * Our mission to the check the difference of the input avg and the real average.
     * If the predicted avg is not enough to cover the sum, it means this avg is lower than the real average
     * Otherwise return true if the predicted avg is enough to over the sum.
     *
     * Time Cost 19ms
     * @param nums
     * @param avg
     * @return
     */
    private boolean feasibleV0(int[] nums, int avg) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            /**
             * Use long to prevent overflow
             */
            if (sum > (long)avg * (i + 1)) {
                return false;
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
        int lo = 0;
        int hi = 1000_000_000;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (feasible(nums, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }


    /**
     * Time Cost 25ms
     * This method feasible is less efficient than method feasibleV2, because it needs to
     * iterate all the elements in the array, it doesn't fail rapidly.
     * e.g. nums = [10,1]
     * Assume that the hi is initialized to the maximum of the nums array at first.
     * lo:0, hi:10, mid:5, avg=mid=5
     *   nums[1]:1, x:1, x=x+carry=1+0=1, carry:0, x < avg
     *   nums[0]:10, x:10, x=x+carry=10+0=10, carry:0, x > avg, carry=x-avg=10-5=5
     *   carry:5, carry != 0, return false, lo=mid+1=6
     * lo:6, hi:10, mid=8, avg=mid=8
     *   nums[1]:1, x:1, x=x+carry=1+0=1, carry:0, x < avg
     *   nums[0]:10, x:10, x=x+carry=10+0=10, carry:0, x > avg, carry=x-avg=10-8=2
     *   carry:2, carry != 0, return false, lo=mid+1=9
     * lo:9, hi:10, mid=9, avg=mid=9
     *    nums[1]:1, x:1, x=x+carry=1+0=1, carry:0, x < avg
     *    nums[0]:10, x:10, x=x+carry=10+0=10, carry:0, x > avg, carry=x-avg=10-9=1
     *    carry:1, carry != 0, return false, lo=mid+1=10
     * lo:10, hi:10, while-loop-ended, return lo:10
     *
     * @param nums
     * @param avg
     * @return
     */
    private boolean feasible(int[] nums, long avg) {
        /**
         * In this method implementation,
         * Notice here we cannot access from index-0 such as:
         * for (int i = 0; i < nums.length; i++) {
         * Why?
         * Choose an integer i such that 1 <= i < n and nums[i] > 0.
         * Decrease nums[i] by 1.
         * Increase nums[i - 1] by 1.
         * e.g. nums = [3,7,1,6]
         * e.g. nums = [10,1]
         */
        long prevCarry = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            long x = nums[i];
            x += prevCarry;//add prevCarry to current value
            prevCarry = 0;//reset prevCarry to zero for next iteration
            if (x >= avg) {
                //if average cannot cover the sum of current value and prevCarry,
                //so prevCarry has remained difference to use for next iteration
                prevCarry = x - avg;
            }
        }
        return prevCarry == 0;
    }
}
