package org.ict.algorithm.search;

/**
 * A symbol table implemented with a binary search tree using 
 * iteration instead of recursion for put(), get(), and keys().
 *
 * @author Robert Sedgewick 
 * @author Kevin Wayne
 *
 */
public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
    // root of BST
    private Node root;

    private class Node {
        private Key key;// sorted by key
        private Value val;// associated value
        private Node left, right;// left and right subtrees

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * Insert key-value pair into symbol table (nonrecursive version).
     */
    public void put(Key key, Value val) {
        Node z = new Node(key, val);
        if (root == null) {
            root = z;
            return;
        }
        Node parent = null, x = root;
        while (x != null) {
            
        }
    }
}
