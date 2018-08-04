package org.ict.algorithm.graphs;

import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;

/**
 * $ javac org/ict/algorithm/graphs/DepthFirstSearch.java 
 * Note: ./org/ict/algorithm/graphs/Graph.java uses unchecked or unsafe operations.
 * Note: Recompile with -Xlint:unchecked for details.
 * $ more ../resources/tinyG.txt 
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
 *
 * $ java org/ict/algorithm/graphs/DepthFirstSearch ../resources/tinyG.txt  5
 * 0 1 2 3 4 5 6 
 * Not connected
 *
 * $ java org/ict/algorithm/graphs/DepthFirstSearch ../resources/tinyG.txt  9
 * 9 10 11 12 
 * Not connected
 *
 * The {@code DepthFirstSearch} class represents a data type for 
 * determining the vertices connected to a given source vertex <em>s</em>
 * in an undirected graph. For versions that find the paths, see 
 * {@link DepthFirstPaths} and {@link BreadthFirstPaths}.
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the 
 * number of edges.
 * It uses extra space (not including the graph) proportional to
 * <em>V</em>.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class DepthFirstSearch {
    // marked[v] = is there an s-v path?
    // It also means the v is visited if marked[v] is true
    private boolean[] marked;

    // number of vertices connected to s
    private int count;

    /**
     * Computes the vertices in graph {@code G} that are
     * connected to the source vertex {@code s}.
     *
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex{@code v}?
     *
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of vertices connected to the source vertex {@code s}.
     * @return the number of vertices connected to the source vertex {@code s}.
     */
    public int count() {
        return count;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    /**
     * Unit tests the {@code DepthFirstSeach} data type.
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
        if (search.count() != G.V()) {
            StdOut.println("Not connected");
        } else {
            StdOut.println("connected");
        }
    }

}
