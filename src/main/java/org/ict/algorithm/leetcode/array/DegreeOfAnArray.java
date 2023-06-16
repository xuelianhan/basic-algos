package org.ict.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of non-negative integers nums,
 * the degree of this array is defined as the maximum frequency of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums,
 * that has the same degree as nums.
 *
 * Example 1:
 * Input: nums = [1,2,2,3,1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 *
 * Example 2:
 * Input: nums = [1,2,2,3,1,4,2]
 * Output: 6
 * Explanation:
 * The degree is 3 because the element 2 is repeated 3 times.
 * So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 *
 *
 * Constraints:
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 *
 * @author sniper
 * @date 16 May 2023
 * LC697, Easy, frequency=20
 */
public class DegreeOfAnArray {

    /**
     * Understanding the following solution
     * Time Cost 37ms
     * @param nums
     * @return
     */
    public int findShortestSubArrayV2(int[] nums) {
        int res = Integer.MAX_VALUE;
        int degree = 0;
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> startPos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            if (startPos.get(nums[i]) == null) {
                startPos.put(nums[i], i);
            }
            if (freq.get(nums[i]) == degree) {
                res = Math.min(res, i - startPos.get(nums[i]) + 1);
            } else if (freq.get(nums[i]) > degree) {
                res = i - startPos.get(nums[i]) + 1;
                degree = freq.get(nums[i]);
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Time Cost 38ms
     * @param nums
     * @return
     */
    public int findShortestSubArrayV1(int[] nums) {
        int res = Integer.MAX_VALUE;
        int degree = 0;
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> startPos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            if (freq.get(nums[i]) == 1) {
                startPos.put(nums[i], i);
            }
            if (freq.get(nums[i]) == degree) {
                res = Math.min(res, i - startPos.get(nums[i]) + 1);
            } else if (freq.get(nums[i]) > degree) {
                res = i - startPos.get(nums[i]) + 1;
                degree = freq.get(nums[i]);
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Time Cost 36ms
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        int res = Integer.MAX_VALUE;
        int degree = 0;
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, int[]> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            if (freq.get(nums[i]) == 1) {
                pos.putIfAbsent(nums[i], new int[] {i, i});
            } else {
                int[] arr = pos.get(nums[i]);
                arr[1] = i;
                pos.put(nums[i], arr);
            }
            degree = Math.max(degree, freq.get(nums[i]));
        }

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (degree == entry.getValue()) {
                int[] arr = pos.get(entry.getKey());
                res = Math.min(res, arr[1] - arr[0] + 1);
            }
        }
        return res;
    }
}
