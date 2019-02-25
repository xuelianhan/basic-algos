package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

/**
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * Input:
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},
 * {"$id":"3","neighbors":[{"$ref":"2"},
 * {"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 * Explanation:
 * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 * Note:
 * The number of nodes will be between 1 and 100.
 * The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 * Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 * You must return the copy of the given node as a reference to the cloned graph.
 * 
 * @see https://www.geeksforgeeks.org/clone-an-undirected-graph/
 */
public class GraphClone {
	
	public static void main(String[] args) {
		
	}
	
	public Node cloneGraph(Node node) {
		if (node == null) {
			return null;
		}
		return bfs(node);
    }
	
	private Node bfs(Node src) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(src);
		Map<Node, Node> visited = new HashMap<>();
		visited.put(src, new Node(src.val, src.neighbors));
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			Node clonedCur = visited.get(cur);
			if (cur.neighbors == null) {
				continue;
			}
			List<Node> neighbors = cur.neighbors;
			for (Node neighbor : neighbors) {
				Node copy = visited.get(neighbor);
				// neighbor not visited
				if (copy == null) {
					queue.add(neighbor);
					copy = new Node(neighbor.val, neighbor.neighbors);
					visited.put(neighbor, copy);
				}
				clonedCur.neighbors.add(copy);
			}
		}
		return visited.get(src);
	}

	class Node {
	    public int val;
	    public List<Node> neighbors;

	    public Node() {}

	    public Node(int _val,List<Node> _neighbors) {
	        val = _val;
	        neighbors = _neighbors;
	    }
	}
}


