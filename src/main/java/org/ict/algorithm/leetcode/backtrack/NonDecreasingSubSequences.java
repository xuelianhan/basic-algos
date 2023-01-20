package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array nums,
 * return all the different possible non-decreasing subsequences of the given array with at least two elements.
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,6,7,7]
 * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * Example 2:
 *
 * Input: nums = [4,4,3,2,1]
 * Output: [[4,4]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 * @author sniper
 * @date 20 Jan, 2023
 * LC491, Medium
 */
public class NonDecreasingSubSequences {

    public static void main(String[] args) {
        int[] nums = {4,4,3,2,1};
        NonDecreasingSubSequences instance = new NonDecreasingSubSequences();
        List<List<Integer>> result = instance.findSubsequences(nums);
        System.out.println(result);
    }

    public List<List<Integer>> findSubsequencesV2(int[] nums) {
        return null;
    }

    public List<List<Integer>> findSubsequencesV1(int[] nums) {
        return null;
    }

    /**
     * Similar as {@link SubsetsII}
     * Time Cost 14ms
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        /**
         * Using HashSet to remove duplicated items.
         */
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> track = new ArrayList<>();
        backtrack(nums, track, result, 0);
        return new ArrayList<>(result);
    }

    public void backtrack(int[] nums, List<Integer> track, Set<List<Integer>> result, int k) {
        /**
         * Make a deep copy of track, don't add the track directly
         * At least two elements in track, so using track.size() > 1 or track.size() >= 2
         */
        if (track.size() > 1) {
            result.add(new ArrayList<>(track));
        }
        for (int i = k; i < nums.length; i++) {
            if (!track.isEmpty() && track.get(track.size() - 1) > nums[i]) {
                continue;
            }
            track.add(nums[i]);
            /**
             * Notice here, pass i + 1 into k, not k+1
             */
            backtrack(nums, track, result, i + 1);
            track.remove(track.size() - 1);
        }
    }
}
