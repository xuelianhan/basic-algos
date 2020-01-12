package org.ict.algorithm.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * The {@code ST} class represents an ordered symbol table of generic key-value pairs.
 * It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 * <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 * It also provides ordered methods for finding the <em>minimum</em>,
 * <em>maximum</em>, <em>floor</em>, and <em>ceiling</em>.
 * It also provides a <em>keys</em> method for iterating over all of the keys.
 * A symbol table implements the </em>associative array</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that
 * values cannot be {@code null}---setting the value associated with a key
 * to {@code null} is equivalent to deleting the key from the symbol table.
 * <p>
 * It requires that the key type implements the {@code Comparable} interface and calls the
 * {@code compareTo()} and method to compare two keys. It does not call either
 * {@code equals()} or {@code hashCode()}.
 * <p>
 * This implementation use a <em>red-black BST</em>.
 * The <em>put</em>, <em>get</em>, <em>contains</em>, <em>remove</em>,
 * <em>minimum</em>, <em>maximum</em>, <em>ceiling</em>, and<em>floor</em>
 * operations each take the (log <em>n</em>) time in the worst case,
 * where <em>n</em> is the number of key-value pairs in the symbol table.
 * The <em>size</em> and <em>is-empty</em> operations take the O(1) time.
 * Construction takes the O(1) time.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 * @param <Key> the generic type of keys in this symbol table
 * @param <Value> the generic type of values in this symbol table
 */
public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    /**
     * Initializes an empty symbol table.
     */
    public ST() {
        st = new TreeMap<Key, Value>();
    }

    /**
     * Returns the value associated with the given key in this symbol table.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in this symbol table;
     *         {@code null} if the key is not in this symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null }
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null key");
        }
        return st.get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        if (val == null) {
            st.remove(key);
        } else {
            st.put(key, val);
        }
    }

    public Value remove(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove() with null key");
        }
        return st.remove(key);
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls contains() with null key");
        }
        return st.contains(key);
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return st.lastKey();
    }

    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        Key k = st.ceilingKey(key);
        if (k == null) {
            throw new NoSuchElementException("argument to ceiling() is too large");
        }
        return k;
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        Key k = st.floorKey(key);
        if (k == null) {
            throw new NoSuchElementException("argument to floor() is too large");
        }
        return k;
    }

    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s: st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
