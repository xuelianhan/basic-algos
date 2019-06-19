package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * Input:
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},
 *                                                 {"$id":"3","neighbors":[
 *                                                                         {"$ref":"2"},
 *                                                                         {"$id":"4","neighbors":[
 *                                                                                                 {"$ref":"3"},
 *                                                                                                 {"$ref":"1"}
 *                                                                                                ],
 *                                                                          "val":4}
 *                                                                        ],
 *                                                   "val":3}
 *                                                ],
 *                          "val":2},
 *                          {"$ref":"4"}
 *                        ],
 *  "val":1}
 * 1-------2
 * |       |
 * |       |
 * 4-------3
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
 * @see https://www.journaldev.com/378/java-util-concurrentmodificationexception
 */
public class GraphClone {
	
	public static void main(String[] args) {
		GraphClone graph = new GraphClone(); 
        Node source = graph.buildGraph(); 
        System.out.println("BFS traversal of a graph before cloning"); 
        //graph.bfsCheck(source); 
        System.out.println(source);
        Node newSource = graph.cloneGraph(source); 
        //Node newSource = graph.deepClone(source); 
        System.out.println("BFS traversal of a graph after cloning"); 
        System.out.println(newSource);
        //graph.bfsCheck(newSource); 
	}
	
	public Node deepClone(Node node) {
		if (node == null) {
			return null;
		}
		Stack<Node> stack = new Stack<>();
		stack.add(node);
		Map<Node, Node> visited = new HashMap<>();
		//Copy src
		List<Node> newNeighbors = new ArrayList<>();
		//newNeighbors.addAll(src.neighbors);
		Node newHead = new Node(node.val, newNeighbors);
		visited.put(node, newHead);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			Node curCloned = visited.get(cur);
			Iterator<Node> iter = cur.neighbors.iterator();
			while (iter.hasNext()) {
				Node neighbor = iter.next();
				Node copy = visited.get(neighbor);
				// neighbor not visited
				if (copy == null) {
					List<Node> temp = new ArrayList<>();
					//temp.addAll(neighbor.neighbors);
					copy = new Node(neighbor.val, temp);
					visited.put(neighbor, copy);
					stack.add(neighbor);
				} else {
					
				}
				curCloned.neighbors.add(copy);
			}
		}
		return newHead;
	}
	
	public Node buildGraph() {
		 /* 
        Note : All the edges are Undirected 
        Given Graph: 
        1--2 
        |  | 
        4--3 */
		Node node1 = new Node(1, null); 
        Node node2 = new Node(2, null); 
        Node node3 = new Node(3, null); 
        Node node4 = new Node(4, null); 
        List<Node> v = new ArrayList<Node>(); 
        v.add(node2); 
        v.add(node4); 
        node1.neighbors = v; 
        v = new ArrayList<Node>(); 
        v.add(node1); 
        v.add(node3); 
        node2.neighbors = v; 
        v = new ArrayList<Node>(); 
        v.add(node2); 
        v.add(node4); 
        node3.neighbors = v; 
        v = new ArrayList<Node>(); 
        v.add(node3); 
        v.add(node1); 
        node4.neighbors = v; 
        return node1; 
	}
	
	 // BFS traversal of a graph to 
    // check if the cloned graph is correct 
    public void bfsCheck(Node source) { 
        Queue<Node> q = new LinkedList<Node>(); 
        q.add(source); 
        HashMap<Node,Boolean> visit = new HashMap<Node,Boolean>(); 
        visit.put(source,true); 
        while (!q.isEmpty()) { 
            Node u = q.poll(); 
            System.out.println("Value of Node " + u.val ); 
            System.out.println("Address of Node " + u); 
            if (u.neighbors != null) { 
                List<Node> v = u.neighbors; 
                for (Node g : v) { 
                    if (visit.get(g) == null) { 
                        q.add(g); 
                        visit.put(g,true); 
                    } 
                } 
            } 
        } 
        System.out.println(); 
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
		//Copy src
		List<Node> newNeighbors = new ArrayList<>();
		//newNeighbors.addAll(src.neighbors);
		Node newHead = new Node(src.val, newNeighbors);
		visited.put(src, newHead);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				Node curCloned = visited.get(cur);
				Iterator<Node> iter = cur.neighbors.iterator();
				while (iter.hasNext()) {
					Node neighbor = iter.next();
					Node copy = visited.get(neighbor);
					// neighbor not visited
					if (copy == null) {
						List<Node> temp = new ArrayList<>();
						//temp.addAll(neighbor.neighbors);
						copy = new Node(neighbor.val, temp);
						visited.put(neighbor, copy);
						queue.add(neighbor);
					}
					curCloned.neighbors.add(copy);
				}
			}
		}
		return newHead;
	}

	class Node {
	    public int val;
	    public List<Node> neighbors;

	    public Node() {}

	    public Node(int _val,List<Node> _neighbors) {
	        val = _val;
	        neighbors = _neighbors;
	    }
	    
	    public String toString() {
	    	return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	    }
	}
}


