package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
 * All the numbers of nums are unique.
 *
 * @author sniper
 * @date 31 Oct, 2022
 * LC78
 */
public class Subsets {


    public List<List<Integer>> subsetsV1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        backtrackV1(nums, track, result, 0);
        return result;
    }

    public void backtrackV1(int[] nums, List<Integer> track, List<List<Integer>> result, int k) {
        result.add(new ArrayList<>(track));
        for (int i = k; i < nums.length; i++) {
            track.add(nums[i]);
            /**
             * Notice here, pass i + 1 into k, not k+1
             */
            backtrackV1(nums, track, result, i+1);
            track.remove(track.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track, result, 0);
        return result;
    }

    public void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> result, int k) {
        result.add(new ArrayList<>(track));
        for (int i = k; i < nums.length; i++) {
            track.add(nums[i]);
            /**
             * Notice here, pass i + 1 into k, not k+1
             */
            backtrack(nums, track, result, i+1);
            track.removeLast();
        }
    }
}
