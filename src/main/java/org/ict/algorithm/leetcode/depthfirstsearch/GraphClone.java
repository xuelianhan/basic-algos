package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphClone {
	
	public Node cloneGraph(Node node) {
		HashMap<Integer, Node> map = new HashMap<>();
		return dfs(node, map);
	}
	
	
	private Node dfs(Node node, HashMap<Integer, Node> map) {
		if (node == null) {
			return null;
		}
		if (map.containsKey(node.val)) {
			return map.get(node.val);
		} 
		Node newNode = new Node(node.val, new ArrayList<Node>());
		map.put(newNode.val, newNode);
		for (Node neighbor : newNode.neighbors) {
			newNode.neighbors.add(dfs(neighbor, map));
		}
		return newNode;
	}
	 
	 
}