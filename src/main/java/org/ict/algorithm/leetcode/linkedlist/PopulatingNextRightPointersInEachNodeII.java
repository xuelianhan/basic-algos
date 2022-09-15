package org.ict.algorithm.leetcode.linkedlist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree
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
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node, just like in Figure B.
 * The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
 *
 *
 * Follow-up:
 *
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 * @author sniper
 * @date 14 Sep, 2022
 * LC117
 */
public class PopulatingNextRightPointersInEachNodeII {

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
     * @param root
     * @return
     */
    public Node connectV3(Node root) {

        return root;
    }

    /**
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
    public Node connectV2(Node root) {
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

    public Node connect(Node root) {
        if (null == root) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i == size - 1) {
                    cur.next = null;
                    if (prev != null) {
                        prev.next = cur;
                    }
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
