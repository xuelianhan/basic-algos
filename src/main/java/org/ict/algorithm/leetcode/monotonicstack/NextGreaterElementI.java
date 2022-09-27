package org.ict.algorithm.leetcode.monotonicstack;

import java.util.*;

/**
 *
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
 * If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10^4
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 *
 *
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 * @author sniper
 * @date 27 Sep, 2022
 * LC496
 */
public class NextGreaterElementI {

    /**
     * Understanding the following solution.
     *
     * Use an array to replace of the Deque.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElementV3(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] stack = new int[nums2.length];
        int peek = -1;
        for (int num : nums2) {
            while (peek > -1 && stack[peek] < num) {
                map.put(stack[peek--], num);
            }
            stack[++peek] = num;
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }

    /**
     * Understanding the following solution.
     * Improvement version based on nextGreaterElementV1.
     * Using HashMap to store value-relations directly.
     *
     * Solution provided by <a href="https://leetcode.com/yuxiangmusic/">Yuxiang Zhang</a>
     *
     * Key observation:
     * Suppose we have a decreasing sequence followed by a greater number
     * For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence
     *
     * We use a stack to keep a decreasing sub-sequence,
     * whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones,
     * their next greater element is x.
     * For example [9, 8, 7, 3, 2, 1, 6]
     * The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1.
     * So we pop 1 2 3 whose next greater element should be 6
     *
     *
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElementV2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }


    /**
     * Understanding the following solution.
     * Improvement version based on nextGreaterElement.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElementV1(int[] nums1, int[] nums2) {
        int[] res1 = new int[nums1.length];
        int[] res2 = new int[nums2.length];

        /**
         * Bottom-Top Decreasing Monotonic Stack
         * Using HashMap to store value-index relations of nums2.
         */
        Map<Integer, Integer> indexMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int top = stack.pop();
                res2[top] = i;
            }
            stack.push(i);
            indexMap.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            int idx = res2[indexMap.get(nums1[i])];
            res1[i] = (idx == 0? -1: nums2[idx] );
        }
        return res1;
    }

    /**
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
     * Output: [-1,3,-1]
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            indexMap.put(nums2[i], i);
        }

        int n = nums1.length;
        int[] res1 = new int[n];
        Arrays.fill(res1, -1);

        int[] res2 = new int[nums2.length];
        Arrays.fill(res2, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()]< nums2[i]) {
                int top = stack.pop();
                res2[top] = i;
            }
            stack.push(i);
        }
        System.out.println(Arrays.toString(res2));
        for (int i = 0; i < n; i++) {
            int idx = res2[indexMap.get(nums1[i])];
            res1[i] = (idx == -1? -1: nums2[idx] );
        }
        return res1;
    }
}
