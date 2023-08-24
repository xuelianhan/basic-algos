package org.ict.algorithm.company.hoyoverse;

import java.util.Stack;

/**
 * Implement a queue with two stacks,
 * using n elements to perform n insertions of integers at the end of the queue (push),
 * and n deletions of integers at the head of the queue (pop).
 * The elements of the queue are of type int.
 * The operation is guaranteed to be legal, i.e.,
 * the pop operation is guaranteed to have an element already in the queue.
 *
 * Data range: n ≤ 1000
 * Requirement:
 * The space complexity of storing n elements is O(n),
 * The time complexity of both insertion and deletion is O(1)
 *
 * Example 1:
 * Input: ["PSH1","PSH2","POP","POP"]
 * Expected: 1, 2
 * Description:
 * "PSH1":stands for inserting 1 to the end of the queue
 * "PSH2":represents the insertion of 2 at the end of the queue
 * "POP":represents the deletion of an element, first in first out => return 1
 * "POP":represents the deletion of an element, first in first out => return 2
 *
 * Example 2:
 * ["PSH2","POP","PSH1","POP"]
 * Expected: 2, 1
 *
 * @author sniper
 * @date 21 Jun 2023
 * NC76, Easy
 * LC232, Easy, Design, frequency=2
 */
public class ImplementQueueWithTwoStacks {

    /**
     * Store the elderly added element
     */
    Stack<Integer> stack1 = new Stack<>();
    /**
     * Store the newly added element
     */
    Stack<Integer> stack2 = new Stack<>();

    public void push(int x) {
        stack2.push(x);
    }

    public int pop() {
        shiftStack();
        return stack1.pop();
    }

    public int peek() {
        shiftStack();
        return stack1.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    private void shiftStack() {
        /**
         * Notice here
         */
        if (!stack1.isEmpty()) {
            return;
        }
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }


}
