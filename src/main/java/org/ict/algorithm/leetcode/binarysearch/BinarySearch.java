package org.ict.algorithm.leetcode.binarysearch;

/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * -104 < nums[i], target < 10^4
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
 *
 * Binary Search: low <= high OR low < high ?
 * The short answer is it depends on how you define your search space, is it two-closed ([l, r]) or half-opened ([l, r))?
 * if it is closed, you should use l <= r, otherwise use l < r. I personally prefer the former.
 * I recommend this article for more details about
 * binary search
 * @see <a href="https://leetcode.com/discuss/general-discussion/786126/Python-Powerful-Ultimate-Binary-Search-Template.-Solved-many-problems"></a>
 * @see <a href="https://leetcode.com/explore/learn/card/binary-search/125/template-i/938/"></a>
 * @author sniper
 * @date 26 Nov, 2022
 * LC704
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 1;
        BinarySearch instance = new BinarySearch();
        instance.searchV1(nums, target);
    }

    /**
     * Binary Search in Real Numbers
     * When precision is not assure, using the fixed-times to replace.
     * @param nums
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public double searchV8(double[] nums, double lowerBound, double upperBound) {
        double lo = lowerBound;
        double hi = upperBound;
        for (int i = 0; i < 100; i++) {
            double mid = lo + (hi - lo) / 2.0;
            if (calc(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return lo;
    }

    /**
     * Binary Search in Real Numbers
     * @param nums
     * @param eps precision of result
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public double searchV7(double[] nums, double eps, double lowerBound, double upperBound) {
        double lo = lowerBound;
        double hi = upperBound;
        /**
         * Or writing like this:
         * while (hi - lo > eps) {
         *    //same as following
         * }
         */
        while (lo + eps < hi) {
            double mid = lo + (hi - lo) / 2.0;
            if (calc(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return lo;
    }

    private boolean calc(double mid) {
        //function to decide how to search
        return true;
    }

    /**
     * Find the maximum number that satisfy with <= x(x or precursor of x)
     * mid = (l + r)>>1, mid doesn't get value of r, it means mid round down.
     * mid = (l + r + 1)>>1, mid doesn't get value of l, it means mid round up.
     *
     * mid = low + (high - low + 1) / 2;
     *     = low + (high + 1)/2 - low/2
     *     = low / 2 + (high + 1) / 2
     *     = (low + high + 1) / 2
     *
     * @param nums
     * @param x
     * @return
     */
    public int searchV6(int[] nums, int x) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (nums[mid] <= x) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return nums[low] == x ? low : -1;
    }


    /**
     * Find the minimum number that satisfy with >= x(x or successor of x)
     * mid = (l + r)>>1, mid doesn't get value of r, it means mid round down.
     * mid = (l + r + 1)>>1, mid doesn't get value of l, it means mid round up.
     * @param nums
     * @param x
     * @return
     */
    public int searchV5(int[] nums, int x) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low] == x ? low : -1;
    }


    /**
     * Think about edge cases:
     * when 'left' and 'right' are close enough and search space become small(like size 3,2,1)
     *
     * @param nums
     * @param x
     * @return
     */
    public int searchV4(int[] nums, int x) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            //  Prevent (left + right) overflow
            int mid = low + (high - low) / 2;
            if (nums[mid] < x) {
                low = mid + 1;
            } else if(nums[mid] > x) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        // End Condition: high > low
        return -1;
    }

    /**
     * Think about edge cases:
     * when 'left' and 'right' are close enough and search space become small(like size 3,2,1)
     * @param nums
     * @param x
     * @return
     */
    public int searchV3(int[] nums, int x) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            //  Prevent (left + right) overflow
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) {
                return mid;
            } else if (nums[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // End Condition: high > low
        return -1;
    }

    public int searchV2(int[] nums, int x) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) {
                return mid;
            } else if (nums[mid] < x) {
                low = mid;
            } else {
                high = mid;
            }
        }

        if (nums[low] == x) {
            return low;
        }
        if (nums[high] == x) {
            return high;
        }
        return -1;
    }


    public int searchV1(int[] nums, int x) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) {
                return mid;
            } else if (nums[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        // End Condition: high == low
        if (low != nums.length && nums[low] == x) {
            return low;
        }
        return -1;
    }

}
