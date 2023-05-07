package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [[1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]]
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [[1,2,2],
 * [5]]
 *
 * Constraints:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 * @author sniper
 * @date 02 Nov, 2022
 * LC40, Medium, frequency=3
 */
public class CombinationSumII {

    /**
     * Time Cost 12ms
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2V1(int[] candidates, int target) {
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
        backtrackV1(result, track, candidates, target, 0, 0);
        return result;
    }

    public void backtrackV1(List<List<Integer>> result, LinkedList<Integer> track, int[] candidates, int target, int sum, int pos) {
        if (sum > target) {
            return;
        } else if (sum == target) {
            result.add(new ArrayList<>(track));
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            /**
             * Skip duplicates
             */
            if (i > pos && candidates[i] == candidates[i-1]) {
                continue;
            }
            track.add(candidates[i]);
            sum += candidates[i];
            /**
             * i + 1 because each number in candidates may only be used once in the combination.
             */
            backtrackV1(result, track, candidates, target, sum,  i + 1);
            track.removeLast();
            sum -= candidates[i];
        }
    }


    /**
     * Time Cost 5ms
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            /**
             * Skip duplicates
             */
            if (i > pos && candidates[i] == candidates[i-1]) {
                continue;
            }
            track.add(candidates[i]);
            /**
             *  i + 1 because each number in candidates may only be used once in the combination.
             */
            backtrack(result, track, candidates, target - candidates[i], i + 1);
            track.removeLast();
        }
    }
}
