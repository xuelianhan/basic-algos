package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Stack;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;


/**
 * $ cat ../resources/tinyG.txt 
 * 13
 * 13
 * 0 5
 * 4 3
 * 0 1
 * 9 12
 * 6 4
 * 5 4
 * 0 2
 * 11 12
 * 9 10
 * 0 6
 * 7 8
 * 9 11
 * 5 3
 * $ javac org/ict/algorithm/graphs/Cycle.java 
 * $ java org/ict/algorithm/graphs/Cycle ../resources/tinyG.txt 
 * 3 4 5 3 
 *
 * The {@code Cycle} class represents a data type for 
 * determining whether an undirected graph has a simple cycle.
 * The <em>hasCycle</em> operation determines whether the 
 * graph has a cycle and, if so, the <em>cycle</em> operation
 * returns one.
 *
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case).
 * where <em>V</em> is the number of vertices and <em>E</em> is 
 * the number of edges.
 * Afterwards, the <em>hasCycle</em> operation takes constant time;
 * the <em>cycle</em> operation takes time proportional to the
 * length of the cycle.
 * <p>
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Cycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    /**
     * Determines whether the undirectd graph {@code G} has a cycle and,
     * if so, finds such a cycle.
     *
     * @param G the undirected graph
     */
    public Cycle(Graph G) {
        if (hasSelfLoop(G)) {
            return;
        }
        if (hasParallelEdges(G)) {
            return;
        }
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                // dfs visit v, and initialize second vertex to -1 means this -1 vertex not exists.
                dfs(G, -1, v);
            }
        }
    }

    // does this graph have a self loop?
    // side effect: initialize cycle to be self loop
    private boolean hasSelfLoop(Graph G) {
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;
    }

    // does this graph have two parallel edges?
    // side effect: initialize cycle to be two parallel edges
    private boolean hasParallelEdges(Graph G) {
        marked = new boolean[G.V()];
        
        for (int v = 0; v < G.V(); v++) {
            // check for parallel edges incident to v
            for (int w : G.adj(v)) {
                // why marked[w] not !marked[w]?
                // see page518 the concept of parallel edges:
                // Two edges that connect the same pair of vertices are parallel.
                // So the marked[w] must be true if v-w is parallel.
                // marked[w] is true means the vertex w has been visited via vertex v
                // At the second time meeting w, this edge is paralled with the 
                // edge firstly visit via v.
                if (marked[w]) {
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;

                }
                marked[w] = true;
            }

            // reset so marked[v] false for all v
            for (int w : G.adj(v)) {
                marked[w] = false;
            }
        }
        return false;
    }

    /**
     * Returns true if the graph {@code G} has a cycle.
     *
     * @return {@code true} if the graph has a cycle; {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a cycle in the graph {@code G}.
     * @return a cycle if the graph {@code G} has a cycle.
     * and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            // short circuit if cycle already found
            if (cycle != null) {
                return;
            }

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, v, w);
            } else if (w != u) {
            // check for cycle(but disregard reverse of edge leading to v) 
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("Graph is acylic");
        }
    }
}
