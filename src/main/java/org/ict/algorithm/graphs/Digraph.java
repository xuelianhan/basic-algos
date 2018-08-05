package org.ict.algorithm.graphs;

import java.util.NoSuchElementException;
import org.ict.algorithm.fundamentals.Bag;
import org.ict.algorithm.fundamentals.Stack;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;


/**
 * $ javac org/ict/algorithm/graphs/Digraph.java
 * $ cat ../resources/tinyDG.txt 
 * 13
 * 22
 * 4  2
 * 2  3
 * 3  2
 * 6  0
 * 0  1
 * 2  0
 * 11 12
 * 12  9
 * 9 10
 * 9 11
 * 7  9
 * 10 12
 * 11  4
 * 4  3
 * 3  5
 * 6  8
 * 8  6
 * 5  4
 * 0  5
 * 6  4
 * 6  9
 * 7  6
 * $ java org/ict/algorithm/graphs/Digraph ../resources/tinyDG.txt 
 * 13 vertices, 22 edges 
 * 0: 5 1 
 * 1: 
 * 2: 0 3 
 * 3: 5 2 
 * 4: 3 2 
 * 5: 4 
 * 6: 9 4 8 0 
 * 7: 6 9 
 * 8: 6 
 * 9: 11 10 
 * 10: 12 
 * 11: 4 12 
 * 12: 9 
 *
 *
 * The {@code Digraph} class represents a directed graph of vertices
 * named 0 through <em>V</em> - 1.
 * It supports the following two primary operations: add an edge to the 
 * digraph, iterate over all of the vertices adjacent from a given vertex.
 * Parallel edges and self-loops are permitted.
 * <p>
 *
 * This implementation uses an adjacency-lists representation, which is
 * a vertex-indexed array of {@link Bag} objects.
 * All operations take constant time (in the worst case) except 
 * iterating over the vertices adjacent from a given vertex, which takes
 * time proportional to the number of such vertices.
 * <p>
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator"); 

    // number of vertices in this digraph
    private final int V;

    // number of edges in this digraph
    private int E;

    // adj[v] = adjacency list for vertex v
    private Bag<Integer>[] adj;

    // indegree[v] = indegree of vertex v
    private int[] indegree;

    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Digraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[])new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry
     * separated by whitespace.
     *
     * @param in the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative 
     * @throws IllegalArgumentException if the input stream is in the wrong format 
     */
    public Digraph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) {
                throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
            }
            indegree = new int[V];
            adj = (Bag<Integer>[])new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }

            int E = in.readInt();
            if (E < 0) {
                throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
            }
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
    }

    /**
     * Initializes a new Digraph that is a deep copy of the specified digraph.
     * @param G the digraph to copy
     */
    public Digraph(Digraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < V; v++) {
            this.indegree[v] = G.indegree(v);
        }
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + " is not between 0 and " + (V-1));
        }
    }  

    /**
     * Adds the directed edge v->w to this digraph.
     *
     * @param v the tail vertex 
     * @param w the head vertex 
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *
     * @param v the vertex
     * @return the vertices adjacent from vertex {@code v} in this digraph, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size(); 
    }

    /**
     * Returns the number of directed edges incident to vertex{@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     * 
     * @return the reverse of the digraph
     */
    public Digraph revese() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }        

    /**
     * Returns  a string representation of this graph.
     *
     * @return the number of vertices 
     * <em>V</em>, followed by the number of edges
     * <em>E</em>, followed by the
     * <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V +" vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code Digraph} data type
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
    }

}
