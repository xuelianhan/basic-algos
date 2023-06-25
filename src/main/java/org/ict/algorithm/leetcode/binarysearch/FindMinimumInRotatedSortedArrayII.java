package org.ict.algorithm.leetcode.binarysearch;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates,
 * return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums is sorted and rotated between 1 and n times.
 *
 *
 * Follow up:
 * This problem is similar to Find Minimum in Rotated Sorted Array,
 * but nums may contain duplicates.
 * Would this affect the runtime complexity?
 * How and why?
 *
 * @author sniper
 * @date 23 Jun 2023
 * LC154, Hard
 */
public class FindMinimumInRotatedSortedArrayII {

    /**
     * Divide-And-Conquer
     * @param nums
     * @return
     */
    public int findMinV1(int[] nums) {
        return help(nums, 0, nums.length - 1);
    }

    private int help(int[] nums, int l, int r) {
        if (l == r) {
            return nums[r];
        }
        /**
         * e.g. nums = [3, 1, 3]
         */
        if (nums[l] < nums[r]) {
            return nums[l];
        }
        int mid = (l + r) / 2;
        int leftV = help(nums, l, mid);
        int rightV = help(nums, mid + 1, r);
        return Math.min(leftV, rightV);
    }


    /**
     * Understanding the following solution
     * -------------------------------------------------------
     * When there are a lot of duplicate numbers in the array,
     * it breaks the dichotomous lookup mechanism and will not achieve O(lgn) time complexity,
     * and will go back to the simple and brutal O(n),
     * such as these two cases: [2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 2],
     * and [2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2].
     * When the first number and the last number and the middle number are all equal,
     * the dichotomous lookup method breaks down because it cannot tell whether to go to the left or the right half.
     * In this case, shifting the right pointer one place to the left (or shifting the left pointer one place to the right),
     * and omitting an identical number has no effect on the result,
     * because only one identical number is removed,
     * and then the dichotomy continues for the remaining part,
     * which in the worst case,
     * such as when all elements of the array are identical,
     * the time complexity rises to O(n).
     * ------------------------------------------
     * class Solution:
     *     def findMin(self, nums: List[int]) -> int:
     *         l, r = 0, len(nums) - 1
     *         while l < r:
     *             mid = (l + r) // 2
     *             if nums[mid] == nums[r]:
     *                 r -= 1
     *             elif nums[mid] < nums[r]:
     *                 r = mid
     *             else:
     *                 l = mid + 1
     *         return nums[l]
     * -------------------------------------------
     * class Solution {
     * public:
     *     int findMin(vector<int>& nums) {
     *         int l = 0;
     *         int r = (int)nums.size() - 1;
     *         while (l < r) {
     *             int mid = l + (r - l) / 2;
     *             if (nums[mid] == nums[r]) {
     *                 r--;
     *             } else if (nums[mid] < nums[r]) {
     *                 r = mid;
     *             } else {
     *                 l = mid + 1;
     *             }
     *         }
     *         return nums[l];
     *     }
     * };
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == nums[r]) {
                r--;
            } else if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
