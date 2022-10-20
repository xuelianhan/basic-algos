package org.ict.algorithm.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * What can monotonous stack do?
 *
 * 1.Find next greater element.
 * 2.Find previous greater element.
 * 3.Find next less element.
 * 4.Find previous less element.
 *
 * previous: pop continuously until while-loop end, then store the result.
 * next: store the result simultaneously when pop each element.
 *
 * @author sniper
 * @date 17 Oct, 2022
 */
public class MonotonicStackExample {

    public static void main(String[] args) {
        int[] nums = {3, 7, 8, 4};
        increasingMonotonicStackIdx(nums);
        increasingMonotonicStack(nums);
        findPreviousLessElement(nums);
        findNextLessElement(nums);
        findPreviousGreaterElement(nums);
        findNextGreaterElement(nums);
    }

    public static void findNextGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int top = stack.peek();
                stack.pop();
                /**
                 * easy to make mistake here.
                 */
                res[top] = nums[i];
            }

            stack.push(i);
        }
        /**
         * nums = [3, 7, 8, 4]
         * NGE res:[-1, 4, 4, -1]
         */
        System.out.println("NGE res:" + Arrays.toString(res));
    }

    public static void findPreviousGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            res[i] = (stack.isEmpty() ? -1 : nums[stack.peek()]);
            stack.push(i);
        }
        /**
         * nums = [3, 7, 8, 4]
         * PGE res:[-1, 3, 7, 3]
         */
        System.out.println("PGE res:" + Arrays.toString(res));
    }


    /**
     * Find the next less element of each element in a vector with O(n) time:
     * What is the next less element of an element?
     * For example:
     * [3, 7, 8, 4]
     * The next less element of 8 is 4.
     * The next less element of 7 is 4.
     * There is no next less element for 3 and 4.
     * For simplicity of notation, we use abbreviation NLE to denote Next Less Element.
     *
     * Java code (by slightly modifying the paradigm):
     * We do some record when the index is poped out from the stack.
     * @param nums
     */
    public static void findNextLessElement(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int top = stack.peek();
                stack.pop();
                /**
                 * easy to make mistake here.
                 */
                res[top] = nums[i];
            }

            stack.push(i);
        }
        /**
         * nums = [3, 7, 8, 4]
         * NLE res:[-1, 4, 4, -1]
         */
        System.out.println("NLE res:" + Arrays.toString(res));
    }

    /**
     * Find the previous less element of each element in a vector with O(n) time:
     * What is the previous less element of an element?
     * For example:
     * [3, 7, 8, 4]
     * The previous less element of 7 is 3.
     * The previous less element of 8 is 7.
     * The previous less element of 4 is 3.
     * There is no previous less element for 3.
     * For simplicity of notation, we use abbreviation PLE to denote Previous Less Element.
     *
     * Java code (by slightly modifying the paradigm):
     * Instead of directly pushing the element itself, here for simplicity, we push the index.
     * We do some record when the index pushed into the stack.
     */
    public static void findPreviousLessElement(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            res[i] = (stack.isEmpty() ? -1 : nums[stack.peek()]);
            stack.push(i);
        }
        /**
         * nums = [3, 7, 8, 4]
         * PLE res:[-1, 3, 7, 3]
         */
        System.out.println("PLE res:" + Arrays.toString(res));
    }

    /**
     * Top-Bottom increasing monotonic stack.
     * @param nums
     */
    public static void increasingMonotonicStackIdx(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            stack.push(i);
        }
        /**
         * nums = [3, 7, 8, 4]
         * index stack:[3, 0]
         */
        System.out.println("index stack:" + stack);
    }

    /**
     * Top-Bottom increasing monotonic stack.
     * @param nums
     */
    public static void increasingMonotonicStack(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() > nums[i]) {
                stack.pop();
            }
            stack.push(nums[i]);
        }
        /**
         * nums = [3, 7, 8, 4]
         * ele stack:[4, 3]
         */
        System.out.println("ele stack:" + stack);
    }
}
