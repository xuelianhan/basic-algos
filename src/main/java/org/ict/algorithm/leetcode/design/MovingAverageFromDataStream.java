package org.ict.algorithm.leetcode.design;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 *
 * Constraints:
 *
 * 1 <= size <= 1000
 * -10^5 <= val <= 10^5
 * At most 10^4 calls will be made to next.
 *
 * @author sniper
 * @date 12 Jun 2023
 * LC346, Easy, frequency=16
 */
public class MovingAverageFromDataStream {

    /**
     * class MovingAverage:
     *     def __init__(self, size: int):
     *         self.size = size
     *         self.sum = 0
     *         self.queue = deque()
     *
     *     def next(self, val: int) -> float:
     *         if len(self.queue) == self.size:
     *             self.sum -= self.queue.popleft()
     *         self.queue.append(val)
     *         self.sum += val
     *         return self.sum / len(self.queue)
     */
    class MovingAverage {
        private Deque<Integer> q = new ArrayDeque<>();
        private int size;
        private double sum;

        public MovingAverage(int size) {
            this.size = size;
        }

        public double next(int val) {
            if (q.size() == size) {
                sum -= q.poll();
            }

            q.offer(val);
            sum += val;

            return sum / q.size();
        }
    }
}
