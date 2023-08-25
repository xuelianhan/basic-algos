package org.ict.algorithm.leetcode.binarysearch;

/**
 * 
 * Given a sorted array and a target value, 
 * return the index if the target is found.
 *
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * 
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 * 
 * Example 2:
 * Input: [1,3,5,6], 2
 * Output: 1
 * 
 * Example 3:
 * Input: [1,3,5,6], 7
 * Output: 4
 * 
 * Example 4:
 * Input: [1,3,5,6], 0
 * Output: 0
 * 
 * LC35, Easy, frequency:9
 * Must be remembered and write in 30 seconds.
 *
 */
public class SearchInsertPosition {


    /**
     * e.g.1
     * Input:[1, 5], 0
     * low:0, high:1, mid:0
     * nums[mid] > 0, high = mid-1 --> high=-1, while low=0
     *          1-------5
     * -1-------0-------1
     * high----low
     * high less than low, while-loop ended, the insert position is at index 0, which low point at.
     *
     * e.g.2
     * Input:[1, 5], 4
     * low:0, high:1, mid:0
     * nums[mid] < 4, low = mid+1 --> low=1, while high=1
     *          1-------5
     * -1-------0-------1
     *               low,high
     * low:1, high:1, mid:1
     * nums[mid] > 4, high=mid-1 --> high=0, while low=1
     *          1-------5
     * -1-------0-------1
     *         high----low
     * high less than low, while-loop ended, the insert position is at index 1, which low point at.
     *
     *
     * e.g.3
     * Input:[1, 5], 6
     * low:0, high:1, mid:0
     * nums[mid] < 6, low=mid+1 --> low=1, while high=1
     *          1-------5
     * -1-------0-------1
     *               low,high
     * low:1, high:1, mid:1
     * nums[mid] < 5,low:mid+1 --> low=2, while high=1
     *          1-------5
     * -1-------0-------1-------2
     *                 high----low
     * high less than low, while-loop ended, the insert position is at index 2, which low point at.
     *
     * The following code is rank function in Algorithms book written by Robert Sedgwick.
     *
     * @param nums
     * @param target
     * @return
     */
	public static int searchInsertV1(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        /**
         * Notice Point:
         * 1.while condition is low <= high, not low < high.
         * 2.when array[mid] != target, low = mid + 1 or high = mid -1, not write as low = mid or high = mid.
         * 3.the rank(insert position) is low, due to while condition low<= high, this condition will let
         * low greater than high in the above three cases finally.
         */
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target)  {
                return mid;
            } else if(nums[mid] > target) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        /**
         * return (high+1) is ok too.
         */
        return low;
    }
}
