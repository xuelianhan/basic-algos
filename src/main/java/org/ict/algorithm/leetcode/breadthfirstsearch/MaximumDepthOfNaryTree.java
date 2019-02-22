package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 Given a n-ary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
For example, given a 3-ary tree:
 *         1
 *        /|\
 *       3 2 4
 *      / \
 *     5   6
We should return its max depth, which is 3.
Note:
The depth of the tree is at most 1000.
The total number of nodes is at most 5000.
 *
 */
public class MaximumDepthOfNaryTree {
	
	public static void main(String[] args) {
		Node leaf5 = new Node(5, null);
		Node leaf6 = new Node(6, null);
	    List<Node> child3List = new LinkedList<>();
	    child3List.add(leaf5);
	    child3List.add(leaf6);
		Node child3 = new Node(3, child3List);
		Node leaf2 = new Node(2, null);
		Node leaf4 = new Node(4, null);
		List<Node> children = new LinkedList<>();
		children.add(child3);
		children.add(leaf2);
		children.add(leaf4);
		Node root = new Node(1, children);
		int result = maxDepth(root);
		System.out.println(result);
	}
	
	public static int maxDepth(Node root) {
		if (root == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		int maxDepth = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				if (cur == null) {
					continue;
				}
				if (cur.children != null) {
					cur.children.stream().forEach(e ->{
						queue.add(e);
					});
				}
			}
			maxDepth++;
		}
        return maxDepth;
    }

	public static class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val,List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	}
}
