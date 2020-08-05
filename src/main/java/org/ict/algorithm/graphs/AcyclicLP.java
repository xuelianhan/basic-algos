package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Stack;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;

/**
 * The {@code AcylicLP} class represents a data type for solving the 
 * single-source longest paths problem in edge-weighted directed
 * acylic graphs (DAGs). The edge weights can be positive, negative, or zero.
 * <p>
 * This implementation uses a topological-sort based algorithm.
 * The constructor takes The <em>V</em> + <em>E</em> time in the worst
 * case, where <em>V</em> is the number of vertices and <em>E</em> is
 * the the number of edges.
 *
 *
 * @author Robert Sedgewick 
 * @author Kevin Wayne
 *
 */
public class AcyclicLP {
    // distTo[v] = distance of longest s->v path
    private double[] distTo;

    // edgeTo[v] = last edge on longest s->v path
    private DirectedEdge[] edgeTo;

    /**
     * Computes a longest paths tree from {@code s} to every other vertex in
     * the directed acyclic graph {@code G}.
     *
     */
    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        validateVertex(s);
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0.0;
        Topological topological = new Topological(G);
        if (!topological.hasOrder()) {
            throw new IllegalArgumentException("Digraph is not acyclic");
        }
        for (int v : topological.order()) {
            for (DirectedEdge e : G.adj(v)) {
                relax(e);
            }
        }
    }

    /**
     * relax edge e, but update if you find a longer path
     * @param e
     *    e
     * v---->w
     */
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] < distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    /**
     * Returns the length of a longest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param v the destination vertex
     * @return the length of longest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.NEGATIVE_INFINITY} if no such path
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Is there a path from the source vertex {@code s} to vertex {@code v}?
     *
     * @param v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] > Double.NEGATIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex" + v + " is not between 0 and " + (V-1));
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        AcyclicLP lp = new AcyclicLP(G, s);
        for (int v = 0; v< G.V(); v++) {
            if (lp.hasPathTo(v)) {
                StdOut.printf("%d to %d (%.2f) ", s, v, lp.distTo(v));
                for (DirectedEdge e : lp.pathTo(v)) {
                    StdOut.print(e + " ");
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d   no path\n", s ,v);
            }
        }
    }
}
