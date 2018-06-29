package org.ict.algorithm.search;

import org.ict.algorithm.fundamentals.Queue;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 * The {@code LinearProbingHashST} class represents a symbol table of generic
 * key-value pairs.
 *
 * It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>, 
 * <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 * It also provides a <em>keys</em> method for iterating over all of the keys.
 * A symbol table implements the <em>associative</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that 
 * values cannot be {@code null}--setting the value associated with a key to
 * {@code null} is equivalent to deleting the key from the symbol table.
 * <p>
 * This implementation uses a linear probing hash table. It requires that the
 * key type overrides the {@code equals()} and {@code hashCode()} methods.
 * The expected time per <em>put</em>, <em>contains</em>, or  <em>remove</em>,
 * operation is constant, subject to the uniform hashing assumption.
 * The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 *
 * For other implementations, see {@link ST}, {@link BinarySearchST},
 * {@link SequentialSearchST}, {@link BST}, {@link RedBlackBST}, and 
 * {@link SeparateChainingHashST}
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    // number of key-value pairs in the symbol table
    private int n; 
    // size of linear probing table
    private int m;
    // the keys
    private Key[] keys;
    // the values
    private Value[] vals;
    
    /**
     * Initializes an empty symbol tables.
     */
    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     *
     * @param capacity the initial capacity
     */
    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[])new Object[m];
        vals = (Value[])new Object[m]; 
    }

    /**
     * Returns the number of key-value pairs in this symbol table
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns true if this symbol table is empty
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }
    
    // hash function for keys, returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // resizes the hash table to the given capacity by rehashing all of the keys
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key
     * Deletes the specified key (and its associated value) from this symbol table 
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

        // double table size if 50% full
        if (n >= m/2) {
            resize(2*m);
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
           // keys[i] != null means the collision has happened, so i will skip to 
           // the next position (i + 1) % m
           if (keys[i].equals(key)) {
                vals[i] = val;
                return;
           }
        }

        // if keys[i] == null, means no collision, put entry directly.
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * Returns the value associated with the specified key.
     * @param key the key
     * @return the value associated with {@code key};
     *         {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key to get() is null");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    /**
     * Removes the specified key ant its associated value from this symbol table
     * (if the key is in this symbol table)
     *
     * @param key the key
     * @param IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key to delete() is null");
        }
        if (!contains(key)) {
            return; 
        }

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m; 
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        n--;

        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m/8) {
            resize(m/2);
        }
        assert check();
    }

    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symble table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }
    
    // in
}
