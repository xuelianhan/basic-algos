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

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Subsets instance = new Subsets();
        instance.subsets(nums);
    }


    public List<List<Integer>> subsetsV1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        backtrackV1(nums, track, result, 0);
        return result;
    }

    public void backtrackV1(int[] nums, List<Integer> track, List<List<Integer>> result, int k) {
        /**
         * Make a deep copy of track, don't add the track directly
         */
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

    /**
     * backtrack(nums, track, result, 0)
     *   result:[[]], k:0, i:0, track:[1] -> backtrack(nums, track, result, 1)
     *       result:[[], [1]], k:1, i:1, track:[1, 2] -> backtrack(nums, track, result, 2)
     *           result:[[], [1], [1, 2]], k:2, i:2, track:[1,2,3] -> backtrack(nums, track, result, 3)
     *               result:[[], [1], [1,2], [1,2,3]], k:3, i:3, i == nums.length, end-for-loop
     *           k:2, i:2, track.removeLast, track:[1,2], end-for-loop
     *       k:1, i:1, track.removeLast, track:[1], end-for-loop
     *       k:1, i:2, track:[1, 3] -> backtrack(nums, track, result, 3)
     *           result:[[], [1], [1, 2], [1, 2, 3], [1, 3]], k:3, end-for-loop
     *       k:1, i:2, track.removeLast, track:[1], end-for-loop
     *   k:0, i:0, track.removeLast, track:[]
     *   k:0, i:1, track:[2] -> backtrack(nums, track, result, 2)
     *      result:[[], [1], [1, 2], [1, 2, 3], [1, 3], [2]]
     *      k:2, i:2, track:[2, 3] -> backtrack(nums, track, result, 3)
     *        result:[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3]], end-for-loop
     *      k:2, i:2, track.removeLast, track:[2]
     *   k:0, i:1, track.removeLast, track:[]
     *   k:0, i:2, track:[3] -> backtrack(nums, track, result 3)
     *      result:[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
     *   k:0, i:2, track.removeLast, track:[]
     *-----------------------------------------------------------------------
     * result:[[]]
     * after track add, k:0, i:0, track:[1]
     * result:[[], [1]]
     * after track add, k:1, i:1, track:[1, 2]
     * result:[[], [1], [1, 2]]
     * after track add, k:2, i:2, track:[1, 2, 3]
     * result:[[], [1], [1, 2], [1, 2, 3]]
     * after track del, k:2, i:2, track:[1, 2]
     * after track del, k:1, i:1, track:[1]
     * after track add, k:1, i:2, track:[1, 3]
     * result:[[], [1], [1, 2], [1, 2, 3], [1, 3]]
     * after track del, k:1, i:2, track:[1]
     * after track del, k:0, i:0, track:[]
     * after track add, k:0, i:1, track:[2]
     * result:[[], [1], [1, 2], [1, 2, 3], [1, 3], [2]]
     * after track add, k:2, i:2, track:[2, 3]
     * result:[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3]]
     * after track del, k:2, i:2, track:[2]
     * after track del, k:0, i:1, track:[]
     * after track add, k:0, i:2, track:[3]
     * result:[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
     * after track del, k:0, i:2, track:[]
     *
     *
     * @param nums
     * @param track
     * @param result
     * @param k
     */
    public void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> result, int k) {
        /**
         * Make a deep copy of track, don't add the track directly
         */
        result.add(new ArrayList<>(track));
        //System.out.println("result:" + result);
        for (int i = k; i < nums.length; i++) {
            track.add(nums[i]);
            System.out.println("after track add, k:" + k + ", i:" + i + ", track:" + track);
            /**
             * Notice here, pass i + 1 into k, not k+1
             */
            backtrack(nums, track, result, i+1);
            track.removeLast();
            //System.out.println("after track del, k:" + k + ", i:" + i + ", track:" + track);
        }
    }
}
