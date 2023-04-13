package org.ict.algorithm.leetcode.stack;

import java.util.Stack;

/**
 * Given two integer arrays pushed and popped each with distinct values,
 * return true if this could have been the result of a sequence of push and pop operations on an initially empty stack,
 * or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4),
 * pop() -> 4,
 * push(5),
 * pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * Example 2:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * Output: false
 * Explanation: 1 cannot be popped before 2.
 *
 *
 * Constraints:
 *
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * All the elements of pushed are unique.
 * popped.length == pushed.length
 * popped is a permutation of pushed.
 * @author sniper
 * @date 13 Apr, 2023
 * LC946, Medium
 */
public class ValidateStackSequences {

    /**
     * 1.Push each item into the stack;
     * 2.Greedily pop the item from the stack if top item is same as popped element,
     * and increment the pointer of popped.
     * 3.At last, if stack is empty, return true, otherwise return false.
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, j = 0; i < n; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
