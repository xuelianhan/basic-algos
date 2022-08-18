package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it.
 * That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 *
 * Return the answer in an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Explanation:
 * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
 * For nums[1]=1 does not exist any smaller number than it.
 * For nums[2]=2 there exist one smaller number than it (1).
 * For nums[3]=2 there exist one smaller number than it (1).
 * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
 * Example 2:
 *
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 * Example 3:
 *
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 * @author sniper
 * @date 18 Aug, 2022
 * LC1365
 */
public class NumbersSmallerThanCurrentNumber {

    public static void main(String[] args) {
        int[] nums = {8, 1, 2, 2, 3, 0, 0};
        int[] result = smallerNumbersThanCurrentV2(nums);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Solution provided by Tiago Silva,
     * simplify the last loop in smallerNumbersThanCurrentV2 method
     * and remove that (if nums[i] == 0) by simply treating the zero properly on your first loop.
     *
     *  e.g. nums = [8, 1, 2, 2, 3, 0, 0]:
     *
     *  [0, 2, 1, 2, 1, 0, 0, 0, 0, 1, 0, ..., 0]
     *  [0, 2, 3, 5, 6, 6, 6, 6, 6, 7, 7, ..., 7]
     *  [6, 2, 3, 3, 5, 0, 0]
     *
     *  when i >= 1, every bucket[i] means the count of number less than i
     *  bucket[0] == 0, means no numbers < 0, because 0 is the minimum
     *  bucket[1] == 2, means only 2 number < 1
     *  bucket[2] == 3, means only 3 numbers < 2
     *  bucket[3] == 5, means only 5 numbers < 3
     *  bucket[4] == 6, means only 6 numbers < 4
     *  ...
     *  bucket[9] == 7, means only 7 numbers < 8
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrentV3(int[] nums) {
        int[] bucket = new int[102];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]+1]++;
        }
        //System.out.println(Arrays.toString(bucket));
        for (int i = 1; i < 102; i++) {
            bucket[i] += bucket[i-1];
        }
        //System.out.println(Arrays.toString(bucket));
        for (int i = 0; i < nums.length; i++) {
            nums[i] = bucket[nums[i]];
        }
        //System.out.println(Arrays.toString(nums));
        return nums;
    }


    /**
     * Solution provided by equ1n0x
     *
     * e.g. nums = [8, 1, 2, 2, 3, 0, 0]:
     *
     *
     * [2, 1, 2, 1, 0, 0, 0, 0, 1, 0, ..., 0]
     * [2, 3, 5, 6, 6, 6, 6, 6, 7, 7, ..., 7]
     * [8, 1, 2, 2, 3, 0, 0]
     * [6, 2, 3, 3, 5, 0, 0]
     *
     * when i >= 1, every count[i] means the count of number less than or equal to i
     * count[0] == 2, means only 2 numbers <= 0, because zero is the minimum, so it means having 2 zero number
     * count[1] == 3, means only 3 number <= 1, relatively only 2 number < 1
     * count[2] == 5, means only 5 numbers <= 2, relatively only 3 number < 2
     * count[3] == 6, means only 6 numbers <= 3, relatively only 5 number < 3
     * ...
     * count[8] == 7, means only 7 numbers <= 8, relatively only 6 number < 8
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrentV2(int[] nums) {
        /**
         * Due to the condition (0 <= nums[i] <= 100)
         */
        int[] count = new int[101];
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        //System.out.println(Arrays.toString(count));

        for (int i = 1 ; i <= 100; i++) {
            count[i] += count[i-1];
        }
        //System.out.println(Arrays.toString(count));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                res[i] = 0;
            } else {
                res[i] = count[nums[i] - 1];
            }
        }
        //System.out.println(Arrays.toString(nums));
        return res;
    }

    /**
     * Brute-Force solution
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if ( j != i && nums[j] < nums[i]) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }
}
