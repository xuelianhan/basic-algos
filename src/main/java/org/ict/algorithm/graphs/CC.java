package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Queue;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;
/**
 * $ javac org/ict/algorithm/graphs/CC.java 
 * Note: org/ict/algorithm/graphs/CC.java uses unchecked or unsafe operations.
 * Note: Recompile with -Xlint:unchecked for details.
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
 * $ java org/ict/algorithm/graphs/CC ../resources/tinyG.txt 
 * 13 vertices, 13 edges 
 * 0: 6 2 1 5 
 * 1: 0 
 * 2: 0 
 * 3: 5 4 
 * 4: 5 6 3 
 * 5: 3 4 0 
 * 6: 0 4 
 * 7: 8 
 * 8: 7 
 * 9: 11 10 12 
 * 10: 9 
 * 11: 9 12 
 * 12: 11 9 
 *
 * 3 components
 * 0 1 2 3 4 5 6 
 * 7 8 
 * 9 10 11 12 
 *
 * The {@code CC} class represents a data type for
 * determining the connected components in an undirected graph.
 * The <em>id</em> operation determines in which connected component
 * a given vertex lies; the <em>connected</em> operation determines
 * whether two vertices are in the same connected component;
 * the <em>count</em> operation determines the number of connected
 * components; and the <em>size</em> operation determines the number
 * of vertices in the connect component containing a given vertex.
 *
 * The <em>conponent identifier</em> of a connected component is one of
 * the vertices in the connnected component: two vertices have the same
 * component identifier if and only if they are in the same connected 
 * component.
 *
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the
 * number of edges.
 * Afterwards, the <em>id</em>, <em>count</em>, <em>connected</em>,
 * and <em>size</em> operations take constant time.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class CC {
    // marked[v] = has vertex v been marked?
    private boolean[] marked;

    // id[v] = id of connected component containing v
    private int[] id;

    // size[id] = number of vertices in given component
    private int[] size;

    // number of connected components
    private int count;

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph.
     */
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    /**
     * Computes the connected components of the edge-weighted graph {@code G}
     * 
     * @param G the edge-weighted graph
     */
    public CC(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    // depth-first search for a Graph
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    // depth-first search for an EdgeWeightedGraph
    private void dfs(EdgeWeightedGraph G, int v) {
        marked[v] = true; 
        id[v] = count;
        size[count]++;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * Returns the component id of the connected component containing vertex {@code v}.
     *
     * @param v the vertex
     * @return the component id of the connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected component containing vertex {@code v}.
     *
     * @param v the vertex
     * @return the number of vertices in the connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}.
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param v one vertex
     * @param W the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the 
     * same connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     */
    public boolean connected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    /**
     * Unit tests the {@code CC} data type.
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
        CC cc = new CC(G);

        // number of connected components
        int m = cc.count();
        StdOut.println(m + " components");

        // compute list of vertics in each connected component
        Queue<Integer>[] components = (Queue<Integer>[])new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }

        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }

}
