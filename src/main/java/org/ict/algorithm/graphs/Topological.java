package org.ict.algorithm.graphs;

/**
 * Compute topological ordering of a DAG or edge-weighted DAG.
 * Runs in O(V+E) time
 * 
 * The {@code Topological} class represents a data type for
 * determining a topological  order of a directed acyclic graph(DAG).
 * Recall, a digrap has a topological order if and only if it is DAG.
 * The <em>hasOrder</em> operation determines whether the digraph has
 * a topological order, and if so, the <em>order</em> operation returns
 * one.
 * 
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the number
 * of edges.
 * Afterwards, the <em>hasOrder</em> and <em>rank</em> operations takes
 * constant time;
 * the <em>order</em> operation takes time proportional to <em>V</em>.
 * <p>
 * See {@link DirectedCycle}, {@link DirectedCycleX}, and {@link EdgeWeightedDirectedCycle}
 * to compute a directed cycle if the diagraph is not a DAG.
 * See {@link TopologicalX} for a nonrecursive queue-based algorithm
 * to compute a topological order for a DAG.
 * <p>
 *
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class Topological {

	// topological order
	private Iterable<Integer> order;
	
	// rank[v] = rank of vertex v in order
	private int[] rank;
	
	/**
	 * Determines whether the digraph {@code G} has a topologial order and, if so,
	 * finds such a topologial order.
	 * 
	 * @param G the digraph
	 */
	public Topological(Digraph G) {
		DirectedCycle finder = new DirectedCycle(G);
		if (!finder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
			rank = new int[G.V()];
			int i = 0;
			for (int v : order) {
				rank[v] = i++;
			}
		}
	}
	
	/**
	 * Determines whether the edge-weighted digraph {@code G} has a topological
	 * order and, if so, finds such an order.
	 * @param G the edge-weighted digraph
	 */
	public Topological(EdgeWeightedDigraph G) {
		EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
	}
}
