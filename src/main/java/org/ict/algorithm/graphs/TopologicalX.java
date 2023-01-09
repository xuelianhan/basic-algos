package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Queue;

/**
 *  The {@code TopologicalX} class represents a data type for
 *  determining a topological order of a <em>directed acyclic graph</em> (DAG).
 *  A digraph has a topological order if and only if it is a DAG.
 *  The <em>hasOrder</em> operation determines whether the digraph has
 *  a topological order, and if so, the <em>order</em> operation
 *  returns one.
 *  <p>
 *  This implementation uses a nonrecursive, queue-based algorithm.
 *  The constructor takes &Theta;(<em>V</em> + <em>E</em>) time in the worst
 *  case, where <em>V</em> is the number of vertices and <em>E</em>
 *  is the number of edges.
 *  Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the digraph).
 *  <p>
 *  See {@link DirectedCycle}, {@link DirectedCycleX}, and
 *  {@link EdgeWeightedDirectedCycle} to compute a
 *  directed cycle if the digraph is not a DAG.
 *  See {@link Topological} for a recursive version that uses depth-first search.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @date 09 Jan, 2023
 */
public class TopologicalX {
    // vertices in topological order
    private Queue<Integer> order;
    //ranks[v] = order where vertex v appears in order
    private int[] ranks;

    /**
     * Determines whether the digraph {@code G} has a topological order and,
     * if so, finds such a topological order.
     * @param G
     */
    public TopologicalX(Digraph G) {
        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
        }
        ranks = new int[G.V()];
        order = new Queue<>();
        int count = 0;

        // initialize Queue to contain all vertices with in-degree == 0.
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < G.V(); v++) {
            if (indegree[v] == 0) {
                queue.enqueue(v);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            order.enqueue(v);
            ranks[v] = count++;
            for (int w : G.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0) {
                    queue.enqueue(w);
                }
            }
        }
        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        if (count != G.V()) {
            order = null;
        }
        //todo
    }

    public TopologicalX(EdgeWeightedDigraph G) {
        //todo
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean hasOrder() {
        return order != null;
    }

    public int rank(int v) {
        validateVertex(v);
        if (hasOrder()) {
            return ranks[v];
        } else {
            return -1;
        }
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = ranks.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
