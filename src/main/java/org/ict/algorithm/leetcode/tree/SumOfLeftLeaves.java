package org.ict.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the sum of all left leaves in a given binary tree.
 *
 * LC404
 */
public class SumOfLeftLeaves {


    public int bfsSumOfLeftLeaves(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int res = 0;
        Queue<LeftNode> queue = new LinkedList<>();
        LeftNode node = new LeftNode(root, false);
        queue.offer(node);
        while(!queue.isEmpty()) {
            LeftNode curLeftNode = queue.poll();
            TreeNode cur = curLeftNode.treeNode;
            if (null == cur.left && null == cur.right && curLeftNode.isLeft) {
                res += cur.val;
            }
            if (null != cur.left) {
                LeftNode leftNode = new LeftNode(cur.left, true);
                queue.offer(leftNode);
            }
            if (null != cur.right) {
                LeftNode rightNode = new LeftNode(cur.right, false);
                queue.offer(rightNode);
            }
        }
        return res;
    }

    private static class LeftNode {
        private TreeNode treeNode;

        private boolean isLeft;

        public LeftNode(TreeNode treeNode, boolean isLeft) {
            this.treeNode = treeNode;
            this.isLeft = isLeft;
        }
    }

}
