package org.ict.algorithm.leetcode.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums with n objects colored red, white, or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 *
 *
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 *
 * @author sniper
 * @date 03 Jan, 2023
 * LC75
 */
public class SortColors {

    public static void main(String[] args) {
        SortColors instance = new SortColors();
        int[] nums = {2, 0, 1};
        instance.sortColorsV2(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * This is a dutch partitioning problem.
     * <a href="https://en.wikipedia.org/wiki/Dutch_national_flag_problem">Dutch_national_flag_problem</a>
     * The following pseudocode for three-way partitioning which assumes zero-based array indexing was proposed by Dijkstra himself.
     * [2] It uses three indices i, j and k, maintaining the invariant that i ≤ j ≤ k.
     *
     * Entries from 0 up to (but not including) i are values less than mid,
     * entries from i up to (but not including) j are values equal to mid,
     * entries from j up to (and including) k are values not yet sorted, and
     * entries from k + 1 to the end of the array are values greater than mid.
     * procedure three-way-partition(A : array of values, mid : value):
     *     i ← 0
     *     j ← 0
     *     k ← size of A - 1
     *
     *     while j <= k:
     *         if A[j] < mid:
     *             swap A[i] and A[j]
     *             i ← i + 1
     *             j ← j + 1
     *         else if A[j] > mid:
     *             swap A[j] and A[k]
     *             k ← k - 1
     *         else:
     *             j ← j + 1
     * We are classifying the array into four groups: red, white, unclassified, and blue.
     * Initially we group all elements into unclassified.
     * We iterate from the beginning as long as the white pointer is less than the blue pointer.
     *
     * If the white pointer is red (nums[white] == 0),
     * we swap with the red pointer and move both white and red pointer forward.
     * If the pointer is white (nums[white] == 1),
     * the element is already in correct place,
     * so we don't have to swap,
     * just move the white pointer forward.
     * If the white pointer is blue, we swap with the latest unclassified element.
     *
     * @author girikuncoro
     * @see <a href="https://leetcode.com/problems/sort-colors/solutions/26481/python-o-n-1-pass-in-place-solution-with-explanation"></a>
     * @param nums
     */
    public void sortColorsV2(int[] nums) {
        int count0 = 0;//red
        int count1 = 0;//white
        int count2 = nums.length - 1;//blue

        while (count1 <= count2) {
            if (nums[count1] == 0) {
                swap(nums, count0++, count1++);
            } else if (nums[count1] == 1) {
                count1++;
            } else {
                //case nums[count1] == 2
                swap(nums, count1, count2--);
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * Time Cost 1ms
     * Two-pass Solution
     * @param nums
     */
    public void sortColorsV1(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        for (int num : nums) {
            if (num == 0) {
                count0++;
            }
            if (num == 1) {
                count1++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < count0) {
                nums[i] = 0;
            } else if (i < count0 + count1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    /**
     * Time Cost 2ms
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int i = 0;
        if (freq.get(0) != null && freq.get(0) > 0) {
            Arrays.fill(nums, i, freq.get(0), 0);
            i += freq.get(0);
        }
        if (freq.get(1) != null && freq.get(1) > 0) {
            Arrays.fill(nums, i, i + freq.get(1), 1);
            i += freq.get(1);
        }
        if (freq.get(2) != null && freq.get(2) > 0) {
            Arrays.fill(nums, i, i + freq.get(2), 2);
        }
    }
}
