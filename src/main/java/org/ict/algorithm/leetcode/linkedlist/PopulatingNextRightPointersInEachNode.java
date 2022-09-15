package org.ict.algorithm.leetcode.linkedlist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children.
 * The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node,
 * just like in Figure B.
 * The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 212 - 1].
 * -1000 <= Node.val <= 1000
 *
 *
 * Follow-up:
 *
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 * @author sniper
 * @date 14 Sep, 2022
 * LC116
 */
public class PopulatingNextRightPointersInEachNode {


    /**
     * BFS-level-by-level using for-loop
     *
     * Same solution as connectV3 but use for-loop instead of while-loop
     * Time Complexity : O(N), we only traverse each node once, basically doing a standard BFS.
     * Space Complexity : O(1), only constant extra space is being used
     * @param root
     * @return
     */
    public Node connectV4(Node root) {
        Node levelStart = root;
        /**
         * keep level start at the current level
         */
        for (; levelStart != null; levelStart = levelStart.left) {
            for (Node cur = levelStart; cur != null; cur = cur.next) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.next != null && cur.right != null) {
                    cur.right.next = cur.next.left;
                }
            }
        }
        return root;
    }


    /**
     * BFS-level-by-level using while-loop
     *
     * Solution explanation provided by StefanPochmann
     * Simply do it level by level,
     * using the next-pointers of the current level to go through the current level and set the next-pointers of the next level.
     * I say "real" O(1) space because of the many recursive solutions ignoring that recursion management needs space.
     *
     * O(1) Space
     * O(n) Time
     * @param root
     * @return
     */
    public Node connectV3(Node root) {
        /**
         * Level start is the first node of each layer.
         */
        Node levelStart = root;
        while (levelStart != null) {
            Node cur = levelStart;
            while (cur != null) {
                /**
                 * cur is at current level
                 * we use cur to operate the next level
                 *
                 * connect cur's left to cur's right
                 */
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                /**
                 * connect cur's right to the right neighbor of same level.
                 */
                if (cur.next != null && cur.right != null) {
                    cur.right.next = cur.next.left;
                }
                /**
                 * move cur to next node of same level
                 */
                cur = cur.next;
            }
            /**
             * move levelStart to the start of next level.
             */
            levelStart = levelStart.left;
        }
        return root;
    }

    /**
     * Solution explanation provided by Abhishek(archit91)
     * BFS - Right to Left
     * It's important to see that the given tree is a perfect binary tree.
     * Now, we need to populate next pointers of each node with nodes that occur to its immediate right on the same level.
     * This can easily be done with BFS.
     * Since for each node, we require the right node on the same level, we will perform a right-to-left BFS instead of the standard left-to-right BFS.
     *
     * Before starting the traversal of each level, we would initialize a rightNode variable set to NULL.
     * Then, since we are performing right-to-left BFS, we would be starting at rightmost node of each level.
     * We set the next node of cur as rightNode and update rightNode = cur. This would ensure that each node would be assigned its rightNode properly while traversing from right to left.
     * Also, if cur has a child, we would first push its right child and only then its left child (since we are doing right-to-left BFS).
     * Once BFS is completed (after queue becomes empty), all next node would be populated and we can finally return root.
     * This means that each node will always have both children and only the last level of nodes will have no children.
     *
     * Time Complexity : O(N), where N is the number of nodes in the given tree.
     *   We only traverse the tree once using BFS which requires O(N).
     * Space Complexity : O(W) = O(N), where W is the width of given tree.
     *   This is required to store the nodes in queue.
     * Since the given tree is a perfect binary tree, its width is given as W = (N+1)/2 ≈ O(N)
     *
     * @param root
     * @return
     */
    public Node connectV2(Node root) {
        if (null == root) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node right = null;
            for (int i = size - 1; i >=0 ; i--) {
                Node cur = queue.poll();
                cur.next = right;
                right = cur;
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
            }
        }
        return root;
    }


    /**
     * DFS solution( recursive solution)
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (null == root) {
            return null;
        }
        Node left = root.left;
        Node right = root.right;
        Node next = root.next;
        if (left != null) {
            left.next = right;
        }
        if (next != null && right != null) {
            right.next = next.left;
        }
        /**
         * Because we connect next from left to right,
         * So we invoke connect left firstly and connect right secondly.
         */
        connect(left);
        connect(right);
        return root;
    }


    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }
}
