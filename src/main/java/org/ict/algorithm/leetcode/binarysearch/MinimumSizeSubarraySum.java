package org.ict.algorithm.leetcode.binarysearch;

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a sub-array whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 * Constraints:
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 * @author sniper
 * @date 04 Apr, 2023
 * LC209, Medium
 */
public class MinimumSizeSubarraySum {


    /**
     * Slide Window Two-pointers solution
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLenV5(int target, int[] nums) {
        /**
         * Notice the location of r++ and  res = Math.min(res, r - l);
         */
        int l = 0;
        int r = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r++];
            while (sum >= target) {
                res = Math.min(res, r - l);
                sum -= nums[l++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * Slide Window Two-pointers solution
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLenV4(int target, int[] nums) {
        /**
         * Notice the location of r++ and  res = Math.min(res, r - l + 1);
         */
        int l = 0;
        int r = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r];
            while (sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
            r++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


    /**
     * Slide Window Two-pointers solution
     *
     * The O(n) solution is to use two pointers: l and r.
     * First we move r until we get a sum >= s, then we move l to the right until sum < s.
     * In this process, store the minimum length between l and r.
     * Since each element in nums will be visited by l and r for at most once.
     * This algorithm is of O(n) time.
     * @author jianchao-li
     * @see <a href="https://leetcode.com/problems/minimum-size-subarray-sum/solutions/59090/c-o-n-and-o-nlogn"></a>
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLenV3(int target, int[] nums) {
        /**
         * Notice the location of r++ and  res = Math.min(res, r - l);
         */
        int l = 0;
        int r = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r];
            r++;
            while (sum >= target) {
                res = Math.min(res, r - l);
                sum -= nums[l];
                l++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }




    /**
     * Slide Window Two-pointers solution
     *
     * Intuition
     * 862.Shortest Subarray with Sum at Least K
     * Actually I did this first, the same problem but have negatives.
     * I suggest solving this problem first then take 862 as a follow-up.
     *
     * Explanation
     * The result is initialized as res = n + 1.
     * One pass, remove the value from sum s by doing s -= A[j].
     * If s <= 0, it means the total sum of A[i] + ... + A[j] >= sum that we want.
     * Then we update the res = min(res, j - i + 1)
     * Finally we return the result res.
     *
     * Complexity
     * Time O(N)
     * Space O(1)
     *
     * e.g. target = 7, nums = [2,3,1,2,4,3]
     *
     *
     * @author lee215
     * @see <a href="https://leetcode.com/problems/minimum-size-subarray-sum/solutions/433123/java-c-python-sliding-window"></a>
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLenV2(int target, int[] nums) {
        int n = nums.length;
        /**
         * Two points need to notice here:
         * The res has been initialized as (n + 1);
         * The return res using res % (n + 1), if res is (n + 1) at last, it means
         * there is no answer, so (n + 1) % (n + 1) = 0, otherwise return a value from 1 to n.
         */
        int res = n + 1;
        for (int l = 0, r = 0; r < n; r++) {
            target -= nums[r];
            while (target <= 0) {
                res = Math.min(res, r - l + 1);
                target += nums[l];
                l++;
            }
        }
        return res % (n + 1);
    }

    /**
     * Return the minimal length of a sub-array whose sum is greater than or equal to target.
     *
     * Slide Window Two-pointers solution
     * Time Complexity O(N)
     *
     * e.g. target = 7, nums = [2,3,1,2,4,3]
     * l:0, r:0, sum:2, l == r, but sum < 7, r++:r:1
     * l:0, r:1, sum:5, l < r, but sum < 7, r++:r:2
     * l:0, r:2, sum:6, l < r, but sum < 7, r++:r:3
     * l:0, r:3, sum:8, l < r and sum > 7
     *    res=min(max, r-l+1)=min(max, 3-0+1)=4
     *    sum=sum-nums[0]=8-2=6, l++:l:1
     *    l:1, r:3, l < r but sum < 7, inner-while-loop-ended
     *    r:4
     * l:1, r:4, sum=6+4=10, l < r and sum > 7
     *    res=min(4, r-l+1)=min(4, 4)=4
     *    sum=sum-nums[1]=10-3=7, l++:l:2
     *    l < r and sum == 7
     *    res=min(4,4-2+1)=3
     *    sum=sum-nums[2]=7-1=6, l++:l:3
     *    l < r but sum < 7, inner-while-loop-ended
     *    r:5
     * l:3, r:5, sum=6+nums[5]=6+3=9, l < r and sum > 7
     *    res=min(3, 5-3+1)=3
     *    sum=sum-nums[3]=6-2=4, l++:l:4
     *    l < r but sum < 7, inner-while-loop-ended
     *    r:6
     * l:4, r:6, for-loop-ended, return res:3
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLenV1(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int l = 0, r = 0, sum = 0; r < nums.length; r++) {
            sum += nums[r];
            while (l <= r && sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * Binary Search Solution
     * T(k) = T(k/2) + O(N) (using k to make it more clear), making the complexity O(NlogN)
     * Time Complexity O(NlogN)
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        /**
         * Notice here hi is (nums.length + 1),
         * and feasible is true, hi = mid, res = mid,
         * return res finally.
         */
        int lo = 1;
        int hi = nums.length + 1;
        int res = 0;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (feasible(target, nums, mid)) {
                hi = mid;
                res = mid;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }

    /**
     * Maintain a fixed window.
     * @param target
     * @param nums
     * @param window the size of the window
     * @return
     */
    private boolean feasible(int target, int[] nums, int window) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= window) {
                sum -= nums[i - window];
            }
            sum += nums[i];
            if (sum >= target) {
                return true;
            }
        }
        return false;
    }
}
