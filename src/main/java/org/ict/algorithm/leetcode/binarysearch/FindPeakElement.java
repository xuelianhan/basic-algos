package org.ict.algorithm.leetcode.binarysearch;


/**
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given a 0-indexed integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 * In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums[i] != nums[i + 1] for all valid i.
 * @author sniper
 * @date 16 Jan, 2023
 * LC162, Medium
 */
public class FindPeakElement {

    public static void main(String[] args) {
        FindPeakElement instance = new FindPeakElement();
        int[] nums = {1, 2};
        int result = instance.findPeakElement(nums);
        System.out.println(result);
    }

    /**
     * Understanding the following solution.
     * Given an array, we need to find the peak element.
     * As, the sub-portions of the array are increasing/decreasing ( only then we would be able to find peak ),
     * there are sub-portions of array which are sorted, so we could use binary search to get this problem done.
     * But exactly how ?
     *
     * This is an interesting part.
     *
     * For a mid element, there could be three possible cases :
     * Case 1 : mid lies on the right of our result peak ( Observation : Our peak element search space is leftside )
     * Case 2 : mid is equal to the peak element ( Observation : mid element is greater than its neighbors )
     * Case 3 : mid lies on the left. ( Observation : Our peak element search space is rightside )
     *
     * Some base cases :
     *
     * The array could be strictly increasing or strictly decreasing and as we have to return any of the possible peaks,
     * so we could add a condition to check whether the 1st element/last element could be the peak.
     * This point is being also supported by the fact that,
     * we are looking for mid-1/ mid+1, and these indices compromised for 0th index / n-1 th index respectively.
     * @author Naman
     * @see <a href="https://leetcode.com/problems/find-peak-element/solutions/1290642/intuition-behind-conditions-complete-explanation-diagram-binary-search"></a>
     * @param nums
     * @return
     */
    public int findPeakElementV4(int[] nums) {
        /**
         * Single element
         */
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        /**
         * Check whether the first/last element is the peek or not.
         */
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[ n - 2]) {
            return n - 1;
        }

        /**
         * Check the remained in the array
         * Notice here start initializes as 1, end as n - 2, if we still use
         * start = 0, end = n - 1, then nums[mid - 1] will trigger index out of bound exception.
         */
        int start = 1;
        int end = n - 2;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            /**
             * e.g.nums = [1], nums = [2, 1]
             * mid - 1 will lead to index out of bound, so
             * we must process corner case like nums.length == 1
             */
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] < nums[mid - 1]) {
                end = mid - 1;
            } else if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Similar as findPeakElementV2 but return low at last,
     * because low will meet high at the end.
     * @param nums
     * @return
     */
    public int findPeakElementV3(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


    /**
     * Understanding the following solution.
     * Why low < high, instead of low <= high?
     *
     * e.g. nums = [1,3,2]
     * low:0, high:2, low < high, mid = 1
     * nums[mid] = nums[1] = 3, 3 > nums[2] = 2, high = mid = 1
     * low:0, high:1, low < high, mid = 0
     * nums[mid] = nums[0] = 1, 1 < nums[1] = 3, low = mid + 1 = 1
     * low:1, high:1, low == high, while-loop-end, return high:1
     *
     * if using low <= high:
     * low:1, high:1, low == high, mid = 1
     * nums[mid] = nums[1] = 3, 3 > nums[2] = 2, high = mid = 1
     * low:1, high:1, low == high, mid = 1
     * nums[mid] = nums[1] = 3, 3 > nums[2] = 2, high = mid = 1
     * ......
     * You may find that the above process has been a dead-loop,
     * so we cannot use low <= high here if we initialize high with nums.length - 1.
     *
     * Time Complexity O(logN)
     * Space Complexity O(1)
     * @param nums
     * @return
     */
    public int findPeakElementV2(int[] nums) {
       int low = 0;
       int high = nums.length - 1;
       while (low < high) {
           int mid = low + (high - low) / 2;
           if (nums[mid] < nums[mid + 1]) {
               low = mid + 1;
           } else {
               high = mid;
           }
       }
       return high;
    }

    /**
     *
     * Time Complexity O(N)
     * Space Complexity O(1)
     * @param nums
     * @return
     */
    public int findPeakElementV1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                return i - 1;
            }
        }
        return nums.length - 1;
    }

    /**
     * Not recommend.
     * Time Complexity O(N)
     * Space Complexity O(N)
     * e.g. nums = [1, 2], expected 1
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int n = nums.length;
        int[] copy = new int[n + 2];
        for (int i = 1; i < copy.length - 1; i++) {
            copy[i] = nums[i - 1];
        }
        copy[0] = Integer.MIN_VALUE;
        copy[n + 1] = Integer.MIN_VALUE;
        for (int i = 1; i < copy.length -1; i++) {
            if (copy[i] > copy[i - 1] && copy[i] > copy[i + 1]) {
                return i - 1;
            }
        }
        return -1;
    }

}
