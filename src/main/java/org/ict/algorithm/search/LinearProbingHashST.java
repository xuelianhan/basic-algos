package org.ict.algorithm.search;

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


}
