package org.ict.algorithm.graphs;

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


}
