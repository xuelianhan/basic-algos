package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.List;

public class GraphClone {
	
	public Node cloneGraph(Node node) {
        return null;
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


