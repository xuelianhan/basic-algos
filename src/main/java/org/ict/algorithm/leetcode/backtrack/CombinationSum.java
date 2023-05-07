package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * The test cases are generated such that the number of unique combinations
 * that sum up to target is less than 150 combinations for the given input.
 *
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 *
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 *
 * Constraints:
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * All elements of candidates are distinct.
 * 1 <= target <= 40
 * @author sniper
 * @date 02 Nov, 2022
 * LC39, Medium, frequency=8
 */
public class CombinationSum {

    /**
     * Time Cost 6ms
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSumV1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        /**
         * Corner case process.
         * e.g. candidates = [2], target = 1
         */
        Arrays.sort(candidates);
        if (candidates[0] > target) {
            return result;
        }
        backtrackV1(result, track, candidates, target, 0, candidates.length - 1);
        return result;
    }

    public void backtrackV1(List<List<Integer>> result, LinkedList<Integer> track, int[] candidates, int target, int sum, int pos) {
        if (sum > target) {
            return;
        } else if (sum == target) {
            result.add(new ArrayList<>(track));
            return;
        }
        for (int i = pos; i >= 0; i--) {
            track.add(candidates[i]);
            sum += candidates[i];
            /**
             * not i + 1 because we can reuse same elements
             */
            backtrackV1(result, track, candidates, target, sum,  i);
            track.removeLast();
            sum -= candidates[i];
        }
    }

    /**
     * Time Cost 9ms
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        /**
         * Corner case process.
         * e.g. candidates = [2], target = 1
         */
        Arrays.sort(candidates);
        if (candidates[0] > target) {
            return result;
        }
        backtrack(result, track, candidates, target, 0);
        return result;
    }

    public void backtrack(List<List<Integer>> result, LinkedList<Integer> track, int[] candidates, int target, int pos) {
        if (target < 0) {
            /**
             * target may less than zero, we should return to break recursive invoking stack.
             */
            return;
        } else if (0 == target) {
            result.add(new ArrayList<>(track));
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            track.add(candidates[i]);
            /**
             * not i + 1 because we can reuse same elements
             */
            backtrack(result, track, candidates, target - candidates[i], i);
            track.removeLast();
        }
    }

}
