package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * For an undirected graph with tree characteristics, we can choose any node as the root. 
 * The result graph is then a rooted tree. 
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 * 
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. 
 * You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 * You can assume that no duplicate edges will appear in edges. 
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Example 1 :
 * Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *         0
 *         |
 *         1
 *        / \
 *       2   3 
 * 
 * Output: [1]
 * Example 2 :
 * 
 * Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 * 
 *      0  1  2
 *       \ | /
 *         3
 *         |
 *         4
 *         |
 *         5 
 * 
 * Output: [3, 4]
 * Note:
 * 
 * According to the definition of tree on Wikipedia: 
 * “a tree is an undirected graph in which any two vertices are connected by exactly one path. 
 * In other words, any connected graph without simple cycles is a tree.”
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * @author dietpepsi
 * LC310
 */
public class MinimumHeightTrees {
	
	public static void main(String[] args) {
		int n = 6;
		int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
		List<Integer> result = findMinHeightTreesV2(n, edges);
		System.out.println(result);
	}
	/**
	 * Basically, the idea is to eat up all the leaves at the same time, until one/two leaves are left.
	 * 
	 * First let's review some statement for tree in graph theory:
	 * (1) A tree is an undirected graph in which any two vertices are connected by exactly one path.
	 * (2) Any connected graph who has n nodes with n-1 edges is a tree.
     * (3) The degree of a vertex of a graph is the number of edges incident to the vertex.
     * (4) A leaf is a vertex of degree 1. An internal vertex is a vertex of degree at least 2.
     * (5) A path graph is a tree with two or more vertices that is not branched at all.
     * (6) A tree is called a rooted tree if one vertex has been designated the root.
     * (7) The height of a rooted tree is the number of edges on the longest downward path between root and a leaf.
     * OK. Let's stop here and look at our problem.
     * Our problem want us to find the minimum height trees and return their root labels. 
     * First we can think about a simple case -- a path graph.
     * For a path graph of n nodes, find the minimum height trees is trivial. Just designate the middle point(s) as roots.
     * Despite its triviality, let design a algorithm to find them.
     * Suppose we don't know n, nor do we have random access of the nodes. We have to traversal. 
     * It is very easy to get the idea of two pointers. One from each end and move at the same speed. 
     * When they meet or they are one step away, (depends on the parity of n), we have the roots we want.
     * This gives us a lot of useful ideas to crack our real problem.
     * For a tree we can do some thing similar. We start from every end, by end we mean vertex of degree 1 (aka leaves). 
     * We let the pointers move the same speed. 
     * When two pointers meet, we keep only one of them, until the last two pointers meet or one step away we then find the roots.
     * It is easy to see that the last two pointers are from the two ends of the longest path in the graph.
     * The actual implementation is similar to the BFS topological sort. 
     * Remove the leaves, update the degrees of inner vertexes. Then remove the new leaves. 
     * Doing so level by level until there are 2 or 1 nodes left. What's left is our answer!
     * The time complexity and space complexity are both O(n).
     * Note that for a tree we always have V = n, E = n-1.
     * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public static List<Integer> findMinHeightTreesV2(int n, int[][] edges) {
		if (n <= 0) {
			return new ArrayList<>();
		}
		// 1.corner case, only one vertex, so answer is [0]
		if (n == 1) {
			return Collections.singletonList(0);
		}
		// 2.Initialize the adj of graph with vertex number n
		Graph graph = new Graph(n);
		
		// 3.Add edges in graph
		for (int[] edge : edges) {
			graph.addEdge(edge[0], edge[1]);
		}
		
		// 4.Count leaves of graph
		List<Integer> leavesList = graph.leaves();
		while (n > 2) {// We need to remain two leaves node at least.
			List<Integer> newLeavesList = graph.removeLeaves(leavesList);
			n -= leavesList.size();
			// new leaves list has done, so we change the reference to replace old one
			leavesList = newLeavesList;
		}
		return leavesList;
	}
	
	public List<Integer> findMinHeightTreesV1(int n, int[][] edges) {
		if (n <= 0) {
			return new ArrayList<>();
		}
		// 1.corner case, only one vertex, so answer is [0]
		if (n == 1) {
			return Collections.singletonList(0);
		}
		// 2.Initialize the adj of graph with vertex number n
		List<Set<Integer>> adj = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			adj.add(new HashSet<>());
		}
		
		// 3.Add edges in graph
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		
		// 4.Count leaves of graph
		List<Integer> leavesList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (adj.get(i).size() == 1) {
				leavesList.add(i);
			}
		}
		
		while (n > 2) {// We need to remain two leaves node at least.
			n -= leavesList.size();
			List<Integer> newLeavesList = new ArrayList<>();
			for (int i : leavesList) {
				// Find the adjacent vertex j of the current leaf i
				int j = adj.get(i).iterator().next();
				// Remove the current leaf i from  vertex j's adjacency list
				adj.get(j).remove(i);
				
				// If current vertex j to become a leaf due to the remove action above,
				// we need to add it to the new leaves list.
				if (adj.get(j).size() == 1) {
					newLeavesList.add(j);
				}
			}
			// new leaves list has done, so we change the reference to replace old one
			leavesList = newLeavesList;
		}
		return leavesList;
	}
	
	
	public static class Graph {
		private final int V;
		private int E;
		private List<Set<Integer>> adj;
		
		public Graph (int V) {
			this.V = V;
			this.E = 0;
			adj = new ArrayList<>(V);
			for (int v = 0; v < V; v++) {
				adj.add(new HashSet<>());
			}
		}
		
		public void addEdge(int v, int w) {
			adj.get(v).add(w);
			adj.get(w).add(v);
			E++;
		}

		public Set<Integer> adj(int v) {
			return adj.get(v);
		}

		public int degree(int v) {
			return adj.get(v).size();
		}
		
		public void remove(int v, int w) {
			adj.get(v).remove(w);
			adj.get(w).remove(v);
			E--;
		}

		public List<Integer> leaves() {
			List<Integer> leavesList = new ArrayList<>();
			for (int i = 0; i < V; i++) {
				if (adj.get(i).size() == 1) {
					leavesList.add(i);
				}
			}
			return leavesList;
		}
		
		public List<Integer> removeLeaves(List<Integer> oldLeavesList) {
			List<Integer> newLeavesList = new ArrayList<>();
			for (int leaf : oldLeavesList) {
				// Find the adjacent vertex j of the current leaf i
				int neighbor = adj(leaf).iterator().next();
				// Remove the current leaf i from  vertex j's adjacency list
				remove(leaf, neighbor);
				// If current vertex j to become a leaf due to the remove action above,
				// we need to add it to the new leaves list.
				if (degree(neighbor) == 1) {
					newLeavesList.add(neighbor);
				}
			}
			return newLeavesList;
		}

		public int V() {
			return V;
		}

		public int E() {
			return E;
		}
	}
}
