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
 */

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import java.util.Arrays;
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
public class MaxPQ {
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

    


}
