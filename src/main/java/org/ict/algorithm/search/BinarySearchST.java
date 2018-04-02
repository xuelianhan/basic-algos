package org.ict.algorithm.search;

import java.util.Arrays;
import java.util.NoSuchElementException;
import org.ict.algorithm.fundamentals.Queue;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
/**
 *
 * $ javac org/ict/algorithm/search/BinarySearchST.java 
 * Note: org/ict/algorithm/search/BinarySearchST.java uses unchecked or unsafe operations.
 * Note: Recompile with -Xlint:unchecked for details.
 * $ java org/ict/algorithm/search/BinarySearchST < ../resources/tinyST.txt 
 * st.put: key:S, i:0
 * keys[]:[S, null], vals[]:[0, null]
 * st.put: key:E, i:1
 * keys[]:[E, S], vals[]:[1, 0]
 * st.put: key:A, i:2
 * keys[]:[A, E, S, null], vals[]:[2, 1, 0, null]
 * st.put: key:R, i:3
 * keys[]:[A, E, R, S], vals[]:[2, 1, 3, 0]
 * st.put: key:C, i:4
 * keys[]:[A, C, E, R, S, null, null, null], vals[]:[2, 4, 1, 3, 0, null, null, null]
 * st.put: key:H, i:5
 * keys[]:[A, C, E, H, R, S, null, null], vals[]:[2, 4, 1, 5, 3, 0, null, null]
 * st.put: key:E, i:6
 * keys[]:[A, C, E, H, R, S, null, null], vals[]:[2, 4, 6, 5, 3, 0, null, null]
 * st.put: key:X, i:7
 * keys[]:[A, C, E, H, R, S, X, null], vals[]:[2, 4, 6, 5, 3, 0, 7, null]
 * st.put: key:A, i:8
 * keys[]:[A, C, E, H, R, S, X, null], vals[]:[8, 4, 6, 5, 3, 0, 7, null]
 * st.put: key:M, i:9
 * keys[]:[A, C, E, H, M, R, S, X], vals[]:[8, 4, 6, 5, 9, 3, 0, 7]
 * st.put: key:P, i:10
 * keys[]:[A, C, E, H, M, P, R, S, X, null, null, null, null, null, null, null], vals[]:[8, 4, 6, 5, 9, 10, 3, 0, 7, null, null, null, null, null, null, null]
 * st.put: key:L, i:11
 * keys[]:[A, C, E, H, L, M, P, R, S, X, null, null, null, null, null, null], vals[]:[8, 4, 6, 5, 11, 9, 10, 3, 0, 7, null, null, null, null, null, null]
 * st.put: key:E, i:12
 * keys[]:[A, C, E, H, L, M, P, R, S, X, null, null, null, null, null, null], vals[]:[8, 4, 12, 5, 11, 9, 10, 3, 0, 7, null, null, null, null, null, null]
 * st.size():10
 * keys(), lo:A, hi:X
 * enqueue: i:0, keys[i]:A
 * enqueue: i:1, keys[i]:C
 * enqueue: i:2, keys[i]:E
 * enqueue: i:3, keys[i]:H
 * enqueue: i:4, keys[i]:L
 * enqueue: i:5, keys[i]:M
 * enqueue: i:6, keys[i]:P
 * enqueue: i:7, keys[i]:R
 * enqueue: i:8, keys[i]:S
 * keys(), queue size:10
 * A 8
 * C 4
 * E 12
 * H 5
 * L 11
 * M 9
 * P 10
 * R 3
 * S 0
 * X 7
 *
 * The {@code BST} class represents an ordered symbol table of generic
 * key-value pairs.
 * It supports the usual <em>put</em>, <em>get</em>,<em>contains</em>,
 * <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 * It also provides ordered methods for finding the <em>minimum</em>,
 * <em>maximum</em>, <em>floor</em>, <em>select</em>, and <em>ceiling</em>.
 * It also provides a <em>keys</em> method for iterating over all of the keys.
 * A symbol table implements the <em>associative array</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that 
 * values cannot be {@code null}-setting the value associated with a key to
 * {@code null} is equivalent to deleting the key from the symbol table.
 * <p>
 * This implementation uses a sorted array.It requires that the key type 
 * implements the {@code Comparable} interace and calls the {@code compareTo()}
 * method to compare two keys.It does not call either {@code equals()} or 
 * {@code hashCode()}.
 * The <em>put</em> and <em>remove</em> operations each take linear time in the
 * worst case; the <em>contains</em>, <em>ceiling</em>, <em>floor</em>, and 
 * <em>rank</em> operations take logarithmic time; the <em>size</em>, 
 * <em>is-empty</em>, <em>minimum</em>, <em>maximum</em>, and <em>select</em>
 * operations take constant time.Construction takes constant time.
 * <p>
 *
 * For additional documentation, see <a href="">Section3.1</a> of 
 * <i>Algorithms, 4ths Edition</i> by Robert Sedgewick and Kewin Wayne.
 * For other implementations, see {@link ST}, {@link BST},
 * {@link SequentialSearchST}, {@link RedBlackBST},
 * {@link SeparateChainingHashST}, and {@link LinearProbingHashST}.
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    /**
     * Initializes an empty symbol table.
     */
    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     * @param capacity the maximum capacity
     */
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    /**
     * Resize the underlying arrays
     */
    private void resize(int capacity) {
        assert capacity >= n;
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param Key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */ 
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key in this symbol table.
     * 
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        return null;
    }

    /**
     * Returns the number of keys in this symbol table strictly less than {@code key}.
     * @param key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the secified key (and its associated value) from this symbol table
     * if the specified value is {@code null}
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("key to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        //key is already in table
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            StdOut.println("keys[]:" + Arrays.toString(keys) + ", vals[]:" + Arrays.toString(vals));
            return;
        }

        // insert new key-value pair
        if (n == keys.length) {
            resize(2 * keys.length);
        }

        for (int j = n; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
        StdOut.println("keys[]:" + Arrays.toString(keys) + ", vals[]:" + Arrays.toString(vals));
        assert check();
    }

    /**
     * Removes the specified key and associated value from this symbol table
     * (if the key is in the symbol table).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key to delete() is null");
        }
        if (isEmpty()) {
            return;
        }

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || keys[i].compareTo(key) != 0) {
            return; 
        }

        for (int j = i; j < n-1; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        n--;
        keys[n] = null; // to avoid loitering
        vals[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length/4) {
            resize(keys.length/2);
        }
        assert check();
    }

    /**
     * Removes the smallest key and associated value from this symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow error");
        }
        delete(min());
    }

    /**
     * Removes the largest key and associated value from this symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
       if (isEmpty()) {
           throw new NoSuchElementException("Symbol table underflow error");
       }
       delete(max());
    }

    /**
     * Ordered symbol table methods
     */
    /**
     * Returns the smallest key in this symbol table
     * @return the smallest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("called min() with empty symbol table");
        }
        return keys[0];
    }

    /**
     * Returns the largest key in this symbol table.
     * @return the largest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("called max() with empty symbol table");
        }
        return keys[n-1];
    }

    /**
     * Return the kth smallest key in this symbol table.
     * @param k the order statistic
     * @return the {@code k}th smallest key in this symbol table
     * @throws IllegalArgumentException unless {@code k} is between 0 and 
     *         <em>n-1</em>
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument:" + k);
        }
        return keys[k];
    }        

    /**
     * Returns the largest key in this symbol table less than or equal to {@code key}.
     * @param key the key
     * @return the largest key in this symbol table less than or equal to {@code key}.
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        } else {
            return keys[i-1];
        }
    }

    /**
     * Returns the smallest key in this symbol table greater than or equal to {@code key}.
     *
     * @pram key the key
     * @return the smallest key in this symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }

        int i = rank(key);
        if (i == n) {
            return null;
        }
        return keys[i];
    }

    /**
     * Returns the number of keys in this symbol table in the specified range.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return the number of keys in this symbol table between {@code lo}
     *        (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *        is {@code null}
     */
    public int size(Key lo, Key hi) {
        if (lo == null || hi == null) {
            throw new IllegalArgumentException("key to size() is null");
        }
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for(Key key : st.keys())}.
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Returns all keys in this symbol table in the given range,
     * as an {@code Iterable}.
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in this symbol table between {@code lo}
     *          (inclusive) and {@code hi} (inclusive)
     *
     * @throws IllegalArgumentException if either {@code lo} or
     * {@code hi} is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        StdOut.println("keys(), lo:" + lo + ", hi:" + hi);
        if (lo == null || hi == null) {
            throw new IllegalArgumentException("key to keys() is null");
        }
        Queue<Key> queue = new Queue<Key>(); 
        if (lo.compareTo(hi) > 0) {
            return queue;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            StdOut.println("enqueue: i:" + i + ", keys[i]:" + keys[i]); 
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        StdOut.println("keys(), queue size:" + queue.size());
        return queue;
    }

    /**
     * Check internal invariants.
     */
    private boolean check() {
        return isSorted() && rankCheck();
    }

    private boolean isSorted() {
        for (int i = 1; i < size(); i++) {
            if (keys[i].compareTo(keys[i-1]) < 0) {
                return false;
            }
        }
        return true;
    }

    // check that rank(select(i)) = i
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (int i = 0; i < size(); i++) {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Unit tests the {@code BinarySearchST} data type.
     *
     */
    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            StdOut.println("st.put: key:"+ key + ", i:" + i);
            st.put(key, i);
        }
        StdOut.println("st.size():" + st.size());
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
