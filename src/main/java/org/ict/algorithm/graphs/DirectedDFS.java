package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Bag;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;

/**
 * Execution results:
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
 * $ javac org/ict/algorithm/graphs/DirectedDFS.java 
 * $ java org/ict/algorithm/graphs/DirectedDFS ../resources/tinyDG.txt 1 2 
 * 0 1 2 3 4 5
 */

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

    /**
     * Computes the vertices in digraph {@code G} that are
     * connected to any of the source vertices {@code sources}.
     *
     * @param G the graph 
     * @param sources the source vertices
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     * for each vertex {@code s} in {@code sources}
     */
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        validateVertices(sources);
        for (int v : sources) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a directed path from the source vertex (or any of
     * the sources vertices) and vertex {@code v}?
     *
     * @param v the vertex
     * @return {@code true} if there is a directed path, {@code false} otherwise
     * @throws IllegalArgumentException  unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of vertices reachable from the source vertex
     * (or source vertices).
     * @return the number of vertices reachable from the source vertex
     * (or source vertices)
     */
    public int count() {
        return count;
    }
    
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
       int V = marked.length;
       if (v < 0 || v > V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
       }
    }

    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v > V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }

    /**
     * Unit tests
     */
    public static void main(String[] args) {
        // read in digraph from command-line argument
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        // read in sources from command-line arguments
        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 1; i < args.length; i++) {
            int s = Integer.parseInt(args[i]);
            sources.add(s);
        }

        // multiple-source reachability
        DirectedDFS dfs = new DirectedDFS(G, sources);

        // print out vertices reachable from sources
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
    }

}
