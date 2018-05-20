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
}
