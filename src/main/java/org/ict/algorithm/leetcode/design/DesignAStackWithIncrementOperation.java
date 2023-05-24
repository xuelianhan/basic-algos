package org.ict.algorithm.leetcode.design;

/**
 * Design a stack that supports increment operations on its elements.
 *
 * Implement the CustomStack class:
 *
 * CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack.
 * void push(int x) Adds x to the top of the stack if the stack has not reached the maxSize.
 * int pop() Pops and returns the top of the stack or -1 if the stack is empty.
 * void inc(int k, int val) Increments the bottom k elements of the stack by val.
 * If there are less than k elements in the stack,
 * increment all the elements in the stack.
 *
 *
 * Example 1:
 *
 * Input
 * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
 * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
 * Output
 * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
 * Explanation
 * CustomStack stk = new CustomStack(3); // Stack is Empty []
 * stk.push(1);                          // stack becomes [1]
 * stk.push(2);                          // stack becomes [1, 2]
 * stk.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
 * stk.push(2);                          // stack becomes [1, 2]
 * stk.push(3);                          // stack becomes [1, 2, 3]
 * stk.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
 * stk.increment(5, 100);                // stack becomes [101, 102, 103]
 * stk.increment(2, 100);                // stack becomes [201, 202, 103]
 * stk.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
 * stk.pop();                            // return 202 --> Return top of the stack 202, stack becomes [201]
 * stk.pop();                            // return 201 --> Return top of the stack 201, stack becomes []
 * stk.pop();                            // return -1 --> Stack is empty return -1.
 *
 *
 * Constraints:
 *
 * 1 <= maxSize, x, k <= 1000
 * 0 <= val <= 100
 * At most 1000 calls will be made to each method of increment, push and pop each separately.
 * @author sniper
 * @date 24 May 2023
 * LC1381, Medium, frequency=13
 * Tag: tiktok
 */
public class DesignAStackWithIncrementOperation {

    /**
     * class CustomStack:
     *
     *     def __init__(self, maxSize: int):
     *         self.arr = [0] * maxSize
     *         self.pos = 0
     *
     *
     *     def push(self, x: int) -> None:
     *         if self.pos < len(self.arr):
     *             self.arr[self.pos] = x
     *             self.pos += 1
     *
     *     def pop(self) -> int:
     *         if self.pos == 0:
     *             return -1
     *         self.pos -= 1
     *         return self.arr[self.pos]
     *
     *
     *     def increment(self, k: int, val: int) -> None:
     *         for i in range(min(k, self.pos)):
     *             self.arr[i] += val
     */
    static class CustomStackV1 {
        private int[] arr;

        private int pos;

        public CustomStackV1(int maxSize) {
            arr = new int[maxSize];
        }

        public void push(int x) {
            if (pos < arr.length) {
                arr[pos++] = x;
            }
        }

        public int pop() {
            if (pos == 0) {
                return -1;
            }
            return arr[--pos];
        }

        public void increment(int k, int val) {
            if (pos <= 0) {
                return;
            }
            for (int i = 0; i < Math.min(k, pos); i++) {
                arr[i] += val;
            }
        }
    }

    static class CustomStack {
        private int[] arr;

        private int pos;

        public CustomStack(int maxSize) {
            arr = new int[maxSize];
        }

        public void push(int x) {
            if (pos >= arr.length || pos < 0) {
                return;
            }
            arr[pos] = x;
            pos++;
        }

        public int pop() {
            if (pos == 0) {
                return -1;
            }
            pos--;
            int res = arr[pos];
            return res;
        }

        public void increment(int k, int val) {
            if (pos <= 0) {
                return;
            }
            for (int i = 0; i < Math.min(k, pos); i++) {
                arr[i] += val;
            }
        }
    }

    /**
     * Your CustomStack object will be instantiated and called as such:
     * CustomStack obj = new CustomStack(maxSize);
     * obj.push(x);
     * int param_2 = obj.pop();
     * obj.increment(k,val);
     * ---------------------------------------------
     * e.g.
     * ["CustomStack","push","pop","increment","pop","increment","push","pop","push","increment","increment","increment"]
     * [[2],           [34], [],    [8,100],    [],  [9,91],     [63],   [],   [84],  [10,93],    [6,45],     [10,4]]
     * Expected:
     * [null,          null, 34,     null,      -1,   null,      null,   63,   null,  null,       null,       null]
     *
     * CustomStack obj = new CustomStack(2);
     * obj.push(34);           // stack:[34], pos:1
     * obj.pop();              // stack:[], pos:0
     * obj.increment(8, 100);  // stack:[], pos:0
     * obj.pop();              // return -1, stack:[], pos:-1
     * obj.increment(9, 91);   // stack:[], pos:-1
     * obj.push(63);           // stack:[63], pos:0
     * obj.pop();              // stack:[], pos:-1
     * obj.push(84);           // stack:[84]
     * obj.increment(10, 93);  // stack:[177]
     * obj.increment(6, 45);   // stack:[222]
     * obj.increment(10, 4);   // stack:[226]
     */
}
