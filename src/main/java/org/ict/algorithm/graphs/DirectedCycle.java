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
		
	}
}
