package org.ict.algorithm.search;

import java.util.NoSuchElementException;
/**
 * The {@code BST} class represents an ordered symbol table of generic
 * key-value pairs
 * It supports the usual 
 * <em>put</em>, <em>get</em>, <em>contains</em>, <em>delete</em>  
 * <em>size</em>, and <em>is-empty</em> methods
 * It also provides ordered methods for finding the <em>minimum</em>,
 * <em>maxmium</em>, <em>floor</em>, and <em>ceiling</em>
 * It also provides a <em>keys</em> method for iterating over all of
 * the keys
 * A symbol table implements the <em>associative array</em> abstraction:
 * when associating a value with  a key tha t is already in the symbol table,
 * the convention is to replace the old value with the new value
 * Unlike {@link java.util.Map}, this class uses the convention that 
 * values cannot be {@code null}--setting the value associated with a key to
 * {@code null} is equivalent to deleting the key from the symbol table
 * <p>
 * This implementation uses a left-leaning red-black BST
 * It requires that the key type implements the {@code Comparable} interface
 * and calls the {@code compareTo()} method to compare two keys
 * It does not call either {@code equals()} or {@code hashCode()}.
 * The <em>put</em>, <em>contains</em>, <em>remove</em>, <em>minimum</em>
 * <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> operations each 
 * take logarithmic time in the worst case, if the tree becomes unbalanced.
 * The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 *
 * Fro other implementations of the same API, see {@link ST}, {@link BinarySearchST},
 * {@link SequentialSearchST}, {@link BST}, {@link SeparateChainingHashST},
 * {@linke LinearProbingHashST}, and {@link AVLTreeST} see Page 452.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;

    private static final boolean BLACK = false; 

    // root of the BST
    private Node root;

    // BST helper node data type
    private class Node {
        // key
        private Key key;
        // associated data
        private Value val;
        // links to left and right subtrees
        private Node left, right;
        // color of parent link
        private boolean color;
        // subtree count
        private int size;

        public Node() {
           this.key = key;
           this.val = val;
           this.color = color;
           this.size = size;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public RedBlackBST() {}

    /**
     * Node helper methods.
     */
    // is node x red; false if x is null?
    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    // number of node in subtree rooted at x; 
    // 0 if x is null
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     * Is this symbol table empty?
     * @return {@code true} if this symbol table is empty and
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Standard BST search.
     */
    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in 
     * the symbol table and {@code null} if the key is not in the symbol
     * table 
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key to get() is null");
        }
        return get(root, key);
    } 

    // value associated with the given key in subtree rooted at x; 
    // null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and 
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
     public boolean contains(Key key) {
        return get(key) != null;
     }

     /**
      * Red-black tree insertion.
      */
     /**
      * Inserts the specified key-value pair into the symbol table,
      * overwriting the old value with the new value if the symbol 
      * table already contains the specified key
      * Deletes the specified key (and its associated value) from 
      * this symbol table if the specified value is {@code null}
      *
      * @param key the key
      * @param val the value
      * @throws IllegalArgumentException if {@code key} is {@code null}
      */
     public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;
     }

     // insert the key-value pair in the subtree rooted at h
     private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, RED, 1);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }

        //fix-up any right-leaning links
        // todo
        //
        return h;
     }
}
