package org.ict.algorithm.search;

import java.util.NoSuchElementException;
import org.ict.algorithm.fundamentals.Queue;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;


/**
 * The {@code BST} class represents an ordered symbol table of generic
 * key-value pairs
 * It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,  
 * <em>delete</em>, <em>size</em>, and <em>isEmpty</em> methods
 * It also provides ordered methods for finding the <em>minimum</em>, 
 * <em>maximum</em>, <em>floor</em>, <em>select</em>, <em>ceiling</em>
 * It also provides a <em>keys</em> method for iterating over all of the
 * keys
 * A symbol table implements the <em>associative array</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is replace the old value with the new value
 * Unlike {@link java.util.Map}, this class uses the convention that values
 * connot be {@code null}--setting the value associated with a key to 
 * {@code null} is equivalent to deleting the key from the symbol table
 * <p>
 * This implementation uses an (unbalanced) binary search tree
 * It requires that the key type implements the {@code Comparable} interface
 * and calls the {@code compareTo()} method to compare two keys
 * It does not call either{@code equals()} or {@code hashCode()}
 *
 * The <em>put</em>, <em>contains</em>, <em>remove</em>, <em>minimum</em>,
 * <em>maximum</em>, <em>ceiling</em>, <em>floor</em>, <em>select</em>, and 
 * <em>rank</em> operations each take linear time in the worst case,
 * if the tree becomes unbalanced
 * The <em>size</em>, and <em>isEmpty()</em> operations take constant time
 * Construction takes constant time
 *
 * For other implementation, see {@link ST}, {@link BinarySearchST},
 * {@link SequentialSearchST}, {@link RedBlackBST},
 * {@link SeparateChainingHashST}, and {@link LinearProbingHashST}
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class BST<Key extends Comparable<Key>, Value> {
    // root of BST
    private Node root;

    private class Node {
        // sorted by key
        private Key key;

        // associated data
        private Value val;

        //left and right subtrees
        private Node left, right;

        // number of nodes in subtree, include current node itself.
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * Initializes an empty symbol table
     */
    public BST() {}

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x    
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and 
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     * 
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key to get(Node, Key) is null");        
        }
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
           return get(node.left, key); 
        } else if (cmp > 0) {
           return get(node.right, key); 
        } else {
            return node.val;
        }
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     * 
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("key to put(Key, Value) is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        assert check();
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }
        node.size = 1 + size(node.left) + size(node.right); 
        return node;
    }

    /**
     * Removes the smallest key and associated value from the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow");
        }
        root = deleteMin(root);
        assert check();
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Removes the largest key and associated value from the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        
    }
}
