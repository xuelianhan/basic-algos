package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Stack;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.StdRandom;

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
	
	/**
	 * Does the edge-weighted digraph have a directed cycle?
	 * @return {@code true} if the edge-weighted digraph has a directed cycle.
	 *         {@code false} otherwise
	 */
	public boolean hasCycle() {
		return cycle != null;
	}
	
	/**
	 * Returns a directed cycle if the edge-weighted digraph has a directed cycle,
	 * and {@code null} otherwise.
	 * @return
	 */
	public Iterable<DirectedEdge> cycle() {
		return cycle;
	}
	
	/**
	 * Certify that digraph is either acyclic or has a directed cycle.
	 * @return
	 */
	private boolean check() {
		// edge-weighted digraph is cyclic
		if (hasCycle()) {
			// verify cycle
			DirectedEdge first = null, last = null;
			for (DirectedEdge e :cycle()) {
				if (first == null) {
					first = e;
				}
				if (last != null) {
					if (last.to() != first.from()) {
						System.err.printf("cycle edge %s and %s not incident\n", last, e);
						return false;
					}
				}
				last = e;
			}//end-for-loop
			
			if (last.to() != first.from()) {
				System.err.printf("cycle edge %s and %s not incident\n", last, first);
				return false;
			}
		}
		return true;
	}

    public static void main(String[] args) {
        // create random DAG with V vertices and E edges, then add F random edges
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++) {
            vertices[i] = i;
        }
        StdRandom.shuffle(vertices);
        for (int i = 0; i < E; i++) {
            int v, w;
            do {
                v = StdRandom.uniform(V);
                w = StdRandom.uniform(V);
            } while(v >= w);
            double weight = StdRandom.uniform();
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        // add F extra edges
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = StdRandom.uniform(0.0, 1.0);
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        StdOut.println(G);

        // find a directed cycle
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Cycle:");
            for (DirectedEdge e : finder.cycle()) {
                StdOut.print(e + " ");
            }
        } else {
            StdOut.println("No directed cycle");
        }
    }
}
