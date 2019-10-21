package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Stack;

public class DirectedCycle {

	// marked[v] = has vertex v been marked?
	private boolean[] marked;
	// edgeTo[v] = previous vertex on path to v
	private int[] edgeTo;
	// onStack[v] = is vertex on the stack?
	private boolean[] onStack;
	// directed cycle(or null if no such cycle)
	private Stack<Integer> cycle;
	
	public DirectedCycle(Digraph G) {
		marked  = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo  = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if(!marked[v] && cycle == null) {
				dfs(G, v);
			}
		}
	}
	
	// check that algorithm computes either the topological order or finds a directed cycle
	private void dfs(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (cycle != null) {// short circuit if directed cycle found
				return;
			} else if (!marked[w]) {// found new vertex, so recur.
				edgeTo[w] = v;
			} else if (onStack[w]) {// trace back directed cycle
				cycle = new Stack<Integer>();
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
				assert check();
			}
			onStack[v] = false;
		}
	}
	
	/**
	 * Does the digraph have a directed cycle?
	 * @return {@code true} if the digraph has a cycle, {@code false} otherwise
	 */
	public boolean hasCycle() {
		return cycle != null;
	}
	
	/**
	 * Returns a directed cycle if the digraph has a directed cycle, and{@code null} otherwise.
	 * @return
	 */
	public Iterable<Integer> cycle() {
		return cycle;
	}
	
	// certify that the digraph has a directed cycle if it reports one
	private boolean check() {
		if (hasCycle()) {
			
		}
		return true;
	}
}
