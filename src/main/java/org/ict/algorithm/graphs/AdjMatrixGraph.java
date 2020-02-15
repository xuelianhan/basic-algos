package org.ict.algorithm.graphs;

/**
 * Compilation: $ javac org/ict/algorithm/graphs/AdjMatrixGraph.java 
 * Execution:   $ java org/ict/algorithm/graphs/AdjMatrixGraph 4 6
 * Output: 
 * 4 6
 * 0: 1 2 3 
 * 1: 0 2 3 
 * 2: 0 1 
 * 3: 0 1 3 
 *
 * A graph, implemented using an adjacency matrix.
 * Parallel edges are disallowed; self-loops are allowed.
 *
 *
 */

import java.util.Iterator;
import java.util .NoSuchElementException;
import org.ict.algorithm.util.StdRandom;
import org.ict.algorithm.util.StdOut;


public class AdjMatrixGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private boolean[][] adj;

    // empty graph with V vertices
    public AdjMatrixGraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Too few vertices");
        }
        this.V = V;
        this.E = 0;
        this.adj = new boolean[V][V];
    }

    // random graph with V vertices and E edges
    public AdjMatrixGraph(int V, int E) {
        this(V);
        // Graph with V vertices has most V*(V-1)/2 edges with no self-loops, graph here allow self-loops.
        // So the another addition of V edges  
        if (E > (long) V*(V-1)/2 + V) {
            throw new IllegalArgumentException("Too many edges");
        }
        if (E < 0) {
            throw new IllegalArgumentException("Too few edges");
        }

        // can be inefficient
        while (this.E != E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            addEdge(v, w);
        }
    }

    // number of vertices and edges
    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // add undirected edge v-w
    public void addEdge(int v, int w) {
        if (!adj[v][w]) {
            E++;
        }
        adj[v][w] = true;
        adj[w][v] = true;
    }

    // does the graph contain the edge v-w?
    public boolean contains(int v, int w) {
        return adj[v][w];
    }

    // return list of neighbors of v
    public Iterable<Integer> adj(int v) {
        return new AdjIterator(v);
    }

    // support iteration over graph vertices
    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        private int v;
        private int w = 0;

        AdjIterator(int v) {
            this.v = v;
        }

        public Iterator<Integer> iterator() {
            return this;
        }

        public boolean hasNext() {
            while (w < V) {
                if (adj[v][w]) {
                    return true;
                }
                w++;
            }
            return false;
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return w++;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // string representation of Graph - takes quadratic time
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            } 
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        AdjMatrixGraph G = new AdjMatrixGraph(V, E);
        StdOut.println(G);
    }
}
