package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Stack;

/**
 * The {@code EdgeWeightedDirectedCycle} class represents a data type
 * for determining whether an edge-weighted digraph has a directed cycle.
 * The <em>hasCycle</em> operation determines whether the edge-weighted
 * digraph has a directed cycle and, if so, the <em>cycle</em> operation
 * returns one.
 * <p>
 * This implementation uses <em>depth-first-search</em>.
 * The constructor takes the <em>V</em> + <em>E</em> time in thee worst case,
 * where <em>V</em> is the number of vertices and <em>E</em> is the number of
 * edges.
 * Each instance method takes thee O(1) time.
 * It uses the <em>V</em> extra space (not including the edge-weighted digraph).
 * <p>
 * See {@link Topological} to compute a topological order if the edge-weighted
 * digraph is acyclic.
 * <p>
 * @author Robert sedgewick
 * 
 *
 */
public class EdgeWeightedDirectedCycle {
	private boolean[] marked;    // marked[v] = has vertex v been marked?
	private DirectedEdge[] edgeTo; // edgeTo[v] = previous edge on path to v
	private boolean[] onStack; // onStack[v] = is vertex on the stack?
	private Stack<DirectedEdge> cycle; // directed cycle (or null if no such cycle)
	
	/**
	 * Determines whether the edge-weighted digraph {@code G} has a directed cycle and,
	 * if so, finds such a cycle.
	 * @param G
	 */
	public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
			}
		}
		// check that digraph has a cycle
		assert check();
	}
	
	/**
	 * check that algorithm computes either the topological order or finds a directed cycle
	 */
	private void dfs(EdgeWeightedDigraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if (cycle != null) {
				// short circuit if directed cycle found
				return;
			}
			if (!marked[w]) {
				// found new vertex, so recur
				edgeTo[w] = e;
				dfs(G, w);
			} else if (onStack[w]) {
				// trace back directed cycle
				cycle = new Stack<DirectedEdge>();
				DirectedEdge f = e;
				while (f.from() != w) {
					cycle.push(f);
					f = edgeTo[f.from()];
				}
				cycle.push(f);// why push f again? It's constructing a cycle for purpose.
				// Like: f e1 e2 e3 ... f
				return;
			}
		}
		onStack[v] = false;
	}
	
	private boolean check() {
		return false;
	}
}
