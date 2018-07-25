package org.ict.algorithm.graphs;

import org.ict.algorithm.util.StdOut;
/**
 * $ javac org/ict/algorithm/graphs/DirectedEdge.java
 * $ java org/ict/algorithm/graphs/DirectedEdge
 * 12->34  5.67
 *
 * The {@code DirectedEdge} class represents a weighted edge in an 
 * {@link EdgeWeightedDigraph}.Each edge consists of two integers
 * (naming the two vertices) and a real-value weight. The data
 * type provides methods for accessing the two endpoints of the
 * directed edge and the weight. 
 *
 * @author Sedgewick
 * @author Kevin Wayne
 */
public class DirectedEdge {

    private final int v;

    private final int w;

    private final double weight;

    /**
     * Initializes a directed edge from vertex {@code v} to vertex {@code w}
     * with the given {@code weight}.
     *
     * @param v the tail vertex
     * @param w the head vertex
     * @param weight the weight of the directed edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *         is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public DirectedEdge(int v, int w, double weight) {
        if (v < 0) {
            throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        }
        if (w < 0) {
            throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        }
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("Weight is NaN");
        }
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Returns the weight of the directed edge.
     * @return the weight of the directed edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns the tail vertex of the directed edge.
     * 
     * @return the tail vertex of the directed edge
     */
    public int from() {
        return v;
    }

    /**
     * Returns the head vertex of the directed edge. 
     *
     * @return the head vertex of the directed edge 
     */
    public int to() {
        return w;
    }

    /**
     * Returns a string representation of the directed edge.
     *
     * @return a string representation of the directed edge
     */
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }

    /**
     * Unit tests the {@code Edge} data type.
     */
    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        StdOut.println(e);
    }

}
