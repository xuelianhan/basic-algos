package org.ict.algorithm.search;

import org.ict.algorithm.util.StdOut;

/**
 * The {@code SparseVector} class represents a <em>d</em>-dimensional mathematical vector.
 * Vectors are mutable: their values can be changed after they are created.
 * It includes methods for addition, subtraction, dot product, scalar product, unit vector
 * and Euclidean norm.
 * <p>
 * The implementation is a symbol table of indices and values for which the vector
 * coordinates are nonzero. This makes it efficient when most of the vector coordinates
 * are zero.
 * <p>
 * See also {@link Vector} for an immutable (dense) vector data type. 
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class SparseVector {
    private static final double zero = 0.0;
    // dimension
    private int d;
    // the vector, represented by index-value pairs
    private ST<Integer, Double> st;

    /**
     * Initializes a d-dimensional zero vector.
     * @param d the dimension of the vector
     */
    public SparseVector(int d) {
        this.d = d;
        this.st = new ST<Integer, Double>();
    }

    /**
     * Sets the ith coordinate of this vector to the specified value.
     *
     * @param i the index
     * @param value the new value
     * @throws IllegalArgumentException unless i is between 0 and d-1
     */
    public void put(int i , double value) {
        if (i < 0 || i > d) {
            throw new IllegalArgumentException("Illegal index");
        }
        if (value == zero) {
            st.delete(i);
        } else {
            st.put(i, value);
        }
    }

    /**
     * Returns the ith coordinate of this vector.
     * 
     * @param i the index
     * @return the value of the ith coordinate of this vector
     * @throws IllegalArgumenteException unless i is between 0 and d-1
     */
    public double get(int i) {
        if (i < 0 || i >= d) {
            throw new IllegalArgumentException("Illegal index");
        }
        if (st.contains(i)) {
            return st.get(i);
        } else {
            return zero;
        }
    }

    /**
     * Returns the number of nonzero entries in this vector
     * @return the number of nonzero entries in this vector
     */
    public int nnz() {
        return st.size();
    }

    /**
     * Returns the dimension of this vector.
     * 
     * @return the dimension of this vector
     */
    public int dimension() {
        return d;
    }

    /**
     * Returns the linear product of this vector with the specified vector.
     *
     * @param that the other vector
     * @return the dot product between this vector and that vector
     * @throws IllegalArgumentException if the lengths of the two vectors are not equal
     */
    public double dot(SparseVector that) {

    }
}
