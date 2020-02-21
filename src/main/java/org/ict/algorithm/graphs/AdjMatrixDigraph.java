package org.ict.algorithm.graphs;


/**
 *
 *
 */


import java.util.Iterator;
import java.util.NoSuchElementException;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.StdRandom;


public class AdjMatrixDigraph {
    private int V;
    private int E;
    private boolean[][] adj;

    // empty graph with V vertices
    public AdjMatrixDigraph(int V) {
        if (V < 0) {
            throw new RuntimeException("Number of vertices must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        this.adj = new boolean[V][V];
    }

    // random graph with V vertices and E edges
    public AdjMatrixDigraph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new RuntimeException("Number of edges must be nonnegative");
        }
        if (E > V*V) {
            throw new RuntimeExcetpion("Too many edges");
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

    // add directed edge v->w
    public void addEdge(int v, int w) {
        if (!adj[v][w]) {
            E++;
        }
        adj[v][w] = true;
    }

    // return list of neighbors of v
    public Iterable<Integer> adj(int v) {
        return new AdjIterator(v);
    }

    // support iteration over graph vertices
    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        
    }
}

