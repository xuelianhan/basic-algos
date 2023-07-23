package org.ict.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * You are given two 2D integer arrays nums1 and nums2.
 *
 * nums1[i] = [id-i, val-i] indicate that the number with the id idi has a value equal to val-i.
 * nums2[i] = [id-i, val-i] indicate that the number with the id idi has a value equal to val-i.
 * Each array contains unique ids and is sorted in ascending order by id.
 *
 * Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:
 *
 * Only ids that appear in at least one of the two arrays should be included in the resulting array.
 * Each id should be included only once and its value should be the sum of the values of this id in the two arrays.
 * If the id does not exist in one of the two arrays then its value in that array is considered to be 0.
 * Return the resulting array.
 * The returned array must be sorted in ascending order by id.
 *
 *
 *
 * Example 1:
 * Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
 * Output: [[1,6],[2,3],[3,2],[4,6]]
 * Explanation: The resulting array contains the following:
 * - id = 1, the value of this id is 2 + 4 = 6.
 * - id = 2, the value of this id is 3.
 * - id = 3, the value of this id is 2.
 * - id = 4, the value of this id is 5 + 1 = 6.
 *
 * Example 2:
 * Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
 * Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
 * Explanation: There are no common ids, so we just include each id with its value in the resulting list.
 *
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 200
 * nums1[i].length == nums2[j].length == 2
 * 1 <= id-i, val-i <= 1000
 * Both arrays contain unique ids.
 * Both arrays are in strictly ascending order by id.
 * @author sniper
 * @date 23 Jul 2023
 * LC2570, Easy
 */
public class MergeTwo2DArraysBySummingValues {

    public static void main(String[] args) {
        //int[][] nums1 = {{1,2},{2,3},{4,5}};
        //int[][] nums2 = {{1,4},{3,2},{4,1}};
        int[][] nums1 = {{2,4},{3,6},{5,5}};
        int[][] nums2 = {{1,3},{4,3}};
        MergeTwo2DArraysBySummingValues instance = new MergeTwo2DArraysBySummingValues();
        int[][] res = instance.mergeArrays(nums1, nums2);
        System.out.println(Arrays.deepToString(res));
    }

    /**
     * Time Cost 6ms
     * @param nums1
     * @param nums2
     * @return
     */
    public int[][] mergeArraysV1(int[][] nums1, int[][] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] a : nums1) {
            map.put(a[0], a[1]);
        }
        for (int[] b : nums2) {
            map.merge(b[0], b[1], Integer::sum);
        }
        return map.entrySet()
                .stream()
                .map(e -> new int[]{e.getKey(), e.getValue()})
                .toArray(int[][]::new);
    }

    /**
     * Time Cost 4ms
     * @param nums1
     * @param nums2
     * @return
     */
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] a : nums1) {
            map.put(a[0], a[1]);
        }
        for (int[] b : nums2) {
            map.merge(b[0], b[1], Integer::sum);
        }
        int k = map.size();
        int[][] res = new int[k][2];
        int l = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[l][0] = entry.getKey();
            res[l][1] = entry.getValue();
            l++;
        }
        return res;
    }
}
