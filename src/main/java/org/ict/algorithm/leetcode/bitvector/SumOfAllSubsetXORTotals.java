package org.ict.algorithm.leetcode.bitvector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.
 *
 * For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
 * Given an array nums, return the sum of all XOR totals for every subset of nums.
 *
 * Note: Subsets with the same elements should be counted multiple times.
 *
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3]
 * Output: 6
 * Explanation: The 4 subsets of [1,3] are:
 * - The empty subset has an XOR total of 0.
 * - [1] has an XOR total of 1.
 * - [3] has an XOR total of 3.
 * - [1,3] has an XOR total of 1 XOR 3 = 2.
 * 0 + 1 + 3 + 2 = 6
 * Example 2:
 *
 * Input: nums = [5,1,6]
 * Output: 28
 * Explanation: The 8 subsets of [5,1,6] are:
 * - The empty subset has an XOR total of 0.
 * - [5] has an XOR total of 5.
 * - [1] has an XOR total of 1.
 * - [6] has an XOR total of 6.
 * - [5,1] has an XOR total of 5 XOR 1 = 4.
 * - [5,6] has an XOR total of 5 XOR 6 = 3.
 * - [1,6] has an XOR total of 1 XOR 6 = 7.
 * - [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
 * 0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
 * Example 3:
 *
 * Input: nums = [3,4,5,6,7,8]
 * Output: 480
 * Explanation: The sum of all XOR totals for every subset is 480.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 12
 * 1 <= nums[i] <= 20
 * @author sniper
 * @date 03 Dec, 2022
 * LC1863
 */
public class SumOfAllSubsetXORTotals {

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 6, 7, 8};
        SumOfAllSubsetXORTotals instance = new SumOfAllSubsetXORTotals();
        int result = instance.subsetXORSumV1(nums);
        System.out.println(result);
    }

    public int subsetXORSumV3(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return 0;
    }

    public int subsetXORSumV2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return 0;
    }

    /**
     * Time Cost 12ms
     * @param nums
     * @return
     */
    public int subsetXORSumV1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        /**
         * 1.backtrack to get subsets and sum xor result in backtrack directly.
         */
        int[] result = new int[1];
        LinkedList<Integer> track = new LinkedList<>();
        backtrackXOR(nums, track, result, 0);
        return result[0];
    }

    public void backtrackXOR(int[] nums, LinkedList<Integer> track, int[] result, int k) {
        if (track.size() == 1) {
            result[0] += track.get(0);
        } else if (track.size() > 1) {
            int start = track.get(0);
            for (int i = 1; i < track.size(); i++) {
                start = start ^ track.get(i);
            }
            result[0] += start;
        }
        for (int i = k; i < nums.length; i++) {
            track.add(nums[i]);
            backtrackXOR(nums, track, result, i + 1);
            track.removeLast();
        }
    }

    /**
     * Time Cost 52ms
     * @param nums
     * @return
     */
    public int subsetXORSum(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        /**
         * 1.backtrack to get subsets
         */
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track, result, 0);
        /**
         * 2.xor subsets
         */
        return xorList(result);
    }

    public int xorList(List<List<Integer>> result) {
        int res = 0;
        for (List<Integer> list : result) {
            if (null == list || list.size() == 0)  {
                res += 0;
                continue;
            }
            if (list.size() == 1) {
                res += list.get(0);
                continue;
            }
            int start = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                start = start ^ list.get(i);
            }
            res += start;
        }
        return res;
    }

    public void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> result, int k) {
        result.add(new ArrayList<>(track));
        for (int i = k; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, track, result, i + 1);
            track.removeLast();
        }
    }
}
