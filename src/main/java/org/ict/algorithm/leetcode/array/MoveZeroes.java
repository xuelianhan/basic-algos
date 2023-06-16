package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * Given an integer array nums,
 * move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 *
 * Follow up: Could you minimize the total number of operations done?
 * @author sniper
 * @date 26 Sep, 2022
 * LC283, Easy, frequency=22
 */
public class MoveZeroes {

    public static void main(String[] args) {
        //expected: [4,2,4,3,5,1,0,0,0,0]
        //int[] nums = {4,2,4,0,0,3,0,5,1,0};
        int[] nums = {1, 0};
        //int[] nums = {0,1,0,3,12};
        //int[] nums = {0};
        //int[] nums = {45192,0,-659,-52359,-99225,-75991,0,-15155,27382,59818,0,-30645,-17025,81209,887,64648};
        //int[] nums = {0,0};
        MoveZeroes instance = new MoveZeroes();
        instance.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Shift non-zero items to left and fill the remained items with zero
     * @param nums
     */
    public void moveZeroesV3(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    /**
     * int[] nums = {4,2,4,0,0,3,0,5,1,0}
     *
     * @param nums
     */
    public void moveZeroesV2(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int i = 0;
        int j = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }

    /**
     * int[] nums = {4,2,4,0,0,3,0,5,1,0}
     *
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        /**
         * i points at the next zero insert position.
         * j points at the next non-zero position.
         */
        int i = 0;
        int j = 1;//start from 0 is ok too
        for (; j < nums.length && i < nums.length;j++) {
            if (nums[j] != 0 && nums[i] == 0) {
                nums[i] = nums[j];
                nums[j] = 0;
            }
            if (nums[i] != 0) {
                i++;
            }
        }
    }

}
