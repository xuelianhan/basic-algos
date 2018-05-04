package org.ict.algorithm.search;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * The {@code ST} class represents an ordered symbol table of generic 
 * key-value pairs.
 * It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 * <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods. 
 * It also provides ordered methods for finding the <em>minimum</em>, 
 * <em>maximum</em>, <em>floor</em>, and <em>ceiling</em>.
 *
 * It also provides a <em>keys</em> method for iterating over all of the
 * keys.
 * A symbol table implements the <em>associative array</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that 
 * values cannot be {@code null}-setting the value associated with a key to
 * {@code null} is equivalent to deleting the key from the symbol table.
 * <p>
 * This implementation uses a balanced binary search tree. It requires that
 * the key type implements the {@code Comparable} interface and calls the 
 * {@code compareTo()} and method to compare two keys. It does not call 
 * either {@code equals()} or {@code hashCode()}.
 * The <em>put</em>, <em>contains</em>, <em>remove</em>, <em>minimum</em>, 
 * <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> operations each
 * take logarithmic time in the worst case.
 *
 * The<em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 *
 * @param <Key> the generic type of keys in this symbol table
 * @param <Value> the generic type of keys in this symbol table
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
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
     * @return the value associated with the given key if the key is in this
     *         symbol table;
     *         {@code null} if the key is not in this symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null key");
        }
        return st.get(key);
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the 
     * old value with the new value if the symbol table already contains the specified
     * key.Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}
     */
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

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls delete() with null key");
        }
        st.remove(key);
    }

    /**
     * Returns true if this symbol table contain the given key.
     * 
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and 
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls contains() with null key");
        }
        return st.containsKey(key);
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return st.size();
    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns all keys in this symbol table.
     * <p>
     * To iterate over all of the keys in the symbol table named {@code st}.
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        return st.keySet();
    }

    /**
     * @return an iterator to all of the keys in this symbol table
     * @deprecated replaced by {@link #keys()}.
     */
    @Override
    @Deprecated
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    /**
     * Returns the smallest key in this symbol table.
     * 
     * @return the smallest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty() symbol table");
        }
        return st.firstKey();
    }

}
