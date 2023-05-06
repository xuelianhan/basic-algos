package org.ict.algorithm.leetcode.array;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given three integer arrays nums1, nums2, and nums3,
 * return a distinct array containing all the values that are present in at least two out of the three arrays.
 * You may return the values in any order.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 * Output: [3,2]
 * Explanation: The values that are present in at least two arrays are:
 * - 3, in all three arrays.
 * - 2, in nums1 and nums2.
 * Example 2:
 *
 * Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 * Output: [2,3,1]
 * Explanation: The values that are present in at least two arrays are:
 * - 2, in nums2 and nums3.
 * - 3, in nums1 and nums2.
 * - 1, in nums1 and nums3.
 * Example 3:
 *
 * Input: nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
 * Output: []
 * Explanation: No value is present in at least two arrays.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length, nums3.length <= 100
 * 1 <= nums1[i], nums2[j], nums3[k] <= 100
 * @author sniper
 * @date 06 May 2023
 * LC2032, Easy, frequency=1
 */
public class TwoOutOfThree {

    /**
     * Time Cost 7ms
     * ----------------------------------------
     * def twoOutOfThree(self, nums1: List[int], nums2: List[int], nums3: List[int]) -> List[int]:
     * 	return set(nums1) & set(nums2) | set(nums2) & set(nums3) | set(nums1) & set(nums3)
     * ---------------------------------------
     * class Solution:
     *     def twoOutOfThree(self, nums1: List[int], nums2: List[int], nums3: List[int]) -> List[int]:
     *         set12 = set(nums1) & set(nums2)
     *         set23 = set(nums2) & set(nums3)
     *         set13 = set(nums1) & set(nums3)
     *         print(set12)
     *         print(set23)
     *         print(set13)
     *         res = set12 | set23 | set13
     *         print(res)
     *         return res
     * @param nums1
     * @param nums2
     * @param nums3
     * @return
     */
    public List<Integer> twoOutOfThreeV1(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set3 = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int i: nums1) {
            set1.add(i);
            set.add(i);
        }
        for (int i: nums2) {
            set2.add(i);
            set.add(i);
        }
        for (int i: nums3) {
            set3.add(i);
            set.add(i);
        }
        List<Integer> result = new ArrayList<>();
        for (int i: set) {
            if (set1.contains(i) && set2.contains(i)
                    || set2.contains(i) && set3.contains(i)
                    || set1.contains(i) && set3.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }


    /**
     * Time Cost 8ms
     * @param nums1
     * @param nums2
     * @param nums3
     * @return
     */
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> set1 = new HashSet<>();
        List<Integer> list1 = Arrays.stream(nums1).mapToObj(Integer::valueOf).collect(Collectors.toList());
        set1.addAll(list1);

        Set<Integer> set2 = new HashSet<>();
        List<Integer> list2 = Arrays.stream(nums2).mapToObj(Integer::valueOf).collect(Collectors.toList());
        set2.addAll(list2);

        Set<Integer> res = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                res.add(num);
            }
        }

        for (int num: nums3) {
            if (set1.contains(num)) {
                res.add(num);
            }
            if (set2.contains(num)) {
                res.add(num);
            }
        }

        return new ArrayList<>(res);
    }
}
