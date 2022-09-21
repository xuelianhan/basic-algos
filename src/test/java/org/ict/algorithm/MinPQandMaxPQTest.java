package org.ict.algorithm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author sniper
 * @date 21 Sep, 2022
 */
public class MinPQandMaxPQTest {



    public static void main(String[] args) {
        /**
         * [1, 2, 5, 4, 3, 9, 6, 10, 7, 8]
         * root=1 is the minimum.
         * MinPQ default dequeue an ascending order(natural order).
         *
         * [1, 2, 5, 4, 3, 9, 6, 10, 7, 8]
         * iterate output: 1 2 5 4 3 9 6 10 7 8
         * sorted output: 1 2 3 4 5 6 7 8 9 10
         */
        //buildMinPQ();

        /**
         * [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
         * root=10 is the maximum.
         * MaxPQ default dequeue a descending order.
         *
         * [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
         * iterate output: 10 9 8 7 6 5 4 3 2 1
         * sorted output: 10 9 8 7 6 5 4 3 2 1
         */
        //buildMaxPQ();

        int[] arr = {100, 50, 80, 10, 25, 20, 75};
        int k = 3;
        topKMaximum(arr, k);
        //topKMinimum(arr, k);
    }



    public static void buildMinPQ() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(10, (o1, o2) ->{
            if (o1 < o2) {
                return -1;
            } else if (o1 > o2) {
                return 1;
            } else {
                return 0;
            }
        });
        for (int i = 10; i > 0; i --) {
            queue.add(i);
        }
        /**
         * Output:
         * [1, 2, 5, 4, 3, 9, 6, 10, 7, 8]
         *
         * IN a heap, the parent of the node in position k
         * is in position top-down[k/2] and conversely,
         * the two children of the node in position k
         * are in positions 2k and 2k+1.
         *
         *  0  1  2  3  4  5  6  7   8  9  10
         * ----------------------------------
         *    [1, 2, 5, 4, 3, 9, 6, 10, 7, 8]
         *
         *              1
         *           2      5
         *      4      3  9      6
         *
         *   10   7  8
         */
        System.out.println(queue);

        output(queue);

        sort(queue);
    }



    public static void buildMaxPQ() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(10, (o1, o2) ->{
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            } else {
                return 0;
            }
        });
        for (int i = 10; i > 0; i --) {
            queue.add(i);
        }
        /**
         * Output:
         * [1, 2, 5, 4, 3, 9, 6, 10, 7, 8]
         *
         * IN a heap, the parent of the node in position k
         * is in position top-down[k/2] and conversely,
         * the two children of the node in position k
         * are in positions 2k and 2k+1.
         *
         *  0  1  2  3  4  5  6  7   8  9
         * ----------------------------------
         *    [1, 2, 5, 4, 3, 9, 6, 10, 7, 8]
         *
         *              1
         *           2      5
         *      4      3  9      6
         *
         *   10   7  8
         */
        System.out.println(queue);

        output(queue);

        sort(queue);
    }

    /**
     * The built-in iterator for java's PriorityQueue does not traverse the data structure in any particular order.
     * Why?
     * Because the underlying data structure doesn't support it.
     *
     * A minimum top binary heap is only partially ordered, with the smallest element at the root.
     * When you remove that, the heap is reordered so that the next smallest element is at the root.
     *
     * A maximum top binary heap is only partially ordered, with the biggest element at the root.
     * When you remove that, the heap is reordered so that the next biggest element is at the root.
     *
     * There is no efficient ordered traversal algorithm so none is provided in Java.
     *
     * @param queue
     */
    public static void output(PriorityQueue<Integer> queue ) {
        System.out.print("iterate output: ");
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    /**
     * A PriorityQueue is what is called a binary heap.
     * It is only ordered/sorted in the sense that the first element is the least.
     * In other word, it only cares about what is in the front of the queue, the rest are "ordered" when needed.
     *
     * The elements are only ordered as they are dequeued,
     * i.e. removed from the queue using poll().
     * This is the reason why a PriorityQueue manages to have such good performance,
     * as it is not doing any more sorting than it needs at any time.
     *
     * If you want to know how heaps work in detail I recommend this MIT lecture on heaps.
     */
    public static void sort(PriorityQueue<Integer> queue) {
        System.out.print("sorted output: ");
        while (!queue.isEmpty()) {
            Integer x = queue.poll();
            System.out.print(x + " ");
        }
    }

    public static void topKMaximum(int[] arr, int k) {
        System.out.println("Find top-" + k + " maximum elements in array:" + Arrays.toString(arr));
        /**
         * Use the MinPQ default.
         */
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int a : arr) {
            /**
             * add to queue.
             */
            pq.offer(a);
            System.out.println("queue elements:" + pq);

            /**
             * remove the top element, smallest in this case,
             * once the queue reaches the size k
             */
            if (pq.size() > k) {
                Integer root = pq.poll();
                System.out.println("queue size greater than " + k + ", queue root poll:" + root);
                System.out.println("queue elements:" + pq);
            }
        }

        /**
         * The remained elements is top-k elements
         */
        System.out.print("top-" + k + "Maximum: ");
        while (!pq.isEmpty()) {
            Integer x = pq.poll();
            System.out.print(x + " ");
        }
    }

    public static void topKMinimum(int[] arr, int k) {
        System.out.println("Find top-" + k + " minimum elements in array:" + Arrays.toString(arr));

        /**
         * Use the MaxPQ default.
         */
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
                    if (o1 < o2) {
                        return 1;
                    } else if (o1 > o2) {
                        return -1;
                    } else {
                        return 0;
                    }
                });
        for(int a : arr) {
            /**
             * add to queue.
             */
            pq.offer(a);
            System.out.println("queue elements:" + pq);

            /**
             * remove the top element, smallest in this case,
             * once the queue reaches the size k
             */
            if (pq.size() > k) {
                Integer root = pq.poll();
                System.out.println("queue size greater than " + k + ", queue root poll:" + root);
                System.out.println("queue elements:" + pq);
            }
        }

        /**
         * The remained elements is top-k elements
         */
        System.out.print("top-" + k + "Minimum: ");
        while (!pq.isEmpty()) {
            Integer x = pq.poll();
            System.out.print(x + " ");
        }
    }

}
