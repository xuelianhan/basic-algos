package org.ict.algorithm.leetcode.binarysearch;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Constraints:
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -10^4 <= target <= 10^4
 * @author sniper
 * @date 23 Mar, 2023
 * LC33, Medium
 */
public class SearchInRotatedSortedArray {

    public int searchV4(int[] nums, int target) {
        int res = 0;
        //todo
        return res;
    }

    public int searchV3(int[] nums, int target) {
        int res = 0;
        //todo
        return res;
    }

    public int searchV2(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < nums[hi]) {
                /**
                 * e.g. nums:[5,6,1,2,3,4], target:3, mid:2
                 */
                if (nums[mid] < target && nums[hi] >= target) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                if (nums[lo] <= target && nums[mid] > target) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }
        return -1;
    }

    public int searchV1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        /**
         * e.g. nums:[1], target:1
         */
        if (n == 1) {
            return (nums[0] == target ? 0 : -1);
        }
        /**
         * e.g. nums:[1,3], target:3
         */
        if (nums[0] < nums[n - 1]) {
            return binarySearch(nums, target, 0, n - 1);
        }
        int pivot = findPivot(nums);
        if (pivot == -1) {
            return -1;
        }
        if (nums[pivot] == target) {
            return pivot;
        }
        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        } else {
            return binarySearch(nums, target, pivot + 1, n - 1);
        }
    }

    /**
     * @author
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        if (n == 1) {
            return (nums[0] == target ? 0 : -1);
        }
        int pivot = findPivot(nums);
        if (pivot == -1) {
            return binarySearch(nums, target, 0, n - 1);
        }
        if (nums[pivot] == target) {
            return pivot;
        }
        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        } else {
            return binarySearch(nums, target, pivot + 1, n - 1);
        }
    }

    /**
     * Case-1:456123
     *      A
     *      *
     *   *
     * *          *
     *          *
     *        *
     *        B
     * Assume the vertical axis represents the value of the nums array
     * 1.A might be mid, B might be mid + 1;
     * 2.A might be mid-1, B might be mid;
     * -----------------------------------
     * Case-2: 123456, there is no pivot
     * @param nums
     * @return
     */
    private int findPivot(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            /**
             * Compare nums[mid] with its precursor and successor.
             */
            if (mid < hi && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (mid > lo && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }
            if (nums[mid] <= nums[lo]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }
    private int binarySearch(int[] nums, int target, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
