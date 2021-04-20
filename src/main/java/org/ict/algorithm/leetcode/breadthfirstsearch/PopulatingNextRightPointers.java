package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC116
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
 */
public class PopulatingNextRightPointers {

    public Node connect(Node root) {
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
                if (i == size  - 1) {
                    cur.next = null;
                    prev = null;
                } else if (i == 0) {
                    prev = null;
                } else {
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

    class Node {
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
    }
}
