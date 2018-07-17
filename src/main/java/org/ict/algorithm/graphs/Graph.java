package org.ict.algorithm.graphs;

import java.util.NoSuchElementException;

import org.ict.algorithm.fundamentals.Bag;
import org.ict.algorithm.fundamentals.Stack;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;

/**
 * A graph, implemented using an array of sets.
 * Parallel edges and self-loops allowed.
 * The {@code Graph} class represents an undirected graph of vertices
 * named 0 through <em>V</em> - 1.
 * It supports the following two primary operations: add an edge to the graph,
 * iterate over all of the vertices adjacent to a vertex. It also provides
 * methods for returning the number of vertices <em>V</em> and the number 
 * of edges <em>E</em>. Parallel edges and self-loops are permmitted.
 * By convention, a self-loop <em>v</em>-<em>v</em> appears in the 
 * adjacency list of <em>v</em> twice and contributes two to the degree
 * of <em>v</em>.
 * <p>
 * This implementation uses an adjacency-lists representation, which
 * is a vertex-indexed array of {@link Bag} objects.
 * All operations take constant time (in the worst case) except
 * iterating over the vertices adjacent to a given vertex, which takes
 * time proportional to the number of such vertices.
 * <p>
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;

    private int E;

    private Bag<Integer> adj;

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     * 
     * @param V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Graph(int v) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[])new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a graph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @param in the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is the wrong format
     */
    public Graph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) {
                throw new IllegalArgumentException("Number of vertices in a Graph must be nonnegative");
            }
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) {
                throw new IllegalArgumentException("Number of edges in a Graph must be nonnegative");
            }
            for (int i = 0; i < E; i++) {

            }
        } catch(NoSuchElementException e) {
            throw new IllegalArgumentException("Invalid input format in Graph constructor", e);
        }
    }
}
