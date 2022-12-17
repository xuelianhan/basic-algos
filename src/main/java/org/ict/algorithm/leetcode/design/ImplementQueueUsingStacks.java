package org.ict.algorithm.leetcode.design;

import java.util.Stack;

/**
 * Implement a first in first out (FIFO) queue using only two stacks.
 * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 *
 * Implement the MyQueue class:
 *
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 * Notes:
 *
 * You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, the stack may not be supported natively.
 * You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 1, 1, false]
 *
 * Explanation
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 *
 * Constraints:
 *
 * 1 <= x <= 9
 * At most 100 calls will be made to push, pop, peek, and empty.
 * All the calls to pop and peek are valid.
 *
 *
 * Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity?
 * In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.
 * @author sniper
 * @date 17 Dec, 2022
 * LC232
 */
public class ImplementQueueUsingStacks {

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     * e.g.
     * push 1, newStack:1
     * push 2, newStack:2,1
     * push 3, newStack:3,2,1
     *
     *
     */
    class MyQueueV1 {

        private Stack<Integer> oldStack = new Stack<>();

        private Stack<Integer> newStack = new Stack<>();

        public MyQueueV1() {}

        /**
         * Newly elements all being pushed into newStack.
         * @param x
         */
        public void push(int x) {
            newStack.push(x);
        }

        public int pop() {
            shiftStack();
            return oldStack.pop();
        }

        public int peek() {
            shiftStack();
            return oldStack.peek();
        }

        public boolean empty() {
            return oldStack.isEmpty() && newStack.isEmpty();
        }

        /**
         * Transfer elements from newStack to oldStack.
         */
        private void shiftStack() {
            if (!oldStack.isEmpty()) {
                return;
            }
            while (!newStack.isEmpty()) {
                oldStack.push(newStack.pop());
            }
        }
    }

    class MyQueue {

        private Stack<Integer> stack = new Stack<>();

        private Stack<Integer> temp = new Stack<>();

        public MyQueue() {}

        /**
         * Using temp stack to store the existed elements,
         * and push the new element into the
         * @param x
         */
        public void push(int x) {
            while (!stack.isEmpty()) {
                temp.push(stack.pop());
            }
            temp.push(x);
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        }

        public int pop() {
            return stack.pop();
        }

        public int peek() {

            return stack.peek();
        }

        public boolean empty() {
            return stack.isEmpty();
        }
    }

}
