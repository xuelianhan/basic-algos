package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.Queue;
import org.ict.algorithm.fundamentals.Stack;
import org.ict.algorithm.util.StdOut;

/**
 * Computes preorder and postorder for digraph or edge-weighted digraph.
 * Runs in O(E + V) time.
 * 
 * The {@code DepthFirstOrder} class represents a data type for 
 * determining depth-first search ordering of the vertices in a
 * digraph or edge-weighted digraph, including preorder, postorder, and reverse postorder.
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 * Afterwards, the <em>preorder</em>, <em>postorder</em>, and<em>reverse postorder</em>
 * operation take time proportional to <em>V</em>.
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class DepthFirstOrder {
	//marked[v] = has v been marked in dfs?
	private boolean[] marked;
	// pre[v] = preorder number of v
	private int[] pre;
	// post[v] = postorder number of v
	private int[] post;
	// vertices in preorder
	private Queue<Integer> preorder;
	// vertices in postorder
	private Queue<Integer> postorder;
	// counter for preorder numbering
	private int preCounter;
	// counter for postorder numbering
	private int postCounter;
	
	/**
	 * Determines a depth-first order for the digraph {@code G}.
	 * @param G the digraph
	 */
	public DepthFirstOrder(Digraph G) {
		pre = new int[G.V()];
		post = new int[G.V()];
		preorder = new Queue<Integer>();
		postorder = new Queue<Integer>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
			}
		}
		//assert check();
	}
	
	/**
	 * Determines a depth-first-order for the edge-weighted digraph {@code G}
	 * @param G
	 * @param v
	 */
	public DepthFirstOrder(EdgeWeightedDigraph G) {
		pre = new int[G.V()];
		post = new int[G.V()];
		postorder = new Queue<Integer>();
		preorder = new Queue<Integer>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
			}
		}
	}
	
	// run DFS in digraph G from vertex v and compute preorder/postorder
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
	}
	
	// run DFS in edge-weighted digraph G from vertex v and compute preorder/postorder
	private void dfs(EdgeWeightedDigraph G, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		for (DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if (!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
	}
	
	/**
	 * Returns the preorder number of vertex {@code v}.
	 * @param v the vertex
	 * @return the preorder number of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int pre(int v) {
		//validateVertex(v);
		return pre[v];
	}
	
	/**
	 * Returns the postorder number of vertex {@code v}.
	 * @param v the vertex
	 * @return the postorder number of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int post(int v) {
		//validateVertex(v);
		return post[v];
	}
	
	/**
	 * Returns the vertices in postorder
	 * @return the vertices in postorder, as an iterable of vertices
	 */
	public Iterable<Integer> post() {
		return postorder;
	}
	
	/**
	 * Returns the vertices in preorder.
	 * @return the vertices in preorder, as an iterable of vertices
	 */
	public Iterable<Integer> pre() {
		return preorder;
	}
	
	/**
	 * Returns the vertices in reverse postorder.
	 * @return the vertices in reverse postorder, as an iterable of vertices
	 */
	public Iterable<Integer> reversePost() {
		Stack<Integer> reverse = new Stack<Integer>();
		for (int v : postorder) {
			reverse.push(v);
		}
		return reverse;
	}
	
	// check that pre() and post() are consistent with pre(v) and post(v)
	private boolean check() {
		// check that post(v) is consistent with post()
		int r = 0;
		for (int v : post()) {
			if (post(v) != r) {
				StdOut.println("");
				return false;
			}
		}
		return true;
	}
}
