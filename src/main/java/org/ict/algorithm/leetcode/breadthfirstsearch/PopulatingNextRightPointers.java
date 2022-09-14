package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following definition:
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 *
 * Follow up:
 * You may only use constant extra space.
 * Recursive approach is fine,
 * you may assume implicit stack space does not count as extra space for this problem.
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node,
 * just like in Figure B. The serialized output is in level order as connected by the next pointers,
 * with '#' signifying the end of each level.
 *
 * Constraints:
 * The number of nodes in the given tree is less than 4096.
 * -1000 <= node.val <= 1000
 * @author sniper
 * LC116
 */
public class PopulatingNextRightPointers {

    public static void main(String[] args) {
        Node four = new Node(4, null, null, null);
        Node five = new Node(5, null, null, null);
        Node six  = new Node(6, null, null, null);
        Node seven = new Node(7, null, null, null);
        Node two = new Node(2, four, five, null);
        Node three = new Node(3, six, seven, null);
        Node root = new Node(1, two, three, null);
        connect(root);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                System.out.println(cur);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
    }

    public static Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root == null) {
            return null;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i == size - 1) {
                    //The last node of layer
                    cur.next = null;
                    if (prev != null) {
                        prev.next = cur;
                    }
                    prev = null;
                } else {
                    //not the last node of layer
                    if (prev != null) {
                        prev.next = cur;
                    }
                    prev = cur;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}
        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        @Override
        public String toString() {
            return "val:" + val + ",next:" + (next == null ? null : next.val);
        }
    }
}
