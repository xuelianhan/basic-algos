package org.ict.algorithm.leetcode.heap;

import java.util.*;

/**
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far.
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * Constraints:
 * -10^5 <= num <= 10^5
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 10^4 calls will be made to addNum and findMedian.
 *
 *
 * Follow up:
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * @author sniper
 * @date 13 Nov, 2022
 * LC295, Medium
 */
public class FindMedianFromDataStream {

    /**
     * Method from LiYuDong
     * Pair-Top-Heap Solution similar with version in MedianFinderV3.
     * To dynamically maintain the median,
     * we can create 2 binomial heaps: a small top heap and a large top heap,
     * assuming that the current sequence length is M.
     * We always keep:
     * 1. the numbers in the sequence ranked from smallest to largest from 1 to M/2 are stored in the big top heap;
     * 2. the number of numbers in the sequence with ranking from smallest to largest is M/2 + 1 to M is stored in the small top heap;
     *
     * Any time there are too many elements in a heap, it is time to remove the top element of that heap and insert it into another heap.
     * In this way, the median of the sequence is:
     * 1. the median is the top element of either the small top heap or the big top heap if there are an odd number of numbers in total at the end;
     * 2. If there are an even number of numbers at the end,
     * then the median is the sum of the top elements of the small top heap and the big top heap, divided by 2
     *
     */
    class MedianFinderV5 {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        private int size = 0;

        /**
         * e.g. we add 1~5 to minHeap and maxHeap:
         * num:1, size:0, maxHeap:1, minHeap:1, maxHeap:, size:1
         * num:2, size:1, minHeap:1,2, maxHeap:1, minHeap:2, size:2
         * num:3, size:2, maxHeap:3,1, minHeap:2,3, maxHeap:1, size:3
         * num:4, size:3, minHeap:2,3,4, maxHeap:2,1, minHeap:3,4, size:4
         * num:5, size:4, maxHeap:5,2,1, minHeap:3,4,5, maxHeap:2,1, size:5
         * 5 % 2 > 0, mean = minHeap.peek() = 3
         *
         * @param num
         */
        public void addNum(int num) {
            if (size % 2 == 0) {
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
            } else {
                minHeap.offer(num);
                maxHeap.offer(minHeap.poll());
            }
            size++;
        }
        public double findMedian() {
            double res;
            if (size % 2 == 0) {
                res = (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                /**
                 * Notice here, peeking from minHeap or maxHeap depends on details of addNum method.
                 */
                res = minHeap.peek();
            }
            return res;
        }
    }

    /**
     * Pair-Top-Heap Solution similar with version in MedianFinderV3.
     */
    class MedianFinderV4 {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        private int size = 0;

        /**
         * e.g. we add 1~5 to minHeap and maxHeap:
         * num:1, size:0, minHeap:1, maxHeap:1, minHeap:, size:1
         * num:2, size:1, maxHeap:2,1, minHeap:2 maxHeap:1, size:2
         * num:3, size:2, minHeap:2,3, maxHeap:2,1, minHeap:3, size:3
         * num:4, size:3, maxHeap:4,2,1, minHeap:3,4, maxHeap:2,1, size:4
         * num:5, size:4, minHeap:3,4,5, maxHeap:3,2,1, minHeap:4,5, size:5
         * 5 % 2 > 0, mean = maxHeap.peek() = 3
         *
         * @param num
         */
        public void addNum(int num) {
            if (size % 2 == 0) {
                minHeap.offer(num);
                maxHeap.offer(minHeap.poll());
            } else {
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
            }
            size++;
        }
        public double findMedian() {
            double res;
            if (size % 2 == 0) {
                res = (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                /**
                 * Notice here, peeking from minHeap or maxHeap depends on details of addNum method.
                 */
                res = maxHeap.peek();
            }
            return res;
        }
    }


    /**
     * The invariant of the algorithm is two heaps, small and large, each represent half of the current list.
     * The length of smaller half is kept to be n / 2 at all time,
     * and the length of the larger half is either n / 2 or n / 2 + 1 depend on n's parity.
     * This way we only need to peek the two heaps' top number to calculate median.
     * Any time before we add a new number, there are two scenarios, (total n numbers, k = n / 2):
     *
     * (1) length of (small, large) == (k, k)
     * (2) length of (small, large) == (k, k + 1)
     *
     * After adding the number, total (n + 1) numbers, they will become:
     * (1) length of (small, large) == (k, k + 1)
     * (2) length of (small, large) == (k + 1, k + 1)
     *
     * @see <a href="https://leetcode.com/problems/find-median-from-data-stream/solutions/74062/short-simple-java-c-python-o-log-n-o-1"></a>
     * @see <a href="https://leetcode.com/problems/find-median-from-data-stream/solutions/74047/java-python-two-heap-solution-o-log-n-add-o-1-find"></a>
     * @author dietpepsi
     */
    class MedianFinderV3 {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        /**
         * At first, minHeap and maxHeap both have no elements, zero is even.
         */
        private boolean even = true;

        /**
         * e.g. we add 1~5 to minHeap and maxHeap:
         * num:1, even:true, minHeap:1, maxHeap:1, minHeap:, even:false
         * num:2, even:false, maxHeap:2,1, minHeap:2, maxHeap:1, even:true
         * num:3, even:true, minHeap:2,3, maxHeap:2,1, minHeap:3, even:false
         * num:4, even:false, maxHeap:4,2,1, minHeap:3,4, maxHeap:2,1, even:true
         * num:5, even:true, minHeap:3,4,5, maxHeap:3,2,1, minHeap:4,5, even:false
         * even:false, mean = maxHeap.peek() = 3
         *
         * @param num
         */
        public void addNum(int num) {
            if (even) {
                minHeap.offer(num);
                maxHeap.offer(minHeap.poll());
            } else {
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
            }
            even = !even;
        }
        public double findMedian() {
            double res;
            if (even) {
                res = (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                /**
                 * Notice here, peeking from minHeap or maxHeap depends on details of addNum method.
                 */
                res = maxHeap.peek();
            }
            return res;
        }
    }

    /**
     * Pair-Top-Heap Standard Solution
     * MaxHeap + MinHeap for Long type, especially using Long-version while the Integer-version may cause the overflow at (a + b) / 2.0.
     * Assume input: 1,2,3,4,5,6
     * MaxHeap:3,2,1
     * MinHeap:4,5,6
     * Mean = (3 + 4) / 2.0 = 3.5
     */
    class MedianFinderV2 {
        /**
         * The following code equals:
         * PriorityQueue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
         */
        PriorityQueue<Long> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a < b) {
                return 1;
            } else if (a > b) {
                return -1;
            } else {
                return 0;
            }
        });
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        public void addNum(int num) {
            minHeap.add((long)num);
            maxHeap.add(minHeap.poll());
            if (minHeap.size() < maxHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
        }
        public double findMedian() {
            double res;
            if (minHeap.size() > maxHeap.size()) {
                res = minHeap.peek();
            } else {
                res = (minHeap.peek() + maxHeap.peek()) / 2.0;
            }
            return res;
        }
    }

    /**
     * Pair-Top-Heap Standard Solution
     * MaxHeap + MinHeap for Integer type
     * Assume input: 1,2,3,4,5,6
     * MaxHeap:3,2,1
     * MinHeap:4,5,6
     * Mean = (3 + 4) / 2.0 = 3.5
     */
    class MedianFinderV1 {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        public void addNum(int num) {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
            if (minHeap.size() < maxHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
        }
        public double findMedian() {
            double res;
            if (minHeap.size() > maxHeap.size()) {
                res = minHeap.peek();
            } else {
                res = (minHeap.peek() + maxHeap.peek()) / 2.0;
            }
            return res;
        }
    }


    /**
     * I keep two heaps (or priority queues):
     * Max-heap has the smaller half of the numbers.
     * Min-heap has the larger half of the numbers.
     * This gives me direct access to the one or two middle values (they're the tops of the heaps),
     * so getting the median takes O(1) time. And adding one number takes O(log n) time.
     * Supporting both min- and max-heap is more or less cumbersome, depending on the language,
     * so I simply negate the numbers in the heap in which I want the reverse of the default order.
     * To prevent this from causing a bug with -231 (which negated is itself, when using 32-bit ints),
     * I use integer types larger than 32 bits.
     * Using larger integer types also prevents an overflow error when taking the mean of the two middle numbers.
     * I think almost all solutions posted previously have that bug.
     * Update: These are pretty short already, but by now I wrote even shorter ones.
     * @see <a href="https://leetcode.com/problems/find-median-from-data-stream/solutions/74062/short-simple-java-c-python-o-log-n-o-1"></a>
     * @author StefanPochmann
     */
    class MedianFinder {

        /**
         * Max-heap has the smaller half of the numbers.
         * The maxHeap here is actually the min heap, but we store the opposite number in it.
         * maxHeap:-3,-2,-1, although the top value is -3, it's the minimum, but -(-3)=3 is the maximum
         * It use Min-Heap and Opposite number to simulate a Max-Heap.
         *
         * e.g. we add 1~6 to minHeap and maxHeap:
         * num:1, minHeap:1, maxHeap:-1, minHeap:empty, minHeap.size < maxHeap.size, minHeap:1, maxHeap:empty
         * num:2, minHeap:1,2, maxHeap:-1, minHeap:2, minHeap.size == maxHeap.size
         * num:3, minHeap:2,3, maxHeap:-2,-1, minHeap:3, minHeap.size < maxHeap, minHeap:2,3, maxHeap:-1
         * num:4, minHeap:2,3,4, maxHeap:-2,-1, minHeap:3,4, minHeap.size == maxHeap
         * num:5, minHeap:3,4,5, maxHeap:-3,-2,-1, minHeap:4,5, minHeap.size < maxHeap, minHeap:3,4,5, maxHeap:-2,-1
         * num:6, minHeap:3,4,5,6, maxHeap:-3,-2,-1, minHeap:4,5,6, minHeap == maxHeap
         * mean = (minHeap.peek() - maxHeap.peek) / 2.0
         * mean = (4 - (-3)) / 2.0 = 7 / 2.0 = 3.5
         */
        PriorityQueue<Long> maxHeap = new PriorityQueue<>();

        /**
         * It's default min-heap in Java(the top is the minimum)
         * Min-heap has the larger half of the numbers.
         */
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        public void addNum(int num) {
            minHeap.add((long)num);
            maxHeap.add(-minHeap.poll());
            if (minHeap.size() < maxHeap.size()) {
                minHeap.add(-maxHeap.poll());
            }
        }
        public double findMedian() {
            double res;
            if (minHeap.size() > maxHeap.size()) {
                res = minHeap.peek();
            } else {
                res = (minHeap.peek() - maxHeap.peek()) / 2.0;
            }
            return res;
        }
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
}
