package org.ict.algorithm.graphs;

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
 * @author Robert Sedgewick 
 * @author Kevin Wayne
 *
 */
public class AcylicLP {
    // distTo[v]
    private double[] distTo;
    private DirectedEdge[] edgeTo;
}
