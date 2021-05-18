package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal.
 * Each group of children is separated by the null value (See examples)
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000.
 *
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * @author hxl
 * @date 2021/5/18 4:29 PM
 * lc589
 */
public class NarrayTreePreorderTraversal {

    public static void main(String[] args) {
        Node five = new Node(5, null);
        Node six = new Node(6, null);
        List<Node> threeChildren = new ArrayList<>();
        threeChildren.add(five);
        threeChildren.add(six);
        Node three = new Node(3, threeChildren);

        Node two = new Node(2, null);
        Node four = new Node(4, null);
        List<Node> rootChildren = new ArrayList<>();
        rootChildren.add(three);
        rootChildren.add(two);
        rootChildren.add(four);
        Node root = new Node(1, rootChildren);
        List<Integer> result = preorder(root);
        System.out.println(result);
    }


    public static List<Integer> preorder(Node root) {
            List<Integer> list = new ArrayList<>();
            if (null == root) {
                return list;
            }
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node cur = stack.pop();
                if (cur != null) {
                    //middle
                    list.add(cur.val);
                    if (cur.children != null) {
                        for (int i = cur.children.size() - 1; i >= 0; i--) {
                            stack.push(cur.children.get(i));
                        }
                    }
                }
            }
            return list;
    }


    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> _children) {
            this.val = val;
            children = _children;
        }
    }
}
