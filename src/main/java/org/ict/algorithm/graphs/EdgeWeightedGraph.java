package org.ict.algorithm.graph;

import org.ict.algorithm.fundamentals.Bag;


/**
 * The {@code EdgeWeightedGraph} class represents an edge-weighted
 * graph of vertices named 0 through <em>V</em> - 1, where each 
 * undirected edge is of type {@link Edge} and has a real-valued weight.
 * It supports the following two primary operations: add an edge to
 * the graph,
 *
 */
public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;

    private int E;

    private Bag<Edge>[] adj;

    /**
     * Initializes an empty edge-weighted graph with {@code V} vertices and 0 edges.
     *
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedGraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    /**
     * Initializes a random edge-weighted graph with {@code V} vertices and <em>E</em> edges.
     *
     * @param V the number of vertices
     * @param E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public EdgeWeightedGraph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        }
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }
    
}
