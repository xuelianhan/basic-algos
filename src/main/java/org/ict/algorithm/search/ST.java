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
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {


}
