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
public class PermutationI {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        permuteV1(nums);
    }


    public static List<List<Integer>> permuteV2(int[] nums) {
        /**
         * Use LinkedList instead of ArrayList
         */
        List<List<Integer>> result = new LinkedList<>();
        dfs(nums, new LinkedList<>(), new boolean[nums.length],  result);
        return result;
    }


    public static void dfs(int[] nums, LinkedList<Integer> path, boolean[] visited,  List<List<Integer>> result) {
        if (path.size() == nums.length) {
            //Make a deep copy of path here, otherwise we'd be append the same path over and over
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /**
             *  skip visited num
             */
            if (visited[i]) {
                continue;
            }
            /**
             * add num on the path and mark num as visited.
             */
            path.add(nums[i]);
            visited[i] = true;
            dfs(nums, path, visited, result);
            /**
             * remove num off the path and mark num as unvisited.
             */
            path.removeLast();
            visited[i] = false;
        }
    }


    public static List<List<Integer>> permuteV1(int[] nums) {
        /**
         * Use LinkedList instead of ArrayList in order to use the removeLast method.
         */
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrackV1(nums, track, result);
        return result;
    }

    /**
     *             (1, 2, 3)
     *             --------
     *            /   |    \
     *          1/    |2    \3
     *         ---------------
     *      (2,3)   (1,3)   (1,2)
     *        -----------------
     *      2/ \3    1/\3    1/\2
     *      /   \    /  \    /  \
     *     3    2   3   1   2    1
     *
     * e.g.
     * nums = [1, 2, 3]
     * i:0, track:1
     *         |
     *       backtrack(nums, track, result), skip 1
     *       track:1,2
     *         |
     *       backtrack(nums, track, result), skip 1, 2
     *       track:1,2,3
     *         |
     *       backtrack(nums, track, result), track size == 3, result:1,2,3, return, track remove last, track:1,2
     *
     *
     * @param nums
     * @param track
     * @param result
     */
    public static void backtrackV1(int[] nums, LinkedList<Integer> track, List<List<Integer>> result) {
        if (track.size() == nums.length) {
            /**
             * Use new LinkedList to wrapper track instead of adding track into result list directly
             */
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                /**
                 * Skip the number which has been accessed.
                 */
                continue;
            }
            track.add(nums[i]);
            backtrackV1(nums, track, result);
            track.removeLast();
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        backtrack(nums, track, result);
        return result;
    }

    public static void backtrack(int[] nums, List<Integer> track, List<List<Integer>> result) {
        if (track.size() == nums.length) {
            /**
             * Wrapper track with ArrayList is very important.
             * If you don't do that, you will get an empty array.
             */
            result.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track, result);
            /**
             * remove the last one of the track.
             */
            track.remove(track.size() - 1);
        }
    }

}
