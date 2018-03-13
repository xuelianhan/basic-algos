package org.ict.algorithm.sort;

/**
 * Generic max priority queue implementation with a binary heap.
 * Can be used with a comparator instead of the natural order,
 * but the generic Key type must still be Comparable.
 *
 * We use a one-based array to simplify parent and child calculations.
 * Can be optimized by replacing full exchanges with half exchanges
 * (ala insertion sort).
 *
 *
 * $ javac org/ict/algorithm/sort/MaxPQ.java 
 * Note: org/ict/algorithm/sort/MaxPQ.java uses unchecked or unsafe operations.
 * Note: Recompile with -Xlint:unchecked for details.
 * $ more ../resources/tinyPQ.txt 
 * P Q E - X A M - P L E -
 * $ java org/ict/algorithm/sort/MaxPQ < ../resources/tinyPQ.txt 
 * Q 
 * X 
 * P 
 * (6 left on pq
 *
 *
 */

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code MaxPQ} class represents a priority queue of generic keys.
 * It supports the usual <em> insert</em> and <em> delete-the-maximum </em>
 * operations, along with methods for peeking at the maximum key,
 * testing if the priority queue is empty, and iterating through the keys.
 * <p>
 * This implementation uses a binary heap.
 * The <em>insert</em> and <em>delete-the-maximum</em> operations take
 * logarithmic amortized time.
 * The <em>max</em>, <em>size</em>, <em>is-empty</em> operations take constant 
 * time.
 * Construction takes time propotional to the specified capacity or the number
 * of items used to initialize the data structure.
 * <p>
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class MaxPQ<Key> implements Iterable<Key> {
    // store items at indices 1 to n
    private Key[] pq;

    // number of items on priority queue
    private int n;

    //optional comparator
    private Comparator<Key> comparator;
    
    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public MaxPQ(int initCapacity) {
        this.pq = (Key[]) new Object[initCapacity + 1]; 
        this.n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MaxPQ () {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param initCapacity the initial capacity of this priority queue
     * @param comparator the order in which to compare the keys
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        this.pq = (Key[])new Object[initCapacity + 1];
        this.n = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     * 
     * @param comparator the order in which to compare the keys.
     */
    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number of keys, using 
     * sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public MaxPQ(Key[] keys) {
        this.n = keys.length;
        this.pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++) {
            pq[i+1] = keys[i];
        }
        for (int k = n/2; k >=1; k--) {
            sink(k);
        }
        assert isMaxHeap();
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns a largest key on this priority queue.
     * 
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    //helper function to double the size of the heap array 
    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <=n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * Adds a new key to this priority queue.
     * @param x the new key to add to this priority queue
     */
    public void insert(Key x) {
        //double size of array if necessary
        if (n == (pq.length - 1)) {
            resize(2 * pq.length);
        }

        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
        assert isMaxHeap();
    }

    /**
     * Removes and returns a largest key on this priority queue.
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        //at this time, n = length - 1, n+1 = length means the last element
        //to avoid loiter and help with garbage collection
        pq[n+1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        assert isMaxHeap();
        return max;
    }

    /**
     * Helper functions to restore the heap invariant.
     *
     */
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * Helper functions for compares and swaps.
     */
    private boolean less(int i, int j) {
       if (comparator == null) {
            return ((Comparable<Key>)pq[i]).compareTo(pq[j]) < 0;
       } else {
            return comparator.compare(pq[i], pq[j]) < 0;
       }
    }
    
    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..N] a max heap?
    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    //is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeap(int k) {
        //see the parameter k assigned  with 1 in isMaxHeap() method,
        //this mean that when k = 1, if 1 > n satisfied, n must be zero,
        //There is only one element in the priority queue, so it is max heap.
        if (k > n) {
           return true; 
        }
        int left = 2*k;
        int right = 2*k + 1;
        if (left <= n && less(k, left)) {
            return false;
        }
        if (right <= n && less(k, right)) {
            return false;
        }
        return isMaxHeap(left) && isMaxHeap(right); 
    }

    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in descending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     * @return an iterator that iterates over the keys in descending order
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        //create a new pq
        private MaxPQ<Key> copy;
        
        // add all items to copy of heap
        //  takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null) {
                copy = new MaxPQ<Key>(size());
            } else {
                copy = new MaxPQ<Key>(size(), comparator);
            }
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMax();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!"-".equals(item)) {
                pq.insert(item);
            } else if (!pq.isEmpty()) {
                StdOut.println(pq.delMax() + " ");
            }
        }
        StdOut.println("(" + pq.size() + " left on pq");
    }
}
