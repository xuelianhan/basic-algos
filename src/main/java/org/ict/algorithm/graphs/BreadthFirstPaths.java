package org.ict.algorithm.graphs;


/**
 * The {@code BreadthFirstPaths} class represents a data type for finding
 * shortest paths (number of edges) from a source vertex <em>s</em>
 * (or a set of source vertices) 
 * to every other vertex in an undirected graph.
 * <p>
 * This implementation uses breadth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>,
 * where <em>V</em> is the number of vertices and <em>E</em> is the number os edges.
 * Each call to {@link #distTo(int)} and {@link #hasPathTo(int)} takes constant time;
 * each call to {@link $pathTo(int)} takes time proportional to the length of the 
 * path.
 * It uses extra space (not including the graph) proportional to <em>V</em>.
 * <p>
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class BreadthFirstPaths {

    private static final int INFINITY = Integer.MAX_VALUE:
    // marked[v] = is there an s-v path
    private boolean[] marked;

    //edgeTo[v] = previous edge on shortest s-v path
    // It also means last vertex on known path to this vertex
    private int[] edgeTo;

    // distTo[v] = number of edges shortest s-v path
    private int[] distTo;

    /**
     *
     *
     */

    /**
     * Returns the number of edges in a shortest path between the source vertex {@code s} 
     * (or source) and vertex {@code v}
     *
     * @param v the vertex
     * @return the number of edges in a shortest path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     *
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v); 
        return marked[v];
    }

    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     *
     * @param v the vertex
     * @return the sequence of vertices on a path between the source vertex 
     *         {@code s} and vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);

        return path;
    }

    // check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1 
    // provided v is reachable from s

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    // throw an IllegalArgumentException unless {@code 0 <= v <V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }

    /**
     * Unit tests the {@code DepthFirstSeach} data type.
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);

        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d: ", s, v);
                for (int x : bfs.pathTo(v)) {
                    if (x == s) {
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
                StdOut.println();
            } else {
                StdOut.printf(" %d to %d: Not connected\n", s, v);
            }
        }
    }
}
