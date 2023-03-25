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

    /**
     * @see <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/solutions/14425/concise-o-log-n-binary-search-solution"></a>
     * @author 1337beef
     * @param nums
     * @param target
     * @return
     */
    public int searchV7(int[] nums, int target) {
        int res = 0;
        //todo
        return res;
    }

    public int searchV6(int[] nums, int target) {
        int res = 0;
        //todo
        return res;
    }

    public int searchV5(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int split;
            if ((nums[mid] < nums[0]) == (target < nums[0])) {
                split = nums[mid];
            } else {
                split = (target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE);
            }
            if (split < target) {
                lo = mid + 1;
            } else if (split > target) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Explanation:
     * Let's say nums looks like this: [12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
     * Because it's not fully sorted, we can't do normal binary search. But here comes the trick:
     * If target is let's say 14, then we adjust nums to this, where "inf" means infinity:
     * [12, 13, 14, 15, 16, 17, 18, 19, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf]
     * If target is let's say 7, then we adjust nums to this:
     * [-inf, -inf, -inf, -inf, -inf, -inf, -inf, -inf, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
     * And then we can simply do ordinary binary search.
     * Of course, we don't actually adjust the whole array but instead adjust only on the fly only the elements we look at.
     * And the adjustment is done by comparing both the target and the actual element against nums[0].
     * If nums[mid] and target are "on the same side" of nums[0],
     * we just take nums[mid].
     * Otherwise, we use -infinity or +infinity as needed.
     * @see <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/solutions/14435/clever-idea-making-it-simple"></a>
     * @author StefanPochmann
     * @param nums
     * @param target
     * @return
     */
    public int searchV4(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int num;
            if ((nums[mid] < nums[0]) == (target < nums[0])) {
                num = nums[mid];
            } else {
                num = (target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE);
            }
            if (num < target) {
                lo = mid + 1;
            } else if (num > target) {
                hi = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Compare nums[mid] with the left-most element in the array.
     * Similar with searchV2
     * @param nums
     * @param target
     * @return
     */
    public int searchV3(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < nums[lo]) {
                /**
                 * We can assure that range [mid, hi] is ascending.
                 * so we compare target with nums[mid] and nums[hi] at first.
                 * e.g. nums:[5,6,1,2,3,4], mid:2, nums[2]:1, target:3
                 */
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                /**
                 * We can assure that range [lo, mid,] is ascending.
                 * so we compare target with nums[lo] and nums[mid] at first.
                 * e.g. nums:[4,5,6,1,2,3], mid:2, nums[2]:6, target:5
                 */
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * Compare nums[mid] with the right-most element in the array.
     *
     * @param nums
     * @param target
     * @return
     */
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
                 * If the middle number is smaller than the rightmost number, the right half from mid is ordered,
                 * so we compare target with nums[mid] and nums[hi].
                 * e.g. nums:[5,6,1,2,3,4], mid:2, nums[2]:1, target:3, nums[mid] < target and nums[hi] >= target
                 * e.g. nums:[5,6,1,2,3,4], mid:2, nums[2]:1, target:6, nums[hi] < target
                 * e.g. nums:[6,1,2,3,4,5], mid:2, nums[2]:2, target:1, nums[mid] > target
                 */
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                /**
                 * If the middle number is larger than the rightmost number, the left half to mid is ordered,
                 * so we compare target with nums[lo] and nums[mid]
                 * e.g. nums:[4,5,6,1,2,3], mid:2, nums[2]:6, target:5, nums[lo] < target and nums[mid] > target
                 * e.g. nums:[4,5,6,1,2,3], mid:2, nums[2]:6, target:3, nums[lo] > target
                 * e.g. nums:[3,4,5,6,1,2], mid:2, nums[2]:5, target:6, nums[mid] < target
                 */
                if (nums[lo] <= target && target < nums[mid]) {
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
         *
         * If array has been sorted, do a normal binary search
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
