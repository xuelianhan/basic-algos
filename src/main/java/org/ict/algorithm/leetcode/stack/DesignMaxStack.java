package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Description
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
 * Implement the MaxStack class:
 *
 * MaxStack() Initializes the stack object.
 * void push(int x) Pushes element x onto the stack.
 * int pop() Removes the element on top of the stack and returns it.
 * int top() Gets the element on the top of the stack without removing it.
 * int peekMax() Retrieves the maximum element in the stack without removing it.
 * int popMax() Retrieves the maximum element in the stack and removes it.
 * If there is more than one maximum element, only remove the top-most one.
 * You must come up with a solution that supports O(1) for each top call and O(logN) for each other call.
 *
 * Example 1:
 * Input
 * ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
 * [[], [5], [1], [5], [], [], [], [], [], []]
 * Output
 * [null, null, null, null, 5, 5, 1, 5, 1, 5]
 *
 * Explanation
 * MaxStack stk = new MaxStack();
 * stk.push(5);   // [5] the top of the stack and the maximum number is 5.
 * stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
 * stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
 * stk.top();     // return 5, [5, 1, 5] the stack did not change.
 * stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
 * stk.top();     // return 1, [5, 1] the stack did not change.
 * stk.peekMax(); // return 5, [5, 1] the stack did not change.
 * stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
 * stk.top();     // return 5, [5] the stack did not change.
 *
 * Constraints:
 * -10^7 <= x <= 10^7
 * At most 105 calls will be made to push, pop, top, peekMax, and popMax.
 * There will be at least one element in the stack when pop, top, peekMax, or popMax is called.
 * @author sniper
 * @date 03 May 2023
 * LC716, Hard, frequency=41
 */
public class DesignMaxStack {

    class MaxStackV2 {

        public MaxStackV2() {}

    }

    /**
     * LeetCode Official - Approach #1: Two Stacks
     * For peekMax, we remember the largest value we've seen on the side.
     * For example if we add [2, 1, 5, 3, 9] in stack , we'll store [2, 2, 5, 5, 9] in maxStack.
     * This works seamlessly with pop operations, and also it's easy to compute:
     * it's just the maximum of the element we are adding and the previous maximum.
     * For popMax, we know what the current maximum (peekMax) is.
     * We can pop until we find that maximum, then push the popped elements back on the stack.
     *
     * Time Complexity: O(N) for the popMax operation, and O(1) for the other operations,
     * where N is the number of operations performed.
     * Space Complexity: O(N), the maximum size of the stack.
     * @see <a href="https://aaronice.gitbook.io/lintcode/stack/max-stack"></a>
     */
    class MaxStackV1 {
        private Deque<Integer> stack1 = new ArrayDeque<>();

        private Deque<Integer> stack2 = new ArrayDeque<>();

        public MaxStackV1() {}

        public void push(int x) {
            int max = stack2.isEmpty() ? x : stack2.peek();
            stack2.push(max > x ? max : x);
            stack1.push(x);
        }

        public int pop() {
            stack2.pop();
            return stack1.pop();
        }

        public int top() {
            return stack1.peek();
        }

        public int peekMax() {
            return stack2.peek();
        }

        public int popMax() {
            int max = peekMax();
            Stack<Integer> aux = new Stack<>();
            while (top() != max) {
                aux.push(pop());
            }
            pop();
            while (!aux.isEmpty()) {
                push(aux.pop());
            }
            return max;
        }

    }

    /**
     * Two stacks are used to simulate this.
     * stack1 is a normal stack to hold all numbers,
     * while stack2 is a maximum stack to hold the largest number that appears.
     */
    class MaxStack {

        private Deque<Integer> stack1 = new ArrayDeque<>();

        private Deque<Integer> stack2 = new ArrayDeque<>();

        /**
         * Your MaxStack object will be instantiated and called as such:
         * MaxStack obj = new MaxStack();
         * obj.push(x);
         * int param_2 = obj.pop();
         * int param_3 = obj.top();
         * int param_4 = obj.peekMax();
         * int param_5 = obj.popMax();
         */
        public MaxStack() {}

        public void push(int x) {
            stack1.push(x);
            if (stack2.isEmpty() || stack2.peek() <= x) {
                stack2.push(x);
            }
        }

        public int pop() {
            if (!stack2.isEmpty() && stack2.peek() == stack1.peek()) {
                stack2.pop();
            }
            int t = stack1.pop();
            return t;
        }

        public int top() {
            return stack1.peek();
        }

        public int peekMax() {
            return stack2.peek();
        }

        /**
         * In the popMax() function, we save the top element of stack2 into a variable max at first,
         * and then we want to remove this element in stack1,
         * because the stack can't locate the element directly.
         * So we use a temporary stack temp, to save the outgoing element of stack1 into this temporary stack temp.
         * We exit the while-loop until the top element of stack1 and stack2 are the same.
         * At this time, we find stack2's top element in stack1, top elements in stack1 and stack2 are removed respectively.
         * After that, we can add the elements in the temporary stack temp back into stack1.
         * Note that at this time, it is easy to make a mistake that stack2 is not updated at the same time,
         * so we can directly call the push () function.
         * @return
         */
        public int popMax() {
            int max = stack2.peek();
            /**
             * Store the elements not equals max temporarily into a temp stack.
             */
            Stack<Integer> temp = new Stack<>();
            while (stack1.peek() != stack2.peek()) {
                temp.push(stack1.peek());
                stack1.pop();
            }
            /**
             * pop max from stack1 and stack2
             */
            stack1.pop();
            stack2.pop();

            /**
             * push elements of temp back into the stack1 and stack2
             */
            while (!temp.empty()) {
                push(temp.peek());
                temp.pop();
            }
            return max;
        }

    }

}
