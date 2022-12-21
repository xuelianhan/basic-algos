package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Stack;

/**
 * The {@code Bipartite} class represents a data type for 
 * determining whether an undirected graph is bipartite or whether
 * it has an odd-length cycle.
 * The <em>isBipartite</em> operation determines whether the graph
 * is bipartite. If so, the <em>color</em> operations determines a 
 * bipartition; if not, the <em>oddCycle</em> operation determines 
 * a cycle with an odd number of edges.
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the
 * number of edges.
 * Afterwards, the <em>isBipartite</em> and <em>color</em> operations
 * take constant time; the <em>oddCycle</em> operation takes time 
 * proportional to the length of the cycle.
 * See {@link BipartiteX} for a nonrecursive version that uses 
 * breadth-first search.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Bipartite {
    /**
     * Is the graph bipartite?
     */
    private boolean isBipartite;
    /**
     * color[v] gives vertices on one side of bi-partition.
     */
    private boolean[] color;
    /**
     * marked[v] = true if v has been visited in DFS
     */
    private boolean[] marked;
    /**
     * edgeTo[v] = last edge on the path to v.
     */
    private int[] edgeTo;
    /**
     * odd-length cycle
     */
    private Stack<Integer> cycle;


    public Bipartite(Graph G) {
        this.isBipartite = true;
        this.color = new boolean[G.V()];
        this.marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
        assert check(G);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            /**
             * short-circuit if odd-length cycle found
             */
            if (cycle != null) {
                return;
            }

            /**
             * found the uncolored vertex, so recur.
             */
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                /**
                 * if v-w create an odd-length cycle, find it.
                 */
            }

        }
    }

    private boolean check(Graph G) {
        return false;
    }
}
