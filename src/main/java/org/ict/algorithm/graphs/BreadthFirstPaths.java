package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Queue;
import org.ict.algorithm.fundamentals.Stack;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;


/**
 * $ javac org/ict/algorithm/graphs/BreadthFirstPaths.java 
 * $ cat ../resources/tinyCG.txt 
 * 6
 * 8
 * 0 5
 * 2 4
 * 2 3
 * 1 2
 * 0 1
 * 3 4
 * 3 5
 * 0 2
 * $ java org/ict/algorithm/graphs/Graph ../resources/tinyCG.txt 
 * 6 vertices, 8 edges 
 * 0: 2 1 5 
 * 1: 0 2 
 * 2: 0 1 3 4 
 * 3: 5 4 2 
 * 4: 3 2 
 * 5: 3 0 
 *
 * $ java org/ict/algorithm/graphs/BreadthFirstPaths ../resources/tinyCG.txt 0
 * 0 to 0: 0
 * 0 to 1: 0-1
 * 0 to 2: 0-2
 * 0 to 3: 0-2-3
 * 0 to 4: 0-2-4
 * 0 to 5: 0-5
 *
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

    private static final int INFINITY = Integer.MAX_VALUE;

    /**
     * marked[v] = is there an s-v path
     */
    private boolean[] marked;

    /**
     * edgeTo[v] = previous edge on shortest s-v path
     * It also means last vertex on known path to this vertex
     */
    private int[] edgeTo;

    /**
     * distTo[v] = number of edges shortest s-v path
     */
    private int[] distTo;

    /**
     * Computes the shortest path between the source vertex {@code s}
     * and every other vertex in the graph {@code G}.
     *
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);
        //assert check(G, s);
    }

    /**
     * Computes the shortest path between any one of the source vertices in {@code sources}
     * and every other vertex in graph {@code G}.
     *
     * @param G the graph
     * @param sources the source vertices
     * @throws IllegalArgumentException unless {@code 0 <= s < V} for each vertex
     *         {@code s} in  {@code sources}
     *
     */
    public BreadthFirstPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertices(sources);
        bfs(G, sources); 
    }

    /**
     * breadth-first search from a single source
     * @param G
     * @param s
     */
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);
        
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     *  breadth-first search from multiple sources
     * @param G
     * @param sources
     */
    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

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
        int x;

        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);

        return path;
    }

    // check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1 
    // provided v is reachable from s
    //private boolean check(Graph G, int s);

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
