package org.ict.algorithm.search;

import java.util.NoSuchElementException;
import org.ict.algorithm.fundamentals.Queue;

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

        public Node(Key key, Value val, boolean color, int size) {
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
         if (isRed(h.right) && !isRed(h.left)) {
             h = rotateLeft(h);    
         }
         if (isRed(h.left) && isRed(h.left.left)) {
             h = rotateRight(h);
         }
         if (isRed(h.left) && isRed(h.right)) {
            flipColors(h); 
         }
         h.size = size(h.left) + size(h.right) + 1;

         return h;
     }
    
     /*********************************************
      * Red-black tree deletion.
      ********************************************/

     /**
      * Removes the smallest key and associated value from the symbol table.
      * @throws NoSuchElementException if the symbol table is empty
      */
     public void deleteMin() {
         if (isEmpty()) {
             throw new NoSuchElementException("BST underflow");
         }
         // if both children of root are black, set root to red
         if (!isRed(root.left) && !isRed(root.right)) {
             root.color = RED;
         }
         root = deleteMin(root);
         if (!isEmpty()) {
             root.color = BLACK;
         }
     }

     //delete the key-value pair with the minimum key rooted at h
     private Node deleteMin(Node h) {
         if (h.left == null) {
             return null;
         }

         if (!isRed(h.left) && !isRed(h.left.left)) {
             moveRedLeft(h);
         }

         h.left = deleteMin(h.left);

         return balance(h);
     }

     /**
      * Removes the largest key and associated value from the symbol table.
      * @throws NoSuchElementException if the symbol table is empty.
      */
     public void deleteMax() {
         if (isEmpty()) {
             throw new NoSuchElementException("BST underflow");
         }
         
         //if both children of root are black, set root to red
         if (!isRed(root.left) && !isRed(root.right)) {
             root.color = RED;
         }
         root = deleteMax(root);
         if (!isEmpty()) {
             root.color = BLACK;
         }
     }

     // delete the key-value pair with the maximum key rooted at h
     private Node deleteMax(Node h) {
         if (isRed(h.left)) {
            h = rotateRight(h);
         }

         if (h.right == null) {
            return null;
         }

         if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
         }

         h.right = deleteMax(h.right);

         return balance(h);
     }

     /**
      * Removes the specified key and its associated value from this symbol table
      * (if the key is in this symbol table)
      * @param key the key
      * @throws IllegalArgumentException if {@code key} is {@code null}
      */
     public void delete(Key key) {
         if (key == null) {
             throw new IllegalArgumentException("argument to delete() is null");
         }
         if (!contains(key)) {
             return;
         }

         // if both children of root are black, set root to red
         if (!isRed(root.left) && !isRed(root.right)) {
             root.color = RED;
         }

         root = delete(root, key);
         if (!isEmpty()) {
             root.color = BLACK;
         }
         // assert check();
     }

     // delete the key-value pair with the given key rooted at h
     private Node delete(Node h, Key key) {
         // assert get(h, key) != null   
         if (key.compareTo(h.key) < 0) {
             //
             if (!isRed(h.left) && !isRed(h.left.left)) {
                 h = moveRedLeft(h);
             }

             h.left = delete(h.left, key);
         } else {
             if (isRed(h.left)) {
                 h = rotateRight(h);
             }

             if (key.compareTo(h.key) == 0 && (h.right == null)) {
                 return null;
             }

             if (!isRed(h.right) && !isRed(h.right.left)) {
                 h = moveRedRight(h);
             }

             if (key.compareTo(h.key) == 0) {
                 Node x = min(h.right);
                 h.key = x.key;
                 h.val = x.val;
                 h.right = deleteMin(h.right);
             } else {
                 h.right = delete(h.right, key);
             }
         }
         return balance(h);
     }

     /**********************************************
      * Red-black tree helper functions
      *********************************************/

     // make a right-leaning link lean to the left 
     private Node rotateLeft(Node h) {
         Node x = h.right;
         h.right = x.left;
         x.left = h;
         // means x.color = h.color
         x.color = x.left.color;
         //means h.color = RED
         x.left.color = RED;
         x.size = h.size;
         h.size = size(h.left) + size(h.right) + 1;
         return x;
     }

     // make a left-leaning link to the right
     private Node rotateRight(Node h) {
         Node x = h.left;
         h.left = x.right;
         x.right = h;
         // means x.color = h.color
         x.color = x.right.color;
         // means h.color = RED
         x.right.color = RED;
         x.size = h.size;
         h.size = size(h.left) + size(h.right) + 1;
         return x;
     }

     // flip the colors of a node and its two children
     private void flipColors(Node h) {
         // h must have opposite color of its two children
         // means h.color = RED
         h.color = !h.color;
         // means h.left.color = BLACK
         h.left.color = !h.left.color;
         // means h.right.color = BLACK;
         h.right.color = !h.right.color;
     }

     // Assuming that h is red and both h.left and h.left.left
     // are black, make h.left or one of its children red.
     private Node moveRedLeft(Node h) {
         // assert (h != null);
         // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);
         
         flipColors(h);
         if (isRed(h.right.left)) {
             h.right = rotateRight(h.right);
             h = rotateLeft(h);
             flipColors(h);
         }
         return h;
     }
     
     // Assuming that h is red and both h.right and h.right.left
     // are black, make h.right or one of its children red.
     private Node moveRedRight(Node h) {
         // assert (h != null);
         // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
         flipColors(h);
         if (isRed(h.left.left)) {
             h = rotateRight(h);
             flipColors(h);
         }
         return h;
     }

     // restore red-black tree invariant
     private Node balance(Node h) {
         if (isRed(h.right)) {
             h = rotateLeft(h);
         }
         if (isRed(h.left) && isRed(h.left.left)) {
             h = rotateRight(h);
         }
         if (isRed(h.left) && isRed(h.right)) {
             flipColors(h);
         }
         h.size = size(h.left) + size(h.right) + 1; 
         return h;
     }
        
     /******************************************************
      * Utility functions
      *****************************************************/
     /**
      * Returns the height of the BST (a 1-node tree has height 0) 
      */
     public int height() {
         return height(root);
     }

     private int height(Node x) {
         if (x == null) {
             return -1;
         }
         return 1 + Math.max(height(x.left), height(x.right));
     }
     
     /******************************************************
      * Ordered symbol table methods
      *****************************************************/

     /**
      * Returns the smallest key in the symbol table.
      * @return the smallest key in the symbol table
      * @throws NoSuchElementException if the symbol table is empty
      */
     public Key min() {
         if (isEmpty()) {
             throw new NoSuchElementException("calls min() with empty symble table");    
         }
         return min(root).key;
     }

     // the smallest key in subtree rooted at x; null if no such key
     private Node min(Node x) {
         // assert x != null;
         if (x.left == null) {
             return null;
         } else {
             return min(x.left);
         }
     }

     /**
      * Returns the largest key in the symbol table.
      * @return the largest key in the symbol table
      * @throws NoSuchElementException if the symbol table is empty
      */
     public Key max() {
         if (isEmpty()) {
             throw new NoSuchElementException("calls max() with empty symbol table");
         }
         return max(root).key;
     }

     // the largest key in the subtree rooted at x, null if no such key
     private Node max(Node x) {
         // assert x != null;
         if (x.right == null) {
             return null;
         } else {
             return max(x.right);
         }
     }

     /**
      * Returns the largest key in the symbol table less than or equal to {@code key}.
      * @param key the key
      * @return the larget key in the symbol table less than or equal to {@code key}
      * @throws NoSuchElementException if there is no such key
      * @throws IllegalArgumentException if {@code key} is {@code null}
      */
     public Key floor(Key key) {
         if (key == null) {
             throw new IllegalArgumentException("argument to floor() is null");
         }
         if (isEmpty()) {
             throw new NoSuchElementException("calls floor() with empty symbol table");
         }
         Node x = floor(root, key);
         return (x == null ? null : x.key);
     }

     // the largest key in the subtree rooted at x less than or equal to the given key
     private Node floor(Node x, Key key) {
         if (x == null) {
             return null;
         }
         int cmp = key.compareTo(x.key);
         if (cmp == 0) {
             return x;
         }
         if (cmp < 0) {
             return floor(x.left, key);
         } 
         Node t = floor(x.right, key);         

         return (t == null ? x : t);
     }
    
     /**
      * Returns the smallest key in the symbol table greater than or equal to {@code key}.
      * @param key the key
      * @return the smallest key in the symbol table greater than or equal to {@code key}
      * @throws NoSuchElementException if there is no such key
      * @throws IllegalArgumentException if {@code key} is {@code null}
      */
     public Key ceiling(Key key) {
         if (key == null) {
             throw new IllegalArgumentException("argument to ceiling() is null"); 
         }
         if (isEmpty()) {
             throw new NoSuchElementException("calls ceiling with empty symbol table");
         }
         Node x = ceiling(root, key);
         return (x == null ? null : x.key);
     }

     // the smallest key in the subtree rooted at x greater than or equal to the given key
     private Node ceiling(Node x, Key key) {
         if (x == null) {
             return null;
         }
         int cmp = key.compareTo(x.key);
         if (cmp == 0) {
             return x;
         }
         if (cmp > 0) {
             return ceiling(x.right, key);
         }
         Node t = ceiling(x.left, key);

         return (t == null ? x : t);
     }

     /**
      * Return the key in the symbol table whose rank is {@code k}.
      * This is the (k+1)st smallest key in the symbol table.
      * 
      * @param k the order statistic
      * @return the key in the symbol table of rank {@code k}
      * @throws IllegalArgumentException unless {@code k} is between 0 and 
      * <em>n-1</em>
      */
     public Key select(int k) {
         if (k < 0 || k >= size()) {
             throw new IllegalArgumentException("argument to select() is invalid:" + k);
         }
         Node x = select(root, k);
         return x.key;
     }

     // the key of rank k in the subtree rooted at x
     private Node select(Node x, int k) {
         // assert x != null;
         // assert k > 0 && k < size(x);
         int t = size(x.left);
         if (t > k) {
             return select(x.left, k);
         } else if (t < k) {
             return select(x.right, k-t-1);
         } else {
             return x;
         }
     }

     /**
      * Return the number of keys in the symbol table strictly less than {@code key}.
      * @param key the key
      * @return the number of keys in the symbol table strictly less than {@code key}
      * @throws IllegalArgumentException if {@code key} is {@code null}
      */
     public int rank(Key key) {
         if (key == null) {
             throw new IllegalArgumentException("argument to rank() is null");
         }
         return rank(key, root);
     }

     // number of keys less than key in the subtree rooted at x
     private int rank(Key key, Node x) {
         if (x == null) {
             return 0;
         }
         int cmp = key.compareTo(x.key);
         if (cmp < 0) {
             return rank(key, x.left);
         } else if (cmp > 0) {
             return 1 + size(x.left) + rank(key, x.right);
         } else {
             return size(x.left);
         }
     }

     /*********************************************************
      * Range count and range search.
      */

     /**
      * Returns all keys in the symbol table as an {@code Iterable}.
      * To iterate over all of the keys in the symbol table named {@code st},
      * use the foreach notation: {@code for (Key key : st.keys())}.
      * @return all keys in the symbol table as an {@code Iterable}
      */
     public Iterable<Key> keys() {
         if (isEmpty()) {
             return new Queue<Key>();
         }
         return keys(min(), max());
     }
     
     /**
      * Returns all keys in the symbol table in the given range,
      * as an {@code Iterable}
      * @param lo minimum endpoint
      * @param hi maximum endpoint
      * @return all keys in the symbol table between {@code lo}
      *   (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
      * @throws IllegalArgumentException if either {@code lo} or {@code hi}
      *   is {@code null}
      */
     public Iterable<Key> keys(Key lo, Key hi) {
         if (lo == null) {
             throw new IllegalArgumentException("first argument to keys() is null");
         }
         if (hi == null) {
             throw new IllegalArgumentException("second argument to keys() is null");
         }
         Queue<Key> queue = new Queue<Key>();
         // if (isEmpty() || lo.compareTo(hi) > 0) return queue; 
         keys(root, queue, lo, hi);
         return queue;
     }

     // add the keys between lo and hi in the substree rooted at x to the queue
     private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
         if (x == null) {
             return;
         }
         int cmplo = lo.compareTo(x.key);
         int cmphi = hi.compareTo(x.key);
         if (cmplo < 0) {
             keys(x.left, queue, lo, hi);
         }
         if (cmplo <= 0 && cmphi >= 0) {
             queue.enqueue(x.key);
         }
         if (cmphi > 0) {
             keys(x.right, queue, lo ,hi);
         }
     }
      
     /**
      * Returns the number of keys in the symbol table in the given range.
      * 
      * @param lo minimum endpoint
      * @param hi maximum endpoint
      * @return the number of keys in the symbol table between {@code lo}
      *   (inclusive) and {@code hi} (inclusive)
      * @throws IllegalArgumentException if either {@code lo} or {@code hi}
      *   is {@code null}
      */
      public int size(Key lo, Key hi) {
          if (lo == null) {
              throw new IllegalArgumentException("first argument to size() is null");
          }
          if (hi == null) {
              throw new IllegalArgumentException("second argument to size() is null");
          }
          if (lo.compareTo(hi)) {
              return 0;
          }
          if (contains(hi)) {
              return rank(hi) - rank(lo) + 1;
          } else {
              return rank(hi) - rank(lo);
          }
      }
      
      /**
       * Check integrity of red-black tree data structure.
       */
      private boolean check() {
         //
         return false;
      }

      // Does this binary tree satisfy symmetric order?
      // Note: this test also ensures that data structure is a binary tree since order is strict
      private boolean isBST() {
         return isBST(root, null, null);
      }

      // Is the tree rooted at x a BST with all keys strictly between min and max
      // (if min or max is null, treat as empty constraint)
      // Credit: Bob Dondero's elegant solution
      private boolean isBST(Node x, Key min, Key max) {
         if (x == null) {
             return true;
         }
         if (min != null && x.key.compareTo(min) <= 0) {
             return false;
         }
         if (max != null && x.key.compareTo(max) >= 0) {
             return false;
         }
         return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
      }

      // are the size fields correct?
      private boolean isSizeConsistent() {
         return isSizeConsistent(root);
      }

      private boolean isSizeConsistent(Node x) {
         if (x == null){
             return true;
         }
         if (x.size != size(x.left) + size(x.right) + 1) {
             return false;
         }
         return isSizeConsistent(x.left) && isSizeConsistent(x.right);
      }

    

}
