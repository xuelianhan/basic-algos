package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 *
 * Constraints:
 *
 * -2^31 <= val <= 2^31 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 * @author sniper
 * @date 08 Jan, 2023
 * LC155, Medium, frequency=3
 */
public class DesignMinStack {

    /**
     * Time Cost 4ms
     * -----------------
     *
     */
    class MinStackV1 {

        private int MIN_VAL = Integer.MAX_VALUE;

        private Deque<Integer> stack = new ArrayDeque<>();

        /**
         * Your MinStack object will be instantiated and called as such:
         * MinStack obj = new MinStack();
         * obj.push(val);
         * obj.pop();
         * int param_3 = obj.top();
         * int param_4 = obj.getMin();
         */
        public MinStackV1() {}

        public void push(int val) {
            if (val <= MIN_VAL) {
                stack.push(MIN_VAL);
                MIN_VAL = val;
            }
            stack.push(val);
        }

        public void pop() {
            if (stack.pop() == MIN_VAL) {
                MIN_VAL = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return MIN_VAL;
        }
    }

    /**
     * Time Cost 5ms
     */
    class MinStack {

        private Deque<Integer> stack1 = new ArrayDeque<>();

        private Deque<Integer> stack2 = new ArrayDeque<>();
        /**
         * Your MinStack object will be instantiated and called as such:
         * MinStack obj = new MinStack();
         * obj.push(val);
         * obj.pop();
         * int param_3 = obj.top();
         * int param_4 = obj.getMin();
         */
        public MinStack() {}

        public void push(int val) {
            stack1.push(val);
            if (stack2.isEmpty() || stack2.peek() >= val) {
                stack2.push(val);
            }
        }

        public void pop() {
            /**
             * Auto de-boxing here
             */
            int top = stack1.pop();
            if (!stack2.isEmpty() && stack2.peek() == top) {
                stack2.pop();
            }
        }

        public int top() {
            return stack1.peek();
        }

        public int getMin() {
            return stack2.peek();
        }
    }


}
