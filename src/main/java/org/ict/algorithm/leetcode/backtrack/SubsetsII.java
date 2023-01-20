package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 * @author sniper
 * @date 01 Nov, 2022
 * LC90, Medium
 * Similar Question
 * {@link NonDecreasingSubSequences}
 */
public class SubsetsII {


    /**
     * @see <a href="https://leetcode.com/zpcore/">apcore</a>
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDupV1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        /**
         * sort the nums to prepare for subsequent removing duplicates
         */
        Arrays.sort(nums);
        backtrackV1(nums, track, result, 0, false);
        return result;
    }

    /**
     *
     * Each recursion level focuses on one element, we need to decide choose or not choose this element.
     * Every level split into 2 branches.
     * @param nums
     * @param track
     * @param result
     * @param k
     * @param choosePre
     */
    public void backtrackV1(int[] nums, LinkedList<Integer> track, List<List<Integer>> result, int k, boolean choosePre) {
        if (k == nums.length) {
            result.add(new ArrayList<>(track));
            return;
        }
        backtrackV1(nums, track, result, k + 1, false);
        /**
         * removing duplicates, this step requires the input array has sorted already.
         */
        if (k >= 1  && nums[k] == nums[k-1] && !choosePre) {
            return;
        }
        track.add(nums[k]);
        backtrackV1(nums, track, result, k + 1, true);
        track.removeLast();
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        /**
         * sort the nums to prepare for subsequent removing duplicates
         */
        Arrays.sort(nums);
        backtrack(nums, track, result, 0);
        return result;
    }

    /**
     * Each recursion level focuses on all the following elements.
     * Every level split into N branches.
     * We scan through all the following elements and decide whether to choose or not choose that element.
     *
     * @param nums
     * @param track
     * @param result
     * @param k
     */
    public void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> result, int k) {
        result.add(new ArrayList<>(track));
        for (int i = k; i < nums.length; i++) {
            /**
             * removing duplicates, this step requires the input array has sorted already.
             */
            if (i > k && nums[i] == nums[i-1]) {
                continue;
            }
            track.add(nums[i]);
            /**
             * Notice here, pass i + 1 into k, not k+1
             */
            backtrack(nums, track, result, i+1);
            track.removeLast();
        }
    }


}
