package org.ict.algorithm.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given a circular integer array nums (i.e.,
 * the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number.
 * If it doesn't exist, return -1 for this number.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number.
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 *
 *
 * @author sniper
 * @date 27 Sep, 2022
 * LC503
 *
 */
public class NextGreaterElementII {

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        //int[] nums = {1,2,3,4,3,1,2,3,4,3};
        int[] res = nextGreaterElementsV1(nums);
        System.out.println(Arrays.toString(res));
    }




    /**
     * Understand the following process.
     *
     * Solution provided by lee215, a legend man!
     *
     * Loop once, we can get the Next Greater Number of a normal array.
     * Loop twice, we can get the Next Greater Number if a circular array(cyclic array).
     *
     * Input: nums = [1,2,1]
     *
     * 0 1 2
     * 1 2 1
     *
     * i:0, cyclicPos:0, stack:, push 0 into the stack
     * i:1, cyclicPos:1, stack:0, peek:0, nums[0] < nums[1], pop 0 from the stack, res[0]=nums[1]=2
     *                   push 1 into the stack
     *
     * i:2, cyclicPos:2, stack:1, peek:1, nums[1] > nums[2], push 2 into the stack.
     * i:3, cyclicPos:0, stack:1,2, peek:2, nums[2] == nums[0], push 0 into the stack.
     * i:4, cyclicPos:1, stack:1,2,0, peek:0, nums[0] < nums[1], pop 0 from the stack, res[0]=nums[1]=2
     *                   stack:1,2, peek:2, nums[2]< nums[1], pop 2 from the stack, res[2]=nums[1]=2,
     *                   stack:1, peek:1, nums[1]==nums[cyclic]==2
     *                   push 1 into the stack.
     *
     * i:5, cyclicPos:2, stack:1,1, peek:1, nums[1] > nums[2], push 2 into the stack.
     *      for-loop-end
     *                   stack:1,1,2
     *
     *
     * @param nums
     * @return
     */
    public static int[] nextGreaterElementsV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int n = nums.length;
        if (n == 1) {
            return new int[]{-1};
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n * 2; i++) {
            int cyclicPos = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[cyclicPos]) {
                int top = stack.pop();
                res[top] = nums[cyclicPos];
            }
            stack.push(cyclicPos);
        }

        return res;
    }

    /**
     * Expand the input array to double size and Use the Normal array
     * At last, we only get the first half of the array.
     * @param nums
     * @return
     */
    public static int[] nextGreaterElementsV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int n = nums.length;
        if (n == 1) {
            return new int[]{-1};
        }
        /**
         * Expand the original array to double size
         * e.g.
         * nums:[1,2,1]
         * arr:[1,2,1,1,2,1]
         */
        int len = n * 2;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = nums[i % n];
        }

        /**
         * Make use of the Next Greater Element in a normal array.
         */
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                res[stack.pop() % n] = arr[i];
            }
            stack.push(i);
        }
        return res;
    }


    /**
     * input: nums = [1,2,1,1,2,1]
     * output: [2, -1, 2, 2, -1, -1]
     *
     * @param nums
     * @return
     */
    public static int[] nextGreaterElementsForNormalArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int n = nums.length;
        if (n == 1) {
            return new int[]{-1};
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                res[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        return res;
    }
}
