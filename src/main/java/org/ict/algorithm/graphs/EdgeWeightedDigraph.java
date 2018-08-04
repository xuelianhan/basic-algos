package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Bag;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.StdRandom;


/**
 * The {@code EdgeWeightedDigraph} class represents a edge-weighted
 * digraph of vertices named 0 through <em>V-1</em>, where each
 * directed edge is of type {@link DirectedEdge} and has a real-valued
 * weight.
 * It supports the following two primary operations: add a directed 
 * edge to the digraph and iterate over all of edges incident from a
 * given vertex.
 * It also provides methods for returning the number of vertices
 * <em>V</em> and the number of edges <em>E</em>. Parallel edges and
 * self-loops are permitted.
 * <p>
 * This implementation uses an adjacency-lists representation, which
 * is a vertex-indexed array of {@link Bag} objects.
 * All operations take constant time (in the worst case) except 
 * iterating over the edges incident from a given vertex, which takes
 * time proportional to the number of such edges.
 * <p>
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    
    // number of vertices in this digraph
    private final int V;

    // number of edges in this digraph
    private int E; 

    // adj[v] = adjacency list for vertex v
    private Bag<DirectedEdge>[] adj;

    // indegree[v] = indegree of vertex v
    private int[] indegree;

    /**
     * Initializes an empty edge-weighted digraph with {@code V} vertices and 0 edges.
     * 
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedDigraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    /**
     * Initializes a random edge-weighted digraph with {@code V} vertices and <em>E</em> edges.
     *
     * @param V the number of vertices
     * @param E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public EdgeWeightedDigraph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
        }
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    /**
     *
     */
}
