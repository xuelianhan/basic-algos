package org.ict.algorithm.leetcode.stack;

import java.util.*;

/**
 * You are given a nested list of integers nestedList.
 * Each element is either an integer, or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 *
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * Your code will be tested with the following pseudocode:
 *
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 *     append iterator.next() to the end of res
 * return res
 * If res matches the expected flattened list, then your code will be judged as correct.
 *
 *
 *
 * Example 1:
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 *
 * Input: nestedList = [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 *
 *
 * Constraints:
 *
 * 1 <= nestedList.length <= 500
 * The values of the integers in the nested list is in the range [-10^6, 10^6].
 * @author sniper
 * @date 11 Dec, 2022
 * LC341
 */
public class FlattenNestedListIterator {

    private Deque<NestedInteger> stack = new ArrayDeque<>();

    private Deque<NestedInteger> queue = new ArrayDeque<>();

    /**
     * Time Cost 7ms
     */
    class NestedIterator implements Iterator<Integer> {

        public NestedIterator(List<NestedInteger> nestedList) {
            /**
             * Push into the stack from end to front.
             * e.g.[[1,2],3,[4,5]]
             * stack:
             * [1,2]
             * 3
             * [4,5]
             */
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            NestedInteger item = stack.pop();
            return item.getInteger();
        }

        /**
         * If current element is not integer,
         * then remove this element from the stack.
         * e.g.[[1,2],3,[4,5]]
         * stack:
         * [1,2]
         * 3
         * [4,5]
         * ----------
         * peek:[1,2], peek is not Integer
         * pop:[1,2], push 2 into the stack, push 1 into the stack.
         * stack:
         * 1
         * 2
         * 3
         * [4,5]
         * ---------
         * stack is not empty
         * peek:1, 1 is an Integer, return true.
         */
        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger item = stack.peek();
                if (item.isInteger()) {
                    return true;
                }

                stack.pop();
                for (int i = item.getList().size() - 1; i >= 0; i--) {
                    stack.push(item.getList().get(i));
                }
            }
            return false;
        }
    }

    /**
     * Time Cost 6ms
     */
    class NestedIteratorV1 implements Iterator<Integer> {

        public NestedIteratorV1(List<NestedInteger> nestedList) {
            /**
             * Add elements from start to end of the nestedList, appending them to
             * the tail of queue one by one.
             * e.g.[[1,2],3,[4,5]]
             * queue:
             * head-------tail
             * [1,2], 3, [4,5]
             */
            for (int i = 0; i < nestedList.size(); i++) {
                queue.offer(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            NestedInteger item = queue.poll();
            return item.getInteger();
        }

        /**
         * e.g.[[1,2],3,[4,5]]
         * queue:
         * head-------tail
         * [1,2], 3, [4,5]
         * ---------------
         * peek:[1,2], peek is not Integer
         * poll:[1,2], offer 2 into first place, offer 1 into the first place
         * queue:1, 2, 3, [4, 5]
         * ---------------------
         * queue is not empty
         * peek:1, 1 is an Integer
         * return true
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            while (!queue.isEmpty()) {
                NestedInteger item = queue.peek();
                if (item.isInteger()) {
                    return true;
                }
                queue.poll();
                for (int i = item.getList().size() - 1; i >= 0 ; i--) {
                    queue.offerFirst(item.getList().get(i));
                }
            }
            return false;
        }
    }

    /**
     * Time Cost 5ms
     */
    class NestedIteratorV2 implements Iterator<Integer> {

        public NestedIteratorV2(List<NestedInteger> nestedList) {
            flatToQueue(nestedList);
        }

        @Override
        public Integer next() {
            NestedInteger item = queue.poll();
            return item.getInteger();
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }

    public void flatToQueue(List<NestedInteger> nestedList) {
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                queue.offer(item);
            } else {
                flatToQueue(item.getList());
            }
        }
    }


}
