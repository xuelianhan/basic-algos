package org.ict.algorithm.graphs;

import org.ict.algorithm.util.StdOut;
/**
 * A bridge (or cut-edge) is an edge whose deletion increases the number of connected components. 
 * Equivalently, an edge is a bridge if and only if it is not contained in any cycle.
 * Bridge.java uses depth-first search to find time the bridges in a graph. 
 * It takes time proportional to V + E in the worst case.
 * 
 * Identifies bridge edges and prints them out.
 * This decomposes a directed graph into two-edge connected components.
 * Runs in O(V + E) time.
 * 
 * Key quantity: 
 * low[v] = minimum DFS preorder number of v
 * and the set of vertices w for which there is a back edge (x, w)
 * with x a descendant of v and w an ancestor of v.
 * 
 * Note: code assumes no parallel edges, e.g., two parallel edges
 * would be (incorrectly) identified as bridges.
 *  
 * @author Robert Sedgewick 
 * @author Kevin Wayne
 */
public class Bridge {
	
	public static void main(String[] args) {
		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		
		Graph G = GraphGenerator.simple(V, E);
		StdOut.println(G);
		
		Bridge bridge = new Bridge(G);
		StdOut.println("Edge connected components = " + bridge.components());
	}
	
	//Number of bridges
	private int bridges;
	//counter
	private int cnt;
	//pre[v] = orders in which dfs examines v
	private int[] pre;
	//low[v] = lowest preorder of any vertex connected to v
	private int[] low;
	
	public Bridge(Graph G) {
		int[] pre = new int[G.V()];
		int[] low = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			pre[v] = -1;
		}
		for (int v = 0; v < G.V(); v++) {
			low[v] = -1;
		}
		for (int v = 0; v < G.V(); v++) {
			if (pre[v] == -1) {
				dfs(G, v, v);
			}
		}
	}
	
	public int components() {
		return bridges + 1;
	}
	
	private void dfs(Graph G, int u, int v) {
		pre[v] = cnt++;
		low[v] = pre[v];
		for (int w : G.adj(v)) {
			if (pre[w] == -1) {
				dfs(G, v, w);
				low[v] = Math.min(low[v], low[w]);
				if (low[w] == pre[w]) {
					StdOut.println(v + "-" + w + " is a bridge");
                    bridges++;
				}
			} else if (w != u) {
				//update low number - ignore reverse of edge leading to v
				low[v] = Math.min(low[v], pre[w]);
			}
		}
	}
}
