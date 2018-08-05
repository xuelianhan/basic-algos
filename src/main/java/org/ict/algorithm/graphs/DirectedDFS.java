package org.ict.algorithm.graphs;

import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;

/**
 * The {@code DirectedDFS} class represents a data type 
 * for determining the vertices reachable from a given
 * source vertex <em>s</em>(or set of source vertices)
 * in a digraph.For versions that find the paths, see
 * {@link DepthFirstDirectedPaths} and {@link BreadthFirstDirectedPaths}.
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the 
 * number of edges.
 * <p>
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class DirectedDFS {

    // marked[v] = true if v is reachable from source s
    private boolean[] marked;

    // number of vertices reachable from source s
    private int count;

    /**
     * Computes the vertices in digraph {@code G} that are
     * reachable from the source vertex {@code s}.
     *
     * @param G the digraph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < v}
     */
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }
}
