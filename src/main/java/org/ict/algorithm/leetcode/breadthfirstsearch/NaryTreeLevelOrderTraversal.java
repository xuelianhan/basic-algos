package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 
 * Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example, given a 3-ary tree:
 *         1
 *        /|\
 *       3 2 4
 *      / \
 *     5   6
 * We should return its level order traversal:
 *
 * [
     [1],
     [3,2,4],
     [5,6]
]
 

Note:
The depth of the tree is at most 1000.
The total number of nodes is at most 5000.
 *
 * LC429
 */
public class NaryTreeLevelOrderTraversal {
	
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
		List<List<Integer>> result = levelOrder(root);
		System.out.println(result);
	}
	
	public static List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			/* queue size indicates number of nodes at each level */
			int size = queue.size();
			List<Integer> temp = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				if (cur == null) {
					continue;
				}
				temp.add(cur.val);
				if (cur.children != null) {
					cur.children.stream().forEach(e ->{
						queue.add(e);
					});
				}
			}
			result.add(temp);
		}
		return result;    
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
