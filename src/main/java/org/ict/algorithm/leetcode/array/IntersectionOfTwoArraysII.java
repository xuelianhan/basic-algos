package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * Given two integer arrays nums1 and nums2,
 * return an array of their intersection.
 *
 * Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 *
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk,
 * and the memory is limited such that you cannot load all elements into the memory at once?
 *
 * @author sniper
 * @date 24 Sep, 2022
 * LC350
 */
public class IntersectionOfTwoArraysII {

    public int[] intersectV1(int[] nums1, int[] nums2) {

        return null;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        /**
         * 1.Counting frequency of numbers in nums1.
         */
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int num : nums1) {
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }

        /**
         * 2.Counting frequency of numbers in nums2 and exist in nums1.
         */
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num : nums2) {
            if (map1.keySet().contains(num)) {
                map2.put(num, map2.getOrDefault(num, 0) + 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer>> iter = map2.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            /**
             * Calculate the min appearance for each number in map2 and map1.
             */
            int loopSize = Math.min(entry.getValue(), map1.get(entry.getKey()));
            for (int i = 0; i < loopSize; i++) {
                list.add(entry.getKey());
            }
        }
        /**
         * 3.Convert to int array.
         */
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
