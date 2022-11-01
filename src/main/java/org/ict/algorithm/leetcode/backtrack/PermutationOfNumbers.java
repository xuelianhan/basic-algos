package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 * @author sniper
 * @date 2022/5/6
 * LC46
 */
public class PermutationOfNumbers {



    public List<List<Integer>> permuteV1(int[] numbers) {
        /**
         * Use LinkedList instead of ArrayList
         */
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrackV1(numbers, track, result);
        return result;
    }

    /**           (1, 2, 3)
     *             --------
     *            /   |    \
     *          1/    |2    \3
     *         ---------------
     *      (2,3)   (1,3)   (1,2)
     *        -----------------
     *      2/ \3    1/\3    1/\2
     *      /   \    /  \    /  \
     *     3    2   3   1   2    1
     * @param numbers
     * @param track
     * @param result
     */
    public void backtrackV1(int[] numbers, LinkedList<Integer> track, List<List<Integer>> result) {
        if (track.size() == numbers.length) {
            /**
             * Use new LinkedList to wrapper track instead of adding track into result list directly
             */
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (track.contains(numbers[i])) {
                /**
                 * Skip the number which has been accessed.
                 */
                continue;
            }
            track.add(numbers[i]);
            backtrackV1(numbers, track, result);
            track.removeLast();
        }
    }

    public List<List<Integer>> permute(int[] numbers) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        backtrack(numbers, track, result);
        return result;
    }

    public void backtrack(int[] numbers, List<Integer> track, List<List<Integer>> result) {
        if (track.size() == numbers.length) {
            /**
             * Wrapper track with ArrayList is very important.
             * If you don't do that, you will get an empty array.
             */
            result.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (track.contains(numbers[i])) {
                continue;
            }
            track.add(numbers[i]);
            backtrack(numbers, track, result);
            /**
             * remove the last one of the track.
             */
            track.remove(track.size() - 1);
        }
    }

}
