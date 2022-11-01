package org.ict.algorithm.leetcode.backtrack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * @author sniper
 * @date 01 Nov, 2022
 * LC47
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        /**
         * sort the nums to prepare for subsequent skipping duplicates
         */
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfs(nums, track, visited, result);
        return result;
    }

    /**
     * Cost 2ms
     * 1.i > 0 && nums[i] == nums[i-1] && !visited[i-1]
     * 2.i > 0 && nums[i] == nums[i-1] && visited[i-1]
     * Why using 1 is faster than 2?
     * What's the difference of 1 and 2?
     * 
     *
     * @param nums
     * @param path
     * @param visited
     * @param result
     */
    public void dfsV1(int[] nums, LinkedList<Integer> path, boolean[] visited,  List<List<Integer>> result) {
        if (path.size() == nums.length) {
            //Make a deep copy of path here, otherwise we'd be append the same path over and over
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /**
             *  skip visited num
             *  skip duplicated items, this step requires the input array has sorted already.
             *  i > 0 && nums[i] == nums[i-1] && !visited[i-1]
             *
             */
            if (visited[i] || i > 0 && nums[i] == nums[i-1] && !visited[i-1]) {
                continue;
            }
            /**
             * add num on the path and mark num as visited.
             */
            path.add(nums[i]);
            visited[i] = true;
            dfsV1(nums, path, visited, result);
            /**
             * remove num off the path and mark num as unvisited.
             */
            path.removeLast();
            visited[i] = false;
        }
    }

    /**
     * Cost 7ms
     * @param nums
     * @param path
     * @param visited
     * @param result
     */
    public void dfs(int[] nums, LinkedList<Integer> path, boolean[] visited,  List<List<Integer>> result) {
        if (path.size() == nums.length) {
            //Make a deep copy of path here, otherwise we'd be append the same path over and over
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /**
             *  skip visited num
             *  skip duplicated items, this step requires the input array has sorted already.
             *  i > 0 && nums[i] == nums[i-1] && visited[i-1]
             */
            if (visited[i] || i > 0 && nums[i] == nums[i-1] && visited[i-1]) {
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

}
